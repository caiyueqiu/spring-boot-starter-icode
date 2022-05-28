package com.icode.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/9 19:40
 */
@Slf4j
public class ZookeeperLock {
    private String rootPath = "/zookeeper";
    private String currentPath;
    private ZooKeeper zooKeeper;

    public ZookeeperLock(String lockName, ZooKeeper zooKeeper) {
        currentPath = rootPath + "/" + lockName;
        this.zooKeeper = zooKeeper;
    }

    public void lock() {
        if (tryLock()) {
            log.info("获取锁成功");
        } else {
            waitLock();
        }
    }

    public void unlock() {
        try {
            zooKeeper.delete(currentPath, -1);
        } catch (Exception e) {
            log.error("删除节点失败：{}", e.getMessage());
        }
        log.info("释放锁成功");
    }

    private boolean tryLock() {
        try {
            zooKeeper.create(currentPath, "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            log.info("临时节点创建成功");
            return true;
        } catch (Exception e) {
            log.error("创建临时节点失败，{}", e.getMessage());
        }
        return false;
    }

    private void waitLock() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Watcher watcher = watchedEvent -> {
            Watcher.Event.EventType type = watchedEvent.getType();
            //触发NodeDeleted事件，跳过await方法
            if (Watcher.Event.EventType.NodeDeleted.equals(type)) {
                countDownLatch.countDown();
            }
        };
        try {
            //判断当前锁的数据节点是否存在
            Stat exists = zooKeeper.exists(currentPath, watcher);
            if (exists == null) {
                lock();
            } else {
                countDownLatch.await();
                lock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
