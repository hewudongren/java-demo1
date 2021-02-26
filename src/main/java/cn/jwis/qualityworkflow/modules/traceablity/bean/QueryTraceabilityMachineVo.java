package cn.jwis.qualityworkflow.modules.traceablity.bean;


import java.util.List;

public class QueryTraceabilityMachineVo {

    private Integer pageNum;
    private Integer pageSize;
    private String startTime;
    private String endTime;
    private String deviceNo;
    private String line;
    private String parameter;
    private List<String> lineList;
    private List<String> deviceTypeList;
    private List<String> deviceNoList;
    private List<String> parameterList;


    private String deviceType;
    private String tableName;
    private String filedName;
    private String dateTime;
    private String lineName;
    private String deviceNoName;
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public List<String> getLineList() {
        return lineList;
    }

    public void setLineList(List<String> lineList) {
        this.lineList = lineList;
    }

    public List<String> getDeviceTypeList() {
        return deviceTypeList;
    }

    public void setDeviceTypeList(List<String> deviceTypeList) {
        this.deviceTypeList = deviceTypeList;
    }

    public List<String> getDeviceNoList() {
        return deviceNoList;
    }

    public void setDeviceNoList(List<String> deviceNoList) {
        this.deviceNoList = deviceNoList;
    }

    public List<String> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<String> parameterList) {
        this.parameterList = parameterList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getDeviceNoName() {
        return deviceNoName;
    }

    public void setDeviceNoName(String deviceNoName) {
        this.deviceNoName = deviceNoName;
    }
}
