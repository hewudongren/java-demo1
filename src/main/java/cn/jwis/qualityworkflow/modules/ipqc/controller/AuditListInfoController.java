package cn.jwis.qualityworkflow.modules.ipqc.controller;

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
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditListInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryListInfoBean;
import cn.jwis.qualityworkflow.modules.ipqc.service.AuditListInfoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (value = "/auditListInfo")
public class AuditListInfoController {


    @Autowired
    AuditListInfoService auditListInfoService;

    /**
     * @description: 获取IPQC稽核清单列表
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param: bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getAuditListInfo")
    @ApiOperation (response = ResultBean.class, value = "获取IPQC稽核清单列表", notes = "获取IPQC稽核清单列表")
    public ResultBean getAuditListInfo(@RequestBody QueryListInfoBean bean) {
        ResultBean resultBean = new ResultBean();
        Integer pageNum = bean.getPageNum();
        Integer pageSize = bean.getPageSize();
        List<AuditListInfo> data = auditListInfoService.getAuditListInfo(bean);
        Long count = auditListInfoService.getAuditListCount(bean);
        resultBean = ResultBean.pagination(count, pageNum, pageSize, data);
        return resultBean;
    }

    /**
     * @description: 新增稽核清单
     * @author: jiazheng
     * @date: 2020/6/15 17:23
     * @param: bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/insertAuditListInfo")
    @ApiOperation (response = ResultBean.class, value = "新增稽核清单", notes = "新增稽核清单")
    public ResultBean insertAuditListInfo(@RequestBody AuditListInfo bean) {
        ResultBean resultBean = new ResultBean();
        int i = auditListInfoService.insertAuditListInfo(bean);
        if(i!=0){
            resultBean.setCode(i);
            resultBean.setMessage("当天对应班次,机型,工程段,线体已创建单据");
        }
        return resultBean;
    }

    /**
     * @description: 导出稽核清单
     * @author: jiazheng
     * @date: 2020/6/17
     * @param bean:
     * @return:
     **/
    @RequestMapping(value = "/exportAuditListInfo", method = RequestMethod.POST)
    @ApiOperation(value = "导出稽核清单", notes = "导出稽核清单")
    public void exportAuditListInfo(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryListInfoBean bean) {
        auditListInfoService.exportAuditListInfo(response, request, bean);
    }

    /**
     * @description: 获取稽核清单ById
     * @author: jiazheng
     * @date: 2020/6/20 11:02
     * @param: JSONObject:
     * @return: JSONObject
     **/
    @PostMapping (value = "/getAuditListInfoById")
    @ApiOperation (response = ResultBean.class, value = "获取稽核清单ById", notes = "获取稽核清单ById")
    public ResultBean getAuditListInfoById(@RequestBody JSONObject  jsonObject) {
        ResultBean resultBean = new ResultBean();
        JSONObject object = auditListInfoService.getAuditListInfoById(jsonObject);
        resultBean.setData(object);
        return resultBean;
    }
/**
     * @description: 查询保存
     * @author: jiazheng
     * @date: 2020/6/19
     * @param:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping (value = "/getAuditListInfoSave", method = RequestMethod.GET)
    @ApiOperation (value = "查询保存 ", notes = "查询保存")
    public ResultBean getAuditListInfoSave() throws Exception {
        ResultBean resultBean = new ResultBean();
        JSONObject object =auditListInfoService.getAuditAddListSave();
        resultBean.setData(object);
        return resultBean;
    }

/**
     * @description: 保存稽核清单
     * @author: jiazheng
     * @date: 2020/6/15 17:23
     * @param: bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/

 /*   @PostMapping (value = "/insertAuditListContent")
    @ApiOperation (response = ResultBean.class, value = "保存稽核清单", notes = "保存稽核清单")
    public ResultBean insertAuditListContent(@RequestBody AuditListInfo bean) {
        ResultBean resultBean = new ResultBean();
        auditListInfoService.insertAuditListContent(bean);
        return resultBean;
    }*/




}
