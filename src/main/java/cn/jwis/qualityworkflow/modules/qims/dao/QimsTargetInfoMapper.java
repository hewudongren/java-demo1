package cn.jwis.qualityworkflow.modules.qims.dao;

import cn.jwis.qualityworkflow.modules.qims.bean.QimsTargetInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsTargetSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QimsTargetInfoMapper {


    int deleteByPrimaryKey(String id);

    int insert(QimsTargetInfo record);

    int updateByPrimaryKey(QimsTargetInfo record);

    Long getTargetCount(QimsTargetSearch qimsTargetSearch);

    List<QimsTargetInfo> getTarget(QimsTargetSearch qimsTargetSearch);
}