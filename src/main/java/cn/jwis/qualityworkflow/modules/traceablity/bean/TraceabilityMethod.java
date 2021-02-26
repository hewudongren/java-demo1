package cn.jwis.qualityworkflow.modules.traceablity.bean;

import java.io.Serializable;

public class TraceabilityMethod implements Serializable {
    private String id;

    /**
     * 文件编号
     *
     * @mbggenerated
     */
    private String fileNumber;

    /**
     * 文件名称
     *
     * @mbggenerated
     */
    private String fileName;

    /**
     * 文件Id
     *
     * @mbggenerated
     */
    private String filesClassId;

    /**
     * 文件类别
     *
     * @mbggenerated
     */
    private String fileType;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private String createDate;

    /**
     * 生效时间
     *
     * @mbggenerated
     */
    private String fileValidTime;

    /**
     * 失效时间
     *
     * @mbggenerated
     */
    private String fileExpirationTime;

    /**
     * 文件owner
     *
     * @mbggenerated
     */
    private String fileOwner;

    /**
     * 文件版本
     *
     * @mbggenerated
     */
    private String fileVersion;

    /**
     * 文件状态
     *
     * @mbggenerated
     */
    private String fileStatus;

    /**
     * 文件状态描述
     *
     * @mbggenerated
     */
    private String fileStatusDescribe;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String creator;
    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String model;
    private String involveScope;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilesClassId() {
        return filesClassId;
    }

    public void setFilesClassId(String filesClassId) {
        this.filesClassId = filesClassId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFileValidTime() {
        return fileValidTime;
    }

    public void setFileValidTime(String fileValidTime) {
        this.fileValidTime = fileValidTime;
    }

    public String getFileExpirationTime() {
        return fileExpirationTime;
    }

    public void setFileExpirationTime(String fileExpirationTime) {
        this.fileExpirationTime = fileExpirationTime;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getFileStatusDescribe() {
        return fileStatusDescribe;
    }

    public void setFileStatusDescribe(String fileStatusDescribe) {
        this.fileStatusDescribe = fileStatusDescribe;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInvolveScope() {
        return involveScope;
    }

    public void setInvolveScope(String involveScope) {
        this.involveScope = involveScope;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileNumber=").append(fileNumber);
        sb.append(", fileName=").append(fileName);
        sb.append(", filesClassId=").append(filesClassId);
        sb.append(", fileType=").append(fileType);
        sb.append(", createDate=").append(createDate);
        sb.append(", fileValidTime=").append(fileValidTime);
        sb.append(", fileExpirationTime=").append(fileExpirationTime);
        sb.append(", fileOwner=").append(fileOwner);
        sb.append(", fileVersion=").append(fileVersion);
        sb.append(", fileStatus=").append(fileStatus);
        sb.append(", fileStatusDescribe=").append(fileStatusDescribe);
        sb.append(", creator=").append(creator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}