package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaStreamsWordCountProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ProducerConfigValue.BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.ACKS_CONFIG, ProducerConfigValue.ACK_1);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            String[] sendMessages = {"backend best"};

            producer.send(new ProducerRecord<>(KafkaTopic.STREAMS_PLAINTEXT_INPUT, sendMessages[0]));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
