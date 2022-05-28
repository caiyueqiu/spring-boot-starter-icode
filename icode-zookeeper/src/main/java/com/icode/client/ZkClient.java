package com.icode.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/6 22:04
 */
@Slf4j
public class ZkClient {

    private static final String connect = "localhost:2181";
    private static final int sessionTimeout = 2000;
    private ZooKeeper zkClient;

    /**
     * 创建zkClient
     *
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(connect, sessionTimeout, watchedEvent -> {
            List<String> children = null;
            try {
                children = zkClient.getChildren("/", true);
                for (String child : children) {
                    log.info("{}", child);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建子节点
     *
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        /*
        参数1：要创建的节点的路径
        参数2：节点数据
        参数3：节点权限
        参数4：节点的类型
         */
        String node = zkClient.create("/zk", "a.txt".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取子节点并监听节点变化
     *
     * @throws Exception
     */
    @Test
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            log.info("{}", child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 判断节点是否存在
     *
     * @throws Exception
     */
    @Test
    public void exist() throws Exception {
        Stat stat = zkClient.exists("/zk", false);
        if (stat == null) {
            log.info("not exist");
        } else {
            log.info("exist");
        }
    }
}
