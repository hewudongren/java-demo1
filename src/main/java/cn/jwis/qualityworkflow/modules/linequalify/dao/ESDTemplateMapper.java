package cn.jwis.qualityworkflow.modules.linequalify.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.jwis.qualityworkflow.modules.linequalify.bean.ESDTemplateInfo;

@Mapper
public interface ESDTemplateMapper {

    @Delete("DELETE FROM line_attestation_template")
	void deleteEsdTemplate();

	@Insert("INSERT INTO line_attestation_template(id, item, module,audit_category, specific_requirements) "
			+ " VALUES(#{id}, #{item}, #{module}, #{auditCategory}, #{specificRequirements})")
	void saveEsdTemplate(ESDTemplateInfo esdTemplateInfo);

	@Select("SELECT * FROM line_attestation_template")
	List<ESDTemplateInfo> getEsdTemplateList();

}