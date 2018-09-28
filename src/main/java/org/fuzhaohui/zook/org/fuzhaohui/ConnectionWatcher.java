package org.fuzhaohui.zook.org.fuzhaohui;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
 
import java.util.concurrent.CountDownLatch;
 
/**
 * Created by CYX on 2017/3/14.
 */
public class ConnectionWatcher implements Watcher {
 
	//超时时间
	private static final int SESSION_TIMEOUT = 5000;
 
	protected ZooKeeper zk;
	private CountDownLatch connectedSignal = new CountDownLatch(1);
 
	/**
	 * 连接zookeeper
	 *
	 * @param host
	 * @throws Exception
	 */
	public void connect(String host) throws Exception {
		zk = new ZooKeeper(host, SESSION_TIMEOUT, this);
		connectedSignal.await();
	}
 
	/**
	 * 关闭zk连接
	 *
	 * @throws Exception
	 */
	public void closeZk() throws Exception {
		zk.close();
	}
	
 
	public void process(WatchedEvent watchedEvent) {
		if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
			connectedSignal.countDown();
		}
	}
 
}
 
