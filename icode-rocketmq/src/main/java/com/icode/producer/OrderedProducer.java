package com.icode.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/18 22:45
 */
public class OrderedProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_A");
        // NameServer
        producer.setNamesrvAddr("localhost:9876");
        // 开启生产者
        producer.start();
        for (int i = 0; i < 100; i++) {
            Integer orderId = i;
            byte[] bytes = ("hi " + i).getBytes();
            Message msg = new Message("topic_A", "tag_A", bytes);
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % list.size();
                    return list.get(index);
                }
            }, orderId);
            System.out.println(sendResult);
        }
        // 关闭生产者
        producer.shutdown();
    }
}
