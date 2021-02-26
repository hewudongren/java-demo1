package cn.jwis.qualityworkflow.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 许锦标
 * @version 0.1.0
 * @Description 存储导入导出的表头和对应数据库字段名
 * @create 2020-04-28 16:59
 * @since 0.1.0
 **/
public class Title {

	/**
	 * Ecn记录数据库字段
	 */
	public static String[] EcnInfoDb = new String[]{"item_number","item_type","file_name","sender_side","document_originator","model_name","change_type","posting_time","works_section","works_head","quality_head","status","involved_area","change_import_scheme","change_import_time","change_import_mode","project_approval_result","project_approval_opinion","project_approver","project_approver_time","ipqc_verification_results","ipqc_verification_reason","ipqc_verifier","ipqc_verification_time","odm_approval_result","odm_approval_opinion","odm_approver","odm_approver_time","sqe_approval_result","sqe_approval_opinion","sqe_approver","sqe_approver_time","creator","create_date"};

	/**
	 * Ecn记录表头名
	 */
	public static String[] EcnInfoExcel  = new String[]{"项目编号","项目类型","文件名称","发文方","文件发起人","机型名称","变更类型","发文时间","部门名称","工程负责人","质量负责人","状态","涉及区域","变更导入方案","变更导入时间","变更导入方式","工程审批结果","工程审批意见","工程审批人","工程审批时间","PQE验证结果","PQE验证理由","PQE验证人","PQE验证时间","ODM审批结果","ODM审批意见","ODM审批人","ODM审批时间","SQE审批结果","SQE审批意见","SQE审批人","SQE审批时间","创建人","创建时间"};

	/**
	 * Ecn记录数据库字段(驼峰版)
	 */
	public static String[] EcnInfoDbCamel = new String[] {"itemNumber", "itemType", "fileName", "senderSide", "documentOriginator", "modelName", "changeType", "postingTime", "worksSection", "worksHead", "qualityHead", "statusDisplay", "involvedArea", "changeImportScheme", "changeImportTime", "changeImportMode","projectApprovalResult","projectApprovalOpinion","projectApprover",  "projectApproverTime", "ipqcVerificationResults", "ipqcVerificationReason", "ipqcVerifier", "ipqcVerificationTime", "odmApprovalResult", "odmApprovalOpinion", "odmApprover", "odmApproverTime", "sqeApprovalResult", "sqeApprovalOpinion", "sqeApprover", "sqeApproverTime","creator", "createDate"};


	/**
	 * 返工记录表 表头名
	 */
	public static String[] ReworkExcel = new String[] {"数据来源","返工编号","返工主题","返工原因","返工数量","返工确认结果","返工结果确认人","返工结果确认时间","工艺段","机型","问题类型","问题发生时间","根本原因责任方","状态","返工申请审核人","返工申请审核时间","工程处理人","工程处理提交时间","计划处理人","计划处理提交时间","生产处理人","生产处理提交时间","创建人","创建时间"};

	/**
	 * 返工单导入模板信息
	 */
	public static String[] ReworkTemplateExcel = new String[] {"问题发生时间","工艺段","机型（同MES机型名）","返工数量","问题类别","根本原因责任方"};

	/**
	 * 返工单导入模板字段
	 */
	public static String[] ReworkTemplateDb = new String[] {"problemTime","craftsSection","model","reworkQuantity","problemType","rootCauseResponsibility"};

	/**
	 * 返工记录数据库字段
	 */
	public static String[] ReworkDb = new String[]{"data_source","rework_number","rework_theme","rework_reason","rework_quantity","rework_confirm_result","rework_confirmor","rework_confirm_time","crafts_section","model","problem_type","problem_time","root_cause_responsibility","status","auditor","audit_time","project_handler","project_handle_time","plan_handler","plan_handle_time","production_handler","production_execution_time","creator","create_date"};


	public static String[] ReworkDbCamel = new String[]{"dataSource","reworkNumber","reworkTheme","reworkReason","reworkQuantity","reworkConfirmResult","reworkConfirmor","reworkConfirmTime","craftsSection","model","problemType","problemTime","rootCauseResponsibility","statusDisplay","auditor","auditTime","projectHandler","projectHandleTime","planHandler","planHandleTime","productionHandler","productionExecutionTime","creator","createDate"};

	/**
	 * 维修详情Excel表头
	 */
	public static String[] FPYQueryDetailExcel = new String[]{"板号", "集团SN", "IMEI号", "排程", "工艺段", "机型", "订单类别", "故障发生线体", "贴片厂家", "故障发生班别", "故障大类代码", "故障大类描述", "故障代码", "故障描述", "故障备注", "故障原因大类代码", "故障原因大类描述", "故障原因代码", "故障原因描述", "主板类别", "故障处理大类代码", "故障处理大类描述", "故障处理代码", "故障处理描述","维修类别", "责任归属", "故障工位", "维修类型", "更换前条码", "更换后条码", "部件类型", "部件描述", "备注", "位号", "器件料号", "维修状态", "送修人ID", "送修时刻", "最后一次暂存时刻", "回报人ID", "回报时刻", "维修次数", "最新工位状态","产品类型", "制造类型", "项目", "业务机型", "业务工序", "业务班次", "业务日期","部件"};


	/**
	 * 维修详情数据库字段
	 */
	public static String[] FPYQueryDetailDB = new String[]{"psn_code", "group_sn", "imei1", "schedule_code", "process_group", "model", "order_type", "fault_line_code", "smt_manufacture", "workgroup", "fault_class_code", "fault_class_desc", "fault_code", "fault_desc", "fault_remark", "reason_class_code", "reason_class_desc", "reason_code", "reason_desc", "mainboard_class", "handle_class_code", "handle_class_desc", "handle_code", "handle_desc","repair_class", "liability", "fault_station", "repair_type", "original_no", "current_no", "material_type_name", "material_type_desc", "remark", "part_no", "device_material_code", "repair_status", "created_by", "created_datetime", "last_saved_time", "return_user", "return_time", "repair_count", "process_name","model_category","manufacture_type","item","business_model","business_process","business_classes","business_datetime","material_type"};

	/**
	 * 维修明细表名
	 */
	public static String MaintenanceDetail = "MaintenanceDetail.xlsx";

