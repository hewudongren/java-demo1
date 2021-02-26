package cn.jwis.qualityworkflow.modules.factoryaudit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditEventInfo;
import cn.jwis.qualityworkflow.modules.factoryaudit.bean.AuditEventSearch;

@Mapper
public interface AuditEventMapper {

	List<AuditEventInfo> getAuditEventList(AuditEventSearch auditEventSearch);

	void saveAuditEvents(AuditEventInfo auditEventInfo);

	Long getAuditEventCount(AuditEventSearch auditEventSearch);

	@Select("select * from audit_event_info q  where q.id =#{id}")
	AuditEventInfo getAuditEventById(@Param("id") String id);

	void updateById(AuditEventInfo auditEventInfo);
	
	@Select("select * from audit_event_info q  where q.audit_topics =#{topics} AND q.type='Submit' and q.audit_end_date>DATE_SUB(CURDATE(), INTERVAL 3 MONTH) and q.audit_end_date<=CURDATE()")
	List<AuditEventInfo> getAuditEventByTopics(@Param("topics") String topics);

	
	@Select("select DISTINCT audit_topics auditTopics from audit_event_info q  where q.audit_end_date>DATE_SUB(CURDATE(), INTERVAL 3 MONTH) AND q.type='Submit' and q.audit_end_date<=CURDATE() ")
	List<String> getAuditEventTopics();

}