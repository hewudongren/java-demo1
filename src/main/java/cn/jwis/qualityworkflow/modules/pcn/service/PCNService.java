package cn.jwis.qualityworkflow.modules.pcn.service;


import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNInfoBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNListBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.QueryPCNInfo;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface PCNService {



     void saveFCNInfo(PCNInfoBean pcnInfoBean) throws Exception;;

     void confirm(PCNInfoBean pcnInfoBean) throws Exception;

     List<PCNListBean> getPCNList(QueryPCNInfo bean);

     Long getPCNListCount(QueryPCNInfo bean);

    Map<String, Object> getPCNDetailednessInfo(QueryDetailedInfoVo bean)throws Exception;

    void exportPCNData(HttpServletResponse response, HttpServletRequest request, QueryPCNInfo bean);

    List<String> getChangeNameList();

    JSONObject getSuperiorByUser();
}
