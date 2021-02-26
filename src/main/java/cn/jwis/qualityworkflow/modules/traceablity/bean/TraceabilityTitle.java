package cn.jwis.qualityworkflow.modules.traceablity.bean;

public class TraceabilityTitle {

    //TraceabilityMethod

    public static  String[] TraceabilityMethodInfoExcel  = new String[]{"文件编号","文件名称","filesClassId","文件类型","机型","适用范围","创建时间","生效时间"," 失效时间","文件owner","文件版本","文件状态"};

    public static  String[]TraceabilityMethodInfoDbCamel = new String[]{"fileNumber","fileName","filesClassId","fileType","model","involveScope","createDate","fileValidTime","fileExpirationTime","fileOwner","fileVersion","fileStatusDescribe"};

    public static String TraceabilityMethodInfoTable = "TraceabilityMethodInfo.xlsx";


    //TraceabilityMan

    public static  String[] TraceabilityManInfoExcel  = new String[]{"员工编号","员工姓名","入职日期","性别","部门名称","岗位名称","状态","佣工类型","是否关键岗"," 带班班长","班组","岗位技能","认证结果","上岗证有效日期","上岗证生效日期","工序","线体"};

    public static  String[]TraceabilityManInfoDbCamel = new String[]{"employeeNum","employeeName","entryDate","gender","departmentName","jobName","employeeStatus","hirelingType","keyjob","shiftLeader","team","jobSkills","certificationResult","cereffectiveCycle","cereffectiveFrom","process","line"};

    public static String TraceabilityManInfoTable = "TraceabilityManInfo.xlsx";

    //TraceabilityMaterial

    public static  String[] TraceabilityMaterialInfoExcel  = new String[]{"机型","订单","排程","线体","TrackId","IMEI","GSN","主机料号","主机描述","部件类别","部件料号","部件描述","PSN","部件条码","绑定时间","绑定人"};

    public static  String[]TraceabilityMaterialInfoDbCamel = new String[]{"model","mo","schedule_code","charline","sn","imei","gsn","materialcode","description","materialtype","bjmaterial","bjdescription","psn","keyunitbarcode","createtime","createuser"};

    public static String TraceabilityMaterialInfoTable = "TraceabilityMaterialInfo.xlsx";

    //TraceabilityEnvironmentData

    public static  String[] TraceabilityEnvironmentInfoExcel  = new String[]{"点位","温度","湿度","创建时间"};

    public static  String[]TraceabilityEnvironmentInfoDbCamel = new String[]{"position","temperature","humidity","createDate"};

    public static String TraceabilityEnvironmentInfoTable = "TraceabilityEnvironmentInfo.xlsx";
}
