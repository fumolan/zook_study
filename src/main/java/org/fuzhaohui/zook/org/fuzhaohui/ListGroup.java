package org.fuzhaohui.zook.org.fuzhaohui;
 
import org.apache.zookeeper.WatchedEvent;
 
import java.util.List;
 
/**
 * 获取节点下的子节点
 */
public class ListGroup extends ConnectionWatcher {
 
	@Override
	public void connect(String host) throws Exception {
		super.connect(host);
	}
 
	@Override
	public void closeZk() throws Exception {
		super.closeZk();
	}
 
	@Override
	public void process(WatchedEvent watchedEvent) {
		super.process(watchedEvent);
	}
 
 
	public void listGroup(String groupName) throws Exception {
		String path = "/" + groupName;
 
		List<String> nodes = zk.getChildren(path, false);
		if (nodes.isEmpty())
			System.out.println("节点 : " + path + " 下没有子节点 ");
 
		for (String node : nodes) {
			System.out.println(node);
		}
 
	}
 
 
	public static void main(String[] args) throws  Exception{
		ListGroup listGroup = new ListGroup();
		listGroup.connect("172.16.6.20:2181");
		listGroup.listGroup("ocache");
	}
 
}
