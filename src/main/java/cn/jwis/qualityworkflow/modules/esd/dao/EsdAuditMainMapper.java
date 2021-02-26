package cn.jwis.qualityworkflow.modules.esd.dao;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdAudiMainSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditListMain;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditListSecondary;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditMain;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdAuditSecondary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/1 16:32
 */
@Mapper
public interface EsdAuditMainMapper {

	@Select({ "<script>",
			"SELECT distinct ${parameter} from esd_audit_main where ${parameter} is not null",
			"</script>" })
	List<String> getDropdownValue(@Param("parameter") String parameter);

	Long getEsdAudiMainCount(EsdAudiMainSearch esdAudiMainSearch);

	List<EsdAuditMain> getEsdAudiMainList(EsdAudiMainSearch esdAudiMainSearch);

	void  saveEsdAuditMain(EsdAuditMain  esdAuditMain);

	void  saveEsdAuditSecondary(EsdAuditSecondary esdAuditSecondary);

	@Delete("DELETE  FROM esd_audit_secondary WHERE  main_id =#{mainId}")
	void  removeEsdAuditSecondary(@Param("mainId")String mainId);

	@Delete("DELETE  FROM esd_audit_main WHERE  id =#{id}")
	void  removeEsdAudit(@Param("id")String id);

	@Delete("DELETE  FROM esd_audit_list_secondary WHERE  main_id =#{mainId}")
	void  removeEsdAuditListSecondary(@Param("mainId")String mainId);

	@Delete("DELETE  FROM esd_audit_list_main WHERE  id =#{id}")
	void  removeEsdAuditList(@Param("id")String id);

	void  updateEsdAuditMain(EsdAuditMain esdAuditMain);

	@Select({ "<script>",
			"SELECT * from esd_audit_main where id =#{id}",
			"</script>" })
	EsdAuditMain getEsdAuditMainById(@Param("id") String id);

	@Select({ "<script>",
			"SELECT * from esd_audit_secondary where main_id =#{mainId}",
			"</script>" })
	List<EsdAuditSecondary> getEsdAuditSecondaryById(@Param("mainId") String mainId);

	@Select({ "<script>",
			"SELECT distinct ${parameter} from esd_audit_list_main where ${parameter} is not null",
			"</script>" })
	List<String> getDownValueList(@Param("parameter") String parameter);

	Long getEsdAuditCount(EsdAuditSearch esdAuditSearch);

	List<EsdAuditListMain> getEsdAuditList(EsdAuditSearch esdAuditSearch);
	@Select({ "<script>",
			"SELECT distinct document_name from esd_audit_main where document_name is not null AND audit_type =#{parameter} ",
			"</script>" })
	List<String> getDocumentNameList(@Param("parameter") String parameter);

	@Select({ "<script>",
			"SELECT distinct document_number from esd_audit_main where document_number is not null and document_name=#{documentName} ",
			"</script>" })
	List<String> getDocumentNumberListByName(@Param("documentName") String documentName);


	@Select({ "<script>",
			"SELECT s.audit_object,s.specific_requirement,s.problem_classification,s.grade_problem,s.hsfnc",
			"FROM esd_audit_secondary s LEFT JOIN esd_audit_main m ON s.main_id = m.id ",
			"WHERE m.document_number = #{documentNumber}",
			"</script>" })
	List<EsdAuditSecondary> getDetailsByNumber(@Param("documentNumber")String documentNumber);

	void  saveEsdAuditList(EsdAuditListMain esdAuditListMain);

	@Select({ "<script>",
			"SELECT * from esd_audit_list_main where id =#{id}",
			"</script>" })
	EsdAuditListMain getEsdAuditListById(@Param("id") String id);

	@Select({ "<script>",
			"SELECT * from esd_audit_list_secondary where main_id =#{id}",
			"</script>" })
	List<EsdAuditListSecondary> getEsdAuditListSecondaryById(@Param("id") String id);

	void  updateEsdAuditListSecondary(EsdAuditListSecondary esdAuditListSecondary);

    @Update(" update esd_audit_list_secondary set bills_flag='1'  where  id =#{id}")
	void  updateEsdAuditListSecondaryById(@Param("id") String id);

	void  updateEsdAuditListMain(EsdAuditListMain esdAuditListMain);

	@Select({ "<script>",
			"SELECT * from esd_audit_list_secondary where id =#{id}",
			"</script>" })
	EsdAuditListSecondary getEsdAuditListSecondary(@Param("id") String id);


}