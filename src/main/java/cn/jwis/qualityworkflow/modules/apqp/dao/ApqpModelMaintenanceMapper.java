package cn.jwis.qualityworkflow.modules.apqp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.github.pagehelper.Page;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpModelMaintenance;
import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpModelMaintenanceExample;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryModelCascadeVo;
import cn.jwis.qualityworkflow.modules.apqp.bean.QueryModelListVo;

@Mapper
@Component
public interface ApqpModelMaintenanceMapper {
    int countByExample(ApqpModelMaintenanceExample example);

    int deleteByExample(ApqpModelMaintenanceExample example);

    int deleteByPrimaryKey(String id);

    int insert(ApqpModelMaintenance record);

    int insertSelective(ApqpModelMaintenance record);


    List<ApqpModelMaintenance> selectByExample(ApqpModelMaintenanceExample example);

    ApqpModelMaintenance selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ApqpModelMaintenance record, @Param("example") ApqpModelMaintenanceExample example);


    int updateByExample(@Param("record") ApqpModelMaintenance record, @Param("example") ApqpModelMaintenanceExample example);

    int updateByPrimaryKeySelective(ApqpModelMaintenance record);


    int updateByPrimaryKey(ApqpModelMaintenance record);

    Page<ApqpModelMaintenance> getList(QueryModelListVo vo);

    /**
     * @description:
     * @author: xujinbiao
     * @date: 2020/4/30 11:01
     * @param parameter:
     * @return: java.util.List<java.lang.String>
     **/
    @Select({ "<script>",
            "SELECT distinct ${parameter} from apqp_model_maintenance where ${parameter} is not null",
            "</script>" })
    List<Object> getPullDownValue(@Param("parameter") String parameter);

    @Select({ "<script>",
            "SELECT previous_product from apqp_model_maintenance where model = #{model}",
            "</script>" })
    String getPreviousProduct(@Param("model") String model);



    List<String> getCascadeValue(QueryModelCascadeVo vo);
}