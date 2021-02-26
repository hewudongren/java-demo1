package cn.jwis.qualityworkflow.modules.ipqc.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QueryTemplateInfoBean {
    private List<String> modelList;
    private List<String> workSectionList;
    private List<String> creatorList;
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

    public List<String> getWorkSectionList() {
        return workSectionList;
    }

    public void setWorkSectionList(List<String> workSectionList) {
        this.workSectionList = workSectionList;
    }

    public List<String> getCreatorList() {
        return creatorList;
    }

    public void setCreatorList(List<String> creatorList) {
        this.creatorList = creatorList;
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
