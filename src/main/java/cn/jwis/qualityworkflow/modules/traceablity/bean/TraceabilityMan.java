package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;

public class TraceabilityMan implements Serializable {
    private String id;

    /**
     * 员工编号
     *
     * @mbggenerated
     */
    private String employeeNum;

    /**
     * 员工名称
     *
     * @mbggenerated
     */
    private String employeeName;

    /**
     * 入职日期
     *
     * @mbggenerated
     */
    private String entryDate;

    /**
     * 性别
     *
     * @mbggenerated
     */
    private String gender;

    /**
     * 部门名称
     *
     * @mbggenerated
     */
    private String departmentName;

    /**
     * 岗位名称
     *
     * @mbggenerated
     */
    private String jobName;

    /**
     * 岗位状态
     *
     * @mbggenerated
     */
    private String employeeStatus;

    /**
     * 佣工类型
     *
     * @mbggenerated
     */
    private String hirelingType;

    /**
     * 是否关键岗
     *
     * @mbggenerated
     */
    private String keyjob;

    /**
     * 带班班长
     *
     * @mbggenerated
     */
    private Integer shiftLeader;

    /**
     * 班组
     *
     * @mbggenerated
     */
    private String team;

    /**
     * 岗位技能
     *
     * @mbggenerated
     */
    private String jobSkills;

    /**
     * 认证结果
     *
     * @mbggenerated
     */
    private String certificationResult;

    /**
     * 上岗证有效期
     *
     * @mbggenerated
     */
    private String cereffectiveCycle;

    /**
     * 上岗证生效日期
     *
     * @mbggenerated
     */
    private String cereffectiveFrom;
    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private String createDate;
    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String creator;
    /**
     * 工序
     *
     * @mbggenerated
     */
    private String process;
    /**
     * 线体
     *
     * @mbggenerated
     */
    private String line;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getHirelingType() {
        return hirelingType;
    }

    public void setHirelingType(String hirelingType) {
        this.hirelingType = hirelingType;
    }

    public String getKeyjob() {
        return keyjob;
    }

    public void setKeyjob(String keyjob) {
        this.keyjob = keyjob;
    }

    public Integer getShiftLeader() {
        return shiftLeader;
    }

    public void setShiftLeader(Integer shiftLeader) {
        this.shiftLeader = shiftLeader;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(String jobSkills) {
        this.jobSkills = jobSkills;
    }

    public String getCertificationResult() {
        return certificationResult;
    }

    public void setCertificationResult(String certificationResult) {
        this.certificationResult = certificationResult;
    }

    public String getCereffectiveCycle() {
        return cereffectiveCycle;
    }

    public void setCereffectiveCycle(String cereffectiveCycle) {
        this.cereffectiveCycle = cereffectiveCycle;
    }

    public String getCereffectiveFrom() {
        return cereffectiveFrom;
    }

    public void setCereffectiveFrom(String cereffectiveFrom) {
        this.cereffectiveFrom = cereffectiveFrom;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", employeeNum=").append(employeeNum);
        sb.append(", employeeName=").append(employeeName);
        sb.append(", entryDate=").append(entryDate);
        sb.append(", gender=").append(gender);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", jobName=").append(jobName);
        sb.append(", employeeStatus=").append(employeeStatus);
        sb.append(", hirelingType=").append(hirelingType);
        sb.append(", keyjob=").append(keyjob);
        sb.append(", shiftLeader=").append(shiftLeader);
        sb.append(", team=").append(team);
        sb.append(", jobSkills=").append(jobSkills);
        sb.append(", certificationResult=").append(certificationResult);
        sb.append(", cereffectiveCycle=").append(cereffectiveCycle);
        sb.append(", cereffectiveFrom=").append(cereffectiveFrom);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", createDate=").append(createDate);
        sb.append(", creator=").append(creator);
        sb.append(", process=").append(process);
        sb.append(", line=").append(line);
        sb.append("]");
        return sb.toString();
    }
}