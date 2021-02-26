package cn.jwis.qualityworkflow.modules.rework.service.imp;

import static cn.jwis.qualityworkflow.common.Constants.CLOSE_TASK_STATE;
import static cn.jwis.qualityworkflow.common.Constants.CN_LANGUAGE;
import static cn.jwis.qualityworkflow.util.Title.FPYQueryDetailDB;
import static cn.jwis.qualityworkflow.util.Title.FPYQueryDetailExcel;
import static cn.jwis.qualityworkflow.util.Title.MaintenanceDetail;
import static cn.jwis.qualityworkflow.util.Title.ReworkApplyTable;
import static cn.jwis.qualityworkflow.util.Title.ReworkDbCamel;
import static cn.jwis.qualityworkflow.util.Title.ReworkExcel;
import static cn.jwis.qualityworkflow.util.Title.ReworkTable;
import static cn.jwis.qualityworkflow.util.Title.ReworkTemplate;
import static cn.jwis.qualityworkflow.util.Title.ReworkTemplateDb;
import static cn.jwis.qualityworkflow.util.Title.ReworkTemplateExcel;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryPieChartVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryReworkApplyVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryReworkInfoVo;
import cn.jwis.qualityworkflow.modules.rework.bean.QueryTrentChartVo;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfo;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfoExample;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkMoStatus;
import cn.jwis.qualityworkflow.modules.rework.dao.ReworkInfoMapper;
import cn.jwis.qualityworkflow.modules.rework.dao.ReworkMoStatusMapper;
import cn.jwis.qualityworkflow.modules.rework.service.ReworkService;
import cn.jwis.qualityworkflow.service.imp.HistoryProcessRecordImpl;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FileUtil;
import cn.jwis.qualityworkflow.util.ImportAndExportUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.ReflectUtil;
import cn.jwis.qualityworkflow.util.UserServer;
import cn.jwis.qualityworkflow.util.UserUtil;
import cn.jwis.qualityworkflow.util.WorkflowServer;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-15 15:28
 * @since 0.1.0
 **/
@Service
public class ReworkServiceImpl extends BaseClass implements ReworkService {

	private static final String REWORK_TABLE_NAME = "rework_info";

	@Autowired
	ReworkInfoMapper reworkDao;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	WorkflowServer workflowServer;

	@Autowired
	HistoryProcessRecordImpl historyProcessRecord;

	@Autowired
	ReflectUtil reflectUtil;

	@Autowired
	ImportAndExportUtil importAndExportUtil;

	@Value("${jdbc.connection.mo.url}")
	private String jdbcUrl;

	@Value("${jdbc.connection.mo.user}")
	private String jdbcUser;

	@Value("${jdbc.connection.mo.password}")
	private String jdbcPassword;

	@Autowired
	UserServer userServer;

	@Resource
	ReworkMoStatusMapper reworkMoStatusDao;


	public static final Logger log = LoggerFactory.getLogger(ReworkServiceImpl.class);


	@Override
	public PageInfo<JSONObject> getApplyInfo(QueryReworkApplyVo bean) throws Exception{
		reflectUtil.preHandledPageBean(bean);
		String userItemInfos = userServer.getUserItemInfos("Rework-业务管理员");
		if (userItemInfos == null) {
			bean.setAdmin(1);
		}
		bean.setAssignee(UserUtil.getCurrentUserName());
		Page<ReworkInfo> list = reworkDao.getApplyInfo(bean);
		List<JSONObject> result = JSONObjectUtil.toJSONObjectList(list);
		importAndExportUtil.transferData(result, REWORK_TABLE_NAME, CN_LANGUAGE, false);
		PageInfo<JSONObject> page = new PageInfo<>(result);
		page.setTotal(list.getTotal());
		return page;
	}

	@Override
	public void exportApplyInfo(HttpServletResponse response, HttpServletRequest request, QueryReworkApplyVo vo) throws Exception {
		vo.setPageSize(Integer.MAX_VALUE - 1);
		PageInfo<JSONObject> page = getApplyInfo(vo);
		List<JSONObject> list = page.getList();
		// 去除掉数据来源
		String[] title = new String[ReworkExcel.length - 1];
		System.arraycopy(ReworkExcel, 1, title, 0, ReworkExcel.length - 1);
		String[] camelOfDb = new String[ReworkDbCamel.length - 1];
		System.arraycopy(ReworkDbCamel, 1, camelOfDb, 0, ReworkDbCamel.length - 1);
		// 生成和输出excel
		Workbook workbook = ExcelUtil.exporSimple(list, title, camelOfDb);
		ExcelUtil.outputWorkbook(response, workbook, ReworkApplyTable);
	}