	/**
	 * Rework返工申请表名
	 */
	public static String ReworkApplyTable = "ReworkApply.xlsx";

	/**
	 * 返工单模板表名
	 */
	public static String ReworkTemplate = "ReworkTemplate.xlsx";

	/**
	 * Rework返工单信息表名
	 */
	public static String ReworkTable = "Rework.xlsx";

	/**
	 * Ecn导出表名
	 */
	public static String EcnInfoTable = "EcnInfo.xlsx";



	/**
	 * ESD检验标准维护信息模板表名
	 */
	public  static  String EsdInStanTemplate  = "EsdInStanTemplate.xlsx";

	/**
	 * ESD检验标准维护信息模板表头名称
	 */
	public static  String[] EsdInStanTemplateExcel = new String[]{"样品名","检验项目","单位","上限值","下限值","检验工具","备注"};

	public static  String[] EsdInStanTemplateExcel2 = new String[]{"样品名","检验项目","单位","上限值","下限值","备注"};
	/**
	 * ESD检验标准维护信息模板数据库名称
 	 */
	public static  String[] EsdInStanTemplateDb = new String[]{"sampleName","detectionItem","unit","upperLimit","lowerLimit","detectionTool","remark"};
	public static  String[] EsdInStanTemplateDb2 = new String[]{"sampleName","detectionItem","unit","upperLimit","lowerLimit","remark"};
	/**
	 * ESD检验标准维护信息模板表名
	 */
	public  static  String EsdInStanInfoName  = "EsdInStanInfo.xlsx";

	/**
	 * ESD检验标准维护信息表头名称
	 */
	public static  String[] EsdInStanInfoExcel = new String[]{"样品名","检验项目","单位","上限值","下限值","检验工具","备注","更新时间"};
	public static  String[] EsdInStanInfoExcel2 = new String[]{"样品名","检验项目","单位","上限值","下限值","备注","更新时间"};

	/**
	 * ESD检验标准维护信息数据库信息名称
	 */
	public static  String[] EsdInStanInfoDb = new String[]{"sampleName","detectionItem","unit","upperLimit","lowerLimit","detectionTool","remark","updateDate"};
	public static  String[] EsdInStanInfoDb2 = new String[]{"sampleName","detectionItem","unit","upperLimit","lowerLimit","remark","updateDate"};
	/**
	 * ESD检验标准维护信息模板表名
	 */
	public  static  String EsdSamplingLevelInfoTemplate  = "EsdSamplingLevelTemplate.xlsx";

	/**
	 * ESD检验标准维护信息模板表头名称
	 */
	public static  String[] EsdSamplingLevelInfoTemplateExcel = new String[]{"样品名","抽样数量","备注"};
	public static  String[] EsdSamplingLevelInfoTemplateExcel2 = new String[]{"样品名","抽样数量","抽样比率(%)","备注"};
	/**
	 * ESD检验标准维护信息模板数据库名称
	 */
	public static  String[] EsdSamplingLevelInfoTemplateDb = new String[]{"sampleName","samplingQty","remark"};
	public static  String[] EsdSamplingLevelInfoTemplateDb2= new String[]{"sampleName","samplingQty","samplingRate","remark"};
	/**
	 * ESD检验标准维护信息模板表名
	 */
	public  static  String EsdSamplingLevelInfoName  = "EsdSamplingLevelTemplateInfo.xlsx";

	/**
	 * ESD检验标准维护信息表头名称
	 */
	public static  String[] EsdSamplingLevelInfoExcel = new String[]{"样品名","抽样数量","备注","更新时间"};
	public static  String[] EsdSamplingLevelInfoExcel2 = new String[]{"样品名","抽样数量","抽样比率(%)","备注","更新时间"};

	/**
	 * ESD检验标准维护信息数据库信息名称
	 */
	public static  String[] EsdSamplingLevelInfoDb = new String[]{"sampleName","samplingQty","remark","updateDate"};
	public static  String[] EsdSamplingLevelInfoDb2 = new String[]{"sampleName","samplingQty","samplingRate","remark","updateDate"};

	/**
	 * ESD周期性检验表头，数据库名称，Excel文件名
	 */
	public static  String[] EsdCycleInfoDB = new String[]{"itemNumber","sampleName","detectionMonth","samplingQty","qualifiedQty","unqualifiedQty","acceptanceRate","finalAcceptanceRate","nonconformingDisposalRate","creator","createDate","status","causeAnalysis","causalAnalyst","causeAnalysisDate",
	"improvementMeasures","improvementMeasuresor","improvementMeasuresDate","effectVerification","verifiedor","verifiedDate","approvalResult","caseClosingApprover","caseClosingDate"};
	public static  String[] EsdCycleInfExcel = new String[]{"单据编号","样品名","检验月份","抽检数","合格数","不合格数","合格率","最终合格率","不合格处置率","创建人","创建日期","状态","原因分析","原因分析人","原因分析时间",
	"改善措施","改善措施制定人","改善措施制定时间","效果验证","验证人","验证时间","审批结果","结案审批人","结案审批时间"};
	public  static  String  EsdCycleInfoExcelName  = "EsdCycleInfo.xlsx";

