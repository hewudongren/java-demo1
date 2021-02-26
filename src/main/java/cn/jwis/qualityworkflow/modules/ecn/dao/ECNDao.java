package cn.jwis.qualityworkflow.modules.ecn.dao;

import java.util.List;
import java.util.Map;

import cn.jwis.qualityworkflow.modules.ecn.bean.QueryEcnInfoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.jwis.qualityworkflow.modules.ecn.bean.ECNInfoBean;
import cn.jwis.qualityworkflow.modules.ecn.bean.ECNTaskRecordBean;

@Mapper
public interface ECNDao {

	/**
	 * 
	 * @Description: 获取当天ECN流程新增数量
	 * @author longjun
	 * @date 2019年4月19日
	 * @return
	 */
	@Select("select count(1) total from ecn_info where to_days(createDate) = to_days(now())")
	Map<String, Object> getTodayAddEcnCount();

	/**
	 * 
	 * @Description: 获取前一天ECN流程新增数量
	 * @author longjun
	 * @date 2019年4月19日
	 * @return
	 */
	@Select("SELECT count(1) total FROM ecn_info WHERE to_days(now()) - to_days(createDate) = 1")
	Map<String, Object> getYesterdayAddEcnCount();

	/**
	 * @Description:获取当天ECN流程关闭数量
	 * @author longjun
	 * @date 2018年9月5日
	 * @return
	 */
	@Select("select count(1) total from ecn_info where to_days(updateDate) = to_days(now()) and state='Close'")
	Map<String, Object> getTodayCloseEcnCount();

	/**
	 * @Description:获取前一天ECN流程关闭数量
	 * @author longjun
	 * @date 2018年9月5日
	 * @return
	 */
	@Select("SELECT count(1) total FROM ecn_info WHERE to_days(now()) - to_days(updateDate) = 1 and state='Close'")
	Map<String, Object> getYesterdayCloseEcnCount();

	/**
	 * 
	 * @Description: 查询各厂区ECN流程趋势信息
	 * @author longjun
	 * @date 2019年4月19日
	 * @param bean
	 * @return
	 */
	@Select({ "<script>",
			"SELECT factory,DATE_FORMAT(createDate , #{dateFormat}) abscissa,count(1) total FROM ecn_info  WHERE 1 = 1",
			"<if test='factory!=null'>", "AND factory = #{factory}", "</if>", "<if test='startDate!=null'>",
			"AND createDate &gt;=#{startDate,jdbcType=TIMESTAMP}",
			"AND DATE_FORMAT(createDate , '%Y-%m-%d') &lt;= #{endDate,jdbcType=TIMESTAMP}", "</if>",
			" GROUP BY factory,abscissa order by factory,abscissa", "</script>" })
	List<Map<String, Object>> queryEcnTendencyChartInfos(ECNInfoBean bean);

	/**
	 * 
	 * @Description: 查询整体ECN流程趋势信息
	 * @author longjun
	 * @date 2019年4月19日
	 * @param bean
	 * @return
	 */
	@Select({ "<script>",
			"SELECT DATE_FORMAT(createDate , #{dateFormat}) abscissa,count(1) total FROM ecn_info  WHERE 1 = 1",
			"<if test='startDate!=null'>", "AND createDate &gt;=#{startDate,jdbcType=TIMESTAMP}",
			"AND DATE_FORMAT(createDate , '%Y-%m-%d') &lt;= #{endDate,jdbcType=TIMESTAMP}", "</if>",
			" GROUP BY abscissa", "</script>" })
	List<Map<String, Object>> queryEntiretyEcnTendencyInfos(ECNInfoBean bean);

	/**
	 * 
	 * @Description: 保存ECN流程业务信息
	 * @author longjun
	 * @date 2019年4月19日
	 * @param bean
	 * @return
	 */
	@Insert("INSERT INTO ecn_info(id, number, productName, orderNumber, batch, factory, workshop,EcnTarget,EcnQuantity,description,attachment) "
			+ " VALUES(#{id}, EcnNumber(), #{productName}, #{orderNumber}, #{batch}, #{factory}, #{workshop}, #{EcnTarget}, #{EcnQuantity},#{description},#{attachment})")
	void saveEcnInfo(ECNInfoBean bean);

	/**
	 * 
	 * @Description: 更新ECN流程业务信息
	 * @author longjun
	 * @date 2019年4月19日
	 * @param bean
	 */
	@Update("update ecn_info q set q.state=#{state},q.assignee=#{assignee} where q.id = #{id} ")
	void updateEcnInfo(ECNInfoBean bean);

	/**
	 * 
	 * @Description: 获取当前用户已处理的ECN流程
	 * @author longjun
	 * @date 2019年4月19日
	 * @param bean
	 * @return
	 */
	@Select({ "<script>", "SELECT q.*,ar.processInstanceId,ar.taskId FROM ecn_info q,",
			" (SELECT  ecnId,processInstanceId,taskId,assignee FROM ecn_task_record ",
			" WHERE assignee = #{assignee} and taskState='Close') ar where q.id=ar.ecnId ",
			// 写页面的查询条件
			"<if test='state!=null'>", " AND q.state = #{state}", "</if>", "<if test='factory!=null'>",
			" AND q.factory = #{factory}", "</if>", "<if test='workshop!=null'>", " AND q.workshop = #{workshop}",
			"</if>", "<if test='productName!=null'>", " AND q.productName = #{productName}", "</if>",
			"<if test='orderNumber!=null'>", " AND q.orderNumber = #{orderNumber}", "</if>", "<if test='batch!=null'>",
			" AND q.batch = #{batch}", "</if>", "<if test='createDate!=null'>",
			" AND DATE_FORMAT(q.createDate , '%Y-%m-%d') = #{createDate}", "</if>", "order by q.updateDate desc",
			"</script>" })
	List<ECNInfoBean> getEcnInfoList(QueryEcnInfoVo bean);

