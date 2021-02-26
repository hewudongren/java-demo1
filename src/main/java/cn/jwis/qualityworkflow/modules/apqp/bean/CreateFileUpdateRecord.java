package cn.jwis.qualityworkflow.modules.apqp.bean;

import java.util.Date;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-08-14 10:54
 * @since 0.1.0
 **/
public class CreateFileUpdateRecord {

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
}
