package cn.jwis.qualityworkflow.modules.traceablity.service.imp;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;
import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceabilityMachineVo;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMachine;
import cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityMachineData;
import cn.jwis.qualityworkflow.modules.traceablity.dao.TraceabilityMachineMapper;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceabilityMachineService;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;

@Service
public class TraceabilityMachineServiceImpl implements TraceabilityMachineService {

    @Autowired
    TraceabilityMachineMapper traceabilityMachineMapper;


    @Autowired
    IDGeneratorRunner idGeneratorRunner;

    private static SimpleDateFormat timeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar calendar = Calendar.getInstance();

    @Override
    public List<TraceabilityMachine> getTraceabilityMachine(QueryTraceabilityMachineVo bean) {
        List<TraceabilityMachine> resultList = null;
        if (bean.getPageNum() != null && bean.getPageSize() != null) {
            bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
        }
        resultList = traceabilityMachineMapper.getTraceabilityMachine(bean);
        return resultList;
    }

    @Override
    public Long getTraceabilityMachineCount(QueryTraceabilityMachineVo bean) {
        Long  count=traceabilityMachineMapper.getTraceabilityMachineCount(bean);
        return count;
    }

    @Override
    public int insertTraceabilityMachine(TraceabilityMachine bean) {
        int code=0;
        if(bean.getId()!=null && bean.getId()!=""){
            traceabilityMachineMapper.updateTraceabilityMachine(bean);
        }else{
            TraceabilityMachine machineConfiguration = traceabilityMachineMapper.getMachineConfiguration();
            if(machineConfiguration!=null){
                code=-1;
            }
            bean.setCreator(getCurrentUserName());
            bean.setId(String.valueOf(idGeneratorRunner.nextId()));
            bean.setCreateDate(timeDate.format(new Date()));
            traceabilityMachineMapper.insertTraceabilityMachine(bean);
        }
          return code;
    }
    private String getCurrentUserName() {
        UserInfo currentUser = SessionHelper.getCurrentUser();
        String currentUserName = currentUser.getAccount();
        return currentUserName;
    }
    @Override
    public JSONObject getTraceabilityMachineById(JSONObject jsonObject) {
        JSONObject jsonObject1=traceabilityMachineMapper.getTraceabilityMachineById(jsonObject);
        return jsonObject1;
    }


    @Override
    public JSONObject getFourMeMachineChart(QueryTraceabilityMachineVo bean) throws  Exception{
        JSONObject jsonObject=new JSONObject();
        if(bean.getDeviceNo()!=null) {
            String deviceNo = bean.getDeviceNo();
            List<Map<String, String>> list = traceabilityMachineMapper.getTableNameList();
            if (list != null){
                for (int i = 0; i < list.size(); i++) {
                    Map<String, String> map =list.get(i);
                    String tableName=map.get("table_name");
                    String filedName=map.get("filed_name");
                    List<Map<String, Object>> hashMap = traceabilityMachineMapper.getDataByDeviceNo(deviceNo, tableName, filedName);
                    if(hashMap!=null && hashMap.size()>0){
                        String deviceType=map.get("machine_name");
                        bean.setDeviceType(deviceType);
                        jsonObject=getTraceabilityMachineChart(bean);
                        break;
                    }else{
                        continue;
                    }
                }

        }
        }

        return jsonObject;
    }

