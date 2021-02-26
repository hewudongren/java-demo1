package cn.jwis.qualityworkflow.id;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * 连接zookeeper，用于生成数据中唯一id
 * 
 *
 */
public class ZookeeperResgit implements Watcher {

	private CountDownLatch connectedSemaphore;

	public ZookeeperResgit(CountDownLatch connectedSemaphore) {
		super();
		this.connectedSemaphore = connectedSemaphore;
	}
    @Override
	public void process(WatchedEvent event) {
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}

	}
}