	@Override
	public List<Object> getPullDownValue(String parameter) throws Exception {
		String underlineParameter = JSONObjectUtil.camelToUnderline(parameter);
		List<Object> pullDownValue = reworkDao.getPullDownValue(underlineParameter);
		return pullDownValue;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirm(ConfirmVo bean) throws Exception {
		ReworkInfo reworkInfo = reflectUtil.variablesToObject(bean, ReworkInfo.class);
		preHandleConfirm(reworkInfo);
		if (StringUtils.isEmpty(bean.getId())) {
			commitInfo(reworkInfo);
		} else {
			// 获取前台传来的TaskId
			String processInstanceId = bean.getProcessInstanceId();
			String taskId = bean.getTaskId();
			// 调用接口，完成任务
			try {
				workflowServer.finishTask(processInstanceId, taskId, reworkInfo.getVariables(), reworkInfo.getLocalVariables());
			} catch (Exception e) {
				log.info("Rework workflow taskId-" + taskId + " finish task error");
			}
			reflectUtil.updateProcessInfo(reworkInfo);
			// 捕获异常，发生异常不回滚
//			try {
				externalHandle(bean);
//			} catch (Exception e) {
//				log.info(e.getMessage());
//			}
			// 发送流程文件
			reflectUtil.sendWorkflowEmail(ReworkInfo.class, bean.getId(), bean.getStatus());
		}
	}

	@Override
	public Map<String, Object>  getDetailednessInfo(QueryDetailedInfoVo bean) throws Exception {
		Map<String, Object> detailedInfoMap = historyProcessRecord.getDetailedInfoMap(bean);
		ReworkInfo reworkInfo = reworkDao.getInfoByTaskId(bean.getTaskId());
		detailedInfoMap.put("detailedInfo",reworkInfo);
		// 如果单据处于Close状态 追加返工Mo状态
		if(CLOSE_TASK_STATE.equals(reworkInfo.getStatus()) && detailedInfoMap.get("PQEConfirm") != null) {
			Map<String, Object> pqeConfirmMap = (Map)detailedInfoMap.get("PQEConfirm");
			JSONArray array = (JSONArray) pqeConfirmMap.get("list");
			if (array != null && array.size() > 0) {
				List<ReworkMoStatus> reworkMoStatusList = getReworkMoStatusList(reworkInfo.getId());
				Map<String, List<ReworkMoStatus>> map = reworkMoStatusList.stream().collect(Collectors.groupingBy(ReworkMoStatus::getReworkMo));
				for (int i = 0; i < array.size(); i++) {
					JSONObject object = array.getJSONObject(i);
					String reworkMo = object.getString("reworkMo");
					if (reworkMo == null) {
						continue;
					}
					ReworkMoStatus status = map.get(reworkMo).get(0);
					object.put("holdStatus", status.getHoldStatus());
					object.put("reworkMoStatusId", status.getId());
				}
			}
		}
		return detailedInfoMap;
	}

	@Override
	public void exportReworkTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Workbook workbook = ExcelUtil.exporSimple(null, ReworkTemplateExcel, null);
		ExcelUtil.outputWorkbook(response, workbook, ReworkTemplate);
	}

	@Override
	public PageInfo<ReworkInfo> getReworkList(QueryReworkInfoVo bean) throws Exception {
		reflectUtil.preHandledPageBean(bean);
		ReworkInfoExample example = new ReworkInfoExample();
		ReworkInfoExample.Criteria criteria = example.createCriteria();
		// 查询条件
		if (bean.getCraftsSection() != null &&  bean.getCraftsSection().size() > 0) {
			criteria.andCraftsSectionIn(bean.getCraftsSection());
		}
		if (bean.getModel() != null && bean.getModel().size() > 0) {
			criteria.andModelIn(bean.getModel());
		}
		if (bean.getDataSource() != null && bean.getDataSource().size() > 0) {
			criteria.andDataSourceIn(bean.getDataSource());
		}
		if (StringUtils.isNotEmpty(bean.getStartTime()) && StringUtils.isNotEmpty(bean.getEndTime())) {
			criteria.andProblemTimeBetween(DateUtil.parseStringtoDate(bean.getStartTime()), DateUtil.parseStringtoDate(bean.getEndTime()));
		}
		List<ReworkInfo> reworkInfos = reworkDao.selectByExample(example);
		PageInfo<ReworkInfo> page = new PageInfo<>(reworkInfos);
		return page;
	}

