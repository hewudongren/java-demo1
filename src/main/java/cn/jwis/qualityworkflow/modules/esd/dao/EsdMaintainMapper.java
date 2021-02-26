package cn.jwis.qualityworkflow.modules.esd.dao;

import cn.jwis.qualityworkflow.modules.esd.bean.EsdInStanInfo;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdInStanSearch;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdSamplingLevelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EsdMaintainMapper {

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 保存ESD检验标准维护信息
	 * @Date 2020/5/9  11:03
	 * @Param
	 */
	void savaEsdInStanInfo(EsdInStanInfo esdInStanInfo);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 修改ESD检验标准维护信息
	 * @Date 2020/5/9  11:06
	 * @Param
	 */
	void updateEsdInStanInfo(EsdInStanInfo esdInStanInfo);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 查询ESD检验标准维护信息
	 * @Date 2020/5/9  11:17
	 * @Param
	 */
	List<EsdInStanInfo> findEsdInStanInfo(EsdInStanSearch esdInStanSearch);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 查询ESD检验标准维护信息数量
	 * @Date 2020/5/9  14:54
	 * @Param
	 */
	long findEsdInStanInfoCount(EsdInStanSearch esdInStanSearch);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 删除ESD检验标准维护信息
	 * @Date 2020/5/9  11:19
	 * @Param
	 */
	void removeEsdInStanInfo(@Param("id") String id);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 获取样品名的下拉值
	 * @Date 2020/5/9  16:42
	 * @Param
	 */
	@Select("SELECT DISTINCT sample_name FROM esd_inspection_standard_info where category =#{category}")
	List<String> getSampleNameList(@Param("category") String category);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 获取检验项目的下拉值
	 * @Date 2020/5/9  16:42
	 * @Param
	 */
	@Select("SELECT DISTINCT detection_item FROM esd_inspection_standard_info where category = #{category}")
	List<String> getDetectionItemList(@Param("category") String category);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 保存ESD抽样水准维护信息
	 * @Date 2020/5/9  16:48
	 * @Param
	 */
	void savaEsdSamplingLevelInfo(EsdSamplingLevelInfo esdSamplingLevelInfo);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 修改ESD抽样水准维护信息
	 * @Date 2020/5/9  16:49
	 * @Param
	 */
	void updateEsdSamplingLevelInfo(EsdSamplingLevelInfo esdSamplingLevelInfo);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 查询ESD抽样水准维护信息
	 * @Date 2020/5/9  16:52
	 * @Param
	 */
	List<EsdSamplingLevelInfo> findEsdSamplingLevelInfo(EsdInStanSearch esdInStanSearch);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 查询ESD抽样水准维护信息总数
	 * @Date 2020/5/9  16:53
	 * @Param
	 */
	long findEsdSamplingLevelInfoCount(EsdInStanSearch esdInStanSearch);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 删除ESD抽样水准维护信息
	 * @Date 2020/5/9  16:53
	 * @Param
	 */
	void removeEsdSamplingLevelInfo(@Param("id") String id);

	/**
	 * @return
	 * @Author yuyangyang
	 * @Description 获取样品名的下拉值(抽样水准)
	 * @Date 2020/5/9  16:53
	 * @Param
	 */
	@Select("SELECT DISTINCT sample_name FROM esd_sampling_level_info where category = #{category}")
	List<String> getSampleNameListLevel(String category);
}
