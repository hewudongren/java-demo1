package cn.jwis.qualityworkflow.util;

import cn.jwis.qualityworkflow.bean.BaseException;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsBlackInfo;
import cn.jwis.qualityworkflow.modules.qims.bean.QimsCqaInfo;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.jwis.qualityworkflow.bean.BaseClass.subZeroAndDot;
import static cn.jwis.qualityworkflow.util.Title.EcnInfoDb;
import static cn.jwis.qualityworkflow.util.Title.EcnInfoExcel;
import static org.apache.poi.ss.usermodel.CellType.BLANK;

/**
 * Created by yu Created date 2019/8/13 9:15
 */
@Component
public class ExcelUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	private static final String XLS = ".xls";

	private static final String XLSX = ".xlsx";

	private static String dfsURL;

	@Value("${dfs.service.url}")
	public void setDfsURL(String dfsURL) {
		ExcelUtil.dfsURL = dfsURL;
	}

	/**
	 * 读取excel文件时通过文件名以及输入流读取WorkBook的信息
	 */
	public static Workbook getWorkBook(String file, InputStream inputStream) {
		Workbook workbook = null;
		try {
			if (file.endsWith(XLS)) {
				workbook = new HSSFWorkbook(inputStream);
			} else if (file.endsWith(XLSX)) {
				workbook = new XSSFWorkbook(inputStream);
			} else {
				throw new BaseException("上传文件格式有误");
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return workbook;
	}

	/**
	 * 简单导入(Excel)
	 */
	public static List<JSONObject> readExcel(File file, List<String> title, String[] titles) throws IOException {
		Workbook workbook = getWorkBook(file.getName(), new FileInputStream(file));
		List<JSONObject> list = new ArrayList<>();
		if (workbook != null) {
			// 获取sheet页的数据(简单使用默认只有一个sheet页)
			Sheet sheet = workbook.getSheetAt(0);
			// 获取总行数
			int maxRow = sheet.getLastRowNum();
			// 从第一行的表头判断Excel是否是标准模版
			for (int i = 0; i <= maxRow; i++) {
				JSONObject jsonObject = new JSONObject();
				Row row = sheet.getRow(i);
				if (isRowEmpty(row)) {
					continue;
				}
				if (i == 0) {
					//判断表头是否符合要求
					short cellNum = row.getLastCellNum();
					if (cellNum != titles.length) {
						throw new BaseException("导入模板表头信息有误");
					}
					for (int j = 0; j < cellNum; j++) {
						String temp = getCellValue(row.getCell(j));
						if (!temp.equals(titles[j])) {
							throw new BaseException("导入模板有误");
						}
					}
				} else {
					for (int j = 0; j < title.size(); j++) {
						String key = title.get(j);
						String value = getCellValue(row.getCell(j));
						jsonObject.put(key, value);
					}
					list.add(jsonObject);
				}
			}
			workbook.close();
		}
		return list;

	}

	/**
	 * 简单导出(Excel)(xlsx格式)
	 */
	public static Workbook exporSimple(List<JSONObject> list, String[] title, String[] strings) {
		// 创建workbook工作
		SXSSFWorkbook workbook = new SXSSFWorkbook();
//         //使用模版的格式
//         Map<String,XSSFCellStyle> map = creatStandardStyle(workbook);
//         XSSFCellStyle titleStyle = map.get("title");
//         XSSFCellStyle commonStyle = map.get("common");
		// 创建sheet页
		SXSSFSheet sheet = workbook.createSheet();
		// 写入标题行
		SXSSFRow firstRow = sheet.createRow(0);
		for (int i = 0; i < title.length; i++) {
			Cell cell = firstRow.createCell(i);
			cell.setCellValue(title[i]);
			// cell.setCellStyle(titleStyle);
		}
		// 写入数据行
		int size = 0;
		int length = 0;
		if (list != null) {
			size = list.size();
			length = strings.length;
		}
		if (list != null && size > 0) {
			for (int i = 0; i < size; i++) {
				JSONObject temp = list.get(i);
				SXSSFRow row = sheet.createRow(i + 1);
				for (int j = 0; j < length; j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(subZeroAndDot(JSONObjectUtil.getValue(temp, strings[j])));
					// cell.setCellStyle(commonStyle);
				}
			}
		}
		return workbook;
	}

	/**
	 * 简单导出(Excel)(数值不去出多余的0)(xlsx格式)
	 */
	public static Workbook exporSimple3(List<JSONObject> list, String[] title, String[] strings) {
		// 创建workbook工作
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		SXSSFSheet sheet = workbook.createSheet();
		// 写入标题行
		SXSSFRow firstRow = sheet.createRow(0);
		for (int i = 0; i < title.length; i++) {
			Cell cell = firstRow.createCell(i);
			cell.setCellValue(title[i]);
		}
		// 写入数据行
		int size = 0;
		if (list != null) {
			size = list.size();
		}
		int length = strings.length;
		if (list != null && size > 0) {
			for (int i = 0; i < size; i++) {
				JSONObject temp = list.get(i);
				SXSSFRow row = sheet.createRow(i + 1);
				for (int j = 0; j < length; j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(JSONObjectUtil.getValue(temp, strings[j]));
				}
			}
		}
		return workbook;
	}

	public static void outputWorkbook(HttpServletResponse response, Workbook workbook, String fileName) {
		ExcelUtil.setResponseHeader(response, fileName);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			logger.error(
					UserUtil.getCurrentUserName() + " export " + fileName + "happen error, cause " + e.getMessage());
		}
	}

	/**
	 * 定义一个模版的标题和数据的格式
	 * 
	 * @param workbook
	 * @return
	 */
	public static Map<String, XSSFCellStyle> creatStandardStyle(XSSFWorkbook workbook) {
		Map<String, XSSFCellStyle> map = new HashMap<>();
		XSSFCellStyle style = null;
		// 创建标题的格式：
		Font monthFont = workbook.createFont();
		monthFont.setFontName("Arial");
		monthFont.setFontHeightInPoints((short) 10);
		monthFont.setColor(IndexedColors.WHITE.getIndex());
		style = workbook.createCellStyle();
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFont(monthFont);
		style.setWrapText(true);
		map.put("title", style);
		// 创建普通数据的格式
		style = workbook.createCellStyle();
		Font cellFont = workbook.createFont();
		cellFont.setFontName("Arial");
		cellFont.setFontHeightInPoints((short) 10);
		style.setFont(cellFont);
		style.setWrapText(true);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		map.put("common", style);
		return map;
	}

	/**
	 * 发送响应流的方式
	 * 
	 * @param response
	 * @param fileName
	 */
	public static void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
			response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
		} catch (Exception e) {
			logger.error("发送响应流失败");
		}
	}

	/**
	 * 获取单元格的值（对格式进行校验）
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = null;
		if (cell == null) {
			return cellValue;
		}
		// if (cell.getCellType() == CELL_TYPE_NUMERIC) //
		// 把数字当成String来读，避免出现1读成1.0的情况
		// {
		// cell.setCellType(Cell.CELL_TYPE_STRING);
		// }
		switch (cell.getCellTypeEnum()) // 判断数据的类型
		{
		case NUMERIC: // 数字 0
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				if (cell.getCellStyle().getDataFormat() != 14) {
					logger.error("请输入正确的日期格式");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
				} catch (Exception e) {
					logger.error("请输入正确的日期格式");
				}
			} else {
				cellValue = subZeroAndDot(String.valueOf(cell.getNumericCellValue()));
				// 判断是否是科学计数法格式的数字
				if (isNumber(cellValue)) {
					// 科学计数法转换成字符串
					BigDecimal bigDecimal = new BigDecimal(cellValue);
					cellValue = subZeroAndDot(bigDecimal.toPlainString());
				}
			}
			break;
		case STRING: // 字符串 1
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case BOOLEAN: // Boolean 4
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA: // 公式 2
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case BLANK: // 空值 3
			cellValue = "";
			break;
		case ERROR: // 故障 5
			cellValue = "error";
			break;
		default:
			cellValue = "error";
			break;
		}
		return cellValue;
	}

	/**
	 * 根据下载的模版生成workbook
	 */
	public static XSSFWorkbook createWorkbook(String fileName, String templateUrl) {
		//将文件模块读入到内存
		InputStream is = HttpClientUtil.clientGETRequestGetIO(templateUrl);
		XSSFWorkbook workbook = null;
		try {
			if (fileName.endsWith(XLSX)) {
				workbook = new XSSFWorkbook(is); // 2007
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new BaseException(e.getMessage());
		}
		return workbook;
	}

	/**
	 *
	 * @param table 传入的表名
	 * @param flag  flag为True时返回的是Excel的表名，为false时返回的是数据库字段名
	 * @return
	 */
	public static String[] getList(String table, boolean flag) {
		String[] strings = null;
		switch (table) {
		case "ecn_info":
			strings = EcnInfoDb;
			if (flag) {
				strings = EcnInfoExcel;
			}
			break;
		default:
		}
		return strings;
	}

	/**
	 * 简单导出(Excel)(xlsx格式)
	 */
	public static Workbook exporSimple2(List<Map<String, Object>> list, String[] title, String[] strings) {
		// 创建workbook工作
		SXSSFWorkbook workbook = new SXSSFWorkbook();
//         //使用模版的格式
//         Map<String,XSSFCellStyle> map = creatStandardStyle(workbook);
//         XSSFCellStyle titleStyle = map.get("title");
//         XSSFCellStyle commonStyle = map.get("common");
		// 创建sheet页
		SXSSFSheet sheet = workbook.createSheet();
		// 写入标题行
		SXSSFRow firstRow = sheet.createRow(0);
		for (int i = 0; i < title.length; i++) {
			Cell cell = firstRow.createCell(i);
			cell.setCellValue(title[i]);
			// cell.setCellStyle(titleStyle);
		}
		// 写入数据行
		int size = 0;
		if (list != null) {
			size = list.size();
		}
		int length = strings.length;
		if (list != null && size > 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> map = list.get(i);
				SXSSFRow row = sheet.createRow(i + 1);
				for (int j = 0; j < length; j++) {
					Cell cell = row.createCell(j);
					String value = null;
					if (map.get(strings[j]) != null) {
						value = subZeroAndDot(map.get(strings[j]).toString());
					}
					cell.setCellValue(value);
					// cell.setCellStyle(commonStyle);
				}
			}
		}
		return workbook;
	}

	/**
	 * FPY以及FTY导出报表格式
	 */
	public static void exportReport(List<JSONObject> list, HttpServletResponse response, List<Map<String, Object>> data,
			String fileName, String flagKey, String valueKey) {
		if (data.size() == 0) {
			throw new BaseException("请勿导出空表");
		}
		String[] title = new String[list.size()];
		String[] db = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Set<String> set = list.get(i).keySet();
			for (String string : set) {
				db[i] = string;
				title[i] = list.get(i).getString(string);
			}
		}
		setResponseHeader(response, fileName);
		Workbook workbook = exportReport(data, title, db, flagKey, valueKey);
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BaseException("导出失败");
		}
	}

	public static Workbook exportReport(List<Map<String, Object>> list, String[] title, String[] strings,
			String flagKey, String valueKey) {
		// 创建workbook工作
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		// 定义合格和不合格的表格格式
		// 绿底黑字（合格的样式）
		CellStyle style2 = workbook.createCellStyle();
		Font font2 = workbook.createFont();
		font2.setFontName("Arial");
		font2.setFontHeightInPoints((short) 10);
		style2.setFont(font2);
		style2.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// 红底白字（不合格样式）
		CellStyle style5 = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setColor(HSSFColor.WHITE.index);
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		style5.setFont(font);
		style5.setFillForegroundColor(IndexedColors.RED.getIndex());
		style5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// 定义不同Arial,10
		CellStyle style3 = workbook.createCellStyle();
		style3.setFont(font2);
		// 定义表头
		CellStyle style4 = workbook.createCellStyle();
		Font font3 = workbook.createFont();
		font3.setFontName("Arial");
		font3.setFontHeightInPoints((short) 10);
		font3.setBold(true);
		style4.setFont(font3);
		// 创建sheet页
		SXSSFSheet sheet = workbook.createSheet();
		// 写入标题行
		SXSSFRow firstRow = sheet.createRow(0);
		for (int i = 0; i < title.length; i++) {
			Cell cell = firstRow.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style4);
		}
		// 写入数据行
		int size = 0;
		if (list != null) {
			size = list.size();
		}
		int length = strings.length;
		if (list != null && size > 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> map = list.get(i);
				SXSSFRow row = sheet.createRow(i + 1);
				for (int j = 0; j < length; j++) {
					Cell cell = row.createCell(j);
					String value = "-";
					cell.setCellStyle(style3);
					if (map.get(strings[j]) != null) {
						if (map.get(strings[j]) instanceof JSONObject) {
							JSONObject jsonObject = (JSONObject) map.get(strings[j]);
							// 兼容LRR 和 VLRR 的flagKey
							if ("exceedStandard".equals(flagKey)) {
								boolean flag = jsonObject.getBoolean(flagKey);
								String target = jsonObject.getString("target");
								value = jsonObject.getString(valueKey);
								if (flag) {
									cell.setCellStyle(style5);
								} else if (!flag && target != null) {
									cell.setCellStyle(style2);
								}
							} else {
								String flag = jsonObject.getString(flagKey);
								value = jsonObject.getString(valueKey);
								if ("0".equals(flag)) {
									cell.setCellStyle(style2);
								} else if ("1".equals(flag)) {
									cell.setCellStyle(style5);
								}
							}
						} else {
							value = map.get(strings[j]).toString();
						}
					}
					cell.setCellValue(value);
				}
			}
		}
		return workbook;
	}

	/**
	 * 判断一行是否是空行
	 * 
	 * @param row
	 * @return
	 */
	public static boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellTypeEnum() != BLANK) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否是科学计数法的数字
	 */
	public static boolean isNumber(String str) {
		if (str.indexOf("E") > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Author yuyangyang
	 * @Description 导出黑色问题8D报告
	 * @Date 2020/8/7 14:51
	 * @Param
	 * @return
	 */
	public static void createBlackFile(String fileName, QimsBlackInfo bean, HttpServletResponse response,
			String fileOid) throws Exception {
		String url = dfsURL + "file/downloadByOid?oid=" + fileOid;
		//根据模板创建工作簿
		XSSFWorkbook wb = createWorkbook(fileName, url);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		setValue(sheet, 1, 8, bean.getQuestionNumber());
		setValue(sheet, 3, 3, bean.getReportSubject());
		setValue(sheet, 4, 3, bean.getAbnormalResponsiblePerson());
		setValue(sheet, 4, 7, bean.getFailuresNumber());
		setValue(sheet, 5, 3, bean.getReportingTime());
		setValue(sheet, 7, 3, bean.getCrew());
		setValue(sheet, 8, 3, bean.getGroupLeader());
		setValue(sheet, 10, 3, bean.getFailurePhenomenon());
		setValue(sheet, 11, 3, bean.getProblemDescription());
		String assessmentResult = changeInfo(bean.getAssessmentResult());
		setValue(sheet, 13, 7, assessmentResult);
		setValue(sheet, 14, 3, bean.getBasisForAssessment());
		setValue(sheet, 15, 3, bean.getPrimaryCause());
		setValue(sheet, 16, 3, bean.getPreliminaryAnalysisConclusion());
		setValue(sheet, 17, 3, bean.getDefectType());
		String isStop = changeInfo(bean.getIsStop());
		setValue(sheet, 17, 7, isStop);
//        setValue(sheet,18,3,bean.getStopLineBecause());
		setValue(sheet, 19, 3, bean.getRootCauseDepartment());
		setValue(sheet, 20, 3, bean.getRootCauseAnalyst());
		setValue(sheet, 21, 3, bean.getRootCause());
		setValue(sheet, 22, 3, bean.getRootCausesLeakOut());
		setValue(sheet, 23, 3, bean.getFundamentalAnalysisConclusion());
		setValue(sheet, 25, 3, bean.getLongTermTreatment1());
		setValue(sheet, 26, 3, bean.getLongTermTreatment2());
		setValue(sheet, 27, 3, bean.getLongTermTreatment3());
		setValue(sheet, 28, 3, bean.getLongTermTreatment4());
		setValue(sheet, 29, 3, bean.getLongTermTreatment5());
		setValue(sheet, 31, 3, bean.getEffectVerificationResults());
		setValue(sheet, 33, 3, bean.getLongTermTreatment1());
		setValue(sheet, 34, 3, bean.getLongTermTreatment2());
		setValue(sheet, 35, 3, bean.getLongTermTreatment3());
		setValue(sheet, 36, 3, bean.getLongTermTreatment4());
		setValue(sheet, 37, 3, bean.getLongTermTreatment5());
		setValue(sheet, 38, 3, bean.getRootCauseTime());
		setValue(sheet, 39, 3, bean.getRootCauseAnalyst());
		setValue(sheet, 40, 3, bean.getEffectReviewTime());
		setValue(sheet, 42, 3, bean.getCaseClosingOpinion());
		setValue(sheet, 43, 3, bean.getCaseClosingApprover());
		wb.write(response.getOutputStream());// 保存Excel文件
	}

	/**
	 * @Author yuyangyang
	 * @Description 导出CQA问题8D报告
	 * @Date 2020/8/7 14:52
	 * @Param
	 * @return
	 */
	public static void createCQAFile(String fileName, QimsCqaInfo bean, HttpServletResponse response, String fileOid)
			throws Exception {
		String url = dfsURL + "file/downloadByOid?oid=" + fileOid;
		XSSFWorkbook wb = createWorkbook(fileName, url);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		setValue(sheet, 1, 8, bean.getQuestionNumber());
		setValue(sheet, 3, 3, bean.getReportSubject());
		setValue(sheet, 4, 3, bean.getAbnormalResponsiblePerson());
		setValue(sheet, 4, 7, bean.getFailuresNumber());
		setValue(sheet, 5, 3, bean.getReportingTime());
		setValue(sheet, 7, 3, bean.getCrew());
		setValue(sheet, 8, 3, bean.getGroupLeader());
		setValue(sheet, 10, 3, bean.getProblemDescription());
		setValue(sheet, 12, 7, bean.getAssessmentResult());
		setValue(sheet, 13, 3, bean.getBasisForAssessment());
		setValue(sheet, 14, 3, bean.getBatchReworkQuantity());
		setValue(sheet, 14, 7, bean.getReworkResults());
		setValue(sheet, 15, 3, bean.getPrimaryCause());
		setValue(sheet, 16, 3, bean.getPreliminaryLeakageCauses());
		setValue(sheet, 17, 3, bean.getPreliminaryAnalysisConclusion());
		setValue(sheet, 18, 3, bean.getDefectType());
		String isStop = changeInfo(bean.getIsStop());
		setValue(sheet, 18, 7, isStop);
		setValue(sheet, 19, 3, bean.getStopLineBecause());
		setValue(sheet, 21, 3, bean.getRootCauseDepartment());
		setValue(sheet, 22, 3, bean.getRootCauseAnalyst());
		setValue(sheet, 23, 3, bean.getRootCause());
		setValue(sheet, 24, 3, bean.getRootCausesLeakOut());
		setValue(sheet, 25, 3, bean.getFundamentalAnalysisConclusion());
		setValue(sheet, 27, 3, bean.getLongTermTreatment1());
		setValue(sheet, 28, 3, bean.getLongTermTreatment2());
		setValue(sheet, 29, 3, bean.getLongTermTreatment3());
		setValue(sheet, 30, 3, bean.getLongTermTreatment4());
		setValue(sheet, 31, 3, bean.getLongTermTreatment5());
		setValue(sheet, 33, 3, bean.getEffectVerificationResults());
		setValue(sheet, 35, 3, bean.getLongTermTreatment1());
		setValue(sheet, 36, 3, bean.getLongTermTreatment2());
		setValue(sheet, 37, 3, bean.getLongTermTreatment3());
		setValue(sheet, 38, 3, bean.getLongTermTreatment4());
		setValue(sheet, 39, 3, bean.getLongTermTreatment5());
		setValue(sheet, 40, 3, bean.getRootCauseTime());
		setValue(sheet, 41, 3, bean.getRootCauseAnalyst());
		setValue(sheet, 42, 3, bean.getEffectReviewTime());
		setValue(sheet, 44, 3, bean.getCaseClosingOpinion());
		setValue(sheet, 45, 3, bean.getCaseClosingApprover());
		wb.write(response.getOutputStream());// 保存Excel文件
	}

	/**
	 * @Author yuyangyang
	 * @Description 8D报告的数据写入
	 * @Date 2020/8/7 17:31
	 * @Param
	 * @return
	 */
	public static void setValue(XSSFSheet sheet, int rowNum, int cellNum, Object value) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell cell = row.getCell(cellNum);
		if (value != null) {
			if (value instanceof Date) {
				cell.setCellValue(sdf.format(value));
			} else {
				cell.setCellValue(value.toString());
			}
		}
	}

	public static String changeInfo(String temp) {
		String flag = "是";
		if ("N".equals(temp)) {
			flag = "否";
		}
		return flag;
	}

}
