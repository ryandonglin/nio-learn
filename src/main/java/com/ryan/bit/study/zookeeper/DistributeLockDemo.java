package com.ryan.bit.study.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2017/12/19
 * @since 1.0.0
 */
public class DistributeLockDemo {

    private static int counter = 0;

    public static void plus () {
        counter ++;

        int sleepMs = (int) (Math.random() * 100);

        try {
            Thread.sleep(sleepMs);
        } catch (InterruptedException ex) {

        }
    }

    static class CounterPlus extends Thread {

        private static final String LOCK_ROOT_PATH = "/Locks";
        private static final String LOCK_NODE_NAME = "Lock_";

        // 每个线程持有一个zk客户端，负责获取锁与释放锁
        ZooKeeper zkClient;


        public void run() {
            for (int i = 0; i < 20; i ++) {
                // 访问计数器前需要获取锁
                String path = getLock();
                // logic
                plus();

                // 执行完任务后需要释放锁
                System.out.print(path);
                releaseLock(path);
            }

            closeZkClient();
            System.out.println(Thread.currentThread().getName() + "执行完毕：" + counter);
        }

        /**
         * 获取锁，即创建子节点，当该节点成为序号最小的节点时则获取锁
         */
        private String getLock() {
            try {

                Stat rootStat = zkClient.exists(LOCK_ROOT_PATH, false);
                if (null == rootStat) {
                    zkClient.create(LOCK_ROOT_PATH, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
                // 创建EPHEMERAL_SEQUENTIAL类型节点
                String lockPath = zkClient.create(LOCK_ROOT_PATH + "/" + LOCK_NODE_NAME,
                        Thread.currentThread().getName().getBytes(), Ids.OPEN_ACL_UNSAFE,
                        CreateMode.EPHEMERAL_SEQUENTIAL);
                System.out.println(Thread.currentThread().getName() + " create path : " + lockPath);

                // 尝试获取锁
                tryLock(lockPath);

                return lockPath;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 该函数是一个递归函数 如果获得锁，直接返回；否则，阻塞线程，等待上一个节点释放锁的消息，然后重新tryLock
         */
        private boolean tryLock(String lockPath) throws KeeperException, InterruptedException{
            // 获取LOCK_ROOT_PATH 下所有的子节点，并按照节点序号由小到大排序
            List<String> lockPaths = zkClient.getChildren(LOCK_ROOT_PATH, false);
            Collections.sort(lockPaths);

            // 获取 lockPath在 LOCK_ROOT_PATH下所有节点中的index位置
            int index = lockPaths.indexOf(lockPath.substring(LOCK_ROOT_PATH.length() + 1));

            if (0 == index) { // lockPath 是当前zk目录下的最小节点
                System.out.println(Thread.currentThread().getName() + " get lock, lockPath: " + lockPath);
                return true;
            } else { // lockPath不是序号最小的节点
                Watcher watcherWrapper = new Watcher() {
                    public void process(WatchedEvent watchedEvent) {
                        System.out.println(watchedEvent.getPath() + " has been deleted!");
                        synchronized (this) {
                            notifyAll();
                        }
                    }
                };

                String preLockPath = lockPaths.get(index-1);
                Stat stat = zkClient.exists(LOCK_ROOT_PATH + "/" + preLockPath, watcherWrapper);
                if (null == stat) {
                    return tryLock(lockPath);
                } else { // 阻塞当前进程，直到preLockPath释放了锁，重新tryLock
                    System.out.println(Thread.currentThread().getName() + " wait for " + preLockPath);
                    synchronized (watcherWrapper) {
                        watcherWrapper.wait();
                    }
                    return tryLock(lockPath);
                }
            }
        }

        private void releaseLock(String lockPath) {
            try {
                zkClient.delete(lockPath, -1);
            } catch (InterruptedException ex) {

            } catch (KeeperException ex) {

            }
        }

        public void setZkClient(ZooKeeper zkClient) {
            this.zkClient = zkClient;
        }

        public ZooKeeper getZkClient() {
            return zkClient;
        }

        public void closeZkClient(){
            try {
                zkClient.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public CounterPlus(String threadName) {
            super(threadName);
        }
    }

    public static void main(String[] args) throws Exception{
        CounterPlus threadA = new CounterPlus("ThreadA");
        setZkClient(threadA);
        threadA.start();

        CounterPlus threadB = new CounterPlus("ThreadB");
        setZkClient(threadB);
        threadB.start();

        CounterPlus threadC = new CounterPlus("ThreadC");
        setZkClient(threadC);
        threadC.start();

        CounterPlus threadD = new CounterPlus("ThreadD");
        setZkClient(threadD);
        threadD.start();

        CounterPlus threadE = new CounterPlus("ThreadE");
        setZkClient(threadE);
        threadE.start();
    }

    public static void setZkClient(CounterPlus thread) throws Exception {
        ZooKeeper zkClient = new ZooKeeper("127.0.0.1:2181", 1000, null);
        thread.setZkClient(zkClient);
    }
}
