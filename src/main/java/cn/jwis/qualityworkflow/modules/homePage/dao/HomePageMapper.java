package cn.jwis.qualityworkflow.modules.homePage.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/6 15:50
 */
@Mapper
public interface HomePageMapper {
	@Select("select create_date,creator,status,assigneer from qims_black_info where status != '结案'")
	List<JSONObject> getBlackInfo();

	@Select("select question_entry_time create_date,reporter creator,record_status status,assigneer from gray_problem_info where record_status != '结案'")
	List<JSONObject> getGrayInfo();

	@Select("select create_date,anomaly_reporter creator,status,assigneer from qims_cqa_info where status != '结案'")
	List<JSONObject> getCqaInfo();

	@Select("select create_date,creator,status,assigneer from esd_cycle_info where status != '结案'")
	List<JSONObject> getEsdCycle();

	@Select("select special_date create_date,special_applicant creator,status,assigneer from esd_special_info where status != '结案'")
	List<JSONObject> getEsdSpecial();

	@Select("select create_date,creator,status,assigneer from esd_audit_abnormal where status != '结案'")
	List<JSONObject> getEsdAudit();

	@Select("select create_date,creator,status,assignee assigneer from ecn_info where status != 'Close'")
	List<JSONObject> getEcnInfo();

	@Select("select create_date,creator,status,assignee assigneer from external_document where status != 'Close'")
	List<JSONObject> getExternalDocument();

	@Select("select create_date,creator,status,assignee assigneer from pcn_info where status != 'Close'")
	List<JSONObject> getPcnProcess();

	@Select("select create_date,creator,status,assigneer from line_qualify_info where status != 'Close'")
	List<JSONObject> getLineQuaProcess();

	@Select("select create_date,creator,status, assignee assigneer from rework_info where status != 'Close'")
	List<JSONObject> getReworkProcess();

	@Select("select create_date,creator,status, assignee assigneer from apqp_subdocument where status != 'Close'")
	List<JSONObject> getApqpProcess();

	@Select("select * from kpi_report_info where model_category =#{modelCategory} and date = (select max(date) from kpi_report_info ) order by id")
	List<JSONObject> getKpiReport(@Param("modelCategory") String modelCategory);
}