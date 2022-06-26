package com.example.kafka.pipe;

public abstract class PipeProducerConfigValue {
    public static final String BOOTSTRAP_SERVERS = "localhost:19093,localhost:29093,localhost:39093";
    public static final String ACK_1 = "1";
    public static final int BUFFER_MEMORY = 33_554_432;
    public static final String COMPRESSION_TYPE_LZ4 = "lz4";
    public static final int RETRIES = 0;
    public static final int BATCH_SIZE = 500_000;
    public static final int LINGER_MS = 0;
    public static final int MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION = 10_000_000;
}
