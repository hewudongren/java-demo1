package cn.jwis.qualityworkflow.modules.esd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Description TODO
 * @Author yuyangyang
 * @Date 2020/7/30 16:12
 */
public class EsdTargetInfo {
	@ApiModelProperty(value = "id", name = "id  唯一主键")
	private String id;
	@ApiModelProperty(value = "year", name = "年份")
	private String year;
	@ApiModelProperty(value = "month", name = "月份")
	private String month;
	@ApiModelProperty(value = "esd", name = "esd")
	private Float esd;
	@ApiModelProperty(value = "eos", name = "eos")
	private Float eos;
	@ApiModelProperty(value = "total", name = "total")
	private Float total;
	@ApiModelProperty(value = "creator", name = "creator")
	private  String creator;
	@ApiModelProperty(value = "createDate", name = "createDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createDate;
	@ApiModelProperty(value = "updateDate", name = "updateDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Float getEsd() {
		return esd;
	}

	public void setEsd(Float esd) {
		this.esd = esd;
	}

	public Float getEos() {
		return eos;
	}

	public void setEos(Float eos) {
		this.eos = eos;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
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
}