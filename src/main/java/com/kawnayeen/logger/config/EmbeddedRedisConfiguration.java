package com.kawnayeen.logger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import redis.embedded.RedisServer;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Created by kawnayeen on 1/13/17.
 */
@Configuration
@EnableRedisRepositories
public class EmbeddedRedisConfiguration {
    private static RedisServer redisServer;

    @Bean
    public JedisConnectionFactory connectionFactory() throws IOException{
        redisServer = new RedisServer();
        redisServer.start();
        return new JedisConnectionFactory();
    }

    @PreDestroy
    public void stopRedisServer(){
        redisServer.stop();
    }
}
