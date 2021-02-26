package cn.jwis.qualityworkflow.common;

/**
    *@description 工程全局常量声明
    *@author 许锦标
    *@date 2020/4/26 15:58
    *@email jinbiao.xu@jwis.cn
 */
public class Constants {
	/**
	 * TaskState为关闭状态
	 */
	public static final String CLOSE_TASK_STATE = "Close";

	public static final String ONGOING_TASK_STATE = "OnGoing";

	/**
	 * ECN流程模板的唯一标识
	 */
	public static final String ECN_TEMPLATE_KEY = "QMSEcnProcess";

	/**
	 * ESD周期检验流程模板的唯一标识
	 */
	public static final String ESDCYCLETEMPLATEKEY = "ESDProcess";

	/**
	 * ESD来料检验流程模板的唯一标识
	 */
	public static final String ESDSPECIALTEMPLATEKEY = "ESDACCEPT";

	/**
	 * ESD稽核异常检验流程模板的唯一标识
	 */
	public static final String ESDAUDITTEMPLATEKEY = "ESDAUDIT";

	/**
	 * QIMS黑色问题解决
	 */
	public static final String QIMSBLACKTEMPLATEKEY = "QIMSBLACK";
	/**
	 * QIMS黑色问题解决
	 */
	public static final String QIMSCQATEMPLATEKEY = "QIMSCQA";

	/**
	 * ECN流程模板的唯一标识
	 */
	public static final String EXTERNAL_DOCUMNENT_TEMPLATE_KEY = "QMSExternalDocuments";

	/**
	 * Rework流程模板的唯一标识
	 */
	public static final String REWORK_TEMPLATE_KEY = "ReworkProcess";

	/**
	 * APQP子单据流程模板唯一标识
	 */
	public static final String APQP_TEMPLATE_KEY = "APQPProcess";

	/**
	 * 主单据TemplateKey 实际上不存在本流程
	 */
	public static final String APQP_MASTER_TEMPLATE_KEY = "APQPMasterProcess";

	/**
	 * 历史记录 保存
	 */
	public static final String HISOTORY_PROCESS_SAVE = "SAVE";

	/**
	 * 历史记录提交
	 */
	public static final String HISOTORY_PROCESS_COMMIT = "COMMIT";

	/**
	 * 表示空文件
	 */
	public  static final String NA = "NA";

	public static final String CN_LANGUAGE = "zh-CN";

}
