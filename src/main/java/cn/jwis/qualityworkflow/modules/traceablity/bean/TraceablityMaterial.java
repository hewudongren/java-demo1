package cn.jwis.qualityworkflow.modules.traceablity.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class TraceablityMaterial implements Serializable {
    private String id;

    private String model;

    private String process;

    private String pn;

    private String wip;

    private Integer inventoryNumber;

    private Integer shipmentNumber;

    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

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

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getWip() {
        return wip;
    }

    public void setWip(String wip) {
        this.wip = wip;
    }

    public Integer getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Integer inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Integer getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(Integer shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", model=").append(model);
        sb.append(", process=").append(process);
        sb.append(", pn=").append(pn);
        sb.append(", wip=").append(wip);
        sb.append(", inventoryNumber=").append(inventoryNumber);
        sb.append(", shipmentNumber=").append(shipmentNumber);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}