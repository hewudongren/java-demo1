package cn.jwis.qualityworkflow.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/5/9 16:24
 */
@Mapper
public interface CommonMapper {

	@Insert({"<script>", "INSERT INTO ${tableName}" ,
			"<foreach collection='list' index='' item='item' separator=',' open='(' close=')' >",
			"<if test = 'item.value != null '>",
			"${item.key} </if> </foreach>",
			" VALUES  <foreach collection='list' index='' item='item' separator=',' open='(' close=')' >",
			"<if test = 'item.value != null '>",
			"#{item.value} </if> </foreach>",
			"</script>"})
	void  saveDate (@Param("list") List<JSONObject> list, @Param("tableName") String tableName);

	@Update({"<script>",
			" UPDATE ${tableName} SET " ,
			"<foreach collection='list' index='index' item='item' separator=',' open='' close='' >",
			" ${item.key} = #{item.value}",
			"</foreach>",
			" where id = #{id}",
			"</script>"
	})
	void update(@Param("list") List<JSONObject> list, @Param("tableName") String tableName, @Param("id") String id);

	/**
	 * 批量导入使用
	 * @param list  需要校验的属性列表
	 * @param tableName  表名
	 * @return
	 */
	@Select({"<script>",
			" SELECT  CONCAT_WS(\"-\"," ,
			"<foreach collection='list' index='index' item='item' separator=',' open='' close=')' >",
			" ${item}",
			"</foreach>",
			"from ${tableName}",
			"</script>"
	})
	List<String> getValidationParamKey(@Param("list") List<String> list,@Param("tableName") String tableName);


	@Insert({"<script>", "INSERT INTO ${tableName}" ,
			"<foreach collection='keys' index='' item='item' separator=',' open='(' close=')' >",
			"<if test = 'item != null '>",
			"${item} " ,
			"</if> " ,
			"</foreach>",
			" VALUES  " ,
			"<foreach collection='list' index='index' item='item' separator='' open='' close='' >",
			"<foreach collection='item' index='' item='item' separator=',' open='(' close=')' >",
			"#{item} " ,
			" </foreach>",
			"<if test = 'index &lt; list.size - 1 '>",
			",",
			"</if>",
			"<if test = 'index == list.size - 1 '>",
			";",
			"</if>",
			"</foreach>",
			"</script>"})
	void batchInsert(@Param("keys") List<String> keys, @Param("list") List<List<String>> list, @Param("tableName") String tableName);

	/**
	 * 获取需要导入 导出 需要进行转换的字段
	 * @param tableName
	 * @return
	 */
	@Select({"<script>",
			"select * from import_export_parameter_mapping b where b.table_name = #{tableName} " ,
			"and update_time = (SELECT max( update_time ) FROM import_export_parameter_mapping a" ,
			"where a.table_name = b.table_name AND a.parameter = b.parameter AND a.db_data= b.db_data) ",
			"</script>"})
	List<JSONObject> getTransferParameter(@Param("tableName") String tableName);

	@Select("SELECT DISTINCT department FROM department_mapping  WHERE user_account =#{user}")
	String getDepartment(@Param("user") String user);

	@Select("SELECT * FROM key_to_name")
	List<JSONObject> getKeyToName();

	@Select("SELECT name FROM node_to_name where node =#{node}")
	String getNameByNode(@Param("node")String node);

	//获取ESD的邮件群组
	@Select("SELECT main_delivery FROM esd_email_info where type =#{type} and  is_effective = '是'")
	String getEsdEmailList(@Param("type") String type);

	//获取ESD的邮件抄送群组
	@Select("SELECT cc_person FROM esd_email_info where type =#{type}")
	String getEsdEmailCcList(@Param("type")String type);

}