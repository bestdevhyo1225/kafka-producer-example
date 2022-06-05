package com.example.kafka;

public abstract class ProducerConfigValue {
    public static final String BOOTSTRAP_SERVERS = "localhost:19093,localhost:29093,localhost:39093";
    public static final String ACK_0 = "0";
    public static final String ACK_1 = "1";
    public static final String ACK_ALL = "all"; // -1 값과 동일하다.
    public static final String COMPRESSION_TYPE_GZIP = "gzip";
}
