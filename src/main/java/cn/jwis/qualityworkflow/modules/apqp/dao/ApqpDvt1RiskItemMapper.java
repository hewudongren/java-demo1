package cn.jwis.qualityworkflow.modules.apqp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDvt1RiskItem;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpDvt1RiskItemExample;

@Mapper
public interface ApqpDvt1RiskItemMapper {
    int countByExample(ApqpDvt1RiskItemExample example);

    int deleteByExample(ApqpDvt1RiskItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApqpDvt1RiskItem record);

    int insertSelective(ApqpDvt1RiskItem record);

    List<ApqpDvt1RiskItem> selectByExample(ApqpDvt1RiskItemExample example);

    ApqpDvt1RiskItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApqpDvt1RiskItem record, @Param("example") ApqpDvt1RiskItemExample example);

    int updateByExample(@Param("record") ApqpDvt1RiskItem record, @Param("example") ApqpDvt1RiskItemExample example);

    int updateByPrimaryKeySelective(ApqpDvt1RiskItem record);

    int updateByPrimaryKey(ApqpDvt1RiskItem record);


    List<ApqpDvt1RiskItem> getApqpDvt1RiskItem(@Param("previousProduct") String previousProduct,
                                         @Param("engPhase") String engPhase,
                                         @Param("updateTime") Date updateTime);
}