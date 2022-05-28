package com.icode.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 带回调函数
 *
 * @author caiyq
 * @date 2022/4/14 8:59
 */
@Slf4j
public class ProducerDemo2 {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, 1);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        for (int i = 0; i < 100; i++) {
            kafkaProducer.send(new ProducerRecord<>("topic-test", Integer.toString(i), Integer.toString(i)), (recordMetadata, e) -> {
                if (e == null) {
                    log.info("success:[{}]", recordMetadata.offset());
                } else {
                    e.printStackTrace();
                }
            });
        }
        kafkaProducer.close();
    }
}
