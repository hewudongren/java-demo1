package cn.jwis.qualityworkflow.modules.qims.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class QimsTargetInfo implements Serializable {
    /**
     * 主键，唯一标识
     *
     * @mbggenerated
     */
    private String id;

    /**
     * 问题类型
     *
     * @mbggenerated
     */
    private String problemType;

    /**
     * 周期标识
     *
     * @mbggenerated
     */
    private Integer cycle;

    /**
     * 及时关闭率
     *
     * @mbggenerated
     */
    private Float timelyClosingRate;

    /**
     * 关闭率
     *
     * @mbggenerated
     */
    private Float closingRate;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String creator;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    /**
     * 修改时间
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Float getTimelyClosingRate() {
        return timelyClosingRate;
    }

    public void setTimelyClosingRate(Float timelyClosingRate) {
        this.timelyClosingRate = timelyClosingRate;
    }

    public Float getClosingRate() {
        return closingRate;
    }

    public void setClosingRate(Float closingRate) {
        this.closingRate = closingRate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", problemType=").append(problemType);
        sb.append(", cycle=").append(cycle);
        sb.append(", timelyClosingRate=").append(timelyClosingRate);
        sb.append(", closingRate=").append(closingRate);
        sb.append(", remarks=").append(remarks);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}