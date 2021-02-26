package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;

public class TraceablityEnvironmentMapping implements Serializable {
    private String id;

    /**
     * 序列号
     *
     * @mbggenerated
     */
    private String sn;

    /**
     * 点位
     *
     * @mbggenerated
     */
    private String position;

    /**
     * 工艺段
     *
     * @mbggenerated
     */
    private String processPeriod;

    /**
     * 站点
     *
     * @mbggenerated
     */
    private String site;

    /**
     * 存放位置
     *
     * @mbggenerated
     */
    private String place;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getProcessPeriod() {
        return processPeriod;
    }

    public void setProcessPeriod(String processPeriod) {
        this.processPeriod = processPeriod;
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
        sb.append(", sn=").append(sn);
        sb.append(", position=").append(position);
        sb.append(", processPeriod=").append(processPeriod);
        sb.append(", site=").append(site);
        sb.append(", place=").append(place);
        sb.append(", line=").append(line);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}