	/**
	 * 
	 * @Description: 通过流程TaskId获取ECN流程业务信息
	 * @author longjun
	 * @date 2019年4月19日
	 * @param taskId
	 * @return
	 */
	@Select({ "<script>",
			"SELECT q.*,ar.processInstanceId,ar.taskId,ar.assignee,ar.taskState FROM ecn_info q,(SELECT  ecnId,processInstanceId,taskId,assignee,taskState FROM ecn_task_record WHERE taskId=#{taskId}) ar where q.id=ar.ecnId",
			"</script>" })
	ECNInfoBean getEcnInfoByTaskId(String taskId);

	/**
	 * 
	 * @Description:获取当前用户待办的ECN流程
	 * @author longjun
	 * @date 2019年4月19日
	 * @param bean
	 * @return
	 */
	@Select({ "<script>",
			"SELECT a.* ,ar.processInstanceId,ar.taskId FROM ecn_info a,(SELECT ecnId,processInstanceId,taskId,assignee FROM ecn_task_record WHERE assignee = #{assignee} and taskState is null) ar where a.id=ar.ecnId ",
			// 写页面的查询条件
			"<if test='state!=null'>", "AND a.state = #{state}", "</if>", "<if test='factory!=null'>",
			"AND a.factory = #{factory}", "</if>", "<if test='workshop!=null'>", "AND a.workshop = #{workshop}",
			"</if>", "<if test='productName!=null'>", " AND a.productName = #{productName}", "</if>",
			"<if test='orderNumber!=null'>", " AND a.orderNumber = #{orderNumber}", "</if>", "<if test='batch!=null'>",
			" AND a.batch = #{batch}", "</if>", "<if test='createDate!=null'>",
			"AND DATE_FORMAT(a.createDate , '%Y-%m-%d') = #{createDate}", "</if>", " order by a.updateDate desc",
			"</script>" })
	List<ECNInfoBean> getCommissionEcnInfoList(QueryEcnInfoVo bean);

	/**
	 * 
	 * @Description: 获取跟当前用户相关的所有ECN流程
	 * @author longjun
	 * @date 2019年4月19日
	 * @param bean
	 * @return
	 */
	@Select({ "<script>", "SELECT q.*,ar.processInstanceId,ar.taskId FROM ecn_info q,",
			" (SELECT ecnId,processInstanceId,taskId,assignee  FROM ecn_task_record WHERE assignee = #{assignee}) ar ",
			" where q.id=ar.ecnId ",
			// 写页面的查询条件
			"<if test='state!=null'>", "AND q.state = #{state}", "</if>", "<if test='factory!=null'>",
			"AND q.factory = #{factory}", "</if>", "<if test='workshop!=null'>", "AND q.workshop = #{workshop}",
			"</if>", "<if test='productName!=null'>", " AND q.productName = #{productName}", "</if>",
			"<if test='orderNumber!=null'>", " AND q.orderNumber = #{orderNumber}", "</if>", "<if test='batch!=null'>",
			" AND q.batch = #{batch}", "</if>", "<if test='createDate!=null'>",
			" AND DATE_FORMAT(q.createDate , '%Y-%m-%d') = #{createDate}", "</if>", " order by q.updateDate desc",
			"</script>" })
	List<ECNInfoBean> getAllEcnInfoList(QueryEcnInfoVo bean);

	/**
	 * @Description:更新ECN流程Task节点信息
	 * @author longjun
	 * @date 2018年8月21日
	 * @param processInstanceId
	 */
	@Update({ "<script>",
			"update ecn_task_record e set e.taskState='Close' where e.processInstanceId = #{processInstanceId} and e.taskState is null ",
			"</script>" })
	void updateEcnTaskRecord(String processInstanceId);

	/**
	 * @Description:保存ECN流程Task节点信息
	 * @author longjun
	 * @date 2018年8月21日
	 * @param taskRecordBean
	 */
	@Insert("INSERT INTO ecn_task_record(id, processInstanceId, assignee,ecnId, taskState,taskName,taskId) "
			+ " VALUES(#{id}, #{processInstanceId}, #{assignee}, #{ecnId}, #{taskState},#{taskName},#{taskId})")
	void saveEcnTaskRecord(ECNTaskRecordBean taskRecordBean);

	/**
	 * 
	 * @Description: 通过流程状态（Task节点名）获取该流程状态对应处理人
	 * @author longjun
	 * @date 2019年4月19日
	 * @param string
	 * @param state
	 * @return
	 */
	@Select({ "<script>",
			"SELECT userName FROM user_department_relationship where processingNodeName=#{1} and category=#{0} ",
			"</script>" })
	String getHeadersByState(String string, String state);

	/**
	 * @Description: 获取ECN流程待办数
	 * @author longjun
	 * @date 2018年9月5日
	 * @param account
	 * @return
	 */
	@Select({ "<script>", "select count(1) count from ecn_info where state is not null and state !='Close'",
			"<if test='account!=null'>", "AND assignee = #{account}", "</if>", "</script>" })
	Map<String, Object> getEcnPendingCount(@Param("account") String account);

	/**
	 * @Description:通过状态，获取当天该状态的ECN流程数量
	 * @author longjun
	 * @date 2018年9月5日
	 * @param bean
	 * @return
	 */
	@Select("select count(1) total from ecn_info where 1=1 and state=#{state}")
	Map<String, Object> getTodayEcnCountByState(ECNInfoBean bean);

}