	/**
	 *  ESD周期性检验展示表头
	 */
	public static  String[] EsdCycleInfoDB2 = new String[]{"sampleName","detectionMonth","samplingQty","qualifiedQty","unqualifiedQty","acceptanceRate","finalAcceptanceRate","nonconformingDisposalRate","creator","createDate","status"};
	public static  String[] EsdCycleInfExcel2 = new String[]{"样品名","检验月份","抽检数","合格数","不合格数","合格率","最终合格率","不合格处置率","创建人","创建日期","状态"};
	public static  String[] EsdCycleInfExcelUS2 = new String[]{"sampleName","detectionMonth","samplingQty","qualifiedQty","unqualifiedQty","acceptanceRate","finalAcceptanceRate","nonconformingDisposalRate","creator","createDate","status"};
	/**
	 * ESD来料检验展示数据库名称,Excel表头名称，Excel表头英文名称
	 */
	public static  String[] EsdSpecialInfoDB = new String[]{"specialMiningNo","sampleName","supplier","model","materials","specificationModel","deliveredQty","samplingMethod","samplingRate","samplingQty","ambientTemperature","ambientHumidity","checker","inspectionDate","qualifiedQty","acceptanceRate","finalResult","demandDepartment","demandProposer","demandDate","handlingMethod","remarks","status","treatmentConclusion","disposalResults"};
	public static  String[] EsdSpecialInfoExcel = new String[]{"单据编号","样品名","供应商","机型","料号","规格型号","到货数量","抽样方式","抽样比率","抽检数量","环境温度℃","环境湿度%RH","检验人","检验日期","合格数","合格率","最终结果","需求部门","需求提出人","需求日期","处理方式","备注","状态","处理结论","处置结果验证"};
	public static String[]  EsdSpecialInfoExcelUS = new String[]{"special_mining_no","sample_name","supplier","model","materials","specification_model","delivered_qty","sampling_method","sampling_rate","sampling_qty","ambient_temperature","ambient_humidity","checker","inspection_date","qualified_qty","acceptance_rate","final_result","demand_department","demand_proposer","demand_date","handling_method","remarks","status","treatment_conclusion","disposal_results"};

	/**
	 * ESD来料检验导出数据库字段名称，表头中英文，Excel名称
	 */
	public static  String[] EsdSpecialDB = new String[]{"specialMiningNo","sampleName","supplier","model","materials","specificationModel","deliveredQty","samplingMethod","samplingRate","samplingQty","ambientTemperature","ambientHumidity","checker","inspectionDate","qualifiedQty","acceptanceRate","finalResult","demandDepartment","demandProposer","demandDate","handlingMethod","remarks","status","treatmentConclusion","disposalResults",
			"operation","exceptionDescription","specialReasons","shortTreatmentMeasures","longTreatmentMeasures","longImprovementMeasures","specialApplicant","special_date","departmentTreatmentMeasures","departmentAuditor","esdHandler","esdHandlingComments","esdCoordinator","esdHandlingDate","esdCoordinatResult",
			"esdCoordinatOpinion","committeeAuditor","esdCoordinatDate","handlingComments","departmentAuditOpinion","departmentAuditDate","auditResults","committeeAuditOpinion","committeeAuditDate","verificationOfResults","specialProcurementVerifier","specialProcurementDate"};
	public static  String[] EsdSpecialExcel = new String[]{"单据编号","样品名","供应商","机型","料号","规格型号","到货数量","抽样方式","抽样比率","抽检数量","环境温度℃","环境湿度%RH","检验人","检验日期","合格数","合格率","最终结果","需求部门","需求提出人","需求日期","处理方式","备注","状态","处理结论","处置结果验证","操作","异常描述","特采理由",
			"短期处理措施","长期处理措施","申请部门处理措施","长期改善措施","特采申请人","特采申请时间","部门审核人","ESD处理人","ESD委员会处理意见","ESD协调员","ESD处理时间","ESD协调审核结果","ESD协调审核意见","ESD委员会审核人","ESD协调审核时间","部门审核结果","部门审核意见","部门审核日期","ESD委员会审核结果","ESD委员会审核意见","ESD委员会审核日期",
			"特采处理结果验证记录","ESD特采验证人","ESD特采验证日期"};
	public static String[]  EsdSpecialExcelUS = new String[]{"special_mining_no","sample_name","supplier","model","materials","specification_model","delivered_qty","sampling_method","sampling_rate","sampling_qty","ambient_temperature","ambient_humidity","checker","inspection_date","qualified_qty","acceptance_rate","final_result","demand_department","demand_proposer","demand_date","handling_method","remarks","status","treatment_conclusion","disposal_results",
			"operation","exception_description","special_reasons","short_treatment_measures","long_treatment_measures","long_improvement_measures","special_applicant","special_date","department_treatment_measures","department_auditor","esd_handler","esd_handling_comments","esd_coordinator","esd_handling_date","esd_coordinat_result",
			"esd_coordinat_opinion","committee_auditor","esd_coordinat_date","handling_comments","department_audit_opinion","department_audit_date","audit_results","committee_audit_opinion","committee_audit_date","verification_of_results","special_procurement_verifier","special_procurement_date"};
	public static String EsdSpecialExcelName = "EsdSpecialInfo.xlsx";


