package cn.jwis.qualityworkflow.modules.rework.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-08-18 11:18
 * @since 0.1.0
 **/
@ApiModel("返工MO状态")
public class ReworkMoStatus {

	@ApiModelProperty("id")
	private String id;

	@ApiModelProperty("返工id 对应每个返工单据")
	private String reworkId;

	@ApiModelProperty("返工MO")
	private String reworkMo;

	@ApiModelProperty("单据状态  0表示冻结， 1表示解冻")
	private String holdStatus;

	@ApiModelProperty("创建人")
	private String creator;

	@ApiModelProperty("创建时间")
	private Date createDate;

	@ApiModelProperty("更新时间")
	private Date updateDate;

	public ReworkMoStatus(String id, String reworkId, String reworkMo, String holdStatus, String creator) {
		this.id = id;
		this.reworkId = reworkId;
		this.reworkMo = reworkMo;
		this.holdStatus = holdStatus;
		this.creator = creator;
	}

	public ReworkMoStatus() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReworkId() {
		return reworkId;
	}

	public void setReworkId(String reworkId) {
		this.reworkId = reworkId;
	}

	public String getReworkMo() {
		return reworkMo;
	}

	public void setReworkMo(String reworkMo) {
		this.reworkMo = reworkMo;
	}

	public String getHoldStatus() {
		return holdStatus;
	}

	public void setHoldStatus(String holdStatus) {
		this.holdStatus = holdStatus;
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
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ReworkMoStatus that = (ReworkMoStatus) o;
		return id.equals(that.id) &&
				reworkId.equals(that.reworkId) &&
				reworkMo.equals(that.reworkMo) &&
				holdStatus.equals(that.holdStatus) &&
				creator.equals(that.creator) &&
				createDate.equals(that.createDate) &&
				updateDate.equals(that.updateDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, reworkId, reworkMo, holdStatus, creator, createDate, updateDate);
	}
}
