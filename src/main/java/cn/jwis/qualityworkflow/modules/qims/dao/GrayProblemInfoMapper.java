package cn.jwis.qualityworkflow.modules.qims.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.jwis.qualityworkflow.modules.qims.bean.GrayProblemInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.GrayProblemSearch;

@Mapper
public interface GrayProblemInfoMapper {

    int insert(GrayProblemInfo record);

    int updateByPrimaryKey(GrayProblemInfo record);

    @Select("SELECT gray_number_generator()")
    String getItemNumber();

    @Select({ "<script>",
            "SELECT distinct ${parameter} from gray_problem_info where ${parameter} is not null",
            "</script>" })
    List<String> getDropdownValue(@Param("parameter") String parameter);

    List<GrayProblemInfo> getGrayProblemList(GrayProblemSearch grayProblemSearch);

    Long getGrayProblemCount(GrayProblemSearch grayProblemSearch);
    @Select({ "<script>",
            "SELECT * from gray_problem_info where id = #{id}",
            "</script>" })
    GrayProblemInfo getGrayProblemById(@Param("id") String id);
}