	//QIMS黑色问题字段导出
	public static String QimsBlackExcelName = "QimsBlack.xlsx";
	public static  String[] QimsBlackExcel = new String[]{"状态","结案及时性","问题编号","报告时间","单据来源","问题类型","当前TL","报告主题","异常站点","异常报告人","发生时间","段别","线体","机型","班次","订单号","订单数量","检查数","故障数","故障率(%)","故障现象","问题描述","异常责任部门","异常责任人","异常响应时间","组长","组员","评估结果","评估依据","初步发生原因","初步分析结论","缺陷类型","是否停线","停线原因","临时处理措施","临时处理意见","根本原因分析责任部门","根本原因分析人","初步原因分析人","初步原因分析提交时间","根本产生原因","根本漏出原因","根本分析结论","长期处理措施1","长期处理措施2","长期处理措施3","长期处理措施4","长期处理措施5","根本原因分析提交时间","根本原因分析审核结果","根本原因分析审核意见","根本原因分析审核人","根本原因分析审核时间","效果验证结果","效果验证人","效果验证时间","效果审核结果","效果审核意见","效果审核人","效果审核时间","结案审批结果","结案审批意见","结案审批人","结案审批时间","初步原因分析LT","根本原因分析LT","根本原因审核LT"};
	public static  String[] QimsBlackExcelUS = new String[]{"status","conclusion_of_case","question_number","create_date","data_sources","problem_type","current_lt","report_subject","anomaly_site","anomaly_reporter","reporting_time","paragraph_parting","line","model","frequency","order_sn","order_qty","inspection_qty","failures_number","failure_rate(%)","failure_phenomenon","problem_description","abnormal_responsible_department","abnormal_responsible_person","create_date","group_leader","crew","assessment_result","basis_for_assessment","primary_cause","preliminary_analysis_conclusion","defect_type","is_stop","stop_line_because","temporary_disposal_measures","interim_disposal_advice","root_cause_department","root_cause_analyst","cause_analysis_person","cause_analysis_time","root_cause","root_causes_leak_out","fundamental_analysis_conclusion","long_term_treatment1","long_term_treatment2","long_term_treatment3","long_term_treatment4","long_term_treatment5","root_cause_time","handling_comments","root_cause_analysis_audit_opinion","root_cause_auditor","root_cause_analysis_time","effect_verification_results","effect_verifier","effect_verification_time","audit_results","effect_audit_opinion","effect_reviewer","effect_review_time","case_closing_result","case_closing_opinion","case_closing_approver","case_closing_date","preliminary_cause_analysis_lt","root_cause_analysis_lt","root_cause_audit_lt"};
	public static  String[] QimsBlackDB = new String[]{"status","conclusionOfCase","questionNumber","createDate","dataSources","problemType","currentLt","reportSubject","anomalySite","anomalyReporter","reportingTime","paragraphParting","line","model","frequency","orderSn","orderQty","inspectionQty","failuresNumber","failureRate","failurePhenomenon","problemDescription","abnormalResponsibleDepartment","abnormalResponsiblePerson","createDate","groupLeader","crew","assessmentResult","basisForAssessment","primaryCause","preliminaryAnalysisConclusion","defectType","isStop","stopLineBecause","temporaryDisposalMeasures","interimDisposalAdvice","rootCauseDepartment","rootCauseAnalyst","causeAnalysisPerson","causeAnalysisTime","rootCause","rootCausesLeakOut","fundamentalAnalysisConclusion","longTermTreatment1","longTermTreatment2","longTermTreatment3","longTermTreatment4","longTermTreatment5","rootCauseTime","handlingComments","rootCauseAnalysisAuditOpinion","rootCauseAuditor","rootCauseAnalysisTime","effectVerificationResults","effectVerifier","effectVerificationTime","auditResults","effectAuditOpinion","effectReviewer","effectReviewTime","caseClosingResult","caseClosingOpinion","caseClosingApprover","caseClosingDate","preliminaryCauseAnalysisLt","rootCauseAnalysisLt","rootCauseAuditLt"};
	//QIMS黑色问题看板批次问题发生率导出汇总
	//QIMS黑色问题字段导出
	public static String BlackBatchExcelName = "BlackBatch.xlsx";
	public static  String[] BlackBatchExcel = new String[]{"产品类型","制造类型","项目","机型","缺陷类型","周期","段别","日期","分子","分母","批次发生率"};
	public static  String[] BlackBatchDB = new String[]{"modelCategory","manufactureType","item","model","defectType","cycleValue","process","abscissa","molecule","sum","rate"};
	public static  String[] BlackBatchExcelUS = new String[]{"modelCategory","manufactureType","item","model","defectType","cycleValue","process","abscissa","molecule","sum","rate"};

