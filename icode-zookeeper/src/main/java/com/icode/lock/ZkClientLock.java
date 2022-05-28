package com.icode.lock;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/9 19:46
 */
@Slf4j
public class ZkClientLock {
    private String lockPath = "/lock";
    private ZkClient zkClient;
    private CountDownLatch countDownLatch;
    /***当前请求节点的前一个节点*/
    private String beforePath;
    /***当前请求节点*/
    private String currentPath;

    public ZkClientLock(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public void lock() {
        if (tryLock()) {
            log.info("获取锁成功");
        } else {
            waitLock();
            lock();
        }
    }

    public void unlock() {
        zkClient.delete(currentPath);
        log.info("释放锁成功");
    }

    private boolean tryLock() {
        if (currentPath == null || currentPath.length() == 0) {
            currentPath = zkClient.createEphemeralSequential(lockPath + "/", "lock");
            log.info("当前锁路径为：{}", currentPath);
        }
        //获取全部子节点并排序
        List<String> children = zkClient.getChildren(lockPath);
        Collections.sort(children);

        //当前节点如果排在第一位，返回成功
        if (currentPath.equals(lockPath + "/" + children.get(0))) {
            return true;
        } else {
            //找出上一个节点
            String sequenceNodeName = currentPath.substring(lockPath.length() + 1);
            int i = Collections.binarySearch(children, sequenceNodeName);
            beforePath = lockPath + '/' + children.get(i - 1);
        }
        return false;
    }

    private void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }

            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
            }
        };
        this.zkClient.subscribeDataChanges(beforePath, listener);
        if (this.zkClient.exists(beforePath)) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.zkClient.unsubscribeDataChanges(beforePath, listener);
    }
}
