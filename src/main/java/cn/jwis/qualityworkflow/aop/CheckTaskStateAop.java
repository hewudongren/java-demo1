package cn.jwis.qualityworkflow.aop;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.util.UserUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cn.jwis.qualityworkflow.common.Constants.CLOSE_TASK_STATE;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description 拦截Confirm方法中 重复被提交的记录
 * @create 2020-05-22 15:05
 * @since 0.1.0
 **/
@Component
@Aspect
@Order(2)
public class CheckTaskStateAop {
	public static final Logger logger = LoggerFactory.getLogger(IDGeneratorRunner.class);

	@Autowired
	TaskRecordMapper taskRecordDao;

	// 线程安全的List
	private static  List<String> handlingTaskList = Collections.synchronizedList(new ArrayList<>(1024));


	@Around("@annotation(cn.jwis.qualityworkflow.annotation.CheckTaskState)")
	public Object around(ProceedingJoinPoint point) throws Exception {

		String taskId = null;
		try {
			Object[] args = point.getArgs();
			for (Object arg : args) {
				if (arg instanceof ConfirmVo) {
					ConfirmVo vo = (ConfirmVo) arg;
					if (vo.getTaskId() != null) {
						taskId = vo.getTaskId();
						// 加锁 防止出现异常 这里是判断是否有并发处理
						synchronized(CheckTaskStateAop.class) {
							if (handlingTaskList.contains(taskId)) {
								logger.info(UserUtil.getCurrentUserName() + " try to  confirm  a handling task " + taskId);
								return ResultBean.fail("该记录正在处理中");
							}
							handlingTaskList.add(taskId);
						}
						//这里是统一处理数据的状态 涉及到状态的都要考虑重复提交的问题
						//是在锁释放后相应的处理 这里也只能查询一次数据库获取最新的数据进行判断
						TaskRecord record = taskRecordDao.getByTaskId(taskId);
						if (record == null) {
							logger.info(UserUtil.getCurrentUserName() + " try to  confirm  null task " + taskId);
							return ResultBean.fail("taskId不存在!");
						}
						if ( CLOSE_TASK_STATE.equals(record.getTaskState())) {
							logger.info(UserUtil.getCurrentUserName() + " try to repeat confirm  task " + taskId);
							return ResultBean.fail("该记录已被提交!");
						}
					}
				}
			}
			Object o = point.proceed();
			return o;
		}catch (Throwable e) {
			return ResultBean.fail(e.getMessage());
		} finally {
			if (taskId != null) {
				handlingTaskList.remove(taskId);
			}
		}
	}

}
