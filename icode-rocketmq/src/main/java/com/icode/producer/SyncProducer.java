package com.icode.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/18 22:39
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_A");
        // NameServer
        producer.setNamesrvAddr("localhost:9876");
        // 设置当发送失败时重试发送的次数，默认是2次
        producer.setRetryTimesWhenSendFailed(3);
        // 设置发送超时时间，默认3秒
        producer.setSendMsgTimeout(5000);
        // 开启生产者
        producer.start();
        // 模拟发送100条消息
        for (int i = 0; i < 100; i++) {
            byte[] bytes = ("hi " + i).getBytes();
            Message msg = new Message("topic_A", "tag_A", bytes);
            // 为消息指定key
            msg.setKeys("key-" + i);
            // 发送消息
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        // 关闭生产者
        producer.shutdown();
    }
}
