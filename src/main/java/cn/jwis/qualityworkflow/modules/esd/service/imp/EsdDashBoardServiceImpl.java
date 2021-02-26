package cn.jwis.qualityworkflow.modules.esd.service.imp;

import static cn.jwis.qualityworkflow.util.DateUtil.getEsdTargetFlag;
import static cn.jwis.qualityworkflow.util.Title.EsdTargetDb;
import static cn.jwis.qualityworkflow.util.Title.EsdTargetDbTemplate;
import static cn.jwis.qualityworkflow.util.Title.EsdTargetExcel;
import static cn.jwis.qualityworkflow.util.Title.EsdTargetExcelTemplate;
import static cn.jwis.qualityworkflow.util.Title.EsdTargetExcelUs;
import static cn.jwis.qualityworkflow.util.UserUtil.getCurrentUserName;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.BaseException;
import cn.jwis.qualityworkflow.dao.CommonMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.modules.esd.bean.EsdTargetInfo;
import cn.jwis.qualityworkflow.modules.esd.dao.EsdDashBoardMapper;
import cn.jwis.qualityworkflow.modules.esd.service.EsdDashBoardService;
import cn.jwis.qualityworkflow.service.WebService;
import cn.jwis.qualityworkflow.util.DateUtil;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.FileUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.Title;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/28 19:13
 */
@Service
public class EsdDashBoardServiceImpl extends BaseClass implements EsdDashBoardService {

	private static final Logger logger = LoggerFactory.getLogger(EsdDashBoardServiceImpl.class);
	
	@Value("${fe.maintain.url}")
	private String equipmentWsdlUrl;
	@Value("${jdbc.connection.mes.info}")
	private String jdbcMesUrl;
	@Value("${jdbc.driver}")
	private String driver;

	@Autowired
	WebService webService;

	@Autowired
	IDGeneratorRunner idGeneratorRunner;

	@Autowired
	EsdDashBoardMapper esdDashBoardMapper;

	@Autowired
	CommonMapper commonMapper;

	@Override
	public void getEsdData(String startTime, String endTime) throws IOException {
		JSONObject jsonObject = webService.webServiceEsd(equipmentWsdlUrl, startTime, endTime);
		JSONArray mainlist = jsonObject.getJSONArray("Mainlist");
		String substring = startTime.substring(0, 10);
		for (int i = 0; i <mainlist.size() ; i++) {
			JSONObject jsonObject1 = mainlist.getJSONObject(i);
			Integer number = jsonObject1.getInteger("Number");
			String esdType = jsonObject1.getString("ESDType");
			String id = String.valueOf(idGeneratorRunner.nextId());
			esdDashBoardMapper.insert(id,"webService",esdType,number,substring);
		}
	}

	@Override
	public void saveTargetInfo(EsdTargetInfo esdTargetInfo) {
		//通过年份和月查看是否重复
		checkRule(esdTargetInfo);
		esdTargetInfo.setId(String.valueOf(idGeneratorRunner.nextId()));
		esdTargetInfo.setCreator(getCurrentUserName());
		esdDashBoardMapper.saveTargetInfo(esdTargetInfo);
	}

	@Override
	public void updateTargetInfo(EsdTargetInfo esdTargetInfo) {
		//通过年份和月查看是否重复
		checkRule(esdTargetInfo);
		esdDashBoardMapper.updateTargetInfo(esdTargetInfo);
	}

	@Override
	public void deleteTargetInfo(String id) {
		esdDashBoardMapper.deleteTargetInfo(id);
	}

	@Override
	public List<EsdTargetInfo> getTargetInfo(Integer page,Integer size,String startTime,String endTime) {
		if (isNotNull(page) && isNotNull(size)){
			page = (page-1)*size;
		}
		endTime = DateUtil.plusOneDay(endTime);
		List<EsdTargetInfo> list = esdDashBoardMapper.getTargetInfo(page, size, startTime, endTime);
		return list;
	}

	@Override
	public Long getTargetInfoCount(String startTime, String endTime) {
		endTime = DateUtil.plusOneDay(endTime);
		Long count = esdDashBoardMapper.getTargetInfoCount(startTime,endTime);
		return count;
	}

	@Override
	public Map<String, String> getTitle(HttpServletRequest request) {
		return Title.getTitle(request,EsdTargetDb,EsdTargetExcel,EsdTargetExcelUs);
	}

