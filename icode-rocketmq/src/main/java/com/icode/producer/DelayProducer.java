package com.icode.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/18 23:01
 */
public class DelayProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_A");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            byte[] bytes = ("hi " + i).getBytes();
            Message msg = new Message("topic_A", "tag_A", bytes);
            // 执行消息的延迟等级为3级，即延迟10秒
            msg.setDelayTimeLevel(3);
            SendResult sendResult = producer.send(msg);
            // 输出消息被发送的时间
            System.out.println(new SimpleDateFormat("mm:ss").format(new Date()));
            System.out.println(" , " + sendResult);
        }
        producer.shutdown();
    }
}
