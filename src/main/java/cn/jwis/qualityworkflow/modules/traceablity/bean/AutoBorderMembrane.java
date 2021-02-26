package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;
import java.util.Date;

public class AutoBorderMembrane implements Serializable {
    private Long id;

    private String machineId;

    private Date createdTime;

    private Integer totalOutput;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private String attribute4;

    private String attribute5;

    private String attribute6;

    private byte[] runStatus;

    private byte[] stopStatus;

    private byte[] pauseStatus;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getTotalOutput() {
        return totalOutput;
    }

    public void setTotalOutput(Integer totalOutput) {
        this.totalOutput = totalOutput;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public byte[] getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(byte[] runStatus) {
        this.runStatus = runStatus;
    }

    public byte[] getStopStatus() {
        return stopStatus;
    }

    public void setStopStatus(byte[] stopStatus) {
        this.stopStatus = stopStatus;
    }

    public byte[] getPauseStatus() {
        return pauseStatus;
    }

    public void setPauseStatus(byte[] pauseStatus) {
        this.pauseStatus = pauseStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", machineId=").append(machineId);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", totalOutput=").append(totalOutput);
        sb.append(", attribute1=").append(attribute1);
        sb.append(", attribute2=").append(attribute2);
        sb.append(", attribute3=").append(attribute3);
        sb.append(", attribute4=").append(attribute4);
        sb.append(", attribute5=").append(attribute5);
        sb.append(", attribute6=").append(attribute6);
        sb.append(", runStatus=").append(runStatus);
        sb.append(", stopStatus=").append(stopStatus);
        sb.append(", pauseStatus=").append(pauseStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}