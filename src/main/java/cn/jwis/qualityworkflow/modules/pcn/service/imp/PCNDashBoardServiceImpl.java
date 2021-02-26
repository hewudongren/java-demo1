package cn.jwis.qualityworkflow.modules.pcn.service.imp;

import cn.jwis.qualityworkflow.modules.ecn.bean.EcnInfo;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNDashboardVo;
import cn.jwis.qualityworkflow.modules.pcn.bean.PCNListBean;
import cn.jwis.qualityworkflow.modules.pcn.dao.PCNDashBoardDao;
import cn.jwis.qualityworkflow.modules.pcn.service.PCNDashBoardService;
import cn.jwis.qualityworkflow.modules.pcn.service.PCNService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PCNDashBoardServiceImpl implements PCNDashBoardService {

    @Autowired
    PCNDashBoardDao pcnDashBoardDao;

    @Autowired
    PCNService pcnService;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Date date=new Date();
    Calendar calendar= Calendar.getInstance();
    @Override
    public Map<String, Object> getPCNDashBoard(PCNDashboardVo bean)throws Exception{
         String startTime=bean.getStartTime();
         String endTime=bean.getEndTime();
        Map<String, Object>  map=  pcnDashBoardDao.getPCNDashBoard(startTime,endTime);
        return map;
    }


    @Override
    public List getPCNDashBoardCount(PCNDashboardVo bean) throws Exception {
        String startTime=bean.getStartTime();
        String endTime=bean.getEndTime();
        List<String> list=bean.getItemList();
        List arr= new ArrayList();
        for (int i = 0; i <list.size() ; i++) {
            Map<String, Object>  map=  pcnDashBoardDao.getPCNDashBoardCount(startTime,endTime,list.get(i));
            arr.add(map);
        }

        return arr;
    }



}
