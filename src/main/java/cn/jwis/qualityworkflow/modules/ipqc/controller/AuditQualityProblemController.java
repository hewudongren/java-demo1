package cn.jwis.qualityworkflow.modules.ipqc.controller;


import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.Constants.IPQCHISOTORYPROCESSSAVE;
import static cn.jwis.qualityworkflow.modules.ipqc.bean.IPQCTitle.Constants.IPQCTEMPLATEKEY;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.CretaeHistoryProcessBean;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.ipqc.bean.AuditQualityProblemInfo;
import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryAuditQualityProblem;
import cn.jwis.qualityworkflow.modules.ipqc.service.AuditQualityProblemService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.UserUtil;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (value = "/auditQualityProblem")
public class AuditQualityProblemController {



    @Autowired
    AuditQualityProblemService auditQualityProblemService;

    @Autowired
    HistoryProcessRecordService historyProcessRecordService;

    /**
     * @description: 获取稽核质量问题列表
     * @author: jiazheng
     * @date: 2020/6/17
     * @param: bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getAuditQualityProblemInfo")
    @ApiOperation (response = ResultBean.class, value = "获取稽核质量问题列表", notes = "获取稽核质量问题列表")
    public ResultBean getAuditQualityProblemInfo(@RequestBody QueryAuditQualityProblem bean) {
        ResultBean resultBean = new ResultBean();
        List<AuditQualityProblemInfo> data = auditQualityProblemService.getAuditQualityProblemInfo(bean);
        Long count = auditQualityProblemService.getAuditQualityProblemCount(bean);
        resultBean = ResultBean.pagination(count, bean.getPageNum(), bean.getPageSize(), data);
        return resultBean;
    }


    /**
     * @description: 新增稽核质量问题
     * @author: jiazheng
     * @date: 2020/6/17
     * @param: bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping (value = "/insertAuditQualityProblemInfo", method = RequestMethod.POST)
    @ApiOperation (value = "新增稽核质量问题", notes = "新增稽核质量问题")
    public ResultBean insertAuditQualityProblemInfo(@RequestBody AuditQualityProblemInfo bean) throws Exception {
        ResultBean resultBean = new ResultBean();
        auditQualityProblemService.insertAuditQualityProblemInfo(bean);
        return resultBean;
    }
    /**
     * @param:AuditQualityProblemInfo
     * @Description: 流程处理，审批
     * @author: jiazheng
     * @date: 2020/6/18
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     * @throws: Exception
     **/
    @RequestMapping (value = "/confirm", method = RequestMethod.POST)
    @ApiOperation (value = "流程处理，审批", notes = "流程处理，审批")
    public ResultBean confirm(@RequestBody JSONObject jsonObject) throws Exception {
        ResultBean resultBean = new ResultBean();
        auditQualityProblemService.confirm(jsonObject);
        return resultBean;
    }

    /**
     * @description: 导出稽核质量问题
     * @author: jiazheng
     * @date: 2020/6/20
     * @param bean:
     * @return:
     **/
    @RequestMapping(value = "/exportAuditQualityProblem", method = RequestMethod.POST)
    @ApiOperation(value = "导出稽核质量问题", notes = "导出稽核质量问题")
    public void exportAuditQualityProblem(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryAuditQualityProblem bean) {
        auditQualityProblemService.exportAuditQualityProblem(response, request, bean);
    }

