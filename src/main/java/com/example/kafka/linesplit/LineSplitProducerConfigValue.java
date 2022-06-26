package com.example.kafka.linesplit;

public abstract class LineSplitProducerConfigValue {
    public static final String BOOTSTRAP_SERVERS = "localhost:19093,localhost:29093,localhost:39093";
    public static final String ACK_1 = "1";
    public static final int BUFFER_MEMORY = 33_554_432;
    public static final String COMPRESSION_TYPE_LZ4 = "lz4";
    public static final int RETRIES = 0;
    public static final int BATCH_SIZE = 100_000; // 100KB
    public static final int LINGER_MS = 50;
    public static final int MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION = 10_000_000;
}