	@Override
	public void importReworkInfo(MultipartFile file, HttpServletRequest request) throws Exception {
		File file1 = null;
		try {
			String currentUserName = UserUtil.getCurrentUserName();
			file1 = FileUtil.multipartFileToFile(file);
			ArrayList<String> title = new ArrayList<>(Arrays.asList(ReworkTemplateDb));
			List<JSONObject> list = ExcelUtil.readExcel(file1, title, ReworkTemplateExcel);
			Preconditions.checkArgument(CollectionUtils.isNotEmpty(list), "请勿导入空表");
			List<List<String>> reworkInfos = new ArrayList<>(list.size());
			// 遍历生成bean
			for (JSONObject object: list) {
				List<String> info = new ArrayList<>();
				info.add(String.valueOf(idGeneratorRunner.nextId()));
				info.add("手工导入");
				info.add(currentUserName);
				info.add(object.getString("problemTime"));
				info.add(object.getString("craftsSection"));
				info.add(object.getString("model"));
				info.add(object.getString("reworkQuantity"));
				info.add(object.getString("problemType"));
				info.add(object.getString("rootCauseResponsibility"));
				reworkInfos.add(info);
			}
			reworkDao.batchInsert(reworkInfos);
		} catch (SQLException e) {
			throw new RuntimeException("数据插入数据库时发生错误");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			FileUtil.deleteDir(file1);
		}
	}

	@Override
	public void exportReworkList(HttpServletResponse response, HttpServletRequest request, QueryReworkInfoVo bean) throws Exception {
		bean.setPageSize(Integer.MAX_VALUE - 1);
		PageInfo<ReworkInfo> page = getReworkList(bean);
		List<ReworkInfo> list = page.getList();
		List<JSONObject> result = JSONObjectUtil.toJSONObjectList(list);
		importAndExportUtil.transferData(result, REWORK_TABLE_NAME, CN_LANGUAGE, false);
		// 生成和输出excel
		Workbook workbook = ExcelUtil.exporSimple(result, ReworkExcel, ReworkDbCamel);
		ExcelUtil.outputWorkbook(response, workbook, ReworkTable);
	}

	@Override
	public JSONObject getReworkQuantityChart(QueryTrentChartVo vo) throws Exception {
 		String groupTime = DateUtil.getDateTypeTime(vo.getDateType(), "problem_time");
		vo.setGroupBy(groupTime);
		List<Map<String, Object>> reworkQuantityChart = reworkDao.getReworkQuantityChart(vo);
		JSONObject result = new JSONObject();
		for (Map<String, Object> map: reworkQuantityChart) {
			String time = (String)map.get("time");
			long num = (Long)map.get("num");
			result.put(time, num);
		}
		return result;
	}

	@Override
	public JSONObject getPieChart(QueryPieChartVo vo) throws Exception {
		List<Map<String, Object>> pieChart = reworkDao.getPieChart(vo);
		JSONObject result = new JSONObject();
		for (Map<String, Object> map: pieChart) {
			String type = (String)map.get("type");
			long num = (Long)map.get("num");
			result.put(type, num);
		}
		return result;
	}

	@Override
	public void exportMaintenanceDetail(HttpServletResponse response, HttpServletRequest request, String reworkMo) throws Exception {
		List<JSONObject> jsonObjects = reworkDao.exportMaintenanceDetail(reworkMo);
		Workbook workbook = ExcelUtil.exporSimple(jsonObjects, FPYQueryDetailExcel, FPYQueryDetailDB);
		ExcelUtil.outputWorkbook(response, workbook, MaintenanceDetail);
	}

