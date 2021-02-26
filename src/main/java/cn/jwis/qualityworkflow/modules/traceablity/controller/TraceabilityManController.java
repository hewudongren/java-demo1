package cn.jwis.qualityworkflow.modules.traceablity.controller;


import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityManVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMan;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceabilityManService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping (value = "/traceabilityMan")
public class TraceabilityManController {

    @Autowired
    TraceabilityManService traceabilityManService;

/**
 * @description: 获取TraceabilityMan列表
 * @author: jiazheng
 * @date: 2020/5/6 11:02
 * @param bean:
 * @return: cn.jwis.qualityworkflow.bean.ResultBean
 **/
    @PostMapping (value = "/getTraceabilityMan")
    @ApiOperation (response = ResultBean.class, value = "获取TraceabilityMan列表", notes = "获取TraceabilityMan列表")
    public ResultBean getTraceabilityMan(@RequestBody QueryTraceabilityManVo bean) {
        ResultBean resultBean = new ResultBean();
        List<TraceabilityMan> data = traceabilityManService.getTraceabilityManList(bean);
        Long count = traceabilityManService.getTraceabilityManListCount(bean);
        resultBean = ResultBean.pagination(count, bean.getPageNum(),bean.getPageSize(), data);
        return resultBean;
    }


   /**
    * @description: 导出TraceabilityMan查询数据
    * @author: jiazheng
    * @date: 2020/5/6 11:02
    * @param bean:
    * @return:
    **/
    @RequestMapping(value = "/exportTraceabilityMan", method = RequestMethod.POST)
    @ApiOperation(value = "导出TraceabilityMan查询数据", notes = "导出TraceabilityMan查询数据")
    public void exportTraceablityMan(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryTraceabilityManVo bean) {
        traceabilityManService.exportTraceabilityMan(response, request, bean);
    }


    /**
     * @description: 查询TraceabilityMan下拉列表
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/getTraManPullDownInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询TraceabilityMan下拉列表", notes = "查询TraceabilityMan下拉列表")
    public ResultBean getTraManPullDownInfo() {
        ResultBean resultBean = new ResultBean();
        JSONObject data = traceabilityManService.getTraManPullDownInfo();
        resultBean.setData(data);
        return resultBean;
    }

}
