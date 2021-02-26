package cn.jwis.qualityworkflow.modules.rework.bean;

import cn.jwis.qualityworkflow.modules.ecn.bean.PageBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 查询返工申请管理页面的Vo
 * @create 2020-05-15 15:11
 * @since 0.1.0
 **/
public class QueryReworkApplyVo extends PageBean {
	// 给父类的属性赋值
	public QueryReworkApplyVo() {
		super.setOrderBy("create_date");
		super.setTime("problem_time");
	}

	@ApiModelProperty(" 处理状态 0 是全部  1 是待办，  2是已处理")
	private int flag = 1;

	@ApiModelProperty("处理人")
	private String assignee;

	@ApiModelProperty("工艺段")
	private List<String> craftsSection;

	@ApiModelProperty("机型")
	private List<String> model;

	@ApiModelProperty("返工主题")
	private String reworkTheme;

	//因为mybatis if标签 对于boolean类型不友好 所以用 0代表 false 1代表 true
	@ApiModelProperty("是否为管理员")
	@JsonIgnore
	private int admin = 0;

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public List<String> getCraftsSection() {
		return craftsSection;
	}

	public void setCraftsSection(List<String> craftsSection) {
		this.craftsSection = craftsSection;
	}

	public List<String> getModel() {
		return model;
	}

	public void setModel(List<String> model) {
		this.model = model;
	}

	public String getReworkTheme() {
		return reworkTheme;
	}

	public void setReworkTheme(String reworkTheme) {
		this.reworkTheme = reworkTheme;
	}
}
