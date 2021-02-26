/**
 * 
 */
package cn.jwis.qualityworkflow.aop;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import cn.jwis.qualityworkflow.datasource.DBContextHolder;

/**
 * @ClassName: DataSourceAop
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author longjun
 * @date 2019年10月9日
 */
//@Aspect
//@Order(-1)
//@Component
public class DataSourceAop {

	@Pointcut("!@annotation(cn.jwis.qualityworkflow.annotation.Master) "
			+ "&& (execution(* cn.jwis.qualityworkflow..service.*.select*(..)) "
			+ "|| execution(* cn.jwis.qualityworkflow..service.*.get*(..)))")
	public void readPointcut() {

	}

	@Pointcut("@annotation(cn.jwis.qualityworkflow.annotation.Master) "
			+ "|| execution(* cn.jwis.qualityworkflow..service.*.insert*(..)) "
			+ "|| execution(* cn.jwis.qualityworkflow..service.*.add*(..)) "
			+ "|| execution(* cn.jwis.qualityworkflow..service.*.save*(..)) "
			+ "|| execution(* cn.jwis.qualityworkflow..service.*.update*(..)) "
			+ "|| execution(* cn.jwis.qualityworkflow..service.*.edit*(..)) "
			+ "|| execution(* cn.jwis.qualityworkflow..service.*.delete*(..)) "
			+ "|| execution(* cn.jwis.qualityworkflow..service.*.remove*(..))"
			)

	public void writePointcut() {

	}

	@Before("readPointcut()")
	public void read() {
		DBContextHolder.slave();
	}

	@Before("writePointcut()")
	public void write() {
		DBContextHolder.master();
	}
}