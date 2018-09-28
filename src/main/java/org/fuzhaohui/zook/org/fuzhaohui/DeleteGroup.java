package org.fuzhaohui.zook.org.fuzhaohui;
 
import org.apache.zookeeper.WatchedEvent;
 
import java.util.List;
 
/**
 * 删除zk上的节点.
 * zk不支持递归删除节点,所以必须先将子节点删除才能删除父节点.
 */
public class DeleteGroup extends ConnectionWatcher {
 
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
 
 
	public void delete(String groupName) throws Exception {
		String path = "/" + groupName;
		try {
			List<String> nodes = zk.getChildren(path, false);
			for (String node : nodes) {
				zk.delete(path + "/" + node, -1);
			}
			zk.delete(path, -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
 
	public static void main(String[] args) throws Exception {
		DeleteGroup deleteGroup = new DeleteGroup();
		deleteGroup.connect("172.16.6.20:2181");
		deleteGroup.delete("jb_d");
	}
 
}