	@Override
	public void thawReworkMo(String id) throws Exception {
		if (id == null || "".equals(id)) {
			throw new RuntimeException("id不能为空");
		}
		ReworkMoStatus moStatus = reworkMoStatusDao.getById(id);
		if (moStatus == null) {
			throw new RuntimeException("id对应的Mo不存在");
		}
		if ("1".equals(moStatus.getHoldStatus())) {
			throw new RuntimeException("返工MO已被解冻");
		}
		// 调用thawMoList解冻
		try {
			ArrayList<String> moList = new ArrayList<>(1);
			moList.add(moStatus.getReworkMo());
			thawMoList(moList, moStatus.getReworkId());
		} catch (Exception e) {
			throw new RuntimeException("返工MO 解冻过程向联想数据库插入数据失败!");
		}
	}

	@Override
	public List<ReworkMoStatus> getReworkMoStatusList(String reworkId) throws Exception {
		if (reworkId == null) {
			return null;
		}
		List<ReworkMoStatus> list = reworkMoStatusDao.getByReworkId(reworkId);
		return list;
	}


	/**
	 * 对流程不同的提交阶段进行特定的处理
	 * @param bean
	 * @throws Exception
	 */
	private void preHandleConfirm(ReworkInfo bean)  throws Exception {
		if (StringUtils.isEmpty(bean.getStatus())) {
			return;
		}
		String currentUserName = UserUtil.getCurrentUserName();
		Date now = new Date();
		// Apply阶段的特殊处理
		switch (bean.getStatus()) {
			case "Apply":
				bean.setReworkConfirmor(currentUserName);
				break;
			case "Audit":
				bean.setAuditTime(now);
				break;
			case "Review":
				ReworkInfo reworkInfo = reworkDao.selectByPrimaryKey(bean.getId());
				if (currentUserName.equals(reworkInfo.getPlanHandler())) {
					bean.getLocalVariables().put("reviewDepartment", "计划");
				} else if (currentUserName.equals(reworkInfo.getProductionHandler())) {
					bean.getLocalVariables().put("reviewDepartment", "生产");
				} else if (currentUserName.equals(reworkInfo.getProjectHandler())) {
					bean.getLocalVariables().put("reviewDepartment", "工程");
				}
				break;
			case "ProjectExecution":
				bean.setProjectHandleTime(now);
				break;
			case "ReworkPlan":
				bean.setPlanHandleTime(now);
				break;
			case "ProductionExecution":
				bean.setProductionExecutionTime(now);
				break;
			case "PQEConfirm":
				bean.setReworkConfirmTime(now);
				break;
			default:
				break;
		}
	}

	/**
	 * ReworkPlan节点 需要冻结对应的MO， PQEConfirm节点通过的话需要 解冻对应的MO
	 * @param vo
	 * @throws Exception
	 */
	private void  externalHandle(ConfirmVo vo) throws Exception{
		String status = vo.getStatus();
		List<String> moList = null;
		// 冻结对应的MO
		if ("ReworkPlan".equals(status)) {
			JSONObject variables = vo.getVariables();
			JSONArray moArray = variables.getJSONArray("list");
			moList = new ArrayList<>(moArray.size());
			if (CollectionUtils.isNotEmpty(moArray)) {
				for (int  i = 0; i < moArray.size(); i ++) {
					JSONObject object = moArray.getJSONObject(i);
					String reworkMo = object.getString("reworkMo");
					if (reworkMo != null) {
						moList.add(reworkMo);
					}
				}
			}
			freezeMoList(moList, vo.getId());
		}
		// 通过解冻对应的MO
		else if ("PQEConfirm".equals(status)) {
			JSONObject variables = vo.getVariables();
			String reworkConfirmResult = variables.getString("reworkConfirmResult");
			// 如果PQE结果确认 为通过
			if ("Y".equals(reworkConfirmResult)) {
				JSONArray moArray = variables.getJSONArray("list");
				moList = new ArrayList<>(moArray.size());
				if (CollectionUtils.isNotEmpty(moArray)) {
					for (int  i = 0; i < moArray.size(); i ++) {
						JSONObject object = moArray.getJSONObject(i);
						String reworkMo = object.getString("reworkMo");
						if (reworkMo != null) {
							moList.add(reworkMo);
						}
					}
				}
				thawMoList(moList, vo.getId());
			}
		}
	}

