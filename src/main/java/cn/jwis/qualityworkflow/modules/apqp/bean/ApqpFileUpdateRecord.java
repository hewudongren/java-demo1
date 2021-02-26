package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.io.Serializable;
import java.util.Date;

public class ApqpFileUpdateRecord implements Serializable {
    private String id;

    /**
     * 机型名称
     *
     * @mbggenerated
     */
    private String model;

    /**
     * 文件名称
     *
     * @mbggenerated
     */
    private String fileName;

    /**
     * 文件版本
     *
     * @mbggenerated
     */
    private String fileVersion;

    /**
     * 文件Owner
     *
     * @mbggenerated
     */
    private String fileOwner;

    /**
     * 文件有效时间
     *
     * @mbggenerated
     */
    private Date fileEffectiveTime;

    /**
     * 文件到期时间
     *
     * @mbggenerated
     */
    private Date fileOverdueTime;

    /**
     * 文件修改时间
     *
     * @mbggenerated
     */
    private Date fileUpdateTime;

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
    private Date createDate;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateDate;

    private static final long serialVersionUID = 1L;


    public ApqpFileUpdateRecord() {
    }

    public ApqpFileUpdateRecord(CreateFileUpdateRecord record) {
        this.model = record.getModel();
        this.fileName = record.getFileName();
        this.fileEffectiveTime = record.getFileEffectiveTime();
        this.fileOverdueTime = record.getFileOverdueTime();
        this.fileUpdateTime = record.getFileUpdateTime();
        this.fileOwner = record.getFileOwner();
        this.fileVersion = record.getFileVersion();
        this.creator = record.getCreator();
    }

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public Date getFileEffectiveTime() {
        return fileEffectiveTime;
    }

    public void setFileEffectiveTime(Date fileEffectiveTime) {
        this.fileEffectiveTime = fileEffectiveTime;
    }

    public Date getFileOverdueTime() {
        return fileOverdueTime;
    }

    public void setFileOverdueTime(Date fileOverdueTime) {
        this.fileOverdueTime = fileOverdueTime;
    }

    public Date getFileUpdateTime() {
        return fileUpdateTime;
    }

    public void setFileUpdateTime(Date fileUpdateTime) {
        this.fileUpdateTime = fileUpdateTime;
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
        sb.append(", model=").append(model);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileVersion=").append(fileVersion);
        sb.append(", fileOwner=").append(fileOwner);
        sb.append(", fileEffectiveTime=").append(fileEffectiveTime);
        sb.append(", fileOverdueTime=").append(fileOverdueTime);
        sb.append(", fileUpdateTime=").append(fileUpdateTime);
        sb.append(", creator=").append(creator);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}