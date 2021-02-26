package cn.jwis.qualityworkflow.modules.ipqc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryDashboardVo;
import cn.jwis.qualityworkflow.modules.ipqc.service.IPQCDashBoardService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (value = "/IPQCDashBoard")
public class IPQCDashBoardController {

    @Autowired
    IPQCDashBoardService ipqcDashBoardService;
    /**
     * @param bean:
     * @description: 查询DashBoard
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getIPQCDashBoard")
    @ApiOperation (value = "查询DashBoard", notes = "查询DashBoard")
    public ResultBean getIPQCDashBoard(@RequestBody QueryDashboardVo bean) throws Exception {
        ResultBean resultBean = new ResultBean();
        Map<String, Object> tendencyChartDataMap = ipqcDashBoardService.getIPQCDashBoard(bean);
        resultBean.setData(tendencyChartDataMap);
        return resultBean;
    }
    /**
     * @param bean:
     * @description: 获取不同Item的DashBoard
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping (value = "/getIPQCDashBoardCount", method = RequestMethod.POST)
    @ApiOperation (response = ResultBean.class, value = "获取不同Item的DashBoard", notes = "获取不同Item的DashBoard")
    public ResultBean getIPQCDashBoardCount(@RequestBody QueryDashboardVo bean) throws Exception {
        ResultBean resultBean = new ResultBean();
        List tendencyChartDataMap = ipqcDashBoardService.getIPQCDashBoardCount(bean);
        resultBean.setData(tendencyChartDataMap);
        return resultBean;
    }
    /**
     * @param bean:
     * @description: 查询稽核问题关闭率
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getAuditIssueDashBoard")
    @ApiOperation (value = "查询DashBoard", notes = "查询DashBoard")
    public ResultBean getAuditIssueDashBoard(@RequestBody QueryDashboardVo bean) throws Exception {
        ResultBean resultBean = new ResultBean();
        List<Map<String, Object>> jsonObject = ipqcDashBoardService.getAuditIssueDashBoard(bean);
        resultBean.setData(jsonObject);
        return resultBean;
    }
    /**
     * @param bean:
     * @description: 查询稽核问题平台,巡检问题班长分布
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getPatrolProblemRecordDashBoard")
    @ApiOperation (value = "查询DashBoard", notes = "查询DashBoard")
    public ResultBean getPatrolProblemRecordDashBoard(@RequestBody QueryDashboardVo bean) throws Exception {
        ResultBean resultBean = new ResultBean();
        JSONObject jsonObject = ipqcDashBoardService.getPatrolProblemRecordDashBoard(bean);
        resultBean.setData(jsonObject);
        return resultBean;
    }
}