	//QIMS灰色问题展示
	public static String QimsGrayExcelName = "QimsGray.xlsx";
	public static  String[] QimsGrayDB = new String[]{"recordStatus","isTimely","problemNumber","occurrenceTime","qaTime","problemType","lt","processingTime","problemDescription","occurrenceSite","reporter","model","frequency","defectNumber","defectType","responder","questionEntryTime","modelOwner","solutionAnalysis","qaPerson","factory","responseTime","department","responseLt","problemSolvingLt","line","paragraphParting","ownerGroup"};
	public static  String[] QimsGrayExcelUS = new String[]{"recordStatus","isTimely","problemNumber","occurrenceTime","qaTime","problemType","lt","processingTime","problemDescription","occurrenceSite","reporter","model","frequency","defectNumber","defectType","responder","questionEntryTime","modelOwner","solutionAnalysis","qaPerson","factory","responseTime","department","responseLt","problemSolvingLt","line","paragraphParting","ownerGroup"};
	public static  String[] QimsGrayExcel = new String[]{"状态","结案及时性","问题编号","发生时间","问题回复时间","问题类型","当前LT","在线问题处理完成时间","问题描述","发生站点","报告人","机型","班次","缺陷数量","缺陷类型","待回复OWNER","问题录入时间","机型OWNER","解决方案及分析","问题回复人","工厂","响应时间","部门","响应LT","问题处理LT","线体","分段","Owner所属组"};
	//QIMSCQA问题导出
	public static String QimsCQAExcelName = "QimsCQA.xlsx";
	public static  String[] QimsCQADB = new String[]{"status","conclusionOfCase","questionNumber","createDate","problemType","currentLt","reportSubject","anomalySite","anomalyReporter","reportingTime","line","model","frequency","scheduleSn","scheduleQty","inspectionQty","failuresNumber","failureRate","problemDescription","abnormalDepartment","abnormalResponsiblePerson","groupLeader","crew","assessmentResult","basisForAssessment","qualityTechnician","batchReworkQuantity","reworkResults","riskAssessmentResults","reworkFrozenQuantity","reworkCompletionTime","isStop","stopLineBecause","secondSamplingQuantity","samplingInspectionResults","riskAssessor","evaluateTime","primaryCause","preliminaryLeakageCauses","preliminaryAnalysisConclusion","defectType","temporaryDisposalMeasures","interimDisposalAdvice","rootCauseDepartment","rootCauseAnalyst","causeAnalysisPerson","causeAnalysisTime","rootCause","rootCausesLeakOut","fundamentalAnalysisConclusion","longTermTreatment1","longTermTreatment2","longTermTreatment3","longTermTreatment4","longTermTreatment5","rootCauseAnalyster","rootCauseTime","handlingComments","rootCauseAnalysisAuditOpinion","rootCauseAuditor","rootCauseAnalysisTime","effectVerificationConclusion","effectVerificationResults","effectVerifier","effectVerificationTime","auditResults","effectAuditOpinion","effectReviewer","effectReviewTime","caseClosingResult","caseClosingOpinion","caseClosingApprover","caseClosingDate","preliminaryCauseAnalysisLt","rootCauseAnalysisLt","people","machines","materials","regulations","environment","imelTrackID"};
	public static  String[] QimsCQAExcel = new String[]{"状态（StatusRecord）","结案及时性","问题编号","报告时间","问题类型","当前TL","报告主题","异常站点","异常报告人","发生时间","线体","机型","班次","排程号","排程数量","检查数","故障数","故障率","问题描述","异常责任部门","异常责任人","组长","组员","判定结果","理由及依据","质量技术人员","批返工数量","返工结果","风险评估结果","返工&冻结数量","返工完成时间","是否停线","停线原因","二次抽检数量","抽检结果","风险评估人","评估时间","初步发生原因","初步漏出原因","初步分析结论","缺陷类型","临时处理措施","临时处理意见","根本原因分析责任部门","根本原因分析责任人","初步原因分析人","初步原因分析提交时间","根本产生原因","根本漏出原因","根本分析结论","长期处理措施1","长期处理措施2","长期处理措施3","长期处理措施4","长期处理措施5","根本原因分析人","根本原因分析提交时间","根本原因审核结果","根本原因审核意见","根本原因审核人","根本原因审核时间","效果验证结论","效果验证结果","效果验证人","效果验证时间","效果审核结果","效果审核意见","效果审核人","效果审核时间","结案审批结果","结案审批意见","结案审批人","结案审批时间","初步原因分析LT","根本原因分析LT","人","机","料","法","环","IMEI/Track ID"};
	public static  String[] QimsCQAExcelUS = new String[]{"status","conclusion_of_case","question_number","create_date","problem_type","current_lt","report_subject","anomaly_site","anomaly_reporter","reporting_time","line","model","frequency","schedule_sn","schedule_qty","inspection_qty","failures_number","failure_rate","problem_description","abnormal_department","abnormal_responsible_person","group_leader","crew","assessment_result","basis_for_assessment","quality_technician","batch_rework_quantity","rework_results","risk_assessment_results","rework_frozen_quantity","rework_completion_time","is_stop","stop_line_because","second_sampling_quantity","sampling_inspection_results","risk_assessor","evaluate_time","primary_cause","preliminary_leakage_causes","preliminary_analysis_conclusion","defect_type","temporary_disposal_measures","interim_disposal_advice","root_cause_department","root_cause_analyst","cause_analysis_person","cause_analysis_time","root_cause","root_causes_leak_out","fundamental_analysis_conclusion","long_term_treatment1","long_term_treatment2","long_term_treatment3","long_term_treatment4","long_term_treatment5","root_cause_analyster","root_cause_time","handling_comments","root_cause_analysis_audit_opinion","root_cause_auditor","root_cause_analysis_time","effect_verification_conclusion","effect_verification_results","effect_verifier","effect_verification_time","audit_results","effect_audit_opinion","effect_reviewer","effect_review_time","case_closing_result","case_closing_opinion","case_closing_approver","case_closing_date","preliminary_cause_analysis_lt","root_cause_analysis_lt","people","machines","materials","regulations","environment","IMEI/Track ID"};
	/**
	 * QIMSCQA问题展示
	 */
	public static String[] QimsCQADBa = new String[]{"status","problemType","questionNumber","reportSubject","anomalySite","anomalyReporter","reportingTime","line","model","frequency","scheduleSn","scheduleQty","inspectionQty","failuresNumber","failureRate","problemDescription","abnormalResponsiblePerson","anomalyReporter","createDate","isStop","imelTrackID"};
	public static String[] QimsCQAExcela = new String[]{"状态","问题类型","问题编号","报告主题","异常站点","异常报告人","发生时间","线体","机型","班组","排程号","排程数量","检查数","故障数","故障率","问题描述","异常责任人","创建人","创建时间","是否停线","IMEI/Track ID"};
	public static String[] QimsCQAExcelUSa = new String[]{"status","problemType","questionNumber","reportSubject","anomalySite","anomalyReporter","reportingTime","line","model","frequency","scheduleSn","scheduleQty","inspectionQty","failuresNumber","failureRate","problemDescription","abnormalResponsiblePerson","anomalyReporter","createDate","isStop","imelTrackID"};
	/**
	 * ESD周期性检验单管理
	 */
	public  static  String  EsdInfoExcelName  = "EsdInfo.xlsx";
	public  static  String[] EsdInfoDB = new String[]{"itemNumber","sampleName","detectionMonth","samplingQty","detectionQty","qualifiedQty","unqualifiedQty","acceptanceRate","finalAcceptanceRate","nonconformingDisposalRate","status"};
	public  static  String[]  EsdInfoExcel  = new String[]{"单据编号","样品名","检验月份","抽样数","检测数","合格数","不合格数量","合格率(%)","终检合格率(%)","不合格处置率(%)","状态"};
	/**
	 * DashBoard黑色问题展示
	 */
	public  static  String  BlackExcelName  = "BlackInfo.xlsx";
	public  static  String[] BlackDB = new String[]{"questionNumber","reportSubject","problemDescription","reportingTime","status","currentLt","assigneer","causeAnalysisPerson","rootCauseAnalyst","rootCauseAuditor","assigneerDepartment"};
	public  static  String[]  BlackExcel  = new String[]{"问题编号","问题主题","问题描述","报告时间","状态","LT(到当前时间)","当前处理人","初步原因分析人","根本原因分析","根本原因审核人","部门"};
	public  static  String[]  BlackExcelUS  = new String[]{"questionNumber","reportSubject","problemDescription","reportingTime","status","currentLt","assigneer","causeAnalysisPerson","rootCauseAnalyst","rootCauseAuditor","assigneerDepartment"};
	/**
	 *   CQA问题展示
	 */
	public  static  String  CQAExcelName  = "CQAInfo.xlsx";
	public  static  String[] CQADB = new String[]{"questionNumber","reportSubject","problemDescription","reportingTime","status","currentLt","assigneer","riskAssessor","causeAnalysisPerson","rootCauseAnalyst","rootCauseAuditor","assigneerDepartment"};
	public  static  String[]  CQAExcel  = new String[]{"问题编号","问题主题","问题描述","报告时间","状态","LT(到当前时间)","当前处理人","风险评估人","初步原因分析人","根本原因分析","根本原因审核人","部门"};
	public  static  String[]  CQAExcelUS  = new String[]{"questionNumber","reportSubject","problemDescription","reportingTime","status","currentLt","assigneer","riskAssessor","causeAnalysisPerson","rootCauseAnalyst","rootCauseAuditor","assigneerDepartment"};
	/**
	 *  灰色问题展示
	 */
	public  static  String  GrayExcelName  = "GrayInfo.xlsx";
	public  static  String[] GrayDB = new String[]{"problem_number","problem_description","question_entry_time","lt","assigneer","assigneer_department"};
	public  static  String[]  GrayExcel  = new String[]{"问题编号","问题描述","问题录入时间","LTOA","当前处理人","部门"};
	public  static  String[]  GrayExcelUS  = new String[]{"problemNumber","problemDescription","questionEntryTime","lt","assigneer","assigneerDepartment"};

