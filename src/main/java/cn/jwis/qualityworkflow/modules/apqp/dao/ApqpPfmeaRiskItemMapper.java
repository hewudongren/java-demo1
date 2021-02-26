package cn.jwis.qualityworkflow.modules.apqp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpPfmeaRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpPfmeaRiskItemExample;

@Mapper
public interface ApqpPfmeaRiskItemMapper {
    int countByExample(ApqpPfmeaRiskItemExample example);

    int deleteByExample(ApqpPfmeaRiskItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApqpPfmeaRiskItem record);

    int insertSelective(ApqpPfmeaRiskItem record);

    List<ApqpPfmeaRiskItem> selectByExample(ApqpPfmeaRiskItemExample example);

    ApqpPfmeaRiskItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApqpPfmeaRiskItem record, @Param("example") ApqpPfmeaRiskItemExample example);

    int updateByExample(@Param("record") ApqpPfmeaRiskItem record, @Param("example") ApqpPfmeaRiskItemExample example);

    int updateByPrimaryKeySelective(ApqpPfmeaRiskItem record);

    int updateByPrimaryKey(ApqpPfmeaRiskItem record);

    List<ApqpPfmeaRiskItem> getApqpPfmeaRiskItem(@Param("previousProduct") String previousProduct,
                                          @Param("engPhase") String engPhase,
                                          @Param("updateTime") Date updateTime);
}