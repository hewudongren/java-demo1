package cn.jwis.qualityworkflow.modules.traceablity.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityMachineVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMachine;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceabilityMachineService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (value = "/traceabilityMachine")
public class TraceabilityMachineController {

    @Autowired
    TraceabilityMachineService traceabilityMachineService;

    /**
     * @description: 获取TraceabilityMachine列表
     * @author: jiazheng
     * @date: 2020/5/6
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getTraceabilityMachine")
    @ApiOperation (response = ResultBean.class, value = "获取TraceabilityMachine列表", notes = "获取TraceabilityMachine列表")
    public ResultBean getTraceabilityMachine(@RequestBody QueryTraceabilityMachineVo bean) {
        ResultBean resultBean = new ResultBean();
        List<TraceabilityMachine> data = traceabilityMachineService.getTraceabilityMachine(bean);
        Long count = traceabilityMachineService.getTraceabilityMachineCount(bean);
        resultBean = ResultBean.pagination(count, bean.getPageNum(),bean.getPageSize(), data);
        return resultBean;
    }
    /**
     * @description: 新增TraceabilityMachine
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/insertTraceabilityMachine")
    @ApiOperation (response = ResultBean.class, value = "新增TraceabilityMachine", notes = "新增TraceabilityMachine")
    public ResultBean insertTraceabilityMachine(@RequestBody TraceabilityMachine bean) {
        ResultBean resultBean = new ResultBean();
        int code = traceabilityMachineService.insertTraceabilityMachine(bean);
        if(code!=0){
            resultBean.setCode(-1);
            resultBean.setMessage("配置重复");
        }
        return resultBean;
    }
    /**
     * @description: 获取TraceabilityMachineById
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getTraceabilityMachineById")
    @ApiOperation (response = ResultBean.class, value = "获取TraceabilityMachineById", notes = "获取TraceabilityMachineById")
    public ResultBean getTraceabilityMachineById(@RequestBody JSONObject jsonObject) {
        ResultBean resultBean = new ResultBean();
        JSONObject object=traceabilityMachineService.getTraceabilityMachineById(jsonObject);
        resultBean.setData(object);
        return resultBean;
    }

    /**
     * @description: 删除TraceabilityMachine
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/deleteTraceabilityMachine")
    @ApiOperation (response = ResultBean.class, value = "删除TraceabilityMachine", notes = "删除TraceabilityMachine")
    public ResultBean deleteTraceabilityMachine(@RequestBody JSONObject jsonObject) {
        ResultBean resultBean = new ResultBean();
        traceabilityMachineService.deleteTraceabilityMachine(jsonObject);
        return resultBean;
    }
    /**
     * @description: 根据线体和工序查询设备
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getDeviceName")
    @ApiOperation (response = ResultBean.class, value = "根据线体和工序查询设备", notes = "根据线体和工序查询设备")
    public ResultBean getDeviceName(@RequestBody JSONObject jsonObject) {
        ResultBean resultBean = new ResultBean();
        JSONObject deviceName = traceabilityMachineService.getDeviceName(jsonObject);
        resultBean.setData(deviceName);
        return resultBean;
    }
    /**
     * @description: 根据设备类型查line
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: JSONObject
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @GetMapping (value = "/getLineByDeviceType")
    @ApiOperation (response = ResultBean.class, value = "根据设备类型查line", notes = "根据设备类型查line")
    public ResultBean getLineByDeviceType(@RequestParam(value = "type" ) String type) {
        ResultBean resultBean = new ResultBean();
        JSONObject deviceName = traceabilityMachineService.getLineByDeviceType(type);
        resultBean.setData(deviceName);
        return resultBean;
    }
    /**
     * @description: 根据line查设备
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: JSONObject
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @GetMapping (value = "/getDeviceNoByDeviceType")
    @ApiOperation (response = ResultBean.class, value = "根据line查设备", notes = "根据line查设备")
    public ResultBean getDeviceNoByDeviceType(@RequestParam(value = "line" ) String line,@RequestParam(value = "type" ) String type) {
        ResultBean resultBean = new ResultBean();
        JSONObject deviceName = traceabilityMachineService.getDeviceNoByDeviceType(line,type);
        resultBean.setData(deviceName);
        return resultBean;
    }


    /**
     * @description: 查询TraceabilityMachineX-Bar图
     * @author: jiazheng
     * @date: 2020/5/6
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getTraceabilityMachineChart")
    @ApiOperation (response = ResultBean.class, value = "查询TraceabilityMachineX-Bar图", notes = "查询TraceabilityMachineX-Bar图")
    public ResultBean getTraceabilityMachineChart(@RequestBody QueryTraceabilityMachineVo bean) throws  Exception{
        ResultBean resultBean = new ResultBean();
        JSONObject jsonObject= traceabilityMachineService.getTraceabilityMachineChart(bean);
        resultBean.setData(jsonObject);
        return resultBean;
    }
    /**
     * @description: 查询TraceabilityMachine4Me1
     * @author: jiazheng
     * @date: 2020/5/6
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getFourMeMachineChart")
    @ApiOperation (response = ResultBean.class, value = "查询TraceabilityMachine4Me1", notes = "查询TraceabilityMachine4Me1")
    public ResultBean getFourMeMachineChart(@RequestBody QueryTraceabilityMachineVo bean) throws  Exception{
        ResultBean resultBean = new ResultBean();
        JSONObject jsonObject= traceabilityMachineService.getFourMeMachineChart(bean);
        resultBean.setData(jsonObject);
        return resultBean;
    }
    /**
     * @description: 查询设备配置下拉
     * @author: jiazheng
     * @date: 2020/7/11
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/getMachinePullDownInfo", method = RequestMethod.GET)
    @ApiOperation(value = "查询设备配置下拉", notes = "查询设备配置下拉")
    public ResultBean getMachinePullDownInfo() {
        ResultBean resultBean = new ResultBean();
        JSONObject data = traceabilityMachineService.getMachinePullDownInfo();
        resultBean.setData(data);
        return resultBean;
    }
    /**
     * @description: 查询设备类型下拉
     * @author: jiazheng
     * @date: 2020/7/11
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/getMachineDeviceType", method = RequestMethod.GET)
    @ApiOperation(value = "查询设备类型下拉", notes = "查询设备类型下拉")
    public ResultBean getMachineDeviceType() {
        ResultBean resultBean = new ResultBean();
        JSONObject data = traceabilityMachineService.getMachineDeviceType();
        resultBean.setData(data);
        return resultBean;
    }

    /**
     * @description: SMT接口
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/insetTraceabilityMachine")
    @ApiOperation (response = ResultBean.class, value = "SMT接口", notes = "SMT接口")
    public ResultBean insetTraceabilityMachine(@RequestBody JSONObject jsonObject) throws Exception{
        ResultBean resultBean = new ResultBean();
         traceabilityMachineService.insetTraceabilityMachine(jsonObject);
        return resultBean;
    }

}
