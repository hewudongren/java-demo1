package cn.jwis.qualityworkflow.constants;

/**
    *@description
    *@author 许锦标
    *@date 2019/12/18 15:09
    *@email jinbiao.xu@jwis.cn
 */
public class Constants {
    //LAR有序查询条件值
    public static final String[] LARQUERY = {"item","supplier","productCategories","model"};
    //LRR有序查询条件值
    public static final String[] LRRQUERY = {"model_category","manufacture_type","item","business_model","material_type"};
    //机型 映射的数据库字段
    public static final String MODEL= "business_model";
    //部件 映射的数据库字段
    public static final String PART= "material_type";
    //柏拉图 累计比率
    public static final String CUMALATIVERATE = "cumulativeRate";
    //值为null时的填充值
    public static final String FILLVALUE = "-";
    //目标值数组
    public static final String[] PERIOD = { "Half an hour","Hour","class", "Day", "Week","Month", "Quarter","Half a year","Year"};
    //返回数据 时序数据名称
    public static final String TIMEDATA = "timeData";
    //特殊数据Total 名称
    public static final String TOTAL = "Total";
    //产品类别
    public static final String MODELCATEGORY  = "model_category";
    //制造类型
    public static final String MANUFACTURETYPE = "manufacture_type";
    //项目
    public static final String ITEM = "item";
}