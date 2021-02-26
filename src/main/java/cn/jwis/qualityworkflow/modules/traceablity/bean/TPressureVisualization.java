package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;
import java.util.Date;

public class TPressureVisualization implements Serializable {
    private Integer tId;

    private Date tCreationTime;

    private Date tModifyTime;

    private String tWorkstation;

    private String tDeviceAddress;

    private String tPackingPressure;

    private String tPressureHoldingTime;

    private String tUpperLimitPressure;

    private String tLowerLimitPressure;

    private String tUpperLimitHoldingTime;

    private String tHoldingTimeLowerLimit;

    private String tEquipmentState;

    private String tComputerName;

    private String tIpAddress;

    private String tMacAddress;

    private String tSystemVersion;

    private String tGuid;

    private String deviceNo;

    private String line;
    private static final long serialVersionUID = 1L;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Date gettCreationTime() {
        return tCreationTime;
    }

    public void settCreationTime(Date tCreationTime) {
        this.tCreationTime = tCreationTime;
    }

    public Date gettModifyTime() {
        return tModifyTime;
    }

    public void settModifyTime(Date tModifyTime) {
        this.tModifyTime = tModifyTime;
    }

    public String gettWorkstation() {
        return tWorkstation;
    }

    public void settWorkstation(String tWorkstation) {
        this.tWorkstation = tWorkstation;
    }

    public String gettDeviceAddress() {
        return tDeviceAddress;
    }

    public void settDeviceAddress(String tDeviceAddress) {
        this.tDeviceAddress = tDeviceAddress;
    }

    public String gettPackingPressure() {
        return tPackingPressure;
    }

    public void settPackingPressure(String tPackingPressure) {
        this.tPackingPressure = tPackingPressure;
    }

    public String gettPressureHoldingTime() {
        return tPressureHoldingTime;
    }

    public void settPressureHoldingTime(String tPressureHoldingTime) {
        this.tPressureHoldingTime = tPressureHoldingTime;
    }

    public String gettUpperLimitPressure() {
        return tUpperLimitPressure;
    }

    public void settUpperLimitPressure(String tUpperLimitPressure) {
        this.tUpperLimitPressure = tUpperLimitPressure;
    }

    public String gettLowerLimitPressure() {
        return tLowerLimitPressure;
    }

    public void settLowerLimitPressure(String tLowerLimitPressure) {
        this.tLowerLimitPressure = tLowerLimitPressure;
    }

    public String gettUpperLimitHoldingTime() {
        return tUpperLimitHoldingTime;
    }

    public void settUpperLimitHoldingTime(String tUpperLimitHoldingTime) {
        this.tUpperLimitHoldingTime = tUpperLimitHoldingTime;
    }

    public String gettHoldingTimeLowerLimit() {
        return tHoldingTimeLowerLimit;
    }

    public void settHoldingTimeLowerLimit(String tHoldingTimeLowerLimit) {
        this.tHoldingTimeLowerLimit = tHoldingTimeLowerLimit;
    }

    public String gettEquipmentState() {
        return tEquipmentState;
    }

    public void settEquipmentState(String tEquipmentState) {
        this.tEquipmentState = tEquipmentState;
    }

    public String gettComputerName() {
        return tComputerName;
    }

    public void settComputerName(String tComputerName) {
        this.tComputerName = tComputerName;
    }

    public String gettIpAddress() {
        return tIpAddress;
    }

    public void settIpAddress(String tIpAddress) {
        this.tIpAddress = tIpAddress;
    }

    public String gettMacAddress() {
        return tMacAddress;
    }

    public void settMacAddress(String tMacAddress) {
        this.tMacAddress = tMacAddress;
    }

    public String gettSystemVersion() {
        return tSystemVersion;
    }

    public void settSystemVersion(String tSystemVersion) {
        this.tSystemVersion = tSystemVersion;
    }

    public String gettGuid() {
        return tGuid;
    }

    public void settGuid(String tGuid) {
        this.tGuid = tGuid;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
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
        sb.append(", tId=").append(tId);
        sb.append(", tCreationTime=").append(tCreationTime);
        sb.append(", tModifyTime=").append(tModifyTime);
        sb.append(", tWorkstation=").append(tWorkstation);
        sb.append(", tDeviceAddress=").append(tDeviceAddress);
        sb.append(", tPackingPressure=").append(tPackingPressure);
        sb.append(", tPressureHoldingTime=").append(tPressureHoldingTime);
        sb.append(", tUpperLimitPressure=").append(tUpperLimitPressure);
        sb.append(", tLowerLimitPressure=").append(tLowerLimitPressure);
        sb.append(", tUpperLimitHoldingTime=").append(tUpperLimitHoldingTime);
        sb.append(", tHoldingTimeLowerLimit=").append(tHoldingTimeLowerLimit);
        sb.append(", tEquipmentState=").append(tEquipmentState);
        sb.append(", tComputerName=").append(tComputerName);
        sb.append(", tIpAddress=").append(tIpAddress);
        sb.append(", tMacAddress=").append(tMacAddress);
        sb.append(", tSystemVersion=").append(tSystemVersion);
        sb.append(", tGuid=").append(tGuid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}