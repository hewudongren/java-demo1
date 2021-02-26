package cn.jwis.qualityworkflow.modules.ipqc.bean;

public class IPQCTitle {

    //稽核模板
    //public static  String[] AuditTemplateInfoDb = new String[]{"template_number","template_name","file_number","model","process","version","create_date","creator","update_date","update_person","remark"};

    public static  String[] AuditTemplateInfoExcel  = new String[]{"模板编号","模板名称","文件编号","机型","工程段","版本","创建时间"," 创建人","更新时间","更新人","备注"};

    public static  String[] AuditTemplateInfoDbCamel = new String[]{"templateNumber","templateName","fileNumber","model","process","version","createDate","creator","updateDate","updatePerson","remark"};

    public static  String   AuditTemplateInfoTable = "AuditTemplateInfo.xlsx";


    public static  String[] AuditTemplateDetailInfoExcel  = new String[]{"稽核类别","具体要求","不符合处理意见"};

    public static  String[] AuditTemplateDetailInfoDbCamel = new String[]{"auditType","specificRequirement","notConformityOpinion"};

    public static  String   AuditTemplateDetailInfoTable = "AuditTemplateDetailInfo.xlsx";

    //稽核清单
    public static  String[] AuditListInfoExcel  = new String[]{"模板编号","模板名称","文件编号","机型","工程段","线体","稽核日期"," 班次","检验员","巡检进度","创建人","提交时间"};

    public static  String[] AuditListInfoDbCamel = new String[]{"templateNumber","templateName","fileNumber","model","process","line","auditDate","frequency","inspector","timeInterval","creator","createDate"};

    public static  String   AuditListInfoTable = "AuditListInfo.xlsx";


    //巡检记录
    public static  String[] PatrolProblemRecordInfoExcel  = new String[]{"状态","稽核日期","机型","工程段","线体","班次","检验员","工位号","问题点描述","问题类型","故障类别","问题属性","问题图片","班长","Owner","状态更新时间","确认签字","备注","创建时间"};

    public static  String[] PatrolProblemRecordInfoDbCamel = new String[]{"status","auditDate","model","worksSection","line","frequency","inspector","station","problemDescription","problemType","faultType","problemAttribute","problemPicture","squadLeader","owner","statusUpdateDate","isSignature","remark","createDate"};

    public static  String   PatrolProblemRecordInfoTable = "PatrolProblemRecordInfo.xlsx";


    //稽核质量问题
    public static  String[] AuditQualityProblemInfoExcel  = new String[]{"稽核类型","ODM","稽核日期","班次","机型","工程段","线体"," 问题点描述","问题发生时间","问题类型","故障类别","问题属性","问题图片","Owner","原因分析","改善对策","原因分析人","IPQC审核结果","IPQC审核意见","IPQC确认人","IPQC审核人","IPQC审核时间","QE审核结果","QE审核意见","QE审核人","QE审核时间","ODM审核结果","ODM审核意见","ODM审核人","ODM审核时间","状态"};

    public static  String[] AuditQualityProblemInfoDbCamel = new String[]{"auditType","odm","auditDate","frequency","model","worksSection","line","problemDescription","problemOccurDate","problemType","faultType","problemAttribute","problemPicture","owner","causeAnalysis","improvementStrategy","causalAnalyst","ipqcAuditResults","ipqcAuditOpinion","ipqcConfirmer","ipqcAuditor","ipqcAuditDate","qeAuditResults","qeAuditOpinion","qeAuditor","qeAuditDate","odmAuditResults","odmAuditOpinion","odmAuditor","odmAuditDate","status"};

    public static  String   AuditQualityProblemInfoTable = "AuditQualityProblemInfo.xlsx";



    public static  String[] AuditQualityProblemExcel  = new String[]{"稽核类型","ODM","稽核日期","班次","机型","工程段","线体"," 问题点描述","问题类型","故障类别","问题属性","问题发生时间","Owner"};

    public static  String[] AuditQualityProblemDbCamel = new String[]{"auditType","odm","auditDate","frequency","model","worksSection","line","problemDescription","problemType","faultType","problemAttribute","problemOccurDate","owner"};

    public static  String   AuditQualityProblemInfo = "AuditQualityProblemDownLoad.xlsx";
    public class Constants {
        /**
         * TaskState为关闭状态
         */
        public static final String IPQCCLOSETASKSTATE = "Close";

        /**
         * IPQC流程模板的唯一标识
         */
        public static final String IPQCTEMPLATEKEY = "IPQCAuditProcess";

        /**
         * 历史记录 保存
         */
        public static final String IPQCHISOTORYPROCESSSAVE = "SAVE";

        /**
         * 历史记录提交
         */
        public static final String IPQCHISOTORYPROCESSCOMMIT = "COMMIT";
    }
}
