package cn.jwis.qualityworkflow.modules.qims.bean;

public class QimsTargetSearch {


    private String problemType;

    private Integer cycle;

    private String startTime;

    private String endTime;

    private Integer page;

    private Integer size;

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "QimsTargetSearch{" + "problemType='" + problemType + '\'' + ", cycle=" + cycle + ", startTime='" + startTime + '\'' + ", endTime='" + endTime + '\'' + ", page=" + page + ", size=" + size + '}';
    }
}