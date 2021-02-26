package cn.jwis.qualityworkflow.modules.apqp.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.APQP_MASTER_TEMPLATE_KEY;
import static cn.jwis.qualityworkflow.common.Constants.CN_LANGUAGE;
import static cn.jwis.qualityworkflow.common.Constants.HISOTORY_PROCESS_COMMIT;
import static cn.jwis.qualityworkflow.common.Constants.NA;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.HistoryProcessRecordExample;
import cn.jwis.qualityworkflow.dao.HistoryProcessRecordMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpMasterDocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateMasterDocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.CreateSubDocumentVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryMasterListVo;
import cn.jwis.qualityworkflow.modules.apqp.dao.ApqpMasterDocumentMapper;
import cn.jwis.qualityworkflow.modules.apqp.service.MasterDocumentService;
import cn.jwis.qualityworkflow.modules.apqp.service.SubDocumentService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.ImportAndExportUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ReflectUtil;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.UserUtil;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-25 17:46
 * @since 0.1.0
 **/
@Service
public class MasterDocumentServiceImpl implements MasterDocumentService {

	@Autowired
	ApqpMasterDocumentMapper masterDocumentDao;

	@Autowired
	ReflectUtil reflectUtil;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	SubDocumentService subDocumentService;

	@Autowired
	ImportAndExportUtil importAndExportUtil;

	@Autowired
	HistoryProcessRecordService historyProcessRecordService;

	@Autowired
	HistoryProcessRecordMapper historyProcessRecordDao;

	@Autowired
	UserServer userServer;

	//
	private static final int ZERO = 0;

	private static final String ONGONING = "OnGoing";

	private static final String APQP_MASTER_TABLE_NAME = "apqp_master_document";

	@Override
	public PageInfo<JSONObject> getInfoList(QueryMasterListVo bean) throws Exception {
		String name = userServer.getUserItemInfos("APQP-业务管理员");
		bean.setCreator(name);
		reflectUtil.preHandledPageBean(bean);
		Page<ApqpMasterDocument> list = masterDocumentDao.getList(bean);
		List<JSONObject> result = JSONObjectUtil.toJSONObjectList(list);
		importAndExportUtil.transferData(result, APQP_MASTER_TABLE_NAME, CN_LANGUAGE, false);
		PageInfo<JSONObject> page = new PageInfo<>(result);
		page.setTotal(list.getTotal());
		return page;
	}

	/**
	 * @description:
	 * @author: xujinbiao
	 * @date: 2020/5/26 15:57
	 * @param bean:
	 * @return: void
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(CreateMasterDocument bean) throws Exception {
		ApqpMasterDocument masterDocument = new ApqpMasterDocument(bean);
		String id = String.valueOf(idGeneratorRunner.nextId());
		masterDocument.setId(id);
		String currentUserName = UserUtil.getCurrentUserName();
		masterDocument.setCreator(currentUserName);
		masterDocument.setStatus(ONGONING);
		handleFile(bean, masterDocument);
		// 为了防止 高并发情况下， 生成多个 重复的APQP编号
		String apqpNumber = null;
		synchronized (MasterDocumentServiceImpl.class) {
			apqpNumber = masterDocumentDao.getNumber();
			masterDocument.setApqpNumber(apqpNumber);
			masterDocumentDao.insert(masterDocument);
		}
		// 创建子记录 和 子流程工作流
		subDocumentService.batchInsert(bean, apqpNumber, id);
		// 清空保存记录 之前的保存记录
		historyProcessRecordService.deleteApplyRecord(APQP_MASTER_TEMPLATE_KEY);
		// 记录主单据的插入行为
		CreateHistoryProcessVo vo = new CreateHistoryProcessVo();
		vo.setWorkflowBusinessid(id);
		vo.setContent(JSONObjectUtil.toJSONObject(bean));
		vo.setType(HISOTORY_PROCESS_COMMIT);
		vo.setTemplateKey(APQP_MASTER_TEMPLATE_KEY);
		historyProcessRecordService.save(vo);
	}

	@Override
	public JSONObject getMasterDocument(String id) throws Exception {
		JSONObject result = new JSONObject();
		// 获取
		HistoryProcessRecordExample recordExample = new HistoryProcessRecordExample();
		HistoryProcessRecordExample.Criteria recordCriteria = recordExample.createCriteria();
		recordCriteria.andWorkflowBusinessidEqualTo(id);
		List<HistoryProcessRecord> list = historyProcessRecordDao.selectByExample(recordExample);
		if (CollectionUtils.isNotEmpty(list)) {
			HistoryProcessRecord record = list.get(0);
			JSONObject object = JSONObjectUtil.toJSONObject(record.getContent());
			result.put("historyProcessRecord", object);
		}
		ApqpMasterDocument masterDocument = masterDocumentDao.getById(id);
		result.put("masterDocument", masterDocument);
		return result;
	}

	@Override
	public List<Object> getPullDownValue(String parameter) throws Exception {
		String underlineParameter = JSONObjectUtil.camelToUnderline(parameter);
		List<Object> pullDownValue = masterDocumentDao.getPullDownValue(underlineParameter);
		return pullDownValue;
	}

	@Override
	public void exportInfo(HttpServletResponse response, HttpServletRequest request, QueryMasterListVo vo)
			throws Exception {
		// 调用查询接口 查出List<EcnInfo>
		vo.setPageSize(Integer.MAX_VALUE - 1);
		PageInfo<JSONObject> page = getInfoList(vo);
		List<JSONObject> list = page.getList();
		importAndExportUtil.export(response, request, list, "apqp_master_document");
	}

	/**
	 * @description: 统计处理文件数量
	 * @author: xujinbiao
	 * @date: 2020/5/26 15:23
	 * @param bean:
	 * @param masterDocument:
	 * @return: void
	 **/
	private void handleFile(CreateMasterDocument bean, ApqpMasterDocument masterDocument) {
		List<CreateSubDocumentVo> list = bean.getList();
		int dvt1Num = 0;
		int pemeaNum = 0;
		int qcChartNum = 0;
		for (CreateSubDocumentVo subDocument : list) {
			// TEST工程段，只走NUDD/DFX文件上传流程， 所以总数只加dvt1Num
			if ("TEST".equals(subDocument.getEngPhase())) {
				int dvt1 = (handleFileName(subDocument.getDvt1Document())) ? 1 : 0;
				dvt1Num = dvt1Num + dvt1;
			} else {
				int dvt1 = (handleFileName(subDocument.getDvt1Document())) ? 1 : 0;
				int pemea = (handleFileName(subDocument.getPemeaDocument())) ? 1 : 0;
				int qcChart = (handleFileName(subDocument.getQcChartDocument())) ? 1 : 0;
				dvt1Num = dvt1Num + dvt1;
				pemeaNum = pemeaNum + pemea;
				qcChartNum = qcChartNum + qcChart;
			}
		}
		masterDocument.setDvt1Num(dvt1Num);
		masterDocument.setPemeaNum(pemeaNum);
		masterDocument.setQcCharNum(qcChartNum);
		masterDocument.setDvt1CompletedNum(ZERO);
		masterDocument.setPemeaCompletedNum(ZERO);
		masterDocument.setQcCharCompletedNum(ZERO);
	}

	/**
	 * @description: 文件不为 null , "" ,和 NA时返回 true
	 * @author: xujinbiao
	 * @date: 2020/5/26 15:26
	 * @param fileName:
	 * @return: boolean
	 **/
	private boolean handleFileName(String fileName) {
		if (StringUtils.isNotEmpty(fileName) && !(NA.equals(fileName))) {
			return true;
		}
		return false;
	}
}
