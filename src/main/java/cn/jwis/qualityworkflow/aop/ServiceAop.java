package cn.jwis.qualityworkflow.aop;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.jwis.qualityworkflow.bean.BaseClass;
import cn.jwis.qualityworkflow.bean.BaseException;
import cn.jwis.qualityworkflow.bean.UserInfo;
import cn.jwis.qualityworkflow.interceptor.SessionHelper;


/**
 * Service拦截器，实现权限控制
 */

@Aspect
@Configuration
@Component
public class ServiceAop extends BaseClass {

	
	private static final Logger logger = LoggerFactory.getLogger(ServiceAop.class);
	
	@Pointcut("execution(public * cn.jwis.iqcinspection.service.*.*.search*(..))")
	public void searchObject() {
	}

	@Around("searchObject()")
	public Object doAddBefore(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("测试AOP");
		JSONObject jsonObject  = JSONObject.parseObject(JSONObject.toJSON(joinPoint.getArgs()[0]).toString());
		updateParameter("odm",jsonObject);
	//	updateParameter("category",jsonObject);
		Object[] objects = new Object[1];
		//判断传参是否是JSONObject
		if (joinPoint.getArgs()[0] instanceof JSONObject){
			objects[0] = jsonObject;
		}else {
			objects[0] = toJavaBean(jsonObject,joinPoint.getArgs()[0].getClass());
		}

		return joinPoint.proceed(objects);
	}
	/**
	 * 根据当前用户的信息对传入的参数进行修改
	 */
   private  void  updateParameter(String parameter, JSONObject jsonObject){
	   UserInfo userInfo = SessionHelper.getCurrentUser();
	   String account = "1111";
	   if(isNotNull(userInfo)){
		  account = userInfo.getAccount();
	   }
	   //通账号查询这个用户所属的ODM和Category
	   List<String> list = getList(account,parameter);
	   if (jsonObject.containsKey(parameter) && isNotNull(jsonObject.getJSONArray(parameter)) ){
		   JSONArray jsonArray = jsonObject.getJSONArray(parameter);
	   	   //用户勾选的条件不符合他的权限
		   if (!list.containsAll(jsonArray)){
               throw new BaseException("The selected " +parameter +" does not have permission");
		   }
	   }else {
		   jsonObject.put(parameter,list);
	   }
   }
	/**
	 * 根据用户账号查询用户所属的ODM或者Category
 	 */
	private  List<String>  getList(String account,String parameter){
		List<String> list = new ArrayList<>();
		if ("odm".equals(parameter)){
			//通过用户查询用户所属的odm
			list.add("compal");
			list.add("lcfc");
		}else{
			//通过用户查询用户所属的Category
		}
		return list;
	}

	/**
	 * JSONObject转java Bean
	 */
	private Object toJavaBean(JSONObject jsonObject, Class<?> clase) throws IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
		Object object = clase.newInstance();
		Method[] methods = clase.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("set")){
				String field = method.getName();
				field = field.substring(field.indexOf("set") + 3);
				field = field.toLowerCase().charAt(0) + field.substring(1);//
				method.invoke(object, new Object[]
						{jsonObject.get(field)});
			}
		}
		return object;
	}

}
