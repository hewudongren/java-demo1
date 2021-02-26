package cn.jwis.qualityworkflow.modules.ecn.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.CN_LANGUAGE;
import static cn.jwis.qualityworkflow.util.Title.EcnInfoDbCamel;
import static cn.jwis.qualityworkflow.util.Title.EcnInfoExcel;
import static cn.jwis.qualityworkflow.util.Title.EcnInfoTable;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.ecn.bean.EcnInfo;
import cn.jwis.qualityworkflow.modules.ecn.bean.QueryEcnInfoVo;
import cn.jwis.qualityworkflow.modules.ecn.dao.EcnInfoMapper;
import cn.jwis.qualityworkflow.modules.ecn.service.ECNService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.ImportAndExportUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ReflectUtil;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.UserUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;

@Service
public class ECNServiceImpl implements ECNService {

	@Value("${qms.app.key}")
	private String tenantId;

	@Autowired
	EcnInfoMapper ecnInfoDao;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	WorkflowServer workflowServer;


	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;

	@Autowired
	ReflectUtil reflectUtil;

	@Autowired
	ImportAndExportUtil importAndExportUtil;

	@Autowired
	UserServer userServer;

	public static final String ECN_TABLENAME_NAME = "ecn_info";

	public static final Logger logger = LoggerFactory.getLogger(ECNServiceImpl.class);

	@Override
	public List<Object> getPullDownValue(String paramter) throws Exception {
		String underlineParameter = JSONObjectUtil.camelToUnderline(paramter);
		List<Object> pullDownValue = ecnInfoDao.getPullDownValue(underlineParameter);
		return pullDownValue;
	}


	@Override
	public PageInfo<JSONObject> getEcnInfoList(QueryEcnInfoVo bean) throws Exception{
		// 给检验时间+1
		reflectUtil.preHandledPageBean(bean);
		String userItemInfos = userServer.getUserItemInfos("ECN-业务管理员");
		if (userItemInfos == null) {
			bean.setAdmin(1);
		}
		bean.setAssignee(UserUtil.getCurrentUserName());
		Page<EcnInfo> list = ecnInfoDao.getEcnInfo(bean);
		List<JSONObject> result = JSONObjectUtil.toJSONObjectList(list);
		importAndExportUtil.transferData(result, ECN_TABLENAME_NAME, CN_LANGUAGE, false);
		PageInfo<JSONObject> page = new PageInfo<>(result);
		page.setTotal(list.getTotal());
		return page;
	}

	@Override
	public Map<String, Object> getEcnDetailedInfo(QueryDetailedInfoVo bean) throws Exception{
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		EcnInfo ecnInfo = ecnInfoDao.getEcnInfoByTaskId(bean.getTaskId());
		detailedInfoMap.put("detailedInfo",ecnInfo);
		return detailedInfoMap;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirm(ConfirmVo bean) throws Exception{
		EcnInfo info = reflectUtil.variablesToObject(bean, EcnInfo.class);

		// 如果处在拟制单据阶段 需要把 质量负责人 赋值给pqe处理人
		if ("Apply".equals(info.getStatus())) {
			info.setIpqcHandler(info.getQualityHead());
		}
		// 如果id为null,说明 记录还不存在，需要初始化记录保存接口， 如果id存在则，只需调用更新接口
		if (StringUtils.isEmpty(bean.getId())) {
			commitEcnInfo(info);
		} else {
			// 获取前台传来的TaskId
			String taskId = bean.getTaskId();
			String processInstanceId = bean.getProcessInstanceId();
			// 获取最新的Task,
			// 调用接口，完成任务
			try {
				workflowServer.finishTask(processInstanceId, taskId, info.getVariables(), info.getLocalVariables());
			} catch (Exception e) {
				logger.error("Ecn workflow taskId-" + taskId + " finish task error");
			}
			reflectUtil.updateProcessInfo(info);
			reflectUtil.sendWorkflowEmail(EcnInfo.class, bean.getId(), bean.getStatus());
		}
	}

	@Override
	public void exportEcnInfo(HttpServletResponse response, HttpServletRequest request, QueryEcnInfoVo vo) throws Exception {
		// 调用查询接口 查出List<EcnInfo>
		vo.setPageSize(Integer.MAX_VALUE - 1);
		PageInfo<JSONObject> page = getEcnInfoList(vo);
		List<JSONObject> list = page.getList();
		String[] title = EcnInfoExcel;
		String language = request.getHeader("Language");
		if (("en-US").equals(language)) {
			title = EcnInfoDbCamel;
		}
		Workbook workbook = ExcelUtil.exporSimple(list, title, EcnInfoDbCamel);
		ExcelUtil.outputWorkbook(response, workbook, EcnInfoTable);
	}


	/**
	 * 提交ECN流程 (产生记录，拟制单据阶段完成，进入Plan阶段)
	 * synchronized 是为了 并发请求， 项目编号重复
	 * @param bean
	 */
	private synchronized void  commitEcnInfo(EcnInfo bean)  throws Exception{
		// 自带项目编号 和 id 和 创建人
		bean.setItemNumber(ecnInfoDao.getItemNumber());
		bean.setId(String.valueOf(idGeneratorRunner.nextId()));
		bean.setCreator(UserUtil.getCurrentUserName());
		reflectUtil.commitRecord(bean);
		// 用线程池执行会快一点
		reflectUtil.sendWorkflowEmail(EcnInfo.class, bean.getId(), null);
	}


}
