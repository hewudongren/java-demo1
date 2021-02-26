package cn.jwis.qualityworkflow.modules.apqp.dao;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDocumentInfo;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDocumentInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApqpDocumentInfoMapper {
    int countByExample(ApqpDocumentInfoExample example);

    int deleteByExample(ApqpDocumentInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApqpDocumentInfo record);

    int insertSelective(ApqpDocumentInfo record);

    List<ApqpDocumentInfo> selectByExample(ApqpDocumentInfoExample example);

    ApqpDocumentInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApqpDocumentInfo record, @Param("example") ApqpDocumentInfoExample example);

    int updateByExample(@Param("record") ApqpDocumentInfo record, @Param("example") ApqpDocumentInfoExample example);

    int updateByPrimaryKeySelective(ApqpDocumentInfo record);

    int updateByPrimaryKey(ApqpDocumentInfo record);

    int batchInsert(@Param("infos") List<ApqpDocumentInfo> infos);
}