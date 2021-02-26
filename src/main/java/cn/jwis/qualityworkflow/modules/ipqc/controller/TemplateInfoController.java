package cn.jwis.qualityworkflow.modules.ipqc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditListInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditTemplateDetail;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditTemplateInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryTemplateInfoBean;
import cn.jwis.qualityworkflow.modules.ipqc.service.TemplateInfoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (value = "/auditTemplate")
public class TemplateInfoController {
    @Autowired
    TemplateInfoService templateInfoService;

/**
 * @description: 获取稽核模板
 * @author: jiazheng
 * @date: 2020/5/6 11:02
 * @param: bean:
 * @return: cn.jwis.qualityworkflow.bean.ResultBean
 **/
    @PostMapping (value = "/getAuditTemplateList")
    @ApiOperation (response = ResultBean.class, value = "获取稽核模板", notes = "获取稽核模板")
    public ResultBean getAuditTemplateList(@RequestBody QueryTemplateInfoBean bean) {
        ResultBean resultBean = new ResultBean();
        List<AuditTemplateInfo> data = templateInfoService.getAuditTemplateList(bean);
        Long count = templateInfoService.getAuditTemplateCount(bean);
        resultBean = ResultBean.pagination(count, bean.getPageNum(), bean.getPageSize(), data);
        return resultBean;
    }
/**
 * @description: 新增稽核模板
 * @author: jiazheng
 * @date: 2020/6/15 17:23
 * @param: bean:
 * @return: cn.jwis.qualityworkflow.bean.ResultBean
 **/
    @PostMapping (value = "/insertAuditTemplateList")
    @ApiOperation (response = ResultBean.class, value = "新增稽核模板", notes = "新增稽核模板")
    public ResultBean insertAuditTemplateList(@RequestBody AuditTemplateInfo bean) {
        ResultBean resultBean = new ResultBean();
        templateInfoService.insertAuditTemplateList(bean);
        return resultBean;
    }
    /**
     * @description: 查询稽核模板ById
     * @author: jiazheng
     * @date: 2020/6/16 11:02
     * @param: JSONObject
     * @return: JSONObject
     **/
    @PostMapping (value = "/getAuditTemplateInfoById")
    @ApiOperation (response = ResultBean.class, value = "查询稽核模板ById", notes = "查询稽核模板ById")
    public ResultBean getAuditTemplateInfoById(@RequestBody JSONObject  jsonObject) {
        ResultBean resultBean = new ResultBean();
        JSONObject object = templateInfoService.getAuditTemplateInfoById(jsonObject);
        resultBean.setData(object);
        return resultBean;
    }

    /**
     * @description: 删除稽核模板ById
     * @author: jiazheng
     * @date: 2020/6/16 15:15
     * @param:: JSONObject:
     * @return: JSONObject
     **/
    @PostMapping (value = "/deleteAuditTemplateInfoById")
    @ApiOperation (response = ResultBean.class, value = "删除稽核模板ById", notes = "删除稽核模板ById")
    public ResultBean deleteAuditTemplateInfoById(@RequestBody JSONObject  jsonObject) {
        ResultBean resultBean = new ResultBean();
        List<AuditListInfo> list= templateInfoService.getListInfo(jsonObject);
        if(list.size()>0){
            resultBean.setCode(-1);
            resultBean.setMessage("删除失败,此模板已创建稽核单据");
        }else{
            templateInfoService.deleteAuditTemplateInfoById(jsonObject);
        }
        return resultBean;
    }
    /**
     * @description: 导出稽核模板
     * @author: jiazheng
     * @date: 2020/6/17
     * @param bean:
     * @return:
     **/
    @RequestMapping(value = "/exportAuditTemplateInfo", method = RequestMethod.POST)
    @ApiOperation(value = "导出稽核模板", notes = "导出稽核模板")
    public void exportAuditTemplateInfo(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryTemplateInfoBean bean) {
        templateInfoService.exportAuditTemplateInfo(response, request, bean);
    }
    /**
     * @description: 查询下拉列表
     * @author: jiazheng
     * @date: 2020/6/19
     * @param:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping (value = "/getPullDownList", method = RequestMethod.GET)
    @ApiOperation (value = "查询下拉列表 ", notes = "查询下拉列表")
    public ResultBean getPullDownList() throws Exception {
        JSONObject jsonObject =templateInfoService.getPullDownList();
            return ResultBean.success(jsonObject);

    }

    /**
     * @description: 稽核模板下载
     * @author: jiazheng
     * @date: 2020/6/17
     * @param: bean
     * @return:
     **/
    @RequestMapping(value = "/downLoadAuditTemplate", method = RequestMethod.POST)
    @ApiOperation(value = "稽核模板下载", notes = "稽核模板下载")
    public void downLoadAuditTemplate(HttpServletResponse response, HttpServletRequest request, @RequestBody List<AuditTemplateDetail> list ) {
        templateInfoService.downLoadAuditTemplate(response, request, list);
    }


    /**
     * @description: 批量新增稽核模板
     * @author: jiazheng
     * @date: 2020/6/15 17:23
     * @param: bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/importAuditTemplateList")
    @ApiOperation (response = ResultBean.class, value = "批量新增稽核模板", notes = "批量新增稽核模板")
    public ResultBean importAuditTemplateList(@RequestParam (value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
        ResultBean resultBean = new ResultBean();
        List<JSONObject> list=templateInfoService.importAuditTemplateList(file, request);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("AuditTemplateList",list);
        resultBean.setData(jsonObject);
        return resultBean;
    }

    /**
     * @description: 根據工程段获取下拉列表
     * @author: jiazheng
     * @date: 2020/6/23
     * @param: JSONObject
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getTemplateNameIdList")
    @ApiOperation (response = ResultBean.class, value = "根據工程段获取下拉列表", notes = "根據工程段获取下拉列表")
    public ResultBean getTemplateNameIdList(@RequestBody JSONObject jsonObject) {
        ResultBean resultBean = new ResultBean();
        JSONObject object = templateInfoService.getTemplateNameIdList(jsonObject);
        resultBean.setData(object);
        return resultBean;
    }
}
