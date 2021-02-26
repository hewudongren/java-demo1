package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;

public class TraceabilityEnvironmentData implements Serializable {
    private String  id;
    /**
     * 点位
     *
     * @mbggenerated
     */
    private String position;

    /**
     * 温度
     *
     * @mbggenerated
     */
    private String temperature;

    /**
     * 湿度
     *
     * @mbggenerated
     */
    private String humidity;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private String createDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", position=").append(position);
        sb.append(", temperature=").append(temperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}