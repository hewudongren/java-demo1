package cn.jwis.qualityworkflow.modules.ipqc.bean;

import java.util.List;

import cn.jwis.qualityworkflow.enums.DateType;

public class QueryDashboardVo {
    private String startTime;
    private String endTime;
    private String groupBy;
    private List<String> itemList;
    private String process;
    private DateType dateType;

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

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public DateType getDateType() {
        return dateType;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }
}
