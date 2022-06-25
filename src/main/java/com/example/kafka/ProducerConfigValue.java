package com.example.kafka;

public abstract class ProducerConfigValue {
    public static final String BOOTSTRAP_SERVERS = "localhost:19093,localhost:29093,localhost:39093";
    public static final String ACK_0 = "0";
    public static final String ACK_1 = "1";
    public static final String ACK_ALL = "all"; // -1 값과 동일하다.
    public static final int BUFFER_MEMORY = 33554432;
    public static final String COMPRESSION_TYPE_GZIP = "gzip";
    public static final String COMPRESSION_TYPE_LZ4 = "lz4";
    public static final int RETRIES = 1;
    public static final int BATCH_SIZE = 500000;
    public static final int LINGER_MS = 50;
    public static final int MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION = 1;
}
