package cn.jwis.qualityworkflow.modules.ipqc.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AuditQualityProblemInfo implements Serializable {
    private String id;

    /**
     * 稽核类型
     *
     * @mbggenerated
     */
    private String auditType;

    /**
     * ODM
     *
     * @mbggenerated
     */
    private String odm;

    /**
     * 稽核日期
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

    private List<String> problemPictureList;

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
     * 问题发生时间
     *
     * @mbggenerated
     */
    private String problemOccurDate;

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
     * Owner
     *
     * @mbggenerated
     */
    private String owner;
    /**
     * 责任人
     *
     * @mbggenerated
     */
    private String responsible;

    /**
     * 原因分析
     *
     * @mbggenerated
     */
    private String causeAnalysis;

    /**
     * 改善对策
     *
     * @mbggenerated
     */
    private String improvementStrategy;

    /**
     * 原因分析人
     *
     * @mbggenerated
     */
    private String causalAnalyst;

    /**
     * 完成时间
     *
     * @mbggenerated
     */
    private String finishTime;

    /**
     * IPQC审核结果
     *
     * @mbggenerated
     */
    private String ipqcAuditResults;

    /**
     * IPQC审核意见
     *
     * @mbggenerated
     */
    private String ipqcAuditOpinion;

    /**
     * IPQC确认人
     *
     * @mbggenerated
     */
    private String ipqcConfirmer;

    /**
     * IPQC审核人
     *
     * @mbggenerated
     */
    private String ipqcAuditor;

    /**
     * IPQC审核时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ipqcAuditDate;

    /**
     * QE审核结果
     *
     * @mbggenerated
     */
    private String qeAuditResults;

    /**
     * QE审核意见
     *
     * @mbggenerated
     */
    private String qeAuditOpinion;

    /**
     * QE审核人
     *
     * @mbggenerated
     */
    private String qeAuditor;

    /**
     * QE审核时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date qeAuditDate;

    /**
     * ODM审核结果
     *
     * @mbggenerated
     */
    private String odmAuditResults;

    /**
     * ODM审核意见
     *
     * @mbggenerated
     */
    private String odmAuditOpinion;

    /**
     * ODM审核人
     *
     * @mbggenerated
     */
    private String odmAuditor;

    /**
     * ODM审核时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date odmAuditDate;

    /**
     * 状态
     *
     * @mbggenerated
     */
    private String status;
    /**
     * 状态
     *
     * @mbggenerated
     */
    private String assigneer;
    /**
     * 状态
     *
     * @mbggenerated
     */
    private String assigneerDepartment;
    /**
     * creator
     *
     * @mbggenerated
     */
    private String creator;
    /**
     * 创建时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    /**
     * 修改时间
     *
     * @mbggenerated
     */
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    private String taskId;
    private String processInstanceId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getOdm() {
        return odm;
    }

    public void setOdm(String odm) {
        this.odm = odm;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getCauseAnalysis() {
        return causeAnalysis;
    }

    public void setCauseAnalysis(String causeAnalysis) {
        this.causeAnalysis = causeAnalysis;
    }

    public String getImprovementStrategy() {
        return improvementStrategy;
    }

    public void setImprovementStrategy(String improvementStrategy) {
        this.improvementStrategy = improvementStrategy;
    }

    public String getCausalAnalyst() {
        return causalAnalyst;
    }

    public void setCausalAnalyst(String causalAnalyst) {
        this.causalAnalyst = causalAnalyst;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getIpqcAuditResults() {
        return ipqcAuditResults;
    }

    public void setIpqcAuditResults(String ipqcAuditResults) {
        this.ipqcAuditResults = ipqcAuditResults;
    }

    public String getIpqcAuditOpinion() {
        return ipqcAuditOpinion;
    }

    public void setIpqcAuditOpinion(String ipqcAuditOpinion) {
        this.ipqcAuditOpinion = ipqcAuditOpinion;
    }

    public String getIpqcConfirmer() {
        return ipqcConfirmer;
    }

    public void setIpqcConfirmer(String ipqcConfirmer) {
        this.ipqcConfirmer = ipqcConfirmer;
    }

    public String getIpqcAuditor() {
        return ipqcAuditor;
    }

    public void setIpqcAuditor(String ipqcAuditor) {
        this.ipqcAuditor = ipqcAuditor;
    }

    public Date getIpqcAuditDate() {
        return ipqcAuditDate;
    }

    public void setIpqcAuditDate(Date ipqcAuditDate) {
        this.ipqcAuditDate = ipqcAuditDate;
    }

    public String getQeAuditResults() {
        return qeAuditResults;
    }

    public void setQeAuditResults(String qeAuditResults) {
        this.qeAuditResults = qeAuditResults;
    }

    public String getQeAuditOpinion() {
        return qeAuditOpinion;
    }

    public void setQeAuditOpinion(String qeAuditOpinion) {
        this.qeAuditOpinion = qeAuditOpinion;
    }

    public String getQeAuditor() {
        return qeAuditor;
    }

    public void setQeAuditor(String qeAuditor) {
        this.qeAuditor = qeAuditor;
    }

    public Date getQeAuditDate() {
        return qeAuditDate;
    }

    public void setQeAuditDate(Date qeAuditDate) {
        this.qeAuditDate = qeAuditDate;
    }

    public String getOdmAuditResults() {
        return odmAuditResults;
    }

    public void setOdmAuditResults(String odmAuditResults) {
        this.odmAuditResults = odmAuditResults;
    }

    public String getOdmAuditOpinion() {
        return odmAuditOpinion;
    }

    public void setOdmAuditOpinion(String odmAuditOpinion) {
        this.odmAuditOpinion = odmAuditOpinion;
    }

    public String getOdmAuditor() {
        return odmAuditor;
    }

    public void setOdmAuditor(String odmAuditor) {
        this.odmAuditor = odmAuditor;
    }

    public Date getOdmAuditDate() {
        return odmAuditDate;
    }

    public void setOdmAuditDate(Date odmAuditDate) {
        this.odmAuditDate = odmAuditDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getAssigneer() {
        return assigneer;
    }

    public void setAssigneer(String assigneer) {
        this.assigneer = assigneer;
    }

    public String getAssigneerDepartment() {
        return assigneerDepartment;
    }

    public void setAssigneerDepartment(String assigneerDepartment) {
        this.assigneerDepartment = assigneerDepartment;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public List<String> getProblemPictureList() {
        return problemPictureList;
    }

    public void setProblemPictureList(List<String> problemPictureList) {
        this.problemPictureList = problemPictureList;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getProblemOccurDate() {
        return problemOccurDate;
    }

    public void setProblemOccurDate(String problemOccurDate) {
        this.problemOccurDate = problemOccurDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", auditType=").append(auditType);
        sb.append(", odm=").append(odm);
        sb.append(", auditDate=").append(auditDate);
        sb.append(", frequency=").append(frequency);
        sb.append(", model=").append(model);
        sb.append(", worksSection=").append(worksSection);
        sb.append(", line=").append(line);
        sb.append(", problemDescription=").append(problemDescription);
        sb.append(", problemPicture=").append(problemPicture);
        sb.append(", problemOccurDate=").append(problemOccurDate);
        sb.append(", cumulativeFrequency=").append(cumulativeFrequency);
        sb.append(", problemType=").append(problemType);
        sb.append(", faultType=").append(faultType);
        sb.append(", problemAttribute=").append(problemAttribute);
        sb.append(", owner=").append(owner);
        sb.append(", responsible=").append(responsible);
        sb.append(", causeAnalysis=").append(causeAnalysis);
        sb.append(", improvementStrategy=").append(improvementStrategy);
        sb.append(", causalAnalyst=").append(causalAnalyst);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", ipqcAuditResults=").append(ipqcAuditResults);
        sb.append(", ipqcAuditOpinion=").append(ipqcAuditOpinion);
        sb.append(", ipqcConfirmer=").append(ipqcConfirmer);
        sb.append(", ipqcAuditor=").append(ipqcAuditor);
        sb.append(", ipqcAuditDate=").append(ipqcAuditDate);
        sb.append(", qeAuditResults=").append(qeAuditResults);
        sb.append(", qeAuditOpinion=").append(qeAuditOpinion);
        sb.append(", qeAuditor=").append(qeAuditor);
        sb.append(", qeAuditDate=").append(qeAuditDate);
        sb.append(", odmAuditResults=").append(odmAuditResults);
        sb.append(", odmAuditOpinion=").append(odmAuditOpinion);
        sb.append(", odmAuditor=").append(odmAuditor);
        sb.append(", odmAuditDate=").append(odmAuditDate);
        sb.append(", status=").append(status);
        sb.append(", assigneer=").append(assigneer);
        sb.append(", assigneerDepartment=").append(assigneerDepartment);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}