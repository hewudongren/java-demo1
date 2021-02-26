package cn.jwis.qualityworkflow.modules.ipqc.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class QueryAuditQualityProblem {


    private List<String> modelList;
    private List<String> processList;
    private List<String> problemTypeList;
    private List<String> problemAttributeList;
    private List<String> statusList;
    private Integer pageNum;
    private Integer pageSize;
    private String assignee;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String startTime;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String endTime;

    public List<String> getModelList() {
        return modelList;
    }

    public void setModelList(List<String> modelList) {
        this.modelList = modelList;
    }

    public List<String> getProcessList() {
        return processList;
    }

    public void setProcessList(List<String> processList) {
        this.processList = processList;
    }

    public List<String> getProblemTypeList() {
        return problemTypeList;
    }

    public void setProblemTypeList(List<String> problemTypeList) {
        this.problemTypeList = problemTypeList;
    }

    public List<String> getProblemAttributeList() {
        return problemAttributeList;
    }

    public void setProblemAttributeList(List<String> problemAttributeList) {
        this.problemAttributeList = problemAttributeList;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
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

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
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
}
