package com.ryan.bit.study.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/12/19
 * @since 1.0.0
 */
public class SimpleZookeeperClientDemo {

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 30000, null);
            //zooKeeper.create("/test", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.delete("/hello_world_0000000005", -1);
            String jsonStr = new String("{\"key\":\"hello world\"}");
            for (int i = 0; i <= 5; i++) {
                zooKeeper.create("/test/hello_world_", jsonStr.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