	/**
	 * 提交Rework流程 (产生记录，拟制单据阶段完成，进入下一阶段)
	 * @param bean
	 */
	private synchronized void commitInfo(ReworkInfo bean)  throws Exception{
		// 自带项目编号 和 id 和 创建人
		String creator = UserUtil.getCurrentUserName();
		String reworkNumber = reworkDao.getReworkNumber();
		bean.setId(String.valueOf(idGeneratorRunner.nextId()));
		bean.setCreator(creator);
		bean.setReworkNumber(reworkNumber);
		bean.setDataSource("系统创建");
		// 把creator放入 全局变量中 给流程 最后 PQEConfirm节点使用
		bean.getVariables().put("creator", creator);
		reflectUtil.commitRecord(bean);
		// 用线程池执行会快一点
		reflectUtil.sendWorkflowEmail(ReworkInfo.class, bean.getId(),null);
	}


	/**
	 * 解冻MO
	 * 向联想数据库对应的库中插入记录 和 向rework_mo_status解冻对应的记录
	 * @throws Exception
	 */
	private void thawMoList(List<String> moList, String reworkId) throws Exception {
		// 为空直接返回
		if (CollectionUtils.isEmpty(moList)) {
			return;
		}
		Connection connection = null;
		PreparedStatement statement = null;
		String insertMoSql = "insert into INTERFACE_QHOLD_MANAGE(hold_type, hold_value, hold_status, hold_remark, create_user, create_time,modify_user,modify_time)" +
				"VALUES(?, ?, ?, ?, ?, ?,?,?)";
		try {
			connection = getConnectionInstance();
			statement = connection.prepareStatement(insertMoSql);
			String currentDate = DateUtil.getCurrentDate();
			String currentUserName = UserUtil.getCurrentUserName();
			statement.setString(1, "2");
			// hold_status 0表示冻结， 1表示解冻
			statement.setString(3, "1");
			statement.setString(4, "返工流程返工结果确认节点解冻MO");
			statement.setString(5, currentUserName);
			statement.setString(6, currentDate);
			statement.setString(7, currentUserName);
			statement.setString(8, currentDate);
			for (String mo: moList) {
				statement.setString(2, mo);
				statement.execute();
				log.info(statement.toString());
				reworkMoStatusDao.thawMo(reworkId, mo);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(jdbcUrl + "close connection error, cause:" + e.getMessage());
			}
		}
	}


	/**
	 * 冻结MO
	 * 向联想数据库对应的库中插入记录 和 向rework_mo_status插入记录
	 * @param reworkId rework单据id
 	 * @throws Exception
	 */
	private void freezeMoList(List<String> moList, String reworkId) throws Exception {
		// 为空直接返回
		if (CollectionUtils.isEmpty(moList)) {
			return;
		}
		PreparedStatement statement = null;
		Connection connection = null;
		String insertMoSql = "insert into INTERFACE_QHOLD_MANAGE(hold_type, hold_value, hold_status, hold_remark, create_user, create_time)" +
				"VALUES(?, ?, ?, ?, ?, ?)";
		try {
			connection = getConnectionInstance();
			statement = connection.prepareStatement(insertMoSql);
			String currentUserName = UserUtil.getCurrentUserName();
			String currentDate = DateUtil.getCurrentDate();
			statement.setString(1, "2");
			// hold_status 0表示冻结， 1表示解冻
			statement.setString(3, "0");
			statement.setString(4, "返工流程计划处理节点冻结MO");
			statement.setString(5, currentUserName);
			statement.setString(6, currentDate);
			for (String mo: moList) {
				statement.setString(2, mo);
				statement.execute();
				log.info(statement.toString());
				// 向rework_mo_status插入记录
				long id = idGeneratorRunner.nextId();
				ReworkMoStatus moStatus = new ReworkMoStatus(String.valueOf(id), reworkId, mo, "0", currentUserName);
				reworkMoStatusDao.insert(moStatus);
			}
		} catch (Exception e) {
			log.error("freeze reworkMO error, cause:" + e.getMessage());
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				log.error(jdbcUrl + "close connection error, cause:" + e.getMessage());
			}
		}
	}



	/**
	 * 获取Connection连接
	 * @throws Exception
	 */
	private Connection getConnectionInstance() throws Exception {
		Connection connection = null;
		String driver = "com.mysql.jdbc.Driver";
		try {
			// 指定连接类型
			Class.forName(driver).newInstance();
			// 设置连接超时时间为1s
			DriverManager.setLoginTimeout(1);
			connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			log.info(e.getMessage());
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return connection;
	}
}
