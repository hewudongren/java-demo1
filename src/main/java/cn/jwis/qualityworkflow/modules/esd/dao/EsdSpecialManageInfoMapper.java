package cn.jwis.qualityworkflow.modules.esd.dao;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpeciaManageSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSpecialManageInfo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EsdSpecialManageInfoMapper {

    int insert(EsdSpecialManageInfo record);

    int updateByPrimaryKey(EsdSpecialManageInfo record);

    int updateByPrimaryKey2(EsdSpecialManageInfo record);

    int updateByPrimaryId(@Param("id") String id);

    @Select({ "<script>",
            "SELECT distinct ${parameter} from esd_special_manage_info where ${parameter} is not null",
            "</script>" })
    List<String>  getDropdownValue(@Param("parameter") String parameter);

    /**
     * @Author yuyangyang
     * @Description 来料检验管理界面查询接口
     * @Date  2020/6/17  11:16
     * @Param
     * @return
     */
    List<EsdSpecialManageInfo> findSpecialManageInfo(EsdSpeciaManageSearch esdSpeciaManageSearch);


    /**
     * @Author yuyangyang
     * @Description 来料检验管理界面查询接口总数
     * @Date  2020/6/17  11:16
     * @Param
     * @return
     */
    Long findSpecialManageCount(EsdSpeciaManageSearch esdSpeciaManageSearch);

    @Select({ "<script>",
            "SELECT distinct business_model from t_model where business_model  is not null",
            "</script>" })
    List<String>  getModelList();

    @Select({ "<script>",
            "SELECT  sampling_rate rate,sampling_qty qty from esd_sampling_level_info where sample_name = #{sampleName} and category = 'special'",
            "</script>" })
    JSONObject getRate(@Param("sampleName") String sampleName);

    @Select({ "<script>",
            "SELECT CONCAT(detection_item,'(',unit,')') title,lower_limit min,upper_limit max FROM esd_inspection_standard_info WHERE category ='special' and sample_name =#{sampleName}",
            "</script>" })
    List<JSONObject> findTableNameBySampleName(@Param("sampleName") String sampleName);

    @Select({ "<script>",
            "SELECT * from esd_special_manage_info where id = #{id}",
            "</script>" })
    EsdSpecialManageInfo findEsdSpecialManageDetail(@Param("id") String id);

    @Select({ "<script>",
            "SELECT * from esd_special_detail_info where esd_id = #{id}",
            "</script>" })
    List<JSONObject> getDetailData(@Param("id") String id);

}