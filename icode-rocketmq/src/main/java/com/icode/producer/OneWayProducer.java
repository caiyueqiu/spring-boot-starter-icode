package com.icode.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/18 22:44
 */
public class OneWayProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_A");
        // NameServer
        producer.setNamesrvAddr("localhost:9876");
        // 开启生产者
        producer.start();
        for (int i = 0; i < 100; i++) {
            byte[] bytes = ("hi " + i).getBytes();
            Message msg = new Message("topic_A", "tag_A", bytes);
            // 单向发送
            producer.sendOneway(msg);
        }
        // 关闭生产者
        producer.shutdown();
    }
}
