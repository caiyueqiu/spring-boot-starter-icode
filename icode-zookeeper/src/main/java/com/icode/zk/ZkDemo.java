package com.icode.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/5/20 22:45
 */
public class ZkDemo {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        curatorFramework.start();
    }

    public void add() throws Exception {
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/test", "".getBytes());
//        curatorFramework.setData().forPath();
//        curatorFramework.getData().forPath();
//        curatorFramework.delete().forPath();
    }

    public static void main(String[] args) {

    }
}
