package cn.jwis.qualityworkflow.modules.ipqc.service.imp;

import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.ipqc.bean.*;
import cn.jwis.qualityworkflow.modules.ipqc.dao.AuditListInfoMapper;
import cn.jwis.qualityworkflow.modules.ipqc.dao.AuditTemplateInfoMapper;
import cn.jwis.qualityworkflow.modules.ipqc.service.AuditListInfoService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import cn.jwis.qualityworkflow.util.UserServer;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.*;

@Service
public class AuditListInfoServiceImpl implements AuditListInfoService {

    @Autowired
    AuditListInfoMapper auditListInfoMapper;

    @Autowired
    AuditTemplateInfoMapper auditTemplateInfoMapper;


    @Autowired
    IDGeneratorRunner idGeneratorRunner;

    @Autowired
    UserServer userServer;

    private static SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<AuditListInfo> getAuditListInfo(QueryListInfoBean bean) {
        if (bean.getPageNum() != null && bean.getPageSize() != null) {
            bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
        }
        String userItemInfos = userServer.getUserItemInfos("IPQC-业务管理员");
        bean.setAssignee(userItemInfos);
        List<AuditListInfo> resultList = auditListInfoMapper.getAuditListInfo(bean);
        return resultList;
    }

    @Override
    public Long getAuditListCount(QueryListInfoBean bean) {
        String userItemInfos = userServer.getUserItemInfos("IPQC-业务管理员");
        bean.setAssignee(userItemInfos);
        Long count=  auditListInfoMapper.getAuditListCount(bean);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAuditListInfo(AuditListInfo bean) {
        int code=0;
        if (bean.getId() != null && bean.getId() != "") {
            AuditListSave listSave=auditListInfoMapper.getUpDateSave(bean.getId());
            if(listSave!=null){
                auditListInfoMapper.deleteAuditListSave(listSave.getId());
            }
            if ("SAVE".equals(bean.getStatus())){
                AuditListSave auditListSave=new AuditListSave();
                auditListSave.setStatus("UpdateSave");
                auditListSave.setAuditListId(bean.getId());
                auditListSave.setId(String.valueOf(idGeneratorRunner.nextId()));
                bean.setUpdatePerson(getCurrentUserName());
                Object object=(Object)bean;
                auditListSave.setContent(JSONObjectUtil.toJSONObject(object).toJSONString());
                auditListInfoMapper.insertAuditListSave(auditListSave);
            }else{
                String creator=getCurrentUserName();
                AuditListSave auditListSave=auditListInfoMapper.getAuditListUpdateSave(creator,bean.getId());
                if(auditListSave!=null){
                    auditListInfoMapper.deleteAuditListSave(auditListSave.getId());
                }
                bean.setUpdatePerson(getCurrentUserName());
                auditListInfoMapper.updateAuditListInfo(bean);
            }

        }else{
                String creator=getCurrentUserName();
                List<AuditListSave> auditListSave=auditListInfoMapper.getAuditListSave(creator);
                if(auditListSave.size()>0){
                    for (int i = 0; i <auditListSave.size() ; i++) {
                        AuditListSave auditList =auditListSave.get(i);
                        auditListInfoMapper.deleteAuditListSave(auditList.getId());
                        
                    }
                }
                if ("SAVE".equals(bean.getStatus())){
                    AuditListSave auditListSaveBean=new AuditListSave();
                    auditListSaveBean.setStatus("InsertSave");
                    auditListSaveBean.setId(String.valueOf(idGeneratorRunner.nextId()));
                    auditListSaveBean.setCreator(getCurrentUserName());
                    Object object=(Object)bean;
                    auditListSaveBean.setContent(JSONObjectUtil.toJSONObject(object).toJSONString());
                    auditListInfoMapper.insertAuditListSave(auditListSaveBean);
                }else{
                    if(bean.getLine()=="" || bean.getLine()==null){
                        bean.setLine("");
                    }
                    List<AuditListInfo> auditListInfo=auditListInfoMapper.getAuditListInfoRepetition(bean);
                    if(auditListInfo.size()>0){
                        code=-1;
                    }else {
                        bean.setCreateDate(timeDateFormat.format(new Date()));
                        bean.setCreator(getCurrentUserName());
                        bean.setId(String.valueOf(idGeneratorRunner.nextId()));
                        if (bean.getAuditTemplateId() != "" && bean.getAuditTemplateId() != null) {
                            bean.setAuditTemplateId(bean.getAuditTemplateId());
                        }
                        auditListInfoMapper.insertAuditListInfo(bean);
                    }
                }
        }
            if ("COMMINT".equals(bean.getStatus()) && code==0) {
                    List<AuditListDetail> list = bean.getListDetail();
                    for (int i = 0; i < list.size(); i++) {
                        AuditListDetail auditListDetail = list.get(i);
                        if (auditListDetail.getId() != null && auditListDetail.getId() != "") {
                            int count = 0;
                            auditListDetail.setContent(JSONObject.toJSONString(auditListDetail.getCont()));
                            JSONObject jsonObject = auditListDetail.getCont();
                            Map<String, String> params = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Map<String, String>>() {
                            });
                            for (String key : params.keySet()) {
                                count++;
                            }
                            bean.setTimeInterval(count + "/6");
                            auditListInfoMapper.updateTimeInterval(bean);
                            auditListInfoMapper.updateAuditListDetail(auditListDetail);
                        } else {
                            if (auditListDetail.getAuditListDetailId() != null && auditListDetail.getAuditListDetailId() != "") {
                                auditListDetail.setId(auditListDetail.getAuditListDetailId());
                            } else {
                                auditListDetail.setId(String.valueOf(idGeneratorRunner.nextId()));
                            }
                            auditListDetail.setCreateDate(timeDateFormat.format(new Date()));
                            auditListDetail.setAuditListId(bean.getId());
                            auditListDetail.setContent(JSONObject.toJSONString(auditListDetail.getCont()));
                            int number = 0;
                            JSONObject jsonObject = auditListDetail.getCont();
                            Map<String, String> param = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Map<String, String>>() {
                            });
                            for (String key : param.keySet()) {
                                number++;
                            }
                            if (number == 0) {
                                bean.setTimeInterval("0" + "/6");
                            } else {
                                bean.setTimeInterval(number + "/6");
                            }
                            auditListInfoMapper.updateTimeInterval(bean);
                            auditListInfoMapper.insertAuditListDetail(auditListDetail);
                        }
                    }
                }