    @Override
    public JSONObject getMachinePullDownInfo() {
        JSONObject jsonObject=new JSONObject();
        List<TraceabilityMachine> list = traceabilityMachineMapper.getMachinePullDownInfo();
        List<String> list1=new ArrayList<>();
        List<String> list2=new ArrayList<>();
        List<String> list3=new ArrayList<>();
        List<String> list4=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            TraceabilityMachine traceabilityMachine=list.get(i);
            if(traceabilityMachine.getDeviceType()!="" && traceabilityMachine.getDeviceType()!=null && !list1.contains(traceabilityMachine.getDeviceType()) ){
                list1.add(traceabilityMachine.getDeviceType());
            }
            if(traceabilityMachine.getLine()!="" && traceabilityMachine.getLine()!=null && !list2.contains(traceabilityMachine.getLine()) ){
                list2.add(traceabilityMachine.getLine());
            }
            if(traceabilityMachine.getDeviceNo()!="" && traceabilityMachine.getDeviceNo()!=null && !list3.contains(traceabilityMachine.getDeviceNo()) ){
                list3.add(traceabilityMachine.getDeviceNo());
            }
            if(traceabilityMachine.getParameter()!="" && traceabilityMachine.getParameter()!=null && !list4.contains(traceabilityMachine.getParameter()) ){
                list4.add(traceabilityMachine.getParameter());
            }
        }
        jsonObject.put("deviceTypeList",list1);
        jsonObject.put("lineList",list2);
        jsonObject.put("deviceNoList",list3);
        jsonObject.put("parameterList",list4);
        return jsonObject;
    }

    @Override
    public JSONObject getTraceabilityMachineChart(QueryTraceabilityMachineVo bean) throws  Exception{
        JSONObject jsonObject=new JSONObject();
        if(bean.getDeviceType()!=null){
            String deviceType=bean.getDeviceType();
            List<String> parameterList= traceabilityMachineMapper.getParameterByDeviceType(bean);
            String tableName=traceabilityMachineMapper.getDeviceTableName(deviceType);
            if( tableName!="" && tableName !=null && parameterList!=null){
                for (int i = 0; i <parameterList.size() ; i++) {
                    String parameter =parameterList.get(i);
                    Map<String,String> map = traceabilityMachineMapper.getFiledName(parameter, deviceType);
                    bean.setTableName(tableName);
                   if (map!=null){
                       bean.setFiledName(map.get("filedName"));
                       bean.setDateTime(map.get("dateTime"));
                       bean.setLineName(map.get("lineName"));
                       bean.setDeviceNoName(map.get("deviceNoname"));
                    List<Map<String,Object>>  temperatureList =traceabilityMachineMapper.getPlasmaTemperatureData(bean);

                    JSONObject Object=new JSONObject();
                    Object.put("average",temperatureList);
                    bean.setParameter(parameter);
                    Object = setConfigurationData(bean, Object);
                    jsonObject.put(parameter,Object);
                   }
                }

          }

        }
        return jsonObject;
    }


    private JSONObject setConfigurationData(QueryTraceabilityMachineVo bean,JSONObject object){
        TraceabilityMachine traceabilityMachine = traceabilityMachineMapper.getConfiguration(bean);
        if(traceabilityMachine!=null){
            object.put("specificationFloorValue",traceabilityMachine.getSpecificationFloorValue());
            object.put("specificationUpValue",traceabilityMachine.getSpecificationUpValue());
            object.put("specificationCentreValue",traceabilityMachine.getSpecificationCentreValue());
        }else{
            object.put("specificationFloorValue","0");
            object.put("specificationUpValue","0");
            object.put("specificationCentreValue","0");
        }
        return object;
    }



    @Override
    public void deleteTraceabilityMachine(JSONObject jsonObject) {
        traceabilityMachineMapper.deleteTraceabilityMachine(jsonObject);
    }

    @Override
    public JSONObject getDeviceName(JSONObject jsonObject) {
       List<String> list= traceabilityMachineMapper.getDeviceName(jsonObject);
       JSONObject jsonObject1= new JSONObject();
       if (list!=null){
           jsonObject1.put("deviceList",list);
        }
       return  jsonObject1;
    }

    @Override
    public JSONObject getLineByDeviceType(String type){
        JSONObject jsonObject= new JSONObject();
        String tableName=traceabilityMachineMapper.getDeviceTableName(type);
        String lineFiledName = traceabilityMachineMapper.getLineFiledName(type);
        List<String> list = traceabilityMachineMapper.getLineByDeviceType(tableName, lineFiledName);
        jsonObject.put("Line",list);
        return jsonObject;
    }


    public List<String> getTraceabilityMachineTableName() {
        List<String> list = traceabilityMachineMapper.getTraceabilityMachineList();
        return list;
    }

    public List<String> getTraceabilityMachineFiledName(String tableName) {
        List<String> list=traceabilityMachineMapper.getTraceabilityMachineFiledName(tableName);
        return  list;
    }

    @Override
    @Transactional (rollbackFor = Exception.class)
    public void insetTraceabilityMachine(JSONObject jsonObject)  throws Exception{
        if (jsonObject!=null) {
            String tableName=jsonObject.getString("tableName");
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            List<JSONObject> arr = setNextId(jsonArray,tableName);
            if (arr.size() >=5000) {
                List<JSONObject> arrayList = new ArrayList<>();
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject json = JSONObjectUtil.toJSONObject(arr.get(i));
                    arrayList.add(json);
                    if ((i + 1) % 1000 == 0) {
                        traceabilityMachineMapper.insetTraceabilityMachine(tableName, arrayList);
                        arrayList.clear();
                    }
                    if (i == (arr.size() - 1) && arrayList.size() > 0 && arrayList.size() < 1000) {
                        traceabilityMachineMapper.insetTraceabilityMachine(tableName, arrayList);
                        arrayList.clear();
                    }
                }
            }else{
                traceabilityMachineMapper.insetTraceabilityMachine(tableName, arr);
            }
        }

    }

    @Override
    public JSONObject getMachineDeviceType() {
        JSONObject jsonObject = new JSONObject();
        List<String> list1= new ArrayList<>();
        List<String> list2= new ArrayList<>();
        List<TraceabilityMachineData> list=traceabilityMachineMapper.getMachineDeviceType();
        for (int i = 0; i <list.size() ; i++) {
            TraceabilityMachineData MachineData=list.get(i);
            if(MachineData.getMachineName()!="" && MachineData.getMachineName()!=null && !list1.contains(MachineData.getMachineName())) {
                list1.add(MachineData.getMachineName());
            }
            if(MachineData.getParameter()!="" && MachineData.getParameter()!=null && !list2.contains(MachineData.getParameter())) {
                list2.add(MachineData.getParameter());
            }
        }
        jsonObject.put("deviceType",list1);
        jsonObject.put("deviceParameter",list2);
        return jsonObject;
    }

    @Override
    public JSONObject getDeviceNoByDeviceType(String line, String type) {
        List<String> list =null;
        JSONObject jsonObject= new JSONObject();
        String tableName=traceabilityMachineMapper.getDeviceTableName(type);
        if (tableName!="" && tableName!=null){
            String deviceNoFiledName=traceabilityMachineMapper.getDeviceNoByType(type);
            String lineFiledName = traceabilityMachineMapper.getLineFiledName(type);
            if (deviceNoFiledName!="" && deviceNoFiledName!=null){
                list= traceabilityMachineMapper.getDeviceNoByDeviceType(tableName,deviceNoFiledName,line,lineFiledName);
                jsonObject.put("deviceNoList",list);
            }
        }
        return jsonObject;
    }

    private List<JSONObject> setNextId(JSONArray jsonArray,String tableName){
        List<JSONObject> arrayList=new ArrayList<JSONObject>();
        if(jsonArray!=null && jsonArray.size()>0){
            for (int i = 0; i < jsonArray.size(); i++) {
                Object object = JSONObject.toJSONStringWithDateFormat(jsonArray.get(i), "yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
                JSONObject jsonObject = JSONObjectUtil.toJSONObject(object);
                if("t_pressure_visualization".equals(tableName)){
                    jsonObject.put("t_id",String.valueOf(idGeneratorRunner.nextId()));
                }else{
                    jsonObject.put("id",String.valueOf(idGeneratorRunner.nextId()));
                }

                Iterator iter = jsonObject.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if(entry.getValue()==null){
                        entry.setValue("");
                    }
                }
                arrayList.add(jsonObject);
            }
        }
        return arrayList;
}


    public Date getMaxCreatedTime(String tableName) {
        Date date=null;
        if("dust_free_shed".equals(tableName)){

            date=  traceabilityMachineMapper.getMaxCreateTime();

        }else if("glue_dispenser_weight".equals(tableName)){
            date=  traceabilityMachineMapper.getMaxMeasureTime();
        }else if("t_pressure_visualization".equals(tableName)){
            date= traceabilityMachineMapper.getMaxCreationTime();
        }else{
            date= traceabilityMachineMapper.getMaxCreatedTime();
        }

        return date;
    }

}
