package com.example.kafka.linesplit;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaStreamsLineSplitAsyncCallback implements Callback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (metadata != null) {
            logger.info("Partition: {}, Offset: {}", metadata.partition(), metadata.offset());
        } else {
            logger.error("error", exception);
        }
    }
}
