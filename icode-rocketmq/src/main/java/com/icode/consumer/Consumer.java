package com.icode.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/18 22:42
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        // 定义一个push消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        // NameServer
        consumer.setNamesrvAddr("localhost:9876");
        // 指定从第一条消息开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 指定消费topic与tag
        consumer.subscribe("topic_A", "*");
        // 指定采用广播模式进行消费，默认为集群模式
        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            // 一旦broker中有了其订阅的消息就会触发该方法的执行，其返回值为当前consumer消费的状态
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : list) {
                    System.out.println(msg);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 开启消费者消费
        consumer.start();
        System.out.println("Consumer Started");
    }
}
