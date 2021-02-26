package cn.jwis.qualityworkflow.modules.ecn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;

import cn.jwis.qualityworkflow.modules.ecn.bean.EcnInfo;
import cn.jwis.qualityworkflow.modules.ecn.bean.EcnInfoExample;
import cn.jwis.qualityworkflow.modules.ecn.bean.QueryEcnInfoVo;

@Component
@Mapper
public interface EcnInfoMapper {
    int countByExample(EcnInfoExample example);

    int deleteByExample(EcnInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(EcnInfo record);

    int insertSelective(EcnInfo record);

    List<EcnInfo> selectByExample(EcnInfoExample example);

    EcnInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EcnInfo record, @Param("example") EcnInfoExample example);

    int updateByExample(@Param("record") EcnInfo record, @Param("example") EcnInfoExample example);

    int updateByPrimaryKeySelective(EcnInfo record);

    int updateByPrimaryKey(EcnInfo record);

    Page<EcnInfo> getEcnInfo(QueryEcnInfoVo bean);

    long getCountByExample(EcnInfoExample example);

    /**
     *
     * @Description: 通过流程TaskId获取ECN流程业务信息
     * @author longjun
     * @date 2019年4月19日
     * @param taskId
     * @return
     */
    @Select({ "<script>",
            "SELECT q.*,ar.processInstanceId ,ar.taskId,ar.assignee,ar.taskState " +
                    "FROM ecn_info q,(SELECT  workflow_business_id, process_instance_id as processInstanceId," +
                    "task_id as taskId,assignee,task_state as taskState FROM " +
                    "task_record WHERE task_id=#{taskId}) " +
                    "ar where q.id=ar.workflow_business_id",
            "</script>" })
    EcnInfo getEcnInfoByTaskId(String taskId);

    /**
     * @description:
     * @author: xujinbiao
     * @date: 2020/4/30 11:01
     * @param parameter:
     * @return: java.util.List<java.lang.String>
     **/
    @Select({ "<script>",
            "SELECT distinct ${parameter} from ecn_info where ${parameter} is not null",
            "</script>" })
    List<Object> getPullDownValue(@Param("parameter") String parameter);

    /**
     * @description: 获取项目编号
     * @author: xujinbiao
     * @date: 2020/5/18 10:32
     * @return: java.lang.String
     **/
    @Select("SELECT ecn_number_generator()")
    String getItemNumber();


    /**
     * 根据 ids 获取 List<Ecn>
     * @param ids
     * @return
     */
    List<EcnInfo> selectByPrimaryKeys(@Param("ids") List<String> ids);
}