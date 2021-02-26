package cn.jwis.qualityworkflow.modules.rework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import cn.jwis.qualityworkflow.modules.rework.bean.ReworkMoStatus;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-08-18 11:27
 * @since 0.1.0
 **/
@Mapper
public interface ReworkMoStatusMapper {

	@Insert("insert into rework_mo_status(id, rework_id, rework_mo, hold_status, creator) VALUES(#{id}, #{reworkId}, #{reworkMo}, #{holdStatus}, #{creator})")
	int insert(ReworkMoStatus status);

	/**
	 * 根据reworkId 获取 对应的 记录
	 * @param reworkId
	 * @return
	 */
	List<ReworkMoStatus> getByReworkId(@Param("reworkId") String reworkId);

	/**
	 * 根据Id获取mo状态
	 * @param id
	 * @return
	 */
	ReworkMoStatus getById(@Param("id") String id);


	/**
	 * 根据reworkId 和 reworkMo 解冻对应的Mo
	 */
	@Update("update rework_mo_status set hold_status = '1' where rework_id = #{reworkId} and rework_mo = #{reworkMo}")
	void thawMo(@Param("reworkId") String reworkId, @Param("reworkMo") String reworkMo);

	/**
	 * 根据id冻结对应的Mo
	 * @param id
	 */
	@Update("update rework_mo_status set hold_status = '0' where id = #{id}")
	void freezeMo(String id);

}
