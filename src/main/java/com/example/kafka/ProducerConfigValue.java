package com.example.kafka;

public abstract class ProducerConfigValue {
    public static String BOOTSTRAP_SERVERS = "localhost:19093,localhost:29093,localhost:39093";
    public static String ACK_0 = "0";
    public static String ACK_1 = "1";
    public static String ACK_ALL = "all"; // -1 값과 동일하다.
    public static String COMPRESSION_TYPE_GZIP = "gzip";
}
