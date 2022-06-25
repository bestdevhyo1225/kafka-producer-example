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

        // Ack 모드를 1로 설정
        props.put(ProducerConfig.ACKS_CONFIG, ProducerConfigValue.ACK_1);

        // 잠시 대기 (배치 전송이나 딜레이 등등..) 할 수 있는 전체 메모리 바이트
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, ProducerConfigValue.BUFFER_MEMORY);

        // 메시지를 압축해서 보낼 수 있는데, 어떤 타입으로 압축할 지 정한다.
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, ProducerConfigValue.COMPRESSION_TYPE_LZ4);

        // 메시지를 다시 보내는 횟수
        props.put(ProducerConfig.RETRIES_CONFIG, ProducerConfigValue.RETRIES);

        // 같은 파티션으로 보내는 여러 데이터를 함께 배치로 보내려고 시도하는데, 배치의 크기를 바이트 단위로 조정할 수 있다.
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, ProducerConfigValue.BATCH_SIZE);

        // 배치 형태의 메시지를 보내기 전에 추가적인 메시지들을 위해 기다리는 시간을 조정한다.
        // batch.size 값에 도달하지 못한 상황에서 linger.ms 제한 시간에 도달했을 때, 메시지들을 전송한다.
        props.put(ProducerConfig.LINGER_MS_CONFIG, ProducerConfigValue.LINGER_MS);

        // 이 값은 KafkaProducer 클라이언트가 하나의 브로커로 동시에 전송할 수 있는 요청 수를 의미한다.
        // retries 설정값이 1 이상인 경우 재시도하기 때문에 max.in.flight.requests.per.connection 값이 1보다 크면 순서가 바뀔 수 있다.
        // 순서를 보장하려면 max.in.flight.requests.per.connection 값을 1로 설정해야 한다.
        // 하지만 이렇게 설정하면 동시에 1개의 요청만 처리할 수 있기 때문에 전송 성능이 떨어질 수 있다.
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, ProducerConfigValue.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION);

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
