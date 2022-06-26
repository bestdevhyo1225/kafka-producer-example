package com.example.kafka.linesplit;

import com.example.kafka.KafkaTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaStreamsLineSplitProducerFor {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, LineSplitProducerConfigValue.BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.ACKS_CONFIG, LineSplitProducerConfigValue.ACK_1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, LineSplitProducerConfigValue.BUFFER_MEMORY);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, LineSplitProducerConfigValue.COMPRESSION_TYPE_LZ4);
        props.put(ProducerConfig.RETRIES_CONFIG, LineSplitProducerConfigValue.RETRIES);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, LineSplitProducerConfigValue.BATCH_SIZE);
        props.put(ProducerConfig.LINGER_MS_CONFIG, LineSplitProducerConfigValue.LINGER_MS);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, LineSplitProducerConfigValue.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            String[] sendMessages = {"Apple is Good", "Kafka is Good", "Happy Kafka and Apple"};

            for (int i = 0; i < 3; i++) {
                producer.send(
                    new ProducerRecord<>(KafkaTopic.STREAMS_PLAINTEXT_INPUT, sendMessages[i]),
                    new KafkaStreamsLineSplitAsyncCallback()
                );
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
