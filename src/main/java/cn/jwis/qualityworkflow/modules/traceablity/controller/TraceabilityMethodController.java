package cn.jwis.qualityworkflow.modules.traceablity.controller;


import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityMethodVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMethod;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceabilityMethodService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping (value = "/traceabilityMethod")
public class TraceabilityMethodController {

    @Autowired
    TraceabilityMethodService traceabilityMethodService;


    /**
     * @description: 获取TraceabilityMethod列表
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getTraceabilityMethod")
    @ApiOperation (response = ResultBean.class, value = "获取TraceabilityMethod列表", notes = "获取TraceabilityMethod列表")
    public ResultBean getTraceabilityMethod(@RequestBody QueryTraceabilityMethodVo bean) {
        ResultBean resultBean = new ResultBean();
        List<TraceabilityMethod> data = traceabilityMethodService.getTraceabilityMethodList(bean);
        Long count = traceabilityMethodService.getTraceabilityMethodCount(bean);
        resultBean = ResultBean.pagination(count, bean.getPageNum(),bean.getPageSize(), data);
        return resultBean;
    }


    /**
     * @description: 导出TraceabilityMethod查询数据
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param: bean
     * @return:
     **/
    @RequestMapping(value = "/exportTraceabilityMethod", method = RequestMethod.POST)
    @ApiOperation(value = "导出TraceabilityMethod查询数据", notes = "导出TraceabilityMethod查询数据")
    public void exportTraceabilityMethod(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryTraceabilityMethodVo bean) {
        traceabilityMethodService.exportTraceabilityMethod(response, request, bean);
    }
/**
 * @description: 查询TraceabilityMethod下拉列表
 * @author: jiazheng
 * @date: 2020/5/6 11:02
 * @param: bean
 * @return: cn.jwis.qualityworkflow.bean.ResultBean
 **/
    @RequestMapping(value = "/getTraMethodPullDownInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询TraceabilityMethod下拉列表", notes = "查询TraceabilityMethod下拉列表")
    public ResultBean getTraMethodPullDownInfo() {
        ResultBean resultBean = new ResultBean();
        JSONObject data = traceabilityMethodService.getTraMethodPullDownInfo();
        resultBean.setData(data);
        return resultBean;
    }
}
