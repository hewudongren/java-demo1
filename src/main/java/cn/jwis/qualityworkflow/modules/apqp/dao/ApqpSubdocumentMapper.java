package cn.jwis.qualityworkflow.modules.apqp.dao;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocument;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocumentExample;
import java.util.List;

import cn.jwis.qualityworkflow.modules.apqp.bean.QuerySubDocumentListVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ApqpSubdocumentMapper {
    int countByExample(ApqpSubdocumentExample example);

    int deleteByExample(ApqpSubdocumentExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApqpSubdocument record);

    int insertSelective(ApqpSubdocument record);

    List<ApqpSubdocument> selectByExample(ApqpSubdocumentExample example);

    ApqpSubdocument selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApqpSubdocument record, @Param("example") ApqpSubdocumentExample example);

    int updateByExample(@Param("record") ApqpSubdocument record, @Param("example") ApqpSubdocumentExample example);

    int updateByPrimaryKeySelective(ApqpSubdocument record);

    int updateByPrimaryKey(ApqpSubdocument record);

    /**
     * @description:
     * @author: xujinbiao
     * @date: 2020/4/30 11:01
     * @param parameter:
     * @return: java.util.List<java.lang.String>
     **/
    @Select({ "<script>",
            "SELECT distinct ${parameter} from apqp_subdocument where ${parameter} is not null",
            "</script>" })
    List<Object> getPullDownValue(@Param("parameter") String parameter);

    /**
     * @description: 查询子单据
     * @author: xujinbiao
     * @date: 2020/5/29 15:48
     * @param vo:
     * @return: java.util.List<cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocument>
     **/
    Page<ApqpSubdocument> getInfo(QuerySubDocumentListVo vo);

    /**
     * 查询需要发送主单据汇总 的邮件列表
     * @return
     */
    @Select("select * from apqp_subdocument WHERE status not in ('QC_Char_Audit','NQE', 'Close')")
    List<ApqpSubdocument> getSummaryList();

    /**
     * 查询需要发送主单据汇总 的邮件列表
     * @return
     */
    @Select("select * from apqp_subdocument WHERE master_id = #{masterId}")
    List<ApqpSubdocument> getByMasterId(@Param("masterId") String masterId);
}