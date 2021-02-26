package cn.jwis.qualityworkflow.modules.apqp.dao;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpMasterDocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpMasterDocumentExample;
import java.util.List;

import cn.jwis.qualityworkflow.modules.apqp.bean.QueryMasterListVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ApqpMasterDocumentMapper {
    int countByExample(ApqpMasterDocumentExample example);

    int deleteByExample(ApqpMasterDocumentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApqpMasterDocument record);

    int insertSelective(ApqpMasterDocument record);

    List<ApqpMasterDocument> selectByExample(ApqpMasterDocumentExample example);

    ApqpMasterDocument selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApqpMasterDocument record, @Param("example") ApqpMasterDocumentExample example);

    int updateByExample(@Param("record") ApqpMasterDocument record, @Param("example") ApqpMasterDocumentExample example);

    int updateByPrimaryKeySelective(ApqpMasterDocument record);

    int updateByPrimaryKey(ApqpMasterDocument record);

    Page<ApqpMasterDocument> getList(QueryMasterListVo vo);

    @Select("select apqp_master_document_number_generator()")
    String getNumber();

   @Update("update apqp_master_document set dvt1_completed_num = dvt1_completed_num + 1 where id = #{id} and " +
           "dvt1_completed_num < dvt1_num")
   void addDvt1CompleteNum(@Param("id") String id);

    @Update("update apqp_master_document set pemea_completed_num = pemea_completed_num + 1 where id = #{id} and " +
            "pemea_completed_num < pemea_num")
    void addPemeaCompleteNum(@Param("id") String id);

    @Update("update apqp_master_document set qc_char_completed_num = qc_char_completed_num + 1 where id = #{id} " +
            "and qc_char_completed_num  < qc_char_num ")
    void addQcChartCompleteNum(@Param("id") String id);


    @Update("Update apqp_master_document "+
            "SET status = \"Close\" "+
            "WHERE "+
            "id = #{id} and qc_char_completed_num = qc_char_num "+
            "AND pemea_completed_num = pemea_num "+
            "AND dvt1_completed_num = dvt1_num")
    void updateStatus(@Param("id") String id);

    /**
     * @description:
     * @author: xujinbiao
     * @date: 2020/4/30 11:01
     * @param parameter:
     * @return: java.util.List<java.lang.String>
     **/
    @Select({ "<script>",
            "SELECT distinct ${parameter} from apqp_master_document where ${parameter} is not null",
            "</script>" })
    List<Object> getPullDownValue(@Param("parameter") String parameter);

    /**
     * @description:
     * @author: xujinbiao
     * @date: 2020/4/30 11:01
     * @return: java.util.List<java.lang.String>
     **/
    @Select("SELECT * from apqp_master_document where id = #{id}")
    ApqpMasterDocument getById(@Param("id") String id);
}