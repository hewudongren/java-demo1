package cn.jwis.qualityworkflow.modules.pcn.controller;

import static cn.jwis.qualityworkflow.modules.pcn.bean.PCNConstants.Constants.PCNHISOTORYPROCESSSAVE;
import static cn.jwis.qualityworkflow.modules.pcn.bean.PCNConstants.Constants.PCNTEMPLATEKEY;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.CreateHistoryProcessVo;
import cn.jwis.qualityworkflow.bean.CretaeHistoryProcessBean;
import cn.jwis.qualityworkflow.bean.QueryDetailedInfoVo;
import cn.jwis.qualityworkflow.bean.QueryHistoryProcessRecord;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNDashboardVo;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNInfoBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNListBean;
import cn.jwis.qualityworkflow.modules.pcn.bean.QueryPCNInfo;
import cn.jwis.qualityworkflow.modules.pcn.service.PCNDashBoardService;
import cn.jwis.qualityworkflow.modules.pcn.service.PCNService;
import cn.jwis.qualityworkflow.service.HistoryProcessRecordService;
import cn.jwis.qualityworkflow.util.UserUtil;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (value = "/pcn")
public class PCNController {

    @Autowired
    PCNService pcnService;
    @Autowired
    PCNDashBoardService pcnDashBoardService;

    @Autowired
    HistoryProcessRecordService historyProcessRecordService;

    /**
     * @Description: 获取PCN单列表
     * @author: jiazheng
     * @date: 2020/4/29
     * @param: bean
     * @return:
     */
    @PostMapping (value = "/getPCNList")
    @ApiOperation (response = ResultBean.class, value = "获取PCN单列表", notes = "获取PCN单列表")
    public ResultBean getPCNList(@RequestBody QueryPCNInfo bean) {
        ResultBean resultBean = new ResultBean();
        List<PCNListBean> data = pcnService.getPCNList(bean);
        Long count = pcnService.getPCNListCount(bean);
        resultBean = ResultBean.pagination(count, bean.getPageNum(),bean.getPageSize(), data);
        return resultBean;
    }

    /**
     * @Description: 新增单据
     * @author: jiazheng
     * @date: 2020/4/30
     * @param: pcnInfoBean
     * @return:
     * @throws: Exception
     */
    @RequestMapping (value = "/saveFCNInfo", method = RequestMethod.POST)
    @ApiOperation (value = "PCN单据保存", notes = "PCN单据保存")
    public ResultBean saveFCNInfo(@RequestBody PCNInfoBean pcnInfoBean) throws Exception {
        ResultBean resultBean = new ResultBean();
        pcnService.saveFCNInfo(pcnInfoBean);
        return resultBean;
    }

    /**
     * @param pcnInfoBean
     * @Description: 流程处理，审批
     * @author: jiazheng
     * @date: 2020/4/30 16:04
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     * @throws: Exception
     **/
    @RequestMapping (value = "/confirm", method = RequestMethod.POST)
    @ApiOperation (value = "流程处理，审批", notes = "流程处理，审批")
    public ResultBean confirm(@RequestBody PCNInfoBean pcnInfoBean) throws Exception {
        ResultBean resultBean = new ResultBean();
        pcnService.confirm(pcnInfoBean);
        return resultBean;
    }

    /**
     * @description: 获取PCN品流程详细详情信息
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping (value = "/getPCNDetailednessInfo", method = RequestMethod.POST)
    @ApiOperation (response = ResultBean.class, value = "获取PCN品流程详细详情信息", notes = "获取PCN品流程详细详情信息")
    public ResultBean getPCNDetailednessInfo(@RequestBody QueryDetailedInfoVo bean) throws Exception {
        ResultBean resultBean = new ResultBean();
        Map<String, Object> resultMap = pcnService.getPCNDetailednessInfo(bean);
        resultBean.setData(resultMap);
        return resultBean;
    }
/**
 * @description: 查询保存(SAVE)的历史记录
 * @author: jiazheng
 * @date: 2020/5/6 11:02
 * @param bean:
 * @return: cn.jwis.qualityworkflow.bean.ResultBean
 **/
    @RequestMapping (value = "/getLatestRecordContent", method = RequestMethod.POST)
    @ApiOperation (value = "查询更新时间最新的历史记录 ", notes = "查询更新时间最新的历史记录")
    public ResultBean getLatestRecordContent(@RequestBody QueryHistoryProcessRecord bean) throws Exception {
        bean.setCreator(UserUtil.getCurrentUserName());
        bean.setWorkflowType(PCNTEMPLATEKEY);
        bean.setType(PCNHISOTORYPROCESSSAVE);
        bean.setWorkflowNode(bean.getWorkflowNode());
        bean.setWorkflowBusinessid(bean.getWorkflowBusinessid());
        JSONObject result = historyProcessRecordService.getLatestRecordContent(bean);
        return ResultBean.success(result);
    }

