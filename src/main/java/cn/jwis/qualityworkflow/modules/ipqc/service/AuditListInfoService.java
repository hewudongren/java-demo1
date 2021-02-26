package cn.jwis.qualityworkflow.modules.ipqc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditListInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryListInfoBean;

public interface AuditListInfoService {
    List<AuditListInfo> getAuditListInfo(QueryListInfoBean bean);

    Long getAuditListCount(QueryListInfoBean bean);

    int insertAuditListInfo(AuditListInfo bean);

    void exportAuditListInfo(HttpServletResponse response, HttpServletRequest request, QueryListInfoBean bean);

    JSONObject getAuditAddListSave();

    JSONObject getAuditListInfoById(JSONObject jsonObject);

}