	@Override
	public void exportEsdInStanInfoTemplate(HttpServletResponse response) {
		ExcelUtil.setResponseHeader(response,"EsdTarget.xlsx");
		String[] Title = EsdTargetExcelTemplate;
		Workbook workbook = ExcelUtil.exporSimple(null,Title,null);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public Boolean importTargetInfo(MultipartFile file) {
		Boolean flag = false;
		File file1 = null;
		try {
			file1 = FileUtil.multipartFileToFile(file);
			List<String> title = new ArrayList<>();
			String[] strings = EsdTargetExcelTemplate;
			title = Arrays.asList(EsdTargetDbTemplate);
			List<JSONObject> list = ExcelUtil.readExcel(file1, title,strings);
			if (isNull(list)) {
				throw new BaseException("请勿导入空表");
			}
			for (JSONObject temp : list) {
				EsdTargetInfo esdTargetInfo = JSONObject.parseObject(temp.toJSONString(), EsdTargetInfo.class);
				saveTargetInfo(esdTargetInfo);
				flag = true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		} finally {
			FileUtil.deleteDir(file1);
		}
		return flag;
	}

	@Override
	public JSONObject getEsdDashBoardInfo(JSONObject jsonObject) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject result = new JSONObject();
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		Integer cycle = jsonObject.getIntValue("cycle");
		List<String> list = JSONObjectUtil.jsonArrayToList(jsonObject.getJSONArray("type"));
		//判断是否要计算Total
		list  = getTypeList(list);
		//获取图标的横坐标信息
		Set<String> betweenRuleDateDay = DateUtil.getBetweenRuleDateDay2(cycle, sdf.parse(startTime), sdf.parse(endTime));
		//获取分组的函数
		String molecule = getAbscissa("business_datetime",cycle);
		String denominator = getAbscissa("schedule_date",cycle);
		//获取分子的信息
		List<JSONObject> moleculeInfo = esdDashBoardMapper.getMoleculeInfo(molecule, startTime, endTime);
		//获取分母的信息
		List<JSONObject> denominatorInfo = getDenominator(denominator,startTime,endTime);
	    //获取目标值的信息
		JSONObject targetData = getTargetData();
		//封装数据
		JSONObject chartData = dataEncapsulation(moleculeInfo,denominatorInfo,list,betweenRuleDateDay,targetData,cycle);
		result.put("abscissa",betweenRuleDateDay);
		result.put("chartData",chartData);
		return result;
	}

	@Override
	public List<JSONObject> getEmailList(String category) {
		List<JSONObject> emailList = esdDashBoardMapper.getEmailList(category);
		return emailList;

	}

	@Override
	public void updateEmailInfo(JSONObject jsonObject) {
		String mainDelivery = jsonObject.getString("main_delivery");
		String ccPerson = jsonObject.getString("cc_person");
		String isEffective = jsonObject.getString("is_effective");
		String id = jsonObject.getString("id");
		String updator = getCurrentUserName();
		esdDashBoardMapper.updateEmailInfo(updator,id,mainDelivery,ccPerson,isEffective);
	}


	private JSONObject dataEncapsulation(List<JSONObject> moleculeInfo,List<JSONObject> denominatorInfo,List<String> list,Set<String> betweenRuleDateDay,JSONObject targetData,Integer cycle) throws ParseException {
		//分子转换成JSON对象(转换EOS,ESD的对象)
		JSONObject esdJson =  getEsdJson(moleculeInfo,"ESD类");
		JSONObject eosJson =  getEsdJson(moleculeInfo,"EOS类");
		JSONObject denominator =  getEsdJson(denominatorInfo,"");
		JSONObject result = new JSONObject();
		for (String type:list){
			List<JSONObject> temp = new ArrayList<>();
			if ("ESD".equals(type)){
				temp = getDataJson(esdJson,denominator,betweenRuleDateDay,"esd",targetData,cycle);
			}else if ("EOS".equals(type)){
				temp = getDataJson(eosJson,denominator,betweenRuleDateDay,"eos",targetData,cycle);
			}else {
				temp = getDataTotalJson(eosJson,esdJson,denominator,betweenRuleDateDay,targetData,cycle);
			}
			result.put(type,temp);
		}
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 判断年份和月份是否出现重复
	 * @Date  2020/7/30  17:16
	 * @Param
	 * @return
	 */
	private  void checkRule(EsdTargetInfo esdTargetInfo){
		String id = esdTargetInfo.getId();
		String year = esdTargetInfo.getYear();
		String month = esdTargetInfo.getMonth();
		Long count = 0L;
		if (isNull(id)){
			count = esdDashBoardMapper.getTargetInfoByYearAndMonth(year, month);
			if (count >0 ){
				throw  new BaseException("请勿重新创建相同年份,月份数据");
			}
		}else {
			count = esdDashBoardMapper.getTargetInfoById(year, month,id);
			if (count >0 ){
				throw  new BaseException("请勿重新创建相同年份,月份数据");
			}
		}
	}
	
	/**
	 * @Author yuyangyang
	 * @Description 获取有dashBoard的线体类型条数
	 * @Date  2020/7/30  19:10
	 * @Param 
	 * @return 
	 */
	private  List<String> getTypeList(List<String> list){
		if (isNull(list)){
           list.add("EOS");
           list.add("ESD");
           list.add("Total");
		}
		return list;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取周期函数
	 * @Date  2020/7/30  20:04
	 * @Param
	 * @return
	 */
	private String getAbscissa(String abscissa,Integer cycle){
		String temp = null;
		if (cycle == 3) {
			temp = "DATE_FORMAT(" + abscissa + ",'%Y-%m-%d')";
		} else if (cycle == 4) {
			temp = "CONCAT(DATE_FORMAT(" + abscissa + ",'%x'),\"-WK\",DATE_FORMAT(" + abscissa + ",'%v'))";
		} else if (cycle == 5) {
			temp = "DATE_FORMAT(" + abscissa + ",'%b-%y')";
		}
		return  temp;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取分母的函数
	 * @Date  2020/7/30  21:55
	 * @Param
	 * @return
	 */
	List<JSONObject> getDenominator(String molecule, String startTime, String endTime){
		Connection connection = null;
		connection = getConnectionInstance();
		// 准备执行语句 (判断是否是全量传输)
		String sql = getSql(molecule,startTime,endTime);
		List<JSONObject> result = formatJson(connection,sql);
		return result;
	}
	/**
	 * @Author yuyangyang
	 * @Description 获取分母的查询SQL
	 * @Date  2020/7/31  10:11
	 * @Param 
	 * @return 
	 */
	private String getSql(String molecule,String startTime,String endTime){
		String sql = null;
		sql = "SELECT abscissa,count(1)sum FROM"+
				"(SELECT "+molecule+"abscissa,line_code,sum( man_hour ) FROM"+
				" t_schedule_detail WHERE schedule_date >= "+"'" + startTime + "'" +
				" AND schedule_date <= "+"'" + endTime + "'" +" GROUP BY abscissa, line_code HAVING sum( man_hour ) >= 8) temp GROUP BY abscissa";
		logger.info(sql);
		return sql;
	}
	/**
	 * @Author yuyangyang
	 * @Description 分母查询结果转换成List<JSONObject>
	 * @Date  2020/7/31  10:11
	 * @Param 
	 * @return 
	 */
	private List<JSONObject> formatJson(Connection connection,String sql) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<JSONObject> list = new ArrayList<>();
		try {
			long currentTimeMillis = System.currentTimeMillis();
			statement = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery();
			long currentTimeMillis1 = System.currentTimeMillis();
			logger.info("查询数据时间" + (currentTimeMillis1 - currentTimeMillis));
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				JSONObject object = new JSONObject();
				for (int j = 1; j <= columnCount; j++) {
					object.put(metaData.getColumnName(j), resultSet.getObject(j));
				}
				list.add(object);
			}
			long currentTimeMillis2 = System.currentTimeMillis();
			logger.info("数据转换时间" + (currentTimeMillis2 - currentTimeMillis1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

    /**
     * @Author yuyangyang
     * @Description 连接远程数据库信息
     * @Date  2020/7/31  10:13
     * @Param
     * @return
     */
	public Connection getConnectionInstance() {
		Connection connection = null;
		String jdbcUrl = jdbcMesUrl;
		try {
			// 指定连接类型
			Class.forName(driver).newInstance();
			// 通过JDBC连接串获取连接
			String rwaDataJdbcUrl = jdbcUrl;
			connection = DriverManager.getConnection(rwaDataJdbcUrl);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * @Author yuyangyang
	 * @Description 将查询出来的List<JsonObject>转换成JSON
	 * @Date  2020/7/31  10:45
	 * @Param
	 * @return
	 */
	private JSONObject getEsdJson(List<JSONObject> list,String flag){
		JSONObject result = new JSONObject();
		if (isNotNull(flag)){
			for (JSONObject jsonObject:list) {
				String abscissa = jsonObject.getString("abscissa");
				Integer sum = jsonObject.getInteger("sum");
				String type = jsonObject.getString("type");
				if (flag.equals(type)){
					result.put(abscissa,sum);
				}
			}
		}else {
            for (JSONObject jsonObject:list){
				String abscissa = jsonObject.getString("abscissa");
				Integer sum = jsonObject.getInteger("sum");
				result.put(abscissa,sum);
			}
		}
		return result;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取ESD,EOS的数据展示
	 * @Date  2020/7/31  11:09
	 * @Param
	 * @return
	 */
	private List<JSONObject> getDataJson(JSONObject esdJson,JSONObject denominator,Set<String> betweenRuleDateDay,String key,JSONObject targetData,Integer cycle) throws ParseException {
		List<JSONObject> list = new LinkedList<>();
		for (String abscissa:betweenRuleDateDay) {
			JSONObject temp = new JSONObject();
			String esdNum = getValue(esdJson,abscissa);
			String denominatorNum = getValue(denominator,abscissa);
			temp.put(key,esdNum);
			temp.put("denominator",denominatorNum);
			temp.put("rate",getRate(esdNum,denominatorNum));
			temp.put("abscissa",abscissa);
			String targetFlag = getEsdTargetFlag(abscissa, cycle, key);
			temp.put("target",getTarget(targetData,targetFlag));
			list.add(temp);
		}
		return list;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取Total的数据展示
	 * @Date  2020/7/31  11:09
	 * @Param
	 * @return
	 */
	private List<JSONObject> getDataTotalJson(JSONObject esdJson,JSONObject eosJson,JSONObject denominator,Set<String> betweenRuleDateDay,JSONObject targetData,Integer cycle) throws ParseException {
		List<JSONObject> list = new LinkedList<>();
		for (String abscissa:betweenRuleDateDay) {
			JSONObject temp = new JSONObject();
			String esdNum = getValue(esdJson,abscissa);
			String eosNum = getValue(eosJson,abscissa);
			String denominatorNum = getValue(denominator,abscissa);
			temp.put("denominator",denominatorNum);
			String total = "-";
			if ("-".equals(esdNum)){
				total = eosNum;
			}else if ("-".equals(eosNum)){
				total = esdNum;
			}else {
				total = String.valueOf(Integer.valueOf(eosNum)+Integer.valueOf(esdNum));
			}
			temp.put("total",total);
			temp.put("rate",getRate(total,denominatorNum));
			temp.put("abscissa",abscissa);
			String targetFlag = getEsdTargetFlag(abscissa, cycle, "total");
			temp.put("target",getTarget(targetData,targetFlag));
			list.add(temp);
		}
		return list;
	}
	/**
	 * @Author yuyangyang
	 * @Description 算出占比
	 * @Date  2020/7/31  11:26
	 * @Param
	 * @return
	 */
	private String getRate(String pass,String total){
		if ("-".equals(pass) || "-".equals(total)){
			return  "-";
		}
		BigDecimal passBig = BigDecimal.valueOf(Double.valueOf(pass));
		BigDecimal totalBig = BigDecimal.valueOf(Double.valueOf(total));
		BigDecimal	passRate = passBig.divide(totalBig,2,BigDecimal.ROUND_HALF_DOWN);
		return passRate.toString();
	}
	/**
	 * @Author yuyangyang
	 * @Description 获取数据
	 * @Date  2020/7/31  11:29
	 * @Param
	 * @return
	 */
	private String getValue(JSONObject jsonObject,String key){
		String string = jsonObject.getString(key);
		if (isNull(string)){
			string = "-";
		}
		return string;
	}

	/**
	 * @Author yuyangyang
	 * @Description 获取目标值信息
	 * @Date  2020/7/31  14:41
	 * @Param
	 * @return
	 */
	private JSONObject getTargetData(){
		JSONObject result = new JSONObject();
		List<EsdTargetInfo> list = esdDashBoardMapper.getTargetInfo(null, null, null, null);
		for (EsdTargetInfo esd:list) {
			String year = esd.getYear();
			String month = esd.getMonth();
			Float esd1 = esd.getEsd();
			Float eos = esd.getEos();
			Float total = esd.getTotal();
			result.put(String.join("-",year,month,"esd"),esd1);
			result.put(String.join("-",year,month,"eos"),eos);
			result.put(String.join("-",year,month,"total"),total);
		}
		return result;
	}

	/**
	 * 获取目标值的具体显示
	 */
	private String getTarget(JSONObject targetMap, String targetKey) {
		String target = "-";
		if (isNull(targetMap)) {
			return target;
		}
		if (isNotNull(targetMap.get(targetKey))) {
			target = targetMap.getString(targetKey);
		}
		return target;
	}
}