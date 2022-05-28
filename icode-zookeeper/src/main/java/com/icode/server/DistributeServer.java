package com.icode.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/6 22:37
 */
@Slf4j
public class DistributeServer {
    private static final String connect = "localhost:2181";
    private static final int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    private void register(String hostname) throws Exception {
        String node = zkClient.create("/servers" + hostname, hostname.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        log.info("hostname【{}】 is online", hostname);
    }

    private void getConnect() throws Exception {
        zkClient = new ZooKeeper(connect, sessionTimeout, watchedEvent -> {
        });
    }

    public static void main(String[] args) throws Exception {
        DistributeServer server = new DistributeServer();
        // 获取zk连接
        server.getConnect();
        // 注册服务器到zk集群
        server.register(args[0]);
        // 业务逻辑（延时模拟）
        Thread.sleep(Long.MAX_VALUE);
    }
}
