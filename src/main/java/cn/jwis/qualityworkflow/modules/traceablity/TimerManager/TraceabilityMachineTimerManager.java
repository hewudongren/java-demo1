package cn.jwis.qualityworkflow.modules.traceablity.TimerManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.traceablity.service.TraceabilityMachineService;
import cn.jwis.qualityworkflow.modules.traceablity.service.imp.TraceabilityMachineServiceImpl;

@Component
@EnableScheduling
public class TraceabilityMachineTimerManager {
	
	private static final Logger logger = LoggerFactory.getLogger(TraceabilityMachineTimerManager.class);

	@Autowired
	TraceabilityMachineServiceImpl traceabilityMachineServiceImpl;

	@Autowired
	TraceabilityMachineService traceabilityMachineService;

	@Value("${jdbc.connection.machine.url}")
	private String machineUrl;

	@Value("${jdbc.connection.machine.user}")
	private String machineUser;

	@Value("${jdbc.connection.machine.password}")
	private String machinePassword;

	@Scheduled(cron = "0 */10 * * * ?")
	public void MachineTimerManager() throws Exception {
		logger.info("设备定时任务开启");
		List<String> list = traceabilityMachineServiceImpl.getTraceabilityMachineTableName();
		for (int i = 0; i < list.size(); i++) {
			String tableName = list.get(i);
			List<String> fileNameList = traceabilityMachineServiceImpl.getTraceabilityMachineFiledName(tableName);
			String string = String.join(",", fileNameList);
			Date maxCreatedTime = traceabilityMachineServiceImpl.getMaxCreatedTime(tableName);
			List<JSONObject> arrayList = getLenovoMachineData(tableName, string, maxCreatedTime);
			if (arrayList != null && arrayList.size() > 0) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("tableName", tableName);
				jsonObject.put("data", arrayList);
				traceabilityMachineService.insetTraceabilityMachine(jsonObject);
			}

		}
	}

	private List<JSONObject> getLenovoMachineData(String tableName, String filedName, Date time) throws Exception {
		SimpleDateFormat timeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Connection connection = null;
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = machineUrl;
			String user = machineUser;
			String password = machinePassword;
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<JSONObject> list = null;
		String string = "";
		if (time != null) {
			String date = timeDate.format(time);
			if ("glue_dispenser_weight".equals(tableName)) {
				string += "SELECT " + filedName + " from " + tableName + " where measure_time >" + "'" + date + "'";

			} else if ("t_pressure_visualization".equals(tableName)) {

				string += "SELECT " + filedName + " from " + tableName + " where t_creation_time >" + "'" + date + "'";

			} else if ("t_plasma".equals(tableName)) {
				string += "SELECT " + filedName + " from " + tableName + " where created_time >" + "'" + date + "'";

			} else {
				string += "SELECT " + filedName + " from " + tableName + " where createtime >" + "'" + date + "'";
			}
		} else {
			string += "SELECT " + filedName + " from " + tableName;

		}
		try {
			preparedStatement = connection.prepareStatement(string);
			resultSet = preparedStatement.executeQuery();
			list = convertList(resultSet);

		} catch (SQLException ex) {

		} finally {
			closeAll(connection, preparedStatement, resultSet);
		}
		return list;
	}

	public static void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			// 按顺序关闭
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<JSONObject> convertList(ResultSet rs) throws SQLException {
		List<JSONObject> list = new ArrayList<JSONObject>();
		// 获取键名
		ResultSetMetaData md = rs.getMetaData();
		// 获取行的数量
		int columnCount = md.getColumnCount();
		while (rs.next()) {
			// Map rowData = new HashMap();
			JSONObject jsonObject = new JSONObject();
			// 声明Map
			for (int i = 1; i <= columnCount; i++) {
				// 获取键名及值
				jsonObject.put(md.getColumnName(i), rs.getObject(i));
			}
			list.add(jsonObject);
		}
		return list;
	}
}
