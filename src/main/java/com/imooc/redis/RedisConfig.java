package com.imooc.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    private String host;
    private int port;
    private int timeout;
    private String password;
    private int poolMaxActive;
    private int poolMaxidle;
    private int poolMaxWait;

}
