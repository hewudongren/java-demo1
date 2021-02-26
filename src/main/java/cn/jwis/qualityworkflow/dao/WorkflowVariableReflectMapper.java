package cn.jwis.qualityworkflow.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-09 10:22
 * @since 0.1.0
 **/

@Mapper
@Component
public interface WorkflowVariableReflectMapper {

	@Select("SELECT variable from workflow_variable_reflect where template_key = #{templateKey} and  node = #{status}")
	List<String> getVariables(@Param("templateKey") String  templateKey, @Param("status") String status);

	@Select("SELECT field_value value,display_field_value 'key' from additional_field_info where process = #{process} and  state = #{state}")
	List<JSONObject> appendField(@Param("process") String  process, @Param("state") String state);
}
