package cn.jwis.qualityworkflow.modules.ipqc.controller;


import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.ipqc.bean.*;
import cn.jwis.qualityworkflow.modules.ipqc.service.PatrolProblemRecordService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping (value = "/patrolProblemRecord")
public class PatrolProblemRecordController {

    @Autowired
    PatrolProblemRecordService patrolProblemRecordService;

    /**
     * @description: 获取巡检问题记录列表
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param: bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getPatrolProblemRecordList")
    @ApiOperation (response = ResultBean.class, value = "获取巡检问题记录列表", notes = "获取巡检问题记录列表")
    public ResultBean getPatrolProblemRecordList(@RequestBody QueryPatrolProblemRecord bean) {
        ResultBean resultBean = new ResultBean();
        List<PatrolProblemRecord> data = patrolProblemRecordService.getPatrolProblemRecordList(bean);
        Long count = patrolProblemRecordService.getPatrolProblemRecordCount(bean);
        resultBean = ResultBean.pagination(count, bean.getPageNum(), bean.getPageSize(), data);
        return resultBean;
    }

    /**
     * @description: 新增巡检问题
     * @author: jiazheng
     * @date: 2020/6/15 17:23
     * @param: bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/insertPatrolProblemRecord")
    @ApiOperation (response = ResultBean.class, value = "新增巡检问题", notes = "新增巡检问题")
    public ResultBean insertPatrolProblemRecord(@RequestBody PatrolProblemRecord bean) {
        ResultBean resultBean = new ResultBean();
        JSONObject jsonObject = patrolProblemRecordService.insertPatrolProblemRecord(bean);
        resultBean.setData(jsonObject);
        return resultBean;
    }
    /**
     * @description: 导出巡检记录
     * @author: jiazheng
     * @date: 2020/6/17
     * @param bean:
     * @return:
     **/
    @RequestMapping(value = "/exportPatrolProblemRecord", method = RequestMethod.POST)
    @ApiOperation(value = "导出稽核清单", notes = "导出稽核清单")
    public void exportPatrolProblemRecord(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryPatrolProblemRecord bean) {
        patrolProblemRecordService.exportPatrolProblemRecord(response, request, bean);
    }
    /**
     * @description: 查询稽核单据下拉列表
     * @author: jiazheng
     * @date: 2020/6/19
     * @param:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping (value = "/getPatrolProblemRecordPullDown", method = RequestMethod.GET)
    @ApiOperation (value = "查询稽核单据下拉列表 ", notes = "查询稽核单据下拉列表")
    public ResultBean getPatrolProblemRecordPullDown() throws Exception {
        JSONObject jsonObject =patrolProblemRecordService.getPatrolProblemRecordPullDown();
        return ResultBean.success(jsonObject);
    }



    /**
     * @description: 根据班次和日期查询问题描述
     * @author: jiazheng
     * @date: 2020/6/20 11:02
     * @param: JSONObject:
     * @return: JSONObject
     **/
    @PostMapping (value = "/getProblemDescription")
    @ApiOperation (response = ResultBean.class, value = "根据班次和日期查询问题描述", notes = "根据班次和日期查询问题描述")
    public ResultBean getAuditListInfoById(@RequestBody JSONObject  jsonObject) {
        ResultBean resultBean = new ResultBean();
        JSONObject object = patrolProblemRecordService.getProblemDescription(jsonObject);
        resultBean.setData(object);
        return resultBean;
    }
}
