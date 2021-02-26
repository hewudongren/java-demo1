package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;
import java.util.List;

public class QueryTraceabilityMethodVo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String modelName;
    private List<String> fileOwnerList;
    private String fileName;

    private Integer pageNum;
    private Integer pageSize;
    private String startTime;
    private String endTime;
    private String assignee;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getFileOwnerList() {
        return fileOwnerList;
    }

    public void setFileOwnerList(List<String> fileOwnerList) {
        this.fileOwnerList = fileOwnerList;
    }
}
