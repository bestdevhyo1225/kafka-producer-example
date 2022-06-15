package com.example.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaStreamsPipeProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaStreamsPipeProducer.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ProducerConfigValue.BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.ACKS_CONFIG, ProducerConfigValue.ACK_1);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            RecordMetadata metadata = producer
                .send(new ProducerRecord<>(KafkaTopic.STREAMS_PLAINTEXT_INPUT, "Kafka Streams!"))
                .get();

            logger.info("Partition: {}, Offset: {}", metadata.partition(), metadata.offset());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
