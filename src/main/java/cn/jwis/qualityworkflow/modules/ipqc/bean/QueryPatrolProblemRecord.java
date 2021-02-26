package cn.jwis.qualityworkflow.modules.ipqc.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class QueryPatrolProblemRecord {

    private List<String> modelList;
    private List<String> worksSectionList;
    private List<String> frequencyList;
    private List<String> problemAttributeList;
    private List<String> inspectorList;
    private String auditDate;
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

    public List<String> getWorksSectionList() {
        return worksSectionList;
    }

    public void setWorksSectionList(List<String> worksSectionList) {
        this.worksSectionList = worksSectionList;
    }

    public List<String> getFrequencyList() {
        return frequencyList;
    }

    public void setFrequencyList(List<String> frequencyList) {
        this.frequencyList = frequencyList;
    }

    public List<String> getProblemAttributeList() {
        return problemAttributeList;
    }

    public void setProblemAttributeList(List<String> problemAttributeList) {
        this.problemAttributeList = problemAttributeList;
    }

    public List<String> getInspectorList() {
        return inspectorList;
    }

    public void setInspectorList(List<String> inspectorList) {
        this.inspectorList = inspectorList;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
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
