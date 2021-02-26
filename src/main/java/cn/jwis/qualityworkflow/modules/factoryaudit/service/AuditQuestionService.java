package cn.jwis.qualityworkflow.modules.factoryaudit.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditQuestionInfo;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditQuestionSearch;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:56
 */
@Service
public interface AuditQuestionService {

	void saveAuditQuestionInfo(AuditQuestionInfo auditQuestionInfo) throws Exception;

	void confirm(JSONObject bean) throws Exception;

	List<String> getDropdownValue(String parameter);

	List<AuditQuestionInfo> getAuditQuestionInfoList(AuditQuestionSearch auditQuestionSearch);

	Long getAuditQuestionInfoCount(AuditQuestionSearch auditQuestionSearch);


	Map<String, Object> getAuditQuestionDetailednessInfo(QueryDetailedInfoVo bean) throws Exception;

	void exportAuditQuestionList(HttpServletResponse response, HttpServletRequest request,
			AuditQuestionSearch auditQuestionSearch);

	void exportTemplate(JSONObject jsonObject, HttpServletResponse response, HttpServletRequest request);

	Map<String, Object> getAuditQuestionTrends(AuditQuestionSearch auditQuestionSearch);

	JSONObject getKeywordsPlato(AuditQuestionSearch auditQuestionSearch);

	void sendOverTimeMail();

	
}