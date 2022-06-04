package com.example.kafka.sync;

import com.example.kafka.ProducerConfigValue;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaBookProducerSync {

    private static final Logger logger = LoggerFactory.getLogger(KafkaBookProducerSync.class);

    public static void main(String[] args) {
        // Properties 오브젝트 생성
        Properties props = new Properties();

        // 브로커 리스트를 정의한다.
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ProducerConfigValue.BOOTSTRAP_SERVERS);

        // 메시지의 키와 값에 문자열을 사용할 예정이므로, 내장된 StringSerializer를 지정한다.
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // [ 동기 전송 ]
        // Properties 오브젝트를 전달하여 새 프로듀서를 생성한다.
        // 참고) 아래의 코드에 의해서 producer.close() 메소드를 호출하고, 이를 통해 커넥션 리소스를 정리해준다.
        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            // ProducerRecord 오브젝트를 생성하고, send() 메소드를 사용해 hyo-topic 으로 메시지를 전송한다.
            RecordMetadata metadata = producer
                    .send(new ProducerRecord<>("hyo-topic", "Apache Kafka is a distributed streaming platform"))
                    .get();

            logger.info("Partition: {}, Offset: {}", metadata.partition(), metadata.offset());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