    /**
     * @description: 查询最新的保存记录
     * @author: jiazheng
     * @date: 2020/6/22
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/getLatestRecordContent", method = RequestMethod.GET)
    @ApiOperation(value = "查询更新时间最新的历史记录 ", notes = "查询更新时间最新的历史记录")
    public ResultBean getLatestRecordContent() throws Exception {
        QueryHistoryProcessRecord bean = new QueryHistoryProcessRecord();
        bean.setCreator(UserUtil.getCurrentUserName());
        bean.setWorkflowNode("CreateIPQC");
        bean.setWorkflowType(IPQCTEMPLATEKEY);
        bean.setType(IPQCHISOTORYPROCESSSAVE);
        JSONObject result = historyProcessRecordService.getLatestRecordContent(bean);
        return ResultBean.success(result);
    }

    /**
     * @description: 稽核质量问题保存接口
     * @author: jiazheng
     * @date: 2020/6/22
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/saveRecordContent", method = RequestMethod.POST)
    @ApiOperation(value = "稽核质量问题保存接口 ", notes = "稽核质量问题保存接口")
    public ResultBean saveRecordContent(@RequestBody CretaeHistoryProcessBean bean) throws Exception {
        CreateHistoryProcessVo vo = new CreateHistoryProcessVo();
        vo.setTemplateKey(IPQCTEMPLATEKEY);
        vo.setType(IPQCHISOTORYPROCESSSAVE);
        String node = (bean.getWorkflowNode() != null) ? bean.getWorkflowNode() : "CreateIPQC";
        vo.setContent(bean.getContent());
        vo.setWorkflowBusinessid(bean.getWorkflowBusinessid());
        vo.setWorkflowNode(node);
        historyProcessRecordService.save(vo);
        return ResultBean.success();
    }

    /**
     * @description: 获取稽核质量问题记录详情信息
     * @author: xujinbiao
     * @date: 2020/5/18 11:15
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/getDetailednessInfo", method = RequestMethod.POST)
    @ApiOperation(response = ResultBean.class, value = "获取稽核质量问题记录详情信息", notes = "获取稽核质量问题记录详情信息")
    public ResultBean getDetailednessInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
        ResultBean resultBean = new ResultBean();
        Map<String, Object> resultMap = auditQualityProblemService.getDetailednessInfo(bean);
        resultBean.setData(resultMap);
        return resultBean;
    }


    /**
     * 导入稽核质量问题
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/importAuditQualityProblem", method = RequestMethod.POST)
    @ApiOperation(response = ResultBean.class, value = "导入稽核质量问题", notes = "导入稽核质量问题")
    public ResultBean importAuditQualityProblem(@RequestParam (value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
        ResultBean resultBean = new ResultBean();
        List<JSONObject> list=auditQualityProblemService.importAuditQualityProblem(file, request);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("auditQualityProblemList",list);
        resultBean.setData(jsonObject);
        return resultBean;
    }
    /**
     * @description: 批量新增稽核质量问题
     * @author: jiazheng
     * @date: 2020/6/17
     * @param: bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping (value = "/insertAuditQualityProblemInfoList", method = RequestMethod.POST)
    @ApiOperation (value = "新增稽核质量问题", notes = "新增稽核质量问题")
    public ResultBean insertAuditQualityProblemInfoList(@RequestBody List<AuditQualityProblemInfo> list) throws Exception {
        ResultBean resultBean = new ResultBean();
        auditQualityProblemService.insertAuditQualityProblemInfoList(list);
        return resultBean;
    }
    /**
     * @description: 稽核质量问题下载
     * @author: jiazheng
     * @date: 2020/6/17
     * @param: bean
     * @return:
     **/
    @RequestMapping(value = "/downLoadAuditQualityProblem", method = RequestMethod.POST)
    @ApiOperation(value = "稽核质量问题下载", notes = "稽核质量问题下载")
    public void downLoadAuditQualityProblem(HttpServletResponse response, HttpServletRequest request, @RequestBody List<AuditQualityProblemInfo> list ) {
        auditQualityProblemService.downLoadAuditQualityProblem(response, request, list);
    }

    /**
     * @description: ESD和EOS统计
     * @author: jiazheng
     * @date: 2020/5/6
     * @param: JSONObject
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @GetMapping (value = "/getCountAuditEsdEos")
    @ApiOperation (response = ResultBean.class, value = "ESD和EOS统计", notes = "ESD和EOS统计")
    public ResultBean getCountAuditEsdEos(@RequestParam(value = "startTime" ) String startTime,@RequestParam(value = "endTime" ) String endTime) {
        auditQualityProblemService.getCountAuditEsdEos(startTime,endTime);
        return ResultBean.success();
    }

}
