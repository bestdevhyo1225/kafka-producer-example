package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaBookProducerFor {

    public static void main(String[] args) {
        // Properties 오브젝트 생성
        Properties props = new Properties();

        // 브로커 리스트를 정의한다.
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ProducerConfigValue.BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, ProducerConfigValue.COMPRESSION_TYPE_GZIP);

        // Ack 모드를 1로 설정
        props.put(ProducerConfig.ACKS_CONFIG, ProducerConfigValue.ACK_1);

        // 메시지의 키와 값에 문자열을 사용할 예정이므로, 내장된 StringSerializer를 지정한다.
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Properties 오브젝트를 전달하여 새 프로듀서를 생성한다.
        // 참고) 아래의 코드에 의해서 producer.close() 메소드를 호출하고, 이를 통해 커넥션 리소스를 정리해준다.
        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            // ProducerRecord 오브젝트를 생성하고, send() 메소드를 사용해 hyo-topic 으로 메시지를 전송한다.
            for (int i = 0; i < 100; i++) {
                producer.send(
                    new ProducerRecord<>(KafkaTopic.HYO_TOPIC, "Apache Kafka is a distributed streaming platform")
                );
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
