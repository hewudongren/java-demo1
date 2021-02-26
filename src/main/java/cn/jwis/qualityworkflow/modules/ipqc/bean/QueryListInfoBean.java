package cn.jwis.qualityworkflow.modules.ipqc.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class QueryListInfoBean {
    private List<String> modelList;
    private List<String> processList;
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