	/**
	 * DashBoard黑色问题展示
	 */
	public  static  String  BlackDetailsExcelName  = "BlackDetailsInfo.xlsx";
	public  static  String[] BlackDetailsDB = new String[]{"itCode","name","totalOfCh","passOfCh","rateOfCh","totalOfCa","passOfCa","rateOfCa","totalOfCau","passOfCau","rateOfCau"};
	public  static  String[]  BlackDetailsExcel  = new String[]{"IT编号","姓名","初步原因处理总数","初步原因处理及时数","初步原因处理及时率","根本原因处理总数","根本原因处理及时数","根本原因处理及时率","根本原因审核总数","根本原因审核及时数","根本原因审核及时率"};
	public  static  String[]  BlackDetailsExcelUS  = new String[]{"itCode","name","totalOfCh","passOfCh","rateOfCh","totalOfCa","passOfCa","rateOfCa","totalOfCau","passOfCau","rateOfCau"};
	/**
	 * DashBoardCQA问题详细统计展示
	 */
	public  static  String  CQADetailsExcelName  = "CQADetailsInfo.xlsx";
	public  static  String[] CQADetailsDB = new String[]{"itCode","name","totalOfRa","passOfRa","rateOfRa","totalOfCh","passOfCh","rateOfCh","totalOfCa","passOfCa","rateOfCa","totalOfCau","passOfCau","rateOfCau"};
	public  static  String[]  CQADetailsExcel  = new String[]{"IT编号","姓名","风险评估处理总数","风险评估处理及时数","风险评估处理及时率","初步原因处理总数","初步原因处理及时数","初步原因处理及时率","根本原因处理总数","根本原因处理及时数","根本原因处理及时率","根本原因审核总数","根本原因审核及时数","根本原因审核及时率"};
	public  static  String[]  CQADetailsExcelUS  = new String[]{"itCode","name","totalOfRa","passOfRa","rateOfRa","totalOfCh","passOfCh","rateOfCh","totalOfCa","passOfCa","rateOfCa","totalOfCau","passOfCau","rateOfCau"};

	/**
	 * DashBoard灰色问题详细统计展示
	 */
	public  static  String  GrayDetailsExcelName  = "GrayDetailsInfo.xlsx";
	public  static  String[] GrayDetailsDB = new String[]{"itCode","name","totalOfRp","passOfRp","rateOfRp"};
	public  static  String[]  GrayDetailsExcel  = new String[]{"IT编号","姓名","回复总数","回复及时数","回复及时率"};
	public  static  String[]  GrayDetailsExcelUS  = new String[]{"itCode","name","totalOfRp","passOfRp","rateOfRp"};
	/**
	 * APQP机型维护
	 */
	public static String APQPModelTemplate = "APQPTemplate.xlsx";
	public static String[] APQPModelInfoDB = new String[]{"model","previous_product","product_series","model_category","manufacture_type","item","remark","creator","create_date","update_date"};
	public static String[] APQPModelInfoExcel = new String[]{"机型名称","上一代产品","产品系列","产品类别","制造类型","项目(ODM)","备注","创建人","创建时间","更新时间"};
	public static String[] APQPModelInfoDbCamel = new String[]{"model","previousProduct","productSeries","modelCategory","manufactureType","item","remark","creator","createDate","updateDate"};

	/**
	 * 外来文件记录表头名
	 */
	public static String[] externalDocumentExcel = new String[] {"项目编号","项目类型","文件名称","发文部门","文件发起人","机型名称","文件类型","发文时间","部门名称","负责人","状态","处理人","文件处理方式","确认转化后的文件名称","确认转化后的文件编号","确认新文件开始使用时间","IPQC验证结果","IPQC验证理由","IPQC验证人","IPQC验证时间","LT","创建人","创建时间"};
	public static String[] externalDocumentDb = new String[]{"item_number","item_type","file_name","sender_side","document_originator","model_name","file_type","posting_time","works_section", "in_charge","status","assignee","handle_method","transmutes_file_name","transmutes_file_number","file_usage_time","ipqc_verification_results","ipqc_verification_reason","ipqc_verifier","ipqc_verification_time","LT","creator","create_date"};
	public static  String[] externalDocumentDbCamel = new String[]{"itemNumber","itemType","fileName","senderSide","documentOriginator","modelName","fileType","postingTime","worksSection","inCharge","statusDisplay","assignee","handleMethod","transmutesFileName","transmutesFileNumber","fileUsageTime","ipqcVerificationResults","ipqcVerificationReason","ipqcVerifier","ipqcVerificationTime","lT", "creator", "createDate"};
	public static String externalDocumentTable = "ExternalDocument.xlsx";

	/**
	 * APQP主单据
	 */
	public static String apqpMasterDocumentTable = "APQPMasterDocument.xlsx";
	public static String[] apqpMasterDocumentInfoDB = new String[]{"apqp_number","theme","model_category","manufacture_type","item","model","previous_product","product_series","dvt1_num","dvt1_completed_num","pemea_num","pemea_completed_num","qc_char_num","qc_char_completed_num","status"};
	public static String[] apqpMasterDocumentExcel = new String[]{"APQP编号","主题","产品类别","制造类型","项目(ODM)","机型名称","上一代产品","产品系列","NUDD/FX文档进度","PFMEA文档进度","QC-Chart文档进度","状态"};
	public static String[] apqpMasterDocumentDbCamel = new String[]{"apqpNumber","theme","modelCategory","manufactureType","item","model","previousProduct","productSeries", "dvt1Schedule", "pemeaSchedule", "qcChartSchedule","statusDisplay"};

