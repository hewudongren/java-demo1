package cn.jwis.qualityworkflow.modules.pcn.service;

import cn.jwis.qualityworkflow.modules.pcn.bean.PCNDashboardVo;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNListBean;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @param bean
 * @author jiazheng
 * @Description: 123
 * @date 2020/5/20
 * @return
 */
public interface PCNDashBoardService {

    Map<String, Object> getPCNDashBoard(PCNDashboardVo bean)throws Exception;

    List getPCNDashBoardCount(PCNDashboardVo bean)throws Exception ;
}
