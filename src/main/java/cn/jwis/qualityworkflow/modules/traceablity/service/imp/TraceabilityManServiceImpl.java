package cn.jwis.qualityworkflow.modules.traceablity.service.imp;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityManVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMan;
import cn.jwis.qualityworkflow.modules.traceablity.dao.TraceabilityManMapper;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceabilityManService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityTitle.*;

@Service
public class TraceabilityManServiceImpl implements TraceabilityManService {

    @Autowired
    TraceabilityManMapper traceabilityManMapper;


    @Override
    public List<TraceabilityMan> getTraceabilityManList(QueryTraceabilityManVo bean) {
        List<TraceabilityMan> resultList = null;
        if (bean.getPageNum() != null && bean.getPageSize() != null) {
            bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
        }
            resultList = traceabilityManMapper.getTraceabilityManList(bean);
           return resultList;
    }

    @Override
    public Long getTraceabilityManListCount(QueryTraceabilityManVo bean) {
        Long  count=traceabilityManMapper.getTraceabilityManListCount(bean);
        return count;
    }

    @Override
    public void exportTraceabilityMan(HttpServletResponse response, HttpServletRequest request, QueryTraceabilityManVo bean) {
        List<TraceabilityMan> list = traceabilityManMapper.exportTraceabilityMan(bean);
        // 将List<PCNListBean>  转换为   List<JSONObject>
        List<JSONObject> jsonObjects = new ArrayList<>(list.size());
        list.stream().forEach(d -> {
            JSONObject object = JSONObjectUtil.toJSONObject(d);
            jsonObjects.add(object);
        });
        String[] title = TraceabilityManInfoExcel;
        String language = request.getHeader("Language");
        if (("en-US").equals(language)) {
            title = TraceabilityManInfoDbCamel;
        }
        Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, TraceabilityManInfoDbCamel);
        ExcelUtil.outputWorkbook(response, workbook, TraceabilityManInfoTable);
    }

    @Override
    public JSONObject getTraManPullDownInfo() {
        List<TraceabilityMan> list=traceabilityManMapper.getTraManPullDownInfo();
        JSONObject jsonObject=new JSONObject();
        List<String> list1=new ArrayList<>();
        List<String> list2=new ArrayList<>();
        List<String> list3=new ArrayList<>();
        List<String> list4=new ArrayList<>();
        List<String> list5=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            TraceabilityMan TraceabilityMan=list.get(i);
            if(TraceabilityMan.getDepartmentName()!=null && TraceabilityMan.getDepartmentName()!="" && !list1.contains(TraceabilityMan.getDepartmentName())){
                list1.add(TraceabilityMan.getDepartmentName());
            }
            if(TraceabilityMan.getProcess()!=null && TraceabilityMan.getProcess()!="" && !list2.contains(TraceabilityMan.getProcess())){
                list2.add(TraceabilityMan.getProcess());
            }
            if(TraceabilityMan.getLine()!=null && TraceabilityMan.getLine()!="" && !list3.contains(TraceabilityMan.getLine())){
                list3.add(TraceabilityMan.getLine());
            }
            if(TraceabilityMan.getJobName()!=null && TraceabilityMan.getJobName()!="" && !list4.contains(TraceabilityMan.getJobName())){
                list4.add(TraceabilityMan.getJobName());
            }
            if(TraceabilityMan.getCertificationResult()!=null && TraceabilityMan.getCertificationResult()!="" && !list5.contains(TraceabilityMan.getCertificationResult())){
                list5.add(TraceabilityMan.getCertificationResult());
            }
        }
        jsonObject.put("DepartmentList",list1);
        jsonObject.put("ProcessList",list2);
        jsonObject.put("LineList",list3);
        jsonObject.put("JobNameList",list4);
        jsonObject.put("ResultList",list5);
        return jsonObject;
    }

}
