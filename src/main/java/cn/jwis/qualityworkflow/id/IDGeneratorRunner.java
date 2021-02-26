package cn.jwis.qualityworkflow.id;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 当Springboot启动之后，执行此runner，完成zookeeper的注册获取当前微服务的顺序号
 * 
 *
 */
@Component
@Order(value = 1)
@ConfigurationProperties(ignoreUnknownFields = true, prefix = "id.generator")
public class IDGeneratorRunner implements ApplicationRunner {

	public static final Logger logger = LoggerFactory.getLogger(IDGeneratorRunner.class);

	/**
	 * 默认的数据中心顺序号
	 */
	private long datacenterSequence;

	/**
	 * 单实例部署或本地测试时不使用zookeeper注册获取顺序号的情况下的默认微服务的顺序号
	 */
	private long serviceSequence;

	private String zookeeperHost;

	private int zookeeperPort;

	private String registName;

	/**
	 * 默认12位序列的的每毫秒的初始值
	 */
	private long sequence = 1;

	private long twepoch = 1288834974657L;

	private long workerIdBits = 5L;
	private long datacenterIdBits = 5L;
	private long sequenceBits = 12L;

	private long workerIdShift = sequenceBits;
	private long datacenterIdShift = sequenceBits + workerIdBits;
	private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long lastTimestamp = -1L;

	/**
	 * 尝试注册zookeeper，如果没有注册zookeeper信息，则使用默认的datacenterSequence和serviceSequence
	 * 如果能正常连接zookeeper，则使用zookeeper返回的sequence
	 */
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		try {
			// 多数据中心的情况下，需要连接zookeeper生成各数据中心唯一id，用于全局唯一,当独立部署时不需要zookeeper，使用默认值
			if ((zookeeperHost != null && !"".equals(zookeeperHost.trim())) && (zookeeperPort != 0)) {
				CountDownLatch connectedSemaphore = new CountDownLatch(1);
				ZookeeperResgit zookeeperResgit = new ZookeeperResgit(connectedSemaphore);
				ZooKeeper zookeeper = new ZooKeeper(zookeeperHost + ":" + zookeeperPort, 5000, zookeeperResgit);
				connectedSemaphore.await();
				String nodeSequence = zookeeper.create(registName, "".getBytes(), Ids.OPEN_ACL_UNSAFE,
						CreateMode.EPHEMERAL_SEQUENTIAL);
				logger.info("Zookeeper resgit success:" + nodeSequence);
				String sequence = StringUtils.removeStart(nodeSequence, registName);
				serviceSequence = Integer.valueOf(sequence);
			}
		} catch (Exception e) {
			// 注册失败，则使用默认配置
			e.printStackTrace();
			logger.error("Zookeeper resgit failed:" + e.getMessage());
		}
	}

	/**
	 * 生成唯一的趋势递增的id 同步方法保证唯一
	 * 
	 * @return
	 */
	public synchronized long nextId() {
		long timestamp = timeGen();

		if (timestamp < lastTimestamp) {
			System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
			throw new RuntimeException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}

		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}

		lastTimestamp = timestamp;
		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterSequence << datacenterIdShift)
				| (serviceSequence << workerIdShift) | sequence;
	}

	private long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}
}
