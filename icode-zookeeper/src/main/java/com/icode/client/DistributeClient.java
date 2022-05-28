package com.icode.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/6 22:45
 */
@Slf4j
public class DistributeClient {
    private static final String connect = "localhost:2181";
    private static final int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    private void getServerList() throws Exception {
        List<String> children = zkClient.getChildren("/servers", true);
        List<String> servers = new ArrayList<>();
        for (String child : children) {
            byte[] data = zkClient.getData("/servers" + child, false, null);
            servers.add(new String(data));
        }
        log.info("server【{}】", servers);
    }

    private void getConnect() throws Exception {
        zkClient = new ZooKeeper(connect, sessionTimeout, watchedEvent -> {
            try {
                getServerList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        DistributeClient client = new DistributeClient();
        // 获取zk连接
        client.getConnect();
        // 监听/servers下面子节点的增加和删除
        client.getServerList();
        // 业务逻辑（延时模拟）
        Thread.sleep(Long.MAX_VALUE);
    }
}
