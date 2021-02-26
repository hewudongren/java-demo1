package cn.jwis.qualityworkflow.enums;

import cn.jwis.qualityworkflow.modules.apqp.bean.ApqpSubdocument;
import cn.jwis.qualityworkflow.modules.ecn.bean.EcnInfo;
import cn.jwis.qualityworkflow.modules.ecn.bean.ExternalDocument;
import cn.jwis.qualityworkflow.modules.rework.bean.ReworkInfo;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-07-28 16:34
 * @since 0.1.0
 **/
public enum WorkflowNodeReflect {

	// ECN流程
	ECN_APPLY(EcnInfo.class.getName(),"Apply", "拟制单据"),
	ECN_PLAN(EcnInfo.class.getName(),"Plan", "制定预处置计划"),
	ECN_PQE(EcnInfo.class.getName(),"PQE", "支持部门处理"),
	ECN_SCHEME(EcnInfo.class.getName(),"Scheme", "制定最终处置方案"),
	ECNN_AUDIT(EcnInfo.class.getName(),"Audit", "工程审核"),
	ECNI_PQC(EcnInfo.class.getName(),"IPQC", "PQE验证"),
	ECNO_DM(EcnInfo.class.getName(),"ODM", "ODM审核"),
	ECN_SQE(EcnInfo.class.getName(),"SQE", "SQE审批"),

	// 外来文件流程
	EXT_APPLY(ExternalDocument.class.getName(), "Apply", "拟制单据"),
	EXT_CONFIRM(ExternalDocument.class.getName(), "Confirm", "文件处理确认"),
	EXT_IPQC(ExternalDocument.class.getName(), "IPQC", "IPQC验证"),

	// Rework流程
	REWORK_APPLY(ReworkInfo.class.getName(), "Apply", "拟制单据"),
	REWORK_AUDIT(ReworkInfo.class.getName(), "Audit", "返工申请审核"),
	REWORK_REVIEW(ReworkInfo.class.getName(), "Review", "返工评审"),
	REWORK_Project_Execution(ReworkInfo.class.getName(), "ProjectExecution", "工程处理"),
	Rework_Plan(ReworkInfo.class.getName(), "ReworkPlan", "计划处理"),
	Rework_Production_Execution(ReworkInfo.class.getName(), "ProductionExecution", "生产处理"),
	Rework_PQE_Confirm(ReworkInfo.class.getName(), "PQEConfirm", "返工结果确认"),

	// APQP流程
	APQP_DVT1(ApqpSubdocument.class.getName(), "DVT1", "NUDD/DFX文件上传"),
	APQP_DVT1_Audit(ApqpSubdocument.class.getName(), "DVT1_Audit", "NUDD/DFX文件上传审核"),
	APQP_PEMEA(ApqpSubdocument.class.getName(), "PEMEA", "PFMEA文件上传"),
	APQP_PEMEA_Audit(ApqpSubdocument.class.getName(), "PEMEA_Audit", "PFMEA文件上传审核"),
	APQP_QC_Chart(ApqpSubdocument.class.getName(), "QC_Chart", "QC-Chart文件上传"),
	APQP_QC_Chart_Audit(ApqpSubdocument.class.getName(), "QC_Char_Audit", "QC-Chart文件上传审核"),
	APQP_NQE(ApqpSubdocument.class.getName(), "NQE", "NQE结案审批");

	/**
	 * 根据节点名获取 节点映射名
	 * @param workflow
	 * @param node
	 * @return
	 */
	public static String getNodeReflect(String workflow, String node) {
		if (workflow != null  && node != null ) {
			for (WorkflowNodeReflect enums : WorkflowNodeReflect.values()) {
				if (enums.getWorkflow().equals(workflow) && enums.getNode().equals(node)) {
					return enums.nodeRelectName;
				}
			}
		}
		return null;
	}

	public String getWorkflow() {
		return workflow;
	}

	public String getNode() {
		return node;
	}

	public String getNodeRelectName() {
		return nodeRelectName;
	}

	/**
	 * 流程名
	 */
	private String workflow;

	/**
	 * 节点名
	 */
	private String node;

	/**
	 * 节点映射名
	 */
	private String nodeRelectName;

	WorkflowNodeReflect(String workflow, String node, String nodeRelectName) {
		this.workflow = workflow;
		this.node = node;
		this.nodeRelectName = nodeRelectName;
	}
}
