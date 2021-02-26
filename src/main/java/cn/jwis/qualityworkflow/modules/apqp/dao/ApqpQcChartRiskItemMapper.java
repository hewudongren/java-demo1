package cn.jwis.qualityworkflow.modules.apqp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpQcChartRiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpQcChartRiskItemExample;

@Mapper
public interface ApqpQcChartRiskItemMapper {
    int countByExample(ApqpQcChartRiskItemExample example);

    int deleteByExample(ApqpQcChartRiskItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApqpQcChartRiskItem record);

    int insertSelective(ApqpQcChartRiskItem record);

    List<ApqpQcChartRiskItem> selectByExample(ApqpQcChartRiskItemExample example);

    ApqpQcChartRiskItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApqpQcChartRiskItem record, @Param("example") ApqpQcChartRiskItemExample example);

    int updateByExample(@Param("record") ApqpQcChartRiskItem record, @Param("example") ApqpQcChartRiskItemExample example);

    int updateByPrimaryKeySelective(ApqpQcChartRiskItem record);

    int updateByPrimaryKey(ApqpQcChartRiskItem record);

    List<ApqpQcChartRiskItem> getApqpQcChartRiskItem(@Param("previousProduct") String previousProduct,
                                            @Param("engPhase") String engPhase,
                                            @Param("updateTime") Date updateTime);
}