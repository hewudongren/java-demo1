package cn.jwis.qualityworkflow.modules.traceablity.service.imp;


import static cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityTitle.TraceabilityMaterialInfoDbCamel;
import static cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityTitle.TraceabilityMaterialInfoExcel;
import static cn.jwis.qualityworkflow.modules.traceablity.bean.TraceabilityTitle.TraceabilityMaterialInfoTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.modules.traceablity.bean.QueryTraceablityMaterialVo;
import cn.jwis.qualityworkflow.modules.traceablity.dao.TraceablityMaterialMapper;
import cn.jwis.qualityworkflow.modules.traceablity.service.TraceablityMaterialService;
import cn.jwis.qualityworkflow.util.ExcelUtil;
import cn.jwis.qualityworkflow.util.JSONObjectUtil;


@Service
public class TraceablityMaterialServiceImpl implements TraceablityMaterialService {

    @Autowired
    TraceablityMaterialMapper traceablityMaterialMapper;

    @Value ("${jdbc.connection.Material.url}")
    private String materialUrl;

    @Value("${jdbc.connection.Material.user}")
    private String materialUser;

    @Value("${jdbc.connection.Material.password}")
    private String materialPassword;


    @Override
    public JSONObject getTraceabilityMaterial(QueryTraceablityMaterialVo bean) throws  Exception{
        JSONObject jsonObject=new JSONObject();
        if(bean.getPageNum()!=null && bean.getPageSize()!=null) {
            bean.setPageNum(bean.getPageSize() * (bean.getPageNum() - 1));
        }
            jsonObject = odbc(bean);
            jsonObject.put("currentPage",bean.getPageNum());
            jsonObject.put("pageSize",bean.getPageSize());
            return jsonObject;
    }





    @Override
    public void exportTraceabilityMaterial(HttpServletResponse response, HttpServletRequest request, QueryTraceablityMaterialVo bean) throws  Exception{
        //List list = odbc(bean);
        JSONObject jsonObject =odbc(bean);
        List list=jsonObject.getJSONArray("List");
        // 将List<PCNListBean>  转换为   List<JSONObject>
        List<JSONObject> jsonObjects = new ArrayList<>(list.size());
        list.stream().forEach(d -> {
            JSONObject object = JSONObjectUtil.toJSONObject(d);
            jsonObjects.add(object);
        });
        String[] title = TraceabilityMaterialInfoExcel;
        String language = request.getHeader("Language");
        if (("en-US").equals(language)) {
            title = TraceabilityMaterialInfoDbCamel;
        }
        Workbook workbook = ExcelUtil.exporSimple(jsonObjects, title, TraceabilityMaterialInfoDbCamel);
        ExcelUtil.outputWorkbook(response, workbook, TraceabilityMaterialInfoTable);
    }

    @Override
    public JSONObject getMaterialPullDownInfo(QueryTraceablityMaterialVo bean) throws  Exception{
        JSONObject jsonObject = odbc(bean);
        List list=jsonObject.getJSONArray("List");
        JSONObject jsb= new JSONObject();
        List<String> list1= new ArrayList<>();
        List<String> list2= new ArrayList<>();
        if (jsonObject!=null){
           for (int i = 0; i < list.size(); i++) {
                JSONObject object = JSONObjectUtil.toJSONObject(list.get(i));
                if(!list1.contains(object.getString("sn")) ){
                    list1.add(object.getString("sn"));
                }
                if(!list2.contains(object.getString("bjmaterial"))){
                    list2.add(object.getString("bjmaterial"));
                }

            }
            jsb.put("snList",list1);
            jsb.put("bjmaterialList",list2);
        }

        return jsb;
    }
    public JSONObject  odbc(QueryTraceablityMaterialVo bean) throws  Exception{
        Connection connection = null;
        try {
            String jdbcDriver = "com.mysql.jdbc.Driver";
            String jdbcUrl = materialUrl;
            String jdbcUser = materialUser;
            String jdbcPassword = materialPassword;
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;
        ResultSet  resultSet=null;
        String string="";
        String stringCount="";
        String startTime="";
        String endTime="";
        if (bean.getStartTime()!="" && bean.getEndTime()!="" && bean.getStartTime()!=null && bean.getEndTime()!=null){
             startTime += bean.getStartTime()+ " 00:00:00";
             endTime += bean.getEndTime()+ " 23:59:59";
        }

        string +="SELECT t.* from t_asmmaterialscan t where 1=1 ";
        stringCount +="SELECT count(1) from t_asmmaterialscan t where 1=1 ";
                if(bean.getSn()!=null  && bean.getSn()!="" ) {
                        String sn=bean.getSn();
                        string += " and t.sn like CONCAT('%',"+"'"+sn+"'"+",'%')";
                        stringCount += " and t.sn like CONCAT('%',"+"'"+sn+"'"+",'%')";

                }
                     if(bean.getBjmaterial()!=null && bean.getBjmaterial()!=""){
                                 String bjm= bean.getBjmaterial();
                                 string += " and t.bjmaterial like CONCAT('%',"+"'"+bjm+"'"+",'%')";
                                 stringCount += " and t.bjmaterial like CONCAT('%',"+"'"+bjm+"'"+",'%')";
                     }

        if(bean.getPageNum()!=null && bean.getPageSize()!=null && startTime!="" && startTime!=null && endTime!="" && endTime!=null){
            string+=" and t.createtime>="+"'"+startTime+"'"+" and t.createtime <="+"'"+endTime+"'" +" limit "+bean.getPageNum()+", "+bean.getPageSize();
            stringCount+=" and t.createtime>="+"'"+startTime+"'"+" and t.createtime <="+"'"+endTime+"'";
        }else{
            if (startTime!="" && startTime!=null && endTime!="" && endTime!=null){
                string+=" and t.createtime>="+"'"+startTime+"'"+" and t.createtime <="+"'"+endTime+"'";
                stringCount+=" and t.createtime>="+"'"+startTime+"'"+" and t.createtime <="+"'"+endTime+"'";
            }


        }
        JSONObject jsonObject1=null;
        try {
             preparedStatement = connection.prepareStatement(stringCount);
             resultSet  = preparedStatement.executeQuery();
            int row=0;
            while(resultSet.next()){

                row = resultSet.getInt(1);
            }

            preparedStatement = connection.prepareStatement(string);
               resultSet  = preparedStatement.executeQuery();
               jsonObject1=convertList(resultSet);
               jsonObject1.put("count",row);

        } catch (SQLException ex) {

        } finally {

            closeAll(connection, preparedStatement,resultSet);
        }

        return  jsonObject1;
    }

    public static void closeAll(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet) {
        try {
            //按顺序关闭
            if(resultSet!=null) {
                resultSet.close();
            }
            if (preparedStatement!=null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject convertList(ResultSet rs) throws SQLException{
        List<JSONObject> list = new ArrayList();
        JSONObject object= new JSONObject();
        //获取键名
        ResultSetMetaData md = rs.getMetaData();
        //获取行的数量
        int columnCount = md.getColumnCount();
            while (rs.next()) {
               // Map rowData = new HashMap();
                JSONObject jsonObject= new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    //获取键名及值
                    jsonObject.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(jsonObject);
       }
        object.put("List",list);
        return object;
    }
}