            return code;
    }

    @Override
    public void exportAuditListInfo(HttpServletResponse response, HttpServletRequest request, QueryListInfoBean bean) {
        String userItemInfos = userServer.getUserItemInfos("IPQC-业务管理员");
        bean.setAssignee(userItemInfos);
        List<AuditListInfo> list = auditListInfoMapper.exportAuditListInfo(bean);
        // 将List<AuditListInfo>  转换为   List<JSONObject>
        List<JSONObject> jsonObjects = new ArrayList<>(list.size());
        list.stream().forEach(d -> {
            JSONObject object = JSONObjectUtil.toJSONObject(d);
            jsonObjects.add(object);
        });
        String[] title = AuditListInfoExcel;
        String language = request.getHeader("Language");
        if (("en-US").equals(language)) {
            title = AuditListInfoDbCamel;
        }
        Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, AuditListInfoDbCamel);
        ExcelUtil.outputWorkbook(response, workbook, AuditListInfoTable);
    }

    @Override
    public JSONObject getAuditAddListSave() {
        JSONObject jsonObject=null;
        String string=getCurrentUserName();
        String content = auditListInfoMapper.getAuditAddListSave(string);
        if(content!="" && content!=null){
            jsonObject= JSONObject.parseObject(content);
            jsonObject.put("status","SAVE");
        }
        return jsonObject;

    }

    @Override
    public JSONObject getAuditListInfoById(JSONObject jsonObject) {
     AuditListSave auditListSave=  auditListInfoMapper.getAuditListUpdateSaveById(jsonObject);
     JSONObject object=null;
     if(auditListSave!=null){
         object= JSONObject.parseObject(auditListSave.getContent());
         object.put("status","SAVE");
     }else{
         AuditListInfo bean = auditListInfoMapper.getAuditListInfoById(jsonObject);
         Object obj =(Object)bean;
         JSONObject job = JSONObjectUtil.toJSONObject(obj);
         if(job!=null){
             List<AuditListDetail> list=auditTemplateInfoMapper.getListDetail(bean.getId());
             for (int i = 0; i < list.size(); i++) {
                 AuditListDetail auditListDetail=list.get(i);
                 auditListDetail.setCont(JSONObject.parseObject(auditListDetail.getContent()));
             }
             job.put("listDetail",list);
             job.put("status","COMMINT");
         }
         object=job;

     }

        return object;
    }

    private String getCurrentUserName() {
        UserInfo currentUser = SessionHelper.getCurrentUser();
        String currentUserName = currentUser.getAccount();
        return currentUserName;
    }
}
