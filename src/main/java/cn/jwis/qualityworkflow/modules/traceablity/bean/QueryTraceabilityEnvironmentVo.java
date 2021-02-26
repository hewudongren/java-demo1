package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.util.List;

public class QueryTraceabilityEnvironmentVo {
    private Integer pageNum;
    private Integer pageSize;
    private String startTime;
    private String endTime;
    private String line;
    private String probeNumber;
    private String parameter;
    private List<String> positionList;
    private List<String> parameterList;
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getProbeNumber() {
        return probeNumber;
    }

    public void setProbeNumber(String probeNumber) {
        this.probeNumber = probeNumber;
    }

    public List<String> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<String> positionList) {
        this.positionList = positionList;
    }

    public List<String> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<String> parameterList) {
        this.parameterList = parameterList;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
