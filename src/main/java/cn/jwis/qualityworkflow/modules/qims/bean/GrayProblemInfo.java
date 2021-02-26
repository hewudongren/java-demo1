package cn.jwis.qualityworkflow.modules.qims.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class GrayProblemInfo implements Serializable {
    /**
     * 主键ID
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 问题编号
     *
     * @mbggenerated
     */
    private String problemNumber;

    /**
     * 问题类型
     *
     * @mbggenerated
     */
    private String problemType;

    /**
     * 发生时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date occurrenceTime;

    /**
     * 段别
     *
     * @mbggenerated
     */
    private String paragraphParting;

    /**
     * 线体
     *
     * @mbggenerated
     */
    private String line;

    /**
     * 发生站点
     *
     * @mbggenerated
     */
    private String occurrenceSite;

    /**
     * 机型
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 班次
     *
     * @mbggenerated
     */
    private String frequency;

    /**
     * 报告人
     *
     * @mbggenerated
     */
    private String reporter;

    /**
     * 问题描述
     *
     * @mbggenerated
     */
    private String problemDescription;

    /**
     * 响应时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date responseTime;

    /**
     * 组别
     *
     * @mbggenerated
     */
    private String groups;

    /**
     * 响应人（待回复Owner
     *
     * @mbggenerated
     */
    private String responder;

    /**
     * 处理完成时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date processingTime;


    /**
     * 缺陷类型
     *
     * @mbggenerated
     */
    private String defectType;

    /**
     * 缺陷数量
     *
     * @mbggenerated
     */
    private Integer defectNumber;

    /**
     * 机型Owner
     *
     * @mbggenerated
     */
    private String modelOwner;

    /**
     * 问题回复人
     *
     * @mbggenerated
     */
    private String qaPerson;

    /**
     * 结案及时性
     *
     * @mbggenerated
     */
    private String isTimely;

    /**
     * 解决方案及分析
     *
     * @mbggenerated
     */
    private String solutionAnalysis;

    /**
     * 响应LT
     *
     * @mbggenerated
     */
    private String responseLt;

    /**
     * 问题处理LT
     *
     * @mbggenerated
     */
    private String problemSolvingLt;

    /**
     * LT
     *
     * @mbggenerated
     */
    private String lt;

    /**
     * RecordStatus
     *
     * @mbggenerated
     */
    private String recordStatus;

    /**
     * 问题录入人
     *
     * @mbggenerated
     */
    private String questionTaker;

    /**
     * 问题录入时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date questionEntryTime;

    /**
     * 工厂
     *
     * @mbggenerated
     */
    private String factory;

    /**
     * 部门
     *
     * @mbggenerated
     */
    private String department;

    /**
     * Owner所属组
     *
     * @mbggenerated
     */
    private String ownerGroup;

    /**
     * 问题编码的标识
     *
     * @mbggenerated
     */
    private String itemNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date qaTime;

    private String assigneer;

    private String assigneerDepartment;

    private String businessDatetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblemNumber() {
        return problemNumber;
    }

    public void setProblemNumber(String problemNumber) {
        this.problemNumber = problemNumber;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    public String getParagraphParting() {
        return paragraphParting;
    }

    public void setParagraphParting(String paragraphParting) {
        this.paragraphParting = paragraphParting;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getOccurrenceSite() {
        return occurrenceSite;
    }

    public void setOccurrenceSite(String occurrenceSite) {
        this.occurrenceSite = occurrenceSite;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getResponder() {
        return responder;
    }

    public void setResponder(String responder) {
        this.responder = responder;
    }

    public Date getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Date processingTime) {
        this.processingTime = processingTime;
    }

    public String getDefectType() {
        return defectType;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public Integer getDefectNumber() {
        return defectNumber;
    }

    public void setDefectNumber(Integer defectNumber) {
        this.defectNumber = defectNumber;
    }

    public String getModelOwner() {
        return modelOwner;
    }

    public void setModelOwner(String modelOwner) {
        this.modelOwner = modelOwner;
    }

    public String getQaPerson() {
        return qaPerson;
    }

    public void setQaPerson(String qaPerson) {
        this.qaPerson = qaPerson;
    }

    public String getIsTimely() {
        return isTimely;
    }

    public void setIsTimely(String isTimely) {
        this.isTimely = isTimely;
    }

    public String getSolutionAnalysis() {
        return solutionAnalysis;
    }

    public void setSolutionAnalysis(String solutionAnalysis) {
        this.solutionAnalysis = solutionAnalysis;
    }

    public String getResponseLt() {
        return responseLt;
    }

    public void setResponseLt(String responseLt) {
        this.responseLt = responseLt;
    }

    public String getProblemSolvingLt() {
        return problemSolvingLt;
    }

    public void setProblemSolvingLt(String problemSolvingLt) {
        this.problemSolvingLt = problemSolvingLt;
    }

    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getQuestionTaker() {
        return questionTaker;
    }

    public void setQuestionTaker(String questionTaker) {
        this.questionTaker = questionTaker;
    }

    public Date getQuestionEntryTime() {
        return questionEntryTime;
    }

    public void setQuestionEntryTime(Date questionEntryTime) {
        this.questionEntryTime = questionEntryTime;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(String ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Date getQaTime() {
        return qaTime;
    }

    public void setQaTime(Date qaTime) {
        this.qaTime = qaTime;
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

    public String getBusinessDatetime() {
        return businessDatetime;
    }

    public void setBusinessDatetime(String businessDatetime) {
        this.businessDatetime = businessDatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", problemNumber=").append(problemNumber);
        sb.append(", problemType=").append(problemType);
        sb.append(", occurrenceTime=").append(occurrenceTime);
        sb.append(", paragraphParting=").append(paragraphParting);
        sb.append(", line=").append(line);
        sb.append(", occurrenceSite=").append(occurrenceSite);
        sb.append(", model=").append(model);
        sb.append(", frequency=").append(frequency);
        sb.append(", reporter=").append(reporter);
        sb.append(", problemDescription=").append(problemDescription);
        sb.append(", responseTime=").append(responseTime);
        sb.append(", group=").append(groups);
        sb.append(", responder=").append(responder);
        sb.append(", processingTime=").append(processingTime);
        sb.append(", defectType=").append(defectType);
        sb.append(", defectNumber=").append(defectNumber);
        sb.append(", modelOwner=").append(modelOwner);
        sb.append(", qaPerson=").append(qaPerson);
        sb.append(", isTimely=").append(isTimely);
        sb.append(", solutionAnalysis=").append(solutionAnalysis);
        sb.append(", responseLt=").append(responseLt);
        sb.append(", problemSolvingLt=").append(problemSolvingLt);
        sb.append(", lt=").append(lt);
        sb.append(", recordStatus=").append(recordStatus);
        sb.append(", questionTaker=").append(questionTaker);
        sb.append(", questionEntryTime=").append(questionEntryTime);
        sb.append(", factory=").append(factory);
        sb.append(", department=").append(department);
        sb.append(", ownerGroup=").append(ownerGroup);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}