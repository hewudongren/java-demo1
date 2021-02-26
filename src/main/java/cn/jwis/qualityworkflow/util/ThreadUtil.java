package cn.jwis.qualityworkflow.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
/**
    *@description 
    *@author 许锦标
    *@date 2020/3/31 17:43
    *@email jinbiao.xu@jwis.cn
 */
public class ThreadUtil {
	private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
	// 常规线程池
	// 因为存在许多 邮件任务 -- 流程跳转都会用到发送邮件的任务，所以核心线程池为10
	private static ExecutorService threadPool = new ThreadPoolExecutor(10, 30,
			1000L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(2048), threadFactory, new ThreadPoolExecutor.AbortPolicy());
	// 定时任务线程池
	// 目前用到定时任务的线程池较少，所以只开1个线程即可
	private static ScheduledExecutorService scheduledThreadPool = new ScheduledThreadPoolExecutor(1,
			new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

	/**
	 * 返回常规线程池调用
	 * @return
	 */
	public static ExecutorService getThreadPool() {
		return threadPool;
	}

	/**
	 * 返回定时任务线程池
	 * @return
	 */
	public static ScheduledExecutorService getScheduledThreadPool() {
		return scheduledThreadPool;
	}


}
