package com.example.kafka.pipe;

import com.example.kafka.KafkaTopic;
import com.example.kafka.ProducerConfigValue;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaStreamsPipeProducerFor {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ProducerConfigValue.BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.ACKS_CONFIG, ProducerConfigValue.ACK_1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, ProducerConfigValue.BUFFER_MEMORY);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, ProducerConfigValue.COMPRESSION_TYPE_LZ4);
        props.put(ProducerConfig.RETRIES_CONFIG, ProducerConfigValue.RETRIES);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, ProducerConfigValue.BATCH_SIZE);
        props.put(ProducerConfig.LINGER_MS_CONFIG, ProducerConfigValue.LINGER_MS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            for (int outerIndex = 0; outerIndex < 3; outerIndex++) {
                for (int innerIndex = 0; innerIndex < 10; innerIndex++) {
                    producer.send(
                        new ProducerRecord<>(KafkaTopic.STREAMS_PLAINTEXT_INPUT, "Kafka is Good"),
                        new KafkaStreamsPipeAsyncCallback()
                    );
                }
                Thread.sleep(1000);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
