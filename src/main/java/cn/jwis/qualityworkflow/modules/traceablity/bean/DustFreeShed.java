package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DustFreeShed implements Serializable {
    private Integer id;

    private BigDecimal illuminance;

    private BigDecimal cleanliness;

    private String temperature;

    private String humidity;

    private Date createtime;

    private String deviceaddress;

    private String deviceno;

    private String remark;

    private String line;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getIlluminance() {
        return illuminance;
    }

    public void setIlluminance(BigDecimal illuminance) {
        this.illuminance = illuminance;
    }

    public BigDecimal getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(BigDecimal cleanliness) {
        this.cleanliness = cleanliness;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDeviceaddress() {
        return deviceaddress;
    }

    public void setDeviceaddress(String deviceaddress) {
        this.deviceaddress = deviceaddress;
    }

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        sb.append(", illuminance=").append(illuminance);
        sb.append(", cleanliness=").append(cleanliness);
        sb.append(", temperature=").append(temperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", createtime=").append(createtime);
        sb.append(", deviceaddress=").append(deviceaddress);
        sb.append(", deviceno=").append(deviceno);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}