package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.util.List;

public class QueryTraceabilityManVo {
    private List<String> departmentList;
    private List<String> employeeNumList;
    private List<String> processList;
    private List<String> lineList;
    private List<String> jobsList;
    private List<String> resultList;

    //4M1E
    private String employeeName;

    private Integer pageNum;
    private Integer pageSize;
    private String startTime;
    private String endTime;

    public List<String> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<String> departmentList) {
        this.departmentList = departmentList;
    }

    public List<String> getEmployeeNumList() {
        return employeeNumList;
    }

    public void setEmployeeNumList(List<String> employeeNumList) {
        this.employeeNumList = employeeNumList;
    }

    public List<String> getProcessList() {
        return processList;
    }

    public void setProcessList(List<String> processList) {
        this.processList = processList;
    }

    public List<String> getLineList() {
        return lineList;
    }

    public void setLineList(List<String> lineList) {
        this.lineList = lineList;
    }

    public List<String> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<String> jobsList) {
        this.jobsList = jobsList;
    }

    public List<String> getResultList() {
        return resultList;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
    }

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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
