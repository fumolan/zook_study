package org.fuzhaohui.zook.org.fuzhaohui;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class CreateZNode implements Watcher {

    //超时时间
    private static final int SESSION_TIMEOUT = 5000;

    private ZooKeeper zk;
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
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            connectedSignal.countDown();
        }
    }


    /**
     * 创建zk节点
     *
     * @param zNode
     * @throws Exception
     */
    public void createZNode(String zNode) throws Exception {
        String path = "/" + zNode;
        String createPath = zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(createPath);
    }


    public static void main(String[] args) {
        try {

            CreateZNode create = new CreateZNode();
            create.connect("192.168.125.125:2181");
            create.createZNode("testZNode");
            create.closeZk();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
