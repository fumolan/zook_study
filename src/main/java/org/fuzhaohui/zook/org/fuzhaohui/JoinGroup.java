package org.fuzhaohui.zook.org.fuzhaohui;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;

/**
 * Created by CYX on 2017/3/14.
 */
public class JoinGroup extends ConnectionWatcher {
 
	@Override
	public void connect(String host) throws Exception {
		super.connect(host);
	}
 
	@Override
	public void closeZk() throws Exception {
		super.closeZk();
	}
 
	public void process(WatchedEvent watchedEvent) {
		super.process(watchedEvent);
	}
 
	public void join(String groupName , String memberName)throws Exception {
		String path = "/" + groupName + "/" + memberName;
		String createPath = zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println(createPath);
	}
 
	public static void main(String[] args) throws Exception{
		JoinGroup joinGroup = new JoinGroup();
		joinGroup.connect("192.168.125.125:2181");
		joinGroup.join("testZNode", "twoPath");
		Thread.sleep(30000);
	}
 
}
