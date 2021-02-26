package cn.jwis.qualityworkflow.modules.ipqc.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PatrolProblemRecord implements Serializable {
    private String id;

    /**
     * 机型
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 工程段
     *
     * @mbggenerated
     */
    private String worksSection;

    /**
     * 线体
     *
     * @mbggenerated
     */
    private String line;
    /**
     * 稽核类型
     *
     * @mbggenerated
     */
    private String auditType;

    /**
     * 日期
     *
     * @mbggenerated
     */
    private String auditDate;

    /**
     * 班次
     *
     * @mbggenerated
     */
    private String frequency;

    /**
     * 检验员
     *
     * @mbggenerated
     */
    private String inspector;

    /**
     * 问题点描述
     *
     * @mbggenerated
     */
    private String problemDescription;

    /**
     * 问题图片
     *
     * @mbggenerated
     */
    private String problemPicture;

    /**
     * 累计次数
     *
     * @mbggenerated
     */
    private Integer cumulativeFrequency;

    /**
     * 问题类型
     *
     * @mbggenerated
     */
    private String problemType;

    /**
     * 故障类别
     *
     * @mbggenerated
     */
    private String faultType;

    /**
     * 问题属性
     *
     * @mbggenerated
     */
    private String problemAttribute;

    /**
     * 班长
     *
     * @mbggenerated
     */
    private String squadLeader;

    /**
     * Owner
     *
     * @mbggenerated
     */
    private String owner;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;

    /**
     * 状态更新时间
     *
     * @mbggenerated
     */
    private String statusUpdateDate;

    /**
     * 确认签字
     *
     * @mbggenerated
     */
    private String isSignature;
    /**
     * 是否重复
     *
     * @mbggenerated
     */
    private String whetherRepetition;
    /**
     * 工号
     *
     * @mbggenerated
     */
    private String station;
    /**
     * 创建时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    private String timeInterval;

    private String auditListDetailId;

    private String auditTemplateDetailId;

    private List<String> problemPictureList;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWorksSection() {
        return worksSection;
    }

    public void setWorksSection(String worksSection) {
        this.worksSection = worksSection;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getProblemPicture() {
        return problemPicture;
    }

    public void setProblemPicture(String problemPicture) {
        this.problemPicture = problemPicture;
    }

    public Integer getCumulativeFrequency() {
        return cumulativeFrequency;
    }

    public void setCumulativeFrequency(Integer cumulativeFrequency) {
        this.cumulativeFrequency = cumulativeFrequency;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getProblemAttribute() {
        return problemAttribute;
    }

    public void setProblemAttribute(String problemAttribute) {
        this.problemAttribute = problemAttribute;
    }

    public String getSquadLeader() {
        return squadLeader;
    }

    public void setSquadLeader(String squadLeader) {
        this.squadLeader = squadLeader;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusUpdateDate() {
        return statusUpdateDate;
    }

    public void setStatusUpdateDate(String statusUpdateDate) {
        this.statusUpdateDate = statusUpdateDate;
    }

    public String getIsSignature() {
        return isSignature;
    }

    public void setIsSignature(String isSignature) {
        this.isSignature = isSignature;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }


    public String getAuditListDetailId() {
        return auditListDetailId;
    }

    public void setAuditListDetailId(String auditListDetailId) {
        this.auditListDetailId = auditListDetailId;
    }

    public List<String> getProblemPictureList() {
        return problemPictureList;
    }

    public void setProblemPictureList(List<String> problemPictureList) {
        this.problemPictureList = problemPictureList;
    }

    public String getWhetherRepetition() {
        return whetherRepetition;
    }

    public void setWhetherRepetition(String whetherRepetition) {
        this.whetherRepetition = whetherRepetition;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuditTemplateDetailId() {
        return auditTemplateDetailId;
    }

    public void setAuditTemplateDetailId(String auditTemplateDetailId) {
        this.auditTemplateDetailId = auditTemplateDetailId;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", model=").append(model);
        sb.append(", worksSection=").append(worksSection);
        sb.append(", line=").append(line);
        sb.append(", auditDate=").append(auditDate);
        sb.append(", auditType=").append(auditType);
        sb.append(", frequency=").append(frequency);
        sb.append(", inspector=").append(inspector);
        sb.append(", problemDescription=").append(problemDescription);
        sb.append(", problemPicture=").append(problemPicture);
        sb.append(", cumulativeFrequency=").append(cumulativeFrequency);
        sb.append(", problemType=").append(problemType);
        sb.append(", faultType=").append(faultType);
        sb.append(", problemAttribute=").append(problemAttribute);
        sb.append(", squadLeader=").append(squadLeader);
        sb.append(", owner=").append(owner);
        sb.append(", status=").append(status);
        sb.append(", statusUpdateDate=").append(statusUpdateDate);
        sb.append(", isSignature=").append(isSignature);
        sb.append(", whetherRepetition=").append(whetherRepetition);
        sb.append(", station=").append(station);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}