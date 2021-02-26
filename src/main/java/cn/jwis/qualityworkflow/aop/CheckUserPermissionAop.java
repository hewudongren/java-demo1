package cn.jwis.qualityworkflow.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.jwis.qualityworkflow.bean.ConfirmVo;
import cn.jwis.qualityworkflow.bean.ResultBean;
import cn.jwis.qualityworkflow.bean.TaskRecord;
import cn.jwis.qualityworkflow.dao.TaskRecordMapper;
import cn.jwis.qualityworkflow.id.IDGeneratorRunner;
import cn.jwis.qualityworkflow.util.UserUtil;

/**
 * @author xujinbiao
 * @version 0.1.0
 * @Description
 * @create 2020-05-29 09:33
 * @since 0.1.0
 **/
@Component
@Order(1)
@Aspect
public class CheckUserPermissionAop {

	public static final Logger logger = LoggerFactory.getLogger(IDGeneratorRunner.class);

	@Autowired
	TaskRecordMapper taskRecordDao;

	@Around("@annotation(cn.jwis.qualityworkflow.annotation.CheckUserPermission)")
	public Object around(ProceedingJoinPoint point) throws Exception{
		try {
			Object[] args = point.getArgs();
			for (Object arg : args) {
				if (arg instanceof ConfirmVo) {
					ConfirmVo vo = (ConfirmVo) arg;
					String taskId = vo.getTaskId();
					if (vo.getTaskId() != null) {
						TaskRecord currentTaskAssignees = taskRecordDao.getByTaskId(taskId);
						if (currentTaskAssignees != null) {
							String currentUserName = UserUtil.getCurrentUserName();
							if (!currentUserName.equals(currentTaskAssignees.getAssignee())) {
								logger.info(currentUserName + " try to confirm task " + taskId + ",but not permission");
								return ResultBean.fail("没有权限提交记录");
							}
						} else {
							logger.info(UserUtil.getCurrentUserName() + " try to  confirm  null task " + taskId);
							return ResultBean.fail("taskId不存在!");
						}
					}
				}
			}
			Object o = point.proceed();
			return o;
		}catch (Throwable e) {
			return ResultBean.fail(e.getMessage());
		}
	}
}
