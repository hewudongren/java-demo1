package cn.jwis.qualityworkflow.modules.pcn.bean;

import java.util.List;

public class QueryPCNInfo {

        private List<String> changeNameList;
        private List<String> itemList;
        private List<String> modelList;
        private List<String> changeTypeList;
        private List<String> changeTriggerList;
        private Integer pageNum;
        private Integer pageSize;
        private String startTime;
        private String endTime;
        private String assignee;
    public List<String> getChangeNameList() {
        return changeNameList;
    }

    public void setChangeNameList(List<String> changeNameList) {
        this.changeNameList = changeNameList;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public List<String> getModelList() {
        return modelList;
    }

    public void setModelList(List<String> modelList) {
        this.modelList = modelList;
    }

    public List<String> getChangeTypeList() {
        return changeTypeList;
    }

    public void setChangeTypeList(List<String> changeTypeList) {
        this.changeTypeList = changeTypeList;
    }

    public List<String> getChangeTriggerList() {
        return changeTriggerList;
    }

    public void setChangeTriggerList(List<String> changeTriggerList) {
        this.changeTriggerList = changeTriggerList;
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

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