    /**
     * @description: 查询变更名称下拉列表
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param: bean
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping (value = "/getChangeNameList", method = RequestMethod.GET)
    @ApiOperation (value = "查询变更名称下拉列表 ", notes = "查询变更名称下拉列表")
    public ResultBean getChangeNameList() throws Exception {
        List<String> list =pcnService.getChangeNameList();
        return ResultBean.success(list);
    }
    /**
     * @param bean:
     * @description: 获取DashBoard
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @PostMapping (value = "/getPCNDashBoard")
    @ApiOperation (value = "获取DashBoard", notes = "获取DashBoard")
    public ResultBean getPCNDashBoard(@RequestBody PCNDashboardVo bean) throws Exception {
        ResultBean resultBean = new ResultBean();
        Map<String, Object> tendencyChartDataMap = pcnDashBoardService.getPCNDashBoard(bean);
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
    @RequestMapping (value = "/getPCNDashBoardCount", method = RequestMethod.POST)
    @ApiOperation (response = ResultBean.class, value = "获取不同Item的DashBoard", notes = "获取不同Item的DashBoard")
    public ResultBean getPCNDashBoardCount(@RequestBody PCNDashboardVo bean) throws Exception {
        ResultBean resultBean = new ResultBean();
        List tendencyChartDataMap = pcnDashBoardService.getPCNDashBoardCount(bean);
        resultBean.setData(tendencyChartDataMap);
        return resultBean;
    }
    /**
     * @description: Pcn保存接口
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/saveRecordContent", method = RequestMethod.POST)
    @ApiOperation(value = "Pcn保存接口 ", notes = "Pcn保存接口")
    public ResultBean saveRecordContent(@RequestBody CretaeHistoryProcessBean bean) throws Exception {
        CreateHistoryProcessVo vo = new CreateHistoryProcessVo();
        vo.setTemplateKey(PCNTEMPLATEKEY);
        vo.setType(PCNHISOTORYPROCESSSAVE);
        String node = (bean.getWorkflowNode() != null) ? bean.getWorkflowNode() : "CreatePCN";
        vo.setContent(bean.getContent());
        vo.setWorkflowBusinessid(bean.getWorkflowBusinessid());
        vo.setWorkflowNode(node);
        historyProcessRecordService.save(vo);
        return ResultBean.success();
    }


    /**
     * @description: 导出PCN查询数据
     * @author: jiazheng
     * @date: 2020/5/6 11:02
     * @param bean:
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     **/
    @RequestMapping(value = "/exportPCNData", method = RequestMethod.POST)
    @ApiOperation(value = "导出PCN查询数据", notes = "导出PCN查询数据")
    public void exportPCNData(HttpServletResponse response, HttpServletRequest request, @RequestBody QueryPCNInfo bean) {
        pcnService.exportPCNData(response, request, bean);
    }

    /**
     * @param: pcnInfoBean
     * @Description: 查询当前登录人的所有上级
     * @author: jiazheng
     * @date: 2020/4/30 16:04
     * @return: cn.jwis.qualityworkflow.bean.ResultBean
     * @throws: Exception
     **/
    @RequestMapping (value = "/getSuperiorByUser", method = RequestMethod.GET)
    @ApiOperation (value = "查询当前登录人的所有上级", notes = "查询当前登录人的所有上级")
    public ResultBean confirm() throws Exception {
        ResultBean resultBean = new ResultBean();
        JSONObject object=pcnService.getSuperiorByUser();
        resultBean.setData(object);
        return resultBean;
    }

}