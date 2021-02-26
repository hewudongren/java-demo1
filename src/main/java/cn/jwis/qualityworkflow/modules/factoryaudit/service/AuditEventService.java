package cn.jwis.qualityworkflow.modules.factoryaudit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditEventInfo;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditEventSearch;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/26 17:56
 */
@Service
public interface AuditEventService {

	void saveAuditEvents(AuditEventInfo auditEventInfo);

	List<AuditEventInfo> getAuditEventList(AuditEventSearch auditEventSearch);

	void exportAuditEventList(HttpServletResponse response, HttpServletRequest request, AuditEventSearch auditEventSearch);

	Long getAuditEventCount(AuditEventSearch auditEventSearch);

	AuditEventInfo getAuditEventById(String id);

	List<AuditEventInfo> getAuditEventByTopics(String topics);

	List<AuditEventInfo> getAuditCalendarInfo(AuditEventSearch auditEventSearch);

	List<JSONObject> getYearAuditCalendarInfo(AuditEventSearch auditEventSearch);

	List<String> getAuditEventTopics();


}