	/**
	 * APQP子单据
	 */
	public static String apqpSubdocumentTable = "APQPSubDocument.xlsx";
	public static String[] apqpSubdocumentInfoDB = new String[]{"apqp_number","model_category","manufacture_type","item","model","previous_product","product_series","owner","qe","eng_phase","frequency","dvt1_document","dvt1_uploader","dvt1_upload_time","dvt1_audit_time","dvt1_auditor","dvt1_alarm_date","dvt1_deadline","pemea_document","pemea_uploader","pemea_upload_time","pemea_audit_time","pemea_auditor","pemea_alarm_date","pemea_deadline","qc_chart_document","qc_chart__uploader","qc_chart__upload_time","qc_chart__audit_time","qc_chart__auditor","qc_chart_alarm_date","qc_chart_deadline","nqe_auditor","nqe_audit_time","status","creator","create_date"};
	public static String[] apqpSubdocumentExcel = new String[]{"APQP编号","产品类别","制造类型","项目(ODM)","机型名称","上一代产品","产品系列","Owner","QE","工程段","预警时长","NUDD/FX文档","NUDD/FX文档上传人","NUDD/FX文档上传时间","NUDD/FX文档审核时间","NUDD/FX文档审核人","NUDD/FX文档预警时间","NUDD/FX文档截止日期","PEMEA文档","PEMEA文档上传人","PEMEA文档上传时间","PEMEA文档审核时间","PEMEA文档审核人","PEMEA预警日期","PEMEA截止日期","QCChar文档","QCChar文档上传人","QCChar文档上传时间","QCChar文档审核时间","QCChar文档审核人","QCChar文档预警时间","QCChar文档截止日期","NQE结案审批人","NQE结案时间","状态","创建人","创建时间"};
	public static String[] apqpSubdocumentDbCamel = new String[]{"apqpNumber","modelCategory","manufactureType","item","model","previousProduct","productSeries","owner","qe","engPhase","frequency","dvt1Document","dvt1Uploader","dvt1UploadTime","dvt1AuditTime","dvt1Auditor","dvt1AlarmDate","dvt1Deadline","pemeaDocument","pemeaUploader","pemeaUploadTime","pemeaAuditTime","pemeaAuditor","pemeaAlarmDate","pemeaDeadline","qcChartDocument","qcChartUploader","qcChartUploadTime","qcChartAuditTime","qcChartAuditor","qcChartAlarmDate","qcChartDeadline","nqeAuditor","nqeAuditTime","statusDisplay","creator","createDate"};

	/**
	 * APQP文件更新记录
	 */
	public static String apqpFileUpdateRecordTable = "APQPFileUpdateRecord.xlsx";
	public static String[] apqpFileUpdateRecordInfoDB = new String[]{"model","file_name","file_version","file_owner","file_effective_time","file_overdue_time","file_update_time","creator","create_date"};
	public static String[] apqpFileUpdateRecordExcel = new String[]{"机型名称","文件名称","文件版本","文件Owner","文件有效时间","文件到期时间","文件修改时间","创建人","创建时间"};
	public static String[] apqpFileUpdateRecordDbCamel = new String[]{"model","fileName","fileVersion","fileOwner","fileEffectiveTime","fileOverdueTime","fileUpdateTime","creator","createDate"};

	/**
	 *  ESD来料检验管理展示表头
	 */
	public static  String esdSpeciaManageExcelName= "esdSpeciaManage.xlsx";
	public static  String[] esdSpeciaManageDb = new String[]{"itemNumber","sampleName","supplier","model","materials","specificationModel","deliveredQty","samplingMethod","samplingRate","samplingQty","ambientTemperature","ambientHumidity","checker","inspectionDate","qualifiedQty","acceptanceRate","finalResult","demandDepartment","demandProposer","demandDate","handlingMethod","remarks"};
	public static  String[] esdSpeciaManageExcel = new String[]{"单据编号","样品名","供应商","机型","料号","规格型号","到货数量","抽样方式","抽样比率(%)","抽检数量","环境温度℃","环境湿度%RH","检验人","检验日期","合格数","合格率(%)","最终结果","需求部门","需求提出人","需求日期","处理方式","备注"};
	public static  String[] esdSpeciaManageExcelUS = new String[]{"itemNumber","sampleName","supplier","model","materials","specificationModel","deliveredQty","samplingMethod","samplingRate","samplingQty","ambientTemperature","ambientHumidity","checker","inspectionDate","qualifiedQty","acceptanceRate","finalResult","demandDepartment","demandProposer","demandDate","handlingMethod","remarks"};

	/**
	 * 流程代办表头展示
	 */
	public static  String[] processAgentDB = new String[]{"template_key","theme","item_number","creator","create_date","assignee","task_name","update_date"};
	public static  String[] processAgentExcel = new String[]{"流程类型","主题","项目编号","创建人","创建时间","当前处理人","状态","结束时间"};
	public static  String[] processAgentExcelUS = new String[]{"templateKey","theme","projectNo","createdBy","createDate","assignee","taskName","updateDate"};

	/**
	 * 节假日维护页面展示
	 */
	public static  String[] HolidayDB = new String[]{"date","type","creator","create_date","update_date"};
	public static  String[] HolidayExcel = new String[]{"日期","属性","创建人","创建时间","修改时间"};
	public static  String[] HolidayExcelUS = new String[]{"date","type","creator","create_date","update_date"};

	/**
	 * ESD稽核模板导出表头
	 */
	public static String[] EsdAudiMainDB = new String[]{"auditType","documentNumber","documentName","creator","updateDate","remark"};
	public static String[] EsdAudiMainExcel = new String[]{"稽核类型","文件编号","文件名称","创建人","更新时间","备注"};
	public static String[] EsdAudiMainExcelUS = new String[]{"auditType","documentNumber","documentName","creator","updateDate","remark"};

	/**
	 * ESD稽核副表模板表头
	 */
	public static String[] EsdAudiSecondaryDB = new String[]{"auditObject","specificRequirement","problemClassification","gradeProblem","hsfnc"};
	public static String[] EsdAudiSecondaryExcel = new String[]{"稽核对象","具体要求","问题分类","问题等级","不符合处理意见"};

