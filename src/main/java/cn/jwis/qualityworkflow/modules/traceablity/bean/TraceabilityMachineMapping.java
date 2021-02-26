package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;

public class TraceabilityMachineMapping implements Serializable {
    private String id;

    private String machineSn;

    private String lineName;

    private String site;

    private String place;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMachineSn() {
        return machineSn;
    }

    public void setMachineSn(String machineSn) {
        this.machineSn = machineSn;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", machineSn=").append(machineSn);
        sb.append(", lineName=").append(lineName);
        sb.append(", site=").append(site);
        sb.append(", place=").append(place);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}