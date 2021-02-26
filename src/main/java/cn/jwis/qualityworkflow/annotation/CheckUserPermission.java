package cn.jwis.qualityworkflow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 校验用户是否有权限提交
 * @create 2020-05-29 09:31
 * @since 0.1.0
 **/
@Target(ElementType.METHOD)  //作用到方法上
@Retention(RetentionPolicy.RUNTIME) //运行时有效
public @interface CheckUserPermission {
}
