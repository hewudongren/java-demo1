package cn.jwis.qualityworkflow.modules.pcn.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PCNDashboardVo {

    @ApiModelProperty ("开始时间")
    String startTime;

    @ApiModelProperty ("结束时间")
    String endTime;

    private Integer flag;

    private List<String> itemList;

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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }
}