	/**
	 * ESD稽核单管理表头展示
	 */
	public static  String[] EsdAuditMainDB = new String[]{"auditType","documentNumber","auditDate","frequency","line","auditer","createDate","auditNumber","qualifiedNumber","qualifiedRate","remarks"};
	public static  String[] EsdAuditMainExcel = new String[]{"稽核类型","文件编号","稽核日期","班次","区域/线体","稽核员","创建时间","稽核数","合格数","合格率(%)","备注"};
	public static  String[] EsdAuditMainExcelUS = new String[]{"audit_type","document_number","audit_date","frequency","line","auditer","create_date","audit_number","qualified_number","qualified_rate","remarks"};

	/**
	 * ESD稽核异常流程页面展示表头
	 */
	public static String[] EsdAuditADb = new String[]{"itemNumber","auditType","auditDate","frequency","line","auditer","problemClassification","gradeProblem","responsibleDepartment","personLiable","problemDescription","status","creator","createDate"};
	public static String[] EsdAuditAExcel = new String[]{"问题编号","稽核类型","稽核日期","班次","区域/线体","检验员","问题分类","问题等级","责任部门","责任人","问题描述","状态","创建人","创建时间"};
	public static String[] EsdAuditAExcelUs = new String[]{"item_number","audit_type","audit_date","frequency","line","auditer","problem_classification","grade_problem","responsible_department","person_liable","problem_description","status","creator","create_date"};


	/**
	 * ESD稽核异常流程导出展示表头
	 */
	public static String[] EsdAuditBDb = new String[]{"itemNumber","auditType","auditDate","frequency","line","auditer","problemClassification","gradeProblem","responsibleDepartment","personLiable","problemDescription","status","creator","createDate","causeAnalysis","causalAnalyst","causalAnalystDate","improvementMeasures","improvementMeasuresor","improvementMeasuresDate","effectVerification","verifiedor","verifiedDate","approvalResult","caseClosingApprover","caseClosingDate"};
	public static String[] EsdAuditBExcel = new String[]{"问题编号","稽核类型","稽核日期","班次","区域/线体","检验员","问题分类","问题等级","责任部门","责任人","问题描述","状态","创建人","创建时间","原因分析","原因分析人","原因分析时间","改善措施","改善措施人","改善措施时间","效果验证","效果验证人","效果验证时间","审批结果","结案审批人","结案审批时间"};
	public static String[] EsdAuditBExcelUs = new String[]{"item_number","audit_type","audit_date","frequency","line","auditer","problem_classification","grade_problem","responsible_department","person_liable","problem_description","status","creator","create_date","cause_analysis","causal_analyst","causal_analyst_date","improvement_measures","improvement_measuresor","improvement_measures_date","effect_verification","verifiedor","verified_date","approval_result","case_closing_approver","case_closing_date"};

	/**
	 *  workflow单据状态
	 */
	public static String[] workFlowDb = new String[]{"create_date","creator","table_description","status","assigneer"};
	public static String[] workFlowExcel = new String[]{"创建日期","创建人","表格描述","状态","当前处理人"};
	public static String[] WorkFlowExcelUs = new String[]{"create_date","creator","table_description","status","assigneer"};

	/**
	 * QIMS看板目标值展示
	 */
	public static String[] QimsTargetDb = new String[]{"problemType","cycle","timelyClosingRate","closingRate","remarks","creator","createDate"};
	public static String[] QimsTargetExcel = new String[]{"问题类型","周期","及时关闭率(%)","关闭率(%)","备注","创建人","创建时间"};
	public static String[] QimsTargetExcelUs = new String[]{"problemType","cycle","timelyClosingRate","closingRate","remarks","creator","createDate"};
	/**
	 * QIMS看板目标值导出表头
	 */
	public static String[] QimsTargetDb2 = new String[]{"problemType","cycle","timelyClosingRate","closingRate","remarks"};
	public static String[] QimsTargetExcel2 = new String[]{"问题类型","周期","及时关闭率(%)","关闭率(%)","备注"};
	public static String[] QimsTargetExcelUs2 = new String[]{"problemType","cycle","timelyClosingRate","closingRate","remarks"};

	/**
	 * QIMS首页导出柏拉图信息
	 */
	public static String[] QimsBolaDb = new String[]{"abscissa","sum"};
	public static String[] QimsBolaPersonExcel = new String[]{"用户","数量"};
	public static String[] QimsBolaPersonExcelUs = new String[]{"person","sum"};
	public static String[] QimsBolaDepartmentExcel = new String[]{"部门","数量"};
	public static String[] QimsBolaDepartmentExcelUs = new String[]{"department","sum"};

	/**
	 * ESD看板目标值信息
	 */
	public static  String[] EsdTargetDb = new String[]{"month","year","esd","eos","total","creator","createDate"};
	public static  String[] EsdTargetExcel = new String[]{"月份","年","ESD/Crew Target","EOS/Crew Target","Total/Crew Target","创建人","创建日期"};
	public static  String[] EsdTargetExcelUs = new String[]{"month","year","esd","eos","total","creator","createDate"};

	public static  String[] EsdTargetExcelTemplate = new String[]{"月份","年","ESD/Crew Target","EOS/Crew Target","Total/Crew Target"};
	public static  String[] EsdTargetDbTemplate = new String[]{"month","year","esd","eos","total"};

	/**
	 * 用户部门信息表头信息
	 */
	public static String[] UserDepartmentDb = new String[]{"user_account","user_name","it_code","department","team","position","superior_name","superior","superior_manager_name","superior_manager"};
	public static String[] UserDepartmentExcel = new String[]{"账号","姓名","ITCODE","部门","TEAM","职位","上级主管姓名","上级主管","上级经理姓名","上级经理"};
	public static String[] UserDepartmentExcelUs = new String[]{"user_account","user_name","it_code","department","team","position","superior_name","superior","superior_manager_name","superior_manager"};

 	/**
	 * @Author yuyangyang
	 * @Description 获取页面表头信息和导出的表头信息
	 * @Date  2020/5/30  14:12
	 * @Param
	 * @return
	 */
	public static Map<String, String> getTitle(HttpServletRequest request,String[] db,String[] excel,String[] excelUs){
		String language = request.getHeader("Language");
		String[] dbList = db;
		String[] excelList = excel;
		if ("en-US".equals(language)){
			excelList = excelUs;
		}
		Map<String, String> title = FormatUtil.getTitle(dbList,excelList);
		return title;
	}
}
