package com.icode.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

/**
 * 异步提交offset
 *
 * @author caiyq
 * @date 2022/4/14 21:49
 */
@Slf4j
public class ConsumerDemo4 {
    private static Map<TopicPartition, Long> currentOffset = new HashMap<>();

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-test");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singletonList("topic-test"), new ConsumerRebalanceListener() {
            // 该方法会在ReBalance之前调用
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                commitOffset(currentOffset);
            }
            // 该方法会在ReBalance之后调用
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                currentOffset.clear();
                for (TopicPartition partition : collection) {
                    // 定位到最近提交的offset位置继续消费
                    kafkaConsumer.seek(partition, getOffset(partition));
                }
            }
        });
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ZERO);
            for (ConsumerRecord<String, String> record :records) {
                log.info("offset = {}, key = {}, value = {}", record.offset(), record.key(), record.value());
                currentOffset.put(new TopicPartition(record.topic(), record.partition()), record.offset());
            }
            // 异步提交
            commitOffset(currentOffset);
        }
    }

    private static long getOffset(TopicPartition topicPartition) {
        return 0;
    }

    private static void commitOffset(Map<TopicPartition, Long> currentOffset) {

    }
}
