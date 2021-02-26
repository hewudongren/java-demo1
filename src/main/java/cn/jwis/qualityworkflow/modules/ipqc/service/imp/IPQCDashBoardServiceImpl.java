package cn.jwis.qualityworkflow.modules.ipqc.service.imp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.ipqc.bean.QueryDashboardVo;
import cn.jwis.qualityworkflow.modules.ipqc.dao.IPQCDashBoardDao;
import cn.jwis.qualityworkflow.modules.ipqc.service.IPQCDashBoardService;
import cn.jwis.qualityworkflow.util.DateUtil;

@Service
public class IPQCDashBoardServiceImpl implements IPQCDashBoardService {

    @Autowired
    IPQCDashBoardDao ipqcDashBoardDao;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public Map<String, Object> getIPQCDashBoard(QueryDashboardVo bean) {
        String startTime=bean.getStartTime();
        String endTime=bean.getEndTime();
        Map<String, Object>  map=  ipqcDashBoardDao.getIPQCDashBoard(startTime,endTime,bean.getItemList());
        Map<String, Object>  mmp=  ipqcDashBoardDao.getESDDashBoard(startTime,endTime);
        if(map!=null && mmp!=null){
            map.put("ProcessingCount",Integer.parseInt(map.get("ProcessingCount").toString())+Integer.parseInt(mmp.get("ProcessingCount").toString()));
            map.put("OverdueCount",Integer.parseInt(map.get("OverdueCount").toString())+Integer.parseInt(mmp.get("OverdueCount").toString()));
            map.put("AddCount",Integer.parseInt(map.get("AddCount").toString())+Integer.parseInt(mmp.get("AddCount").toString()));
            map.put("ClosedCount",Integer.parseInt(map.get("ClosedCount").toString())+Integer.parseInt(mmp.get("ClosedCount").toString()));
        }

        return map;
    }

    @Override
    public List<Map<String, Object>> getAuditIssueDashBoard(QueryDashboardVo bean) throws Exception{
        String groupTime = DateUtil.getDateTypeTime(bean.getDateType(), "create_date");
        bean.setGroupBy(groupTime);
        bean.setStartTime(bean.getStartTime()+" 00:00:00");
        bean.setEndTime(bean.getEndTime()+" 23:59:59");
        List<Map<String, Object>> map = ipqcDashBoardDao.getAuditIssueDashBoard(bean);
        return map;
    }

    @Override
    public JSONObject getPatrolProblemRecordDashBoard(QueryDashboardVo bean) {
        List<Map<String, Object>> list1 =  ipqcDashBoardDao.getAuditOwner(bean);
        List<Map<String, Object>> list2 =  ipqcDashBoardDao.getAuditSquadLeader(bean);
        List<Map<String, Object>> list3 =  ipqcDashBoardDao.getAuditFaultType(bean);
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("AuditIssueList",list1);
        jsonObject.put("SquadLeaderList",list2);
        jsonObject.put("FaultTypeList",list3);
        return jsonObject;
    }

    @Override
    public List getIPQCDashBoardCount(QueryDashboardVo bean) {
        String startTime=bean.getStartTime();
        String endTime=bean.getEndTime();
        List<String> list=bean.getItemList();
        List arr= new ArrayList();
        for (int i = 0; i <list.size() ; i++) {
            if ("IPQC稽核".equals(list.get(i)) || "ODM稽核".equals(list.get(i))) {
                Map<String, Object> map = ipqcDashBoardDao.getIPQCDashBoardCount(startTime, endTime, list.get(i));
                arr.add(map);
            }
            if ("ESD稽核".equals(list.get(i))){
                Map<String, Object> mmp = ipqcDashBoardDao.getESDDashBoardCount(startTime,endTime);
                mmp.put("audit_type","ESD");
                arr.add(mmp);
            }
        }
        return arr;
    }

}
