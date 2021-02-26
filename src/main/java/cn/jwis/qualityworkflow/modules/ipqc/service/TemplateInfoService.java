package cn.jwis.qualityworkflow.modules.ipqc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditListInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditTemplateDetail;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditTemplateInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryTemplateInfoBean;

public interface TemplateInfoService {
    List<AuditTemplateInfo> getAuditTemplateList(QueryTemplateInfoBean bean);

    Long getAuditTemplateCount(QueryTemplateInfoBean bean);

    void insertAuditTemplateList(AuditTemplateInfo bean);

    JSONObject getAuditTemplateInfoById(JSONObject  jsonObject);

    void deleteAuditTemplateInfoById(JSONObject  jsonObject);

    void exportAuditTemplateInfo(HttpServletResponse response, HttpServletRequest request, QueryTemplateInfoBean bean);

    JSONObject getPullDownList();

    void downLoadAuditTemplate(HttpServletResponse response, HttpServletRequest request, List<AuditTemplateDetail> list);

    JSONObject getTemplateNameIdList(JSONObject jsonObject);


    List<JSONObject> importAuditTemplateList(MultipartFile file, HttpServletRequest request)throws Exception;

    List<AuditListInfo> getListInfo(JSONObject jsonObject);
}
