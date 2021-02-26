package cn.jwis.qualityworkflow.modules.qims.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/8/3 18:31
 */
@Mapper
public interface QimsSendMailMapper {
    @Select("select * from task_record where task_name in('初步原因分析','根本原因分析','根本原因审核') and task_state is  null and  template_key='QIMSBLACK'")
	List<JSONObject> getBlackList();

	@Select("select * from task_record where task_name in('风险评估','初步原因分析','根本原因分析','根本原因审核') and task_state is  null and  template_key='QIMSCQA'")
	List<JSONObject> getCqaList();

	@Select("select * from qims_lt_standard_info")
	List<JSONObject> getLtStandard();

	@Select("select time from email_history_info where id =#{id}")
	Integer getTimeById(@Param("id") String id);
	@Update("replace into email_history_info(id,time) values(#{id},#{time})")
	void updateById(@Param("id") String id,@Param("time") Integer time);
}