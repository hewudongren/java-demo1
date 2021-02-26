package cn.jwis.qualityworkflow.modules.traceablity.service.imp;


import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityMethodVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMethod;
import cn.jwis.qualityworkflow.modules.traceablity.dao.TraceabilityMethodMapper;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceabilityMethodService;
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
public class TraceabilityMethodServiceImpl implements TraceabilityMethodService {

    @Autowired
    TraceabilityMethodMapper traceabilityMethodMapper;

    @Override
    public List<TraceabilityMethod> getTraceabilityMethodList(QueryTraceabilityMethodVo bean) {
        List<TraceabilityMethod> resultList = null;
        if (bean.getPageNum() != null && bean.getPageSize() != null) {
            bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
        }
        resultList = traceabilityMethodMapper.getTraceabilityMethodList(bean);
        return resultList;
    }

    @Override
    public Long getTraceabilityMethodCount(QueryTraceabilityMethodVo bean) {
        Long  count=traceabilityMethodMapper.getTraceabilityMethodCount(bean);
        return count;
    }

    @Override
    public void exportTraceabilityMethod(HttpServletResponse response, HttpServletRequest request, QueryTraceabilityMethodVo bean) {
        List<TraceabilityMethod> list = traceabilityMethodMapper.exportTraceabilityMethod(bean);
        // 将List<PCNListBean>  转换为   List<JSONObject>
        List<JSONObject> jsonObjects = new ArrayList<>(list.size());
        list.stream().forEach(d -> {
            JSONObject object = JSONObjectUtil.toJSONObject(d);
            jsonObjects.add(object);
        });
        String[] title = TraceabilityMethodInfoExcel;
        String language = request.getHeader("Language");
        if (("en-US").equals(language)) {
            title = TraceabilityMethodInfoDbCamel;
        }
        Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, TraceabilityMethodInfoDbCamel);
        ExcelUtil.outputWorkbook(response, workbook, TraceabilityMethodInfoTable);
    }

    @Override
    public JSONObject getTraMethodPullDownInfo() {
        List<TraceabilityMethod> list=traceabilityMethodMapper.getTraManPullDownInfo();
        JSONObject jsonObject=new JSONObject();
        List<String> list1=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            TraceabilityMethod traceabilityMethod=list.get(i);
            if(traceabilityMethod.getFileOwner()!=null && traceabilityMethod.getFileOwner()!="" && !list1.contains(traceabilityMethod.getFileOwner())){
                list1.add(traceabilityMethod.getFileOwner());
            }

        }
        jsonObject.put("fileOwnerList",list1);
        return jsonObject;
    }
}
