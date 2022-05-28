package com.icode.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/18 22:43
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_A");
        // NameServer
        producer.setNamesrvAddr("localhost:9876");
        // 指定异步发送失败后不进行重试发送
        producer.setRetryTimesWhenSendAsyncFailed(0);
        // 指定新创建的Topic的Queue数量为2，默认为4
        producer.setDefaultTopicQueueNums(2);
        // 开启生产者
        producer.start();
        // 模拟发送100条消息
        for (int i = 0; i < 100; i++) {
            byte[] bytes = ("hi " + i).getBytes();
            try {
                Message msg = new Message("topic_A", "tag_A", bytes);
                // 异步发送，指定回调
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println(sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 异步发送，如果不执行sleep，则消息在发送之前，producer已经关闭，就会报错
        TimeUnit.SECONDS.sleep(3);
        // 关闭生产者
        producer.shutdown();
    }
}
