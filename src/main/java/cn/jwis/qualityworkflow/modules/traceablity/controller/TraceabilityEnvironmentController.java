package cn.jwis.qualityworkflow.modules.traceablity.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityEnvironmentVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityEnvironmentConfiguration;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityEnvironmentData;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceabilityEnvironmentService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (value = "/traceabilityEnvironment")
public class TraceabilityEnvironmentController {


    @Autowired
    TraceabilityEnvironmentService traceabilityEnvironmentService;


    /**
     * @description: 获取TraceabilityEnvironment列表
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getTraceabilityEnvironmentData")
    @ApiOperation (response = ResultBean.class, value = "获取TraceabilityMethod列表", notes = "获取TraceabilityMethod列表")
    public ResultBean getTraceabilityEnvironmentData(@RequestBody QueryTraceabilityEnvironmentVo bean) throws Exception{
        ResultBean resultBean = new ResultBean();
        JSONObject data = traceabilityEnvironmentService.getTraceabilityEnvironmentData(bean);
        resultBean.setData(data);
        return resultBean;
    }
    /**
     * @description: 新增TraceabilityEnvironmentDate
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/insertTraceabilityEnvironment")
    @ApiOperation (response = ResultBean.class, value = "新增TraceabilityEnvironmentDate", notes = "新增TraceabilityEnvironmentDate")
    public ResultBean insertTraceabilityEnvironment(@RequestBody TraceabilityEnvironmentData bean) {
        ResultBean resultBean = new ResultBean();
         traceabilityEnvironmentService.insertTraceabilityEnvironment(bean);
        return resultBean;
    }

    /**
     * @description: 导出TraceabilityEnvironmentData数据
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param bean:
     * @return:
     **/
    @RequestMapping(value = "/exportTraceabilityEnvironmentData", method = RequestMethod.POST)
    @ApiOperation(value = "导出TraceabilityEnvironmentData数据", notes = "导出TraceabilityEnvironmentData数据")
    public void exportTraceablityMan(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryTraceabilityEnvironmentVo bean) {
        traceabilityEnvironmentService.exportTraceabilityEnvironmentData(response, request, bean);
    }
    /**
     * @description: 查询TraceabilityEnvironment下拉列表
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/getTraEnvironmentPullDownInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询TraceabilityEnvironment下拉列表", notes = "查询TraceabilityEnvironment下拉列表")
    public ResultBean getTraEnvironmentPullDownInfo() {
        ResultBean resultBean = new ResultBean();
        JSONObject data = traceabilityEnvironmentService.getTraEnvironmentPullDownInfo();
        resultBean.setData(data);
        return resultBean;
    }

    /**
     * @description: 根据线体查询探头编号
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getProbeNumber")
    @ApiOperation (response = ResultBean.class, value = "根据线体查询探头编号", notes = "根据线体查询探头编号")
    public ResultBean getProbeNumber(@RequestBody JSONObject jsonObject) {
        ResultBean resultBean = new ResultBean();
        List<String> list=traceabilityEnvironmentService.getProbeNumber(jsonObject);
        resultBean.setData(list);
        return resultBean;
    }

    /**
     * @description: 获取环境配置列表列表
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getEnvConfiguration")
    @ApiOperation (response = ResultBean.class, value = "获取环境配置列表列表", notes = "获取环境配置列表列表")
    public ResultBean getEnvConfiguration(@RequestBody QueryTraceabilityEnvironmentVo bean) {
        ResultBean resultBean = new ResultBean();
        List<TraceabilityEnvironmentConfiguration> data = traceabilityEnvironmentService.getEnvConfiguration(bean);
        Long count = traceabilityEnvironmentService.getEnvConfigurationCount(bean);
        resultBean = ResultBean.pagination(count, bean.getPageNum(),bean.getPageSize(), data);
        return resultBean;
    }
    /**
     * @description: 新增修改环境配置列表列表
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/insertEnvConfiguration")
    @ApiOperation (response = ResultBean.class, value = "新增修改环境配置列表列表", notes = "新增修改环境配置列表列表")
    public ResultBean insertEnvConfiguration(@RequestBody TraceabilityEnvironmentConfiguration bean) {
        ResultBean resultBean = new ResultBean();
        int code = traceabilityEnvironmentService.insertEnvConfiguration(bean);
        if(code!=0){
            resultBean.setCode(-1);
            resultBean.setMessage("配置重复");
        }
        return resultBean;
    }
    /**
     * @description: 查询环境配置ById
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getEnvConfigurationById")
    @ApiOperation (response = ResultBean.class, value = "查询环境配置ById", notes = "查询环境配置ById")
    public ResultBean getEnvConfigurationById(@RequestBody JSONObject jsonObject) {
        ResultBean resultBean = new ResultBean();
        JSONObject object=traceabilityEnvironmentService.getEnvConfigurationById(jsonObject);
        resultBean.setData(object);
        return resultBean;
    }
    /**
     * @description: 删除环境配置
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/deleteEnvConfiguration")
    @ApiOperation (response = ResultBean.class, value = "删除环境配置", notes = "删除环境配置")
    public ResultBean deleteEnvConfiguration(@RequestBody JSONObject jsonObject) {
        ResultBean resultBean = new ResultBean();
        traceabilityEnvironmentService.deleteEnvConfiguration(jsonObject);
        return resultBean;
    }

    /**
     * @description: 查询环境配置下拉列表
     * @author: jiazheng
     * @date: 2020/7/11
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/getEnvConfigurationPullDownInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询环境配置下拉列表", notes = "查询环境配置下拉列表")
    public ResultBean getEnvConfigurationPullDownInfo() {
        ResultBean resultBean = new ResultBean();
        JSONObject data = traceabilityEnvironmentService.getEnvConfigurationPullDownInfo();
        resultBean.setData(data);
        return resultBean;
    }
}
