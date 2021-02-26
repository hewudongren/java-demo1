package cn.jwis.qualityworkflow.modules.ecn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;

import cn.jwis.qualityworkflow.modules.ecn.bean.ExternalDocument;
import cn.jwis.qualityworkflow.modules.ecn.bean.ExternalDocumentExample;
import cn.jwis.qualityworkflow.modules.ecn.bean.QueryExternalDocumentVo;

@Mapper
@Component
public interface ExternalDocumentMapper {
	int countByExample(ExternalDocumentExample example);

	int deleteByExample(ExternalDocumentExample example);

	int deleteByPrimaryKey(String id);

	int insert(ExternalDocument record);

	int insertSelective(ExternalDocument record);

	List<ExternalDocument> selectByExample(ExternalDocumentExample example);

	ExternalDocument selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") ExternalDocument record,
			@Param("example") ExternalDocumentExample example);

	int updateByExample(@Param("record") ExternalDocument record, @Param("example") ExternalDocumentExample example);

	int updateByPrimaryKeySelective(ExternalDocument record);

	int updateByPrimaryKey(ExternalDocument record);

	/**
	 * @description:
	 * @author: xujinbiao
	 * @date: 2020/4/30 11:01
	 * @param parameter:
	 * @return: java.util.List<java.lang.String>
	 **/
	@Select({ "<script>", "SELECT distinct ${parameter} from external_document where ${parameter} is not null",
			"</script>" })
	List<Object> getPullDownValue(@Param("parameter") String parameter);

	/**
	 * @description: //获取List
	 * @author: xujinbiao
	 * @date: 2020/5/7 14:28
	 * @param bean:
	 * @return: java.util.List<cn.jwis.qualityworkflow.modules.ecn.bean.ExternalDocument>
	 **/
	Page<ExternalDocument> getInfo(QueryExternalDocumentVo bean);

	/**
	 *
	 * @Description: 通过流程TaskId获取信息
	 * @author longjun
	 * @date 2019年4月19日
	 * @param taskId
	 * @return
	 */
	@Select({ "<script>",
			"SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState FROM  external_document q,(SELECT  workflow_business_id, process_instance_id as processInstanceId,task_id as taskId,assignee,task_state as taskState FROM task_record WHERE task_id=#{taskId}) ar where q.id=ar.workflow_business_id",
			"</script>" })
	ExternalDocument getInfoByTaskId(String taskId);

	/**
	 * @description: 更新LT 时间
	 * @author: xujinbiao
	 * @date: 2020/5/14 16:20
	 * @param id:
	 * @param LT:
	 * @return: int
	 **/
	@Update("update external_document set LT = #{lt} where id = #{id}")
	int updateLt(@Param("id") String id, @Param("lt") String lt);

	/**
	 * @description: 获取项目编号
	 * @author: xujinbiao
	 * @date: 2020/5/18 10:32
	 * @return: java.lang.String
	 **/
	@Select("SELECT external_document_number_generator()")
	String getItemNumber();

	/**
	 * 根据 ids 获取 List<ExternalDocument>
	 * 
	 * @param ids
	 * @return
	 */
	List<ExternalDocument> selectByPrimaryKeys(@Param("ids") List<String> ids);

}