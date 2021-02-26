package cn.jwis.qualityworkflow.modules.ipqc.service.imp;

import static cn.jwis.qualityworkflow.bean.BaseClass.subZeroAndDot;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.PatrolProblemRecordInfoDbCamel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.PatrolProblemRecordInfoExcel;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.PatrolProblemRecordInfoTable;
import static org.apache.poi.common.usermodel.HyperlinkType.URL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.ipqc.bean.PatrolProblemRecord;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryPatrolProblemRecord;
import cn.jwis.qualityworkflow.modules.ipqc.dao.AuditListInfoMapper;
import cn.jwis.qualityworkflow.modules.ipqc.dao.PatrolProblemRecordMapper;
import cn.jwis.qualityworkflow.modules.ipqc.service.PatrolProblemRecordService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;

@Service
public class PatrolProblemRecordServiceImpl implements PatrolProblemRecordService {

    @Autowired
    PatrolProblemRecordMapper patrolProblemRecordMapper;

    @Autowired
    AuditListInfoMapper auditListInfoMapper;

    @Autowired
    IDGeneratorRunner idGeneratorRunner;

    @Value ("${dfs.download.url}")
    private String dfsUrl;

    private static SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public List<PatrolProblemRecord> getPatrolProblemRecordList(QueryPatrolProblemRecord bean){
        bean.setAssignee(getCurrentUserName());
        List<PatrolProblemRecord> resultList = null;
        if (bean.getPageNum() != null && bean.getPageSize() != null) {
            bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
        }
        resultList = patrolProblemRecordMapper.getPatrolProblemRecordList(bean);
        return resultList;
    }

    @Override
    public Long getPatrolProblemRecordCount(QueryPatrolProblemRecord bean) {
        bean.setAssignee(getCurrentUserName());
        Long count=  patrolProblemRecordMapper.getPatrolProblemRecordCount(bean);
        return count;
    }

    @Override
    @Transactional (rollbackFor = Exception.class)
    public JSONObject insertPatrolProblemRecord(PatrolProblemRecord bean) {
        JSONObject object= new JSONObject();
        if (bean.getId()!=null && bean.getId()!=""){
            if(bean.getWhetherRepetition()!="" &&  bean.getWhetherRepetition()!=null && "Y".equals(bean.getWhetherRepetition())){
                bean.setCumulativeFrequency(bean.getCumulativeFrequency()+1);
            }else{
                bean.setCumulativeFrequency(1);
            }
            if("close".equals(bean.getStatus()) || "closed".equals(bean.getStatus())){
                bean.setStatusUpdateDate(timeDate.format(new Date()));
            }
            if(bean.getProblemPictureList()!=null){
                bean.setProblemPicture(spliceString(bean.getProblemPictureList()));
            }
            patrolProblemRecordMapper.updatePatrolProblemRecord(bean);
        }else{
            if(bean.getAuditListDetailId()!=null && bean.getAuditListDetailId()!=""){
                bean.setAuditListDetailId(bean.getAuditListDetailId());
            }else{
                bean.setAuditListDetailId(String.valueOf(idGeneratorRunner.nextId()));
            }
            bean.setId(String.valueOf(idGeneratorRunner.nextId()));
            bean.setAuditDate(timeDateFormat.format(new Date()));
            List<String> problemPictureList = bean.getProblemPictureList();
            bean.setProblemPicture(spliceString(problemPictureList));
            bean.setCreateDate(new Date());
            bean.setStatus("open");
            bean.setCumulativeFrequency(1);
            patrolProblemRecordMapper.insertPatrolProblemRecord(bean);
            object.put("auditListDetailId",bean.getAuditListDetailId());
        }
        return object;

    }

    @Override
    public void exportPatrolProblemRecord(HttpServletResponse response, HttpServletRequest request, QueryPatrolProblemRecord bean) {
        List<PatrolProblemRecord> list = patrolProblemRecordMapper.exportPatrolProblemRecord(bean);
        // 将List<AuditListInfo>  转换为   List<JSONObject>
        List<JSONObject> jsonObjects = new ArrayList<>(list.size());
        list.stream().forEach(d -> {
            JSONObject object = JSONObjectUtil.toJSONObject(d);
            jsonObjects.add(object);
        });
        String[] title = PatrolProblemRecordInfoExcel;
        String language = request.getHeader("Language");
        if (("en-US").equals(language)) {
            title = PatrolProblemRecordInfoDbCamel;
        }
        Workbook workbook =exporSimple(jsonObjects, title, PatrolProblemRecordInfoDbCamel);
        ExcelUtil.outputWorkbook(response, workbook, PatrolProblemRecordInfoTable);
    }

    @Override
    public JSONObject getPatrolProblemRecordPullDown() {
        List<PatrolProblemRecord> list =patrolProblemRecordMapper.getPatrolProblemRecordPullDown();
        JSONObject jsonObject=new JSONObject();
        List<String> list1=new ArrayList<String>();
        if (list.size()>0) {
            for (int i = 0; i < list.size(); i++) {
                PatrolProblemRecord patrolProblemRecord = list.get(i);
                if (patrolProblemRecord != null) {
                    if (patrolProblemRecord.getInspector() != null && patrolProblemRecord.getInspector() != "" &&  !list1.contains(patrolProblemRecord.getInspector())) {
                        list1.add(patrolProblemRecord.getInspector());
                    }
                } else {
                    continue;
                }
            }
        }
        jsonObject.put("inspectorList", list1);
        return jsonObject;
    }

    @Override
    public JSONObject getProblemDescription(JSONObject jsonObject) {
         List<PatrolProblemRecord> list= patrolProblemRecordMapper.getProblemDescription(jsonObject);
         JSONObject json=new JSONObject();
         json.put("problemDescriptionList",list);
         return json;
    }


    private String getCurrentUserName() {
        UserInfo currentUser = SessionHelper.getCurrentUser();
        String currentUserName = currentUser.getAccount();
        return currentUserName;
    }

    private String spliceString(List<String> list){
        String string="";
        int flag=0;
        for (int i = 0; i <list.size() ; i++) {
            if(flag>0){
                string+=",";
            }
            string+=list.get(i);
            flag++;
        }

        return  string;
    }


    /**
     * 简单导出(Excel)(xlsx格式)
     */
    public Workbook exporSimple(List<JSONObject> list, String[] title, String[] strings) {
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
        CreationHelper createHelper = workbook.getCreationHelper();
        if (list != null && size > 0) {
            for (int i = 0; i < size; i++) {
                JSONObject temp = list.get(i);
                SXSSFRow row = sheet.createRow(i + 1);
                for (int j = 0; j < length; j++) {
                    Cell cell = row.createCell(j);
                    String key = strings[j];
                    String subZeroAndDot = subZeroAndDot(JSONObjectUtil.getValue(temp, key));
                    if (StringUtils.isNotBlank(subZeroAndDot) && "problemPicture".equals(key)) {
                        String[] split = subZeroAndDot.split("#");
                        String fileOid = split[0];
                        String fileName = split[2];
                        String url = dfsUrl + fileOid;
                        XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(URL);
                        link.setAddress(url);
                        cell.setHyperlink(link);
                        subZeroAndDot = fileName;
                    }
                    cell.setCellValue(subZeroAndDot);
                }
            }
        }
        return workbook;
    }


}
