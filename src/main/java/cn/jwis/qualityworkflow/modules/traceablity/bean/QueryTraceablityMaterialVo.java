package cn.jwis.qualityworkflow.modules.traceablity.bean;

public class QueryTraceablityMaterialVo {

    private String sn;
    private String bjmaterial;
    private Integer pageNum;
    private Integer pageSize;
    private String startTime;
    private String endTime;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getBjmaterial() {
        return bjmaterial;
    }

    public void setBjmaterial(String bjmaterial) {
        this.bjmaterial = bjmaterial;
    }
}
