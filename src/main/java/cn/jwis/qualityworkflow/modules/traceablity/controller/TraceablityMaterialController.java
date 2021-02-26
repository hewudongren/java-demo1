package cn.jwis.qualityworkflow.modules.traceablity.controller;


import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceablityMaterialVo;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceablityMaterialService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping (value = "/traceabilityMaterial")
public class TraceablityMaterialController {
    @Autowired
    TraceablityMaterialService traceablityMaterialService;


    /**
     * @description: 获取TraceabilityMaterial列表
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getTraceabilityMaterial")
    @ApiOperation (response = ResultBean.class, value = "获取TraceabilityMaterial列表", notes = "获取TraceabilityMaterial列表")
    public ResultBean getTraceabilityMaterial(@RequestBody QueryTraceablityMaterialVo bean) throws  Exception{
        ResultBean resultBean = new ResultBean();
        JSONObject data = traceablityMaterialService.getTraceabilityMaterial(bean);
        resultBean.setData(data);
        return resultBean;
    }


    /**
     * @description: 导出TraceabilityMaterial查询数据
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param bean:
     * @return:
     **/
    @RequestMapping(value = "/exportTraceabilityMaterial", method = RequestMethod.POST)
    @ApiOperation(value = "导出TraceabilityMaterial查询数据", notes = "导出TraceabilityMaterial查询数据")
    public void exportTraceabilityMaterial(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryTraceablityMaterialVo bean) throws  Exception{
        traceablityMaterialService.exportTraceabilityMaterial(response, request, bean);
    }
    /**
     * @description: 查询TraceabilityMaterial下拉列表
     * @author: jiazheng
     * @date: 2020/7/11
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/getMaterialPullDownInfo", method = RequestMethod.POST)
    @ApiOperation(value = "查询TraceabilityMaterial下拉列表", notes = "查询TraceabilityMaterial下拉列表")
    public ResultBean getMaterialPullDownInfo(@RequestBody QueryTraceablityMaterialVo bean) throws  Exception{
        ResultBean resultBean = new ResultBean();
        JSONObject data = traceablityMaterialService.getMaterialPullDownInfo(bean);
        resultBean.setData(data);
        return resultBean;
    }
}
