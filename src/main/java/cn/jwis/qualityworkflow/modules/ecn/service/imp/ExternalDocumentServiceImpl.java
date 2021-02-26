package cn.jwis.qualityworkflow.modules.ecn.service.imp;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.ecn.bean.ExternalDocument;
import cn.jwis.qualityworkflow.modules.ecn.bean.QueryExternalDocumentVo;
import cn.jwis.qualityworkflow.modules.ecn.dao.ExternalDocumentMapper;
import cn.jwis.qualityworkflow.modules.ecn.service.ExternalDocumentService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.*;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static cn.jwis.qualityworkflow.common.Constants.CN_LANGUAGE;


/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-07 14:16
 * @since 0.1.0
 **/
@Service
public class ExternalDocumentServiceImpl implements ExternalDocumentService {


	@Autowired
	ExternalDocumentMapper documentDao;

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

	public static final String EXTERNAL_DOCUMENT_TABLE_NAME = "external_document";

	public static final Logger logger = LoggerFactory.getLogger(ExternalDocumentServiceImpl.class);


	@Override
	public PageInfo<JSONObject> getList(QueryExternalDocumentVo bean) throws Exception{
		// 给检验时间+1
		reflectUtil.preHandledPageBean(bean);
		String userItemInfos = userServer.getUserItemInfos("外来文件-业务管理员");
		bean.setAssignee(userItemInfos);
		Page<ExternalDocument> list = documentDao.getInfo(bean);
		List<JSONObject> result = JSONObjectUtil.toJSONObjectList(list);
		importAndExportUtil.transferData(result, EXTERNAL_DOCUMENT_TABLE_NAME, CN_LANGUAGE, false);
		PageInfo<JSONObject> page = new PageInfo<>(result);
		page.setTotal(list.getTotal());
		return page;
	}

	@Override
	public void exportInfo(HttpServletResponse response, HttpServletRequest request, QueryExternalDocumentVo vo) throws Exception {
		vo.setPageSize(Integer.MAX_VALUE - 1);
		PageInfo<JSONObject> page = getList(vo);
		List<JSONObject> list = page.getList();
		importAndExportUtil.export(response, request, list, EXTERNAL_DOCUMENT_TABLE_NAME);
	}

	@Override
	public List<Object> getPullDownValue(String parameter) throws Exception {
		String underlineParameter = JSONObjectUtil.camelToUnderline(parameter);
		List<Object> pullDownValue = documentDao.getPullDownValue(underlineParameter);
		return pullDownValue;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirm(ConfirmVo bean) throws Exception{
		// 如果id为null,说明 记录还不存在，需要初始化记录保存接口， 如果id存在则，只需调用更新接口
		//将前台传入的数据与实体类数据对应
		ExternalDocument document = reflectUtil.variablesToObject(bean, ExternalDocument.class);
		if (StringUtils.isEmpty(bean.getId())) {
			commitInfo(document);
		} else {
			// 获取前台传来的TaskId （如果不是第一个任务，则可以直接获取流程实例id（一直不变） 和当前任务id（上一个任务完成时已经保存））
			String taskId = bean.getTaskId();
			String processInstanceId = bean.getProcessInstanceId();
			JSONObject variables = bean.getVariables();

			// 调用接口，完成任务
			try {
				workflowServer.finishTask(processInstanceId, taskId, variables, null);
			} catch (Exception e) {
				logger.error("外来文件流程 taskId-" + taskId + " finish task error");
			}
			Map<String, Object> map = reflectUtil.updateProcessInfo(document);
			boolean isProcessInstanceComplete = (boolean) map.get("isProcessInstanceComplete");
			// 更新LT， 外来文件的 LT = 结案时间 减去 创建时间
			if (isProcessInstanceComplete) {
				updateLt(bean.getId());
			}
			reflectUtil.sendWorkflowEmail(ExternalDocument.class, bean.getId(), bean.getStatus());
		}
	}

	@Override
	public Map<String, Object> getDetailednessInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		ExternalDocument document = documentDao.getInfoByTaskId(bean.getTaskId());
		detailedInfoMap.put("detailedInfo",document);
		return detailedInfoMap;
	}

	/**
	 * 提交ECN流程 (产生记录，拟制单据阶段完成，进入Plan阶段)
	 * @param bean
	 */
	private synchronized void commitInfo(ExternalDocument bean)  throws Exception{
		// 自带项目编号 和 id 和 创建人
		//通过数据的函数获取自定义的编号
		bean.setItemNumber(documentDao.getItemNumber());
		bean.setId(String.valueOf(idGeneratorRunner.nextId()));
		bean.setCreator(UserUtil.getCurrentUserName());
		reflectUtil.commitRecord(bean);
		// 用线程池执行会快一点
		reflectUtil.sendWorkflowEmail(ExternalDocument.class, bean.getId(), null);
	}


	private void updateLt(String id) {
		if (StringUtils.isNotEmpty(id)) {
			ExternalDocument document = documentDao.selectByPrimaryKey(id);
			Date createDate = document.getCreateDate();
			Date updateDate = document.getUpdateDate();
			String time = DateUtil.getDate(createDate, updateDate);
			documentDao.updateLt(id, time);
		}
	}
}
