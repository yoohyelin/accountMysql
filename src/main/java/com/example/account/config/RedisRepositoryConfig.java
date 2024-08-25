package com.example.account.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisRepositoryConfig {
    //application.yaml에 설정한 값을 @Value 어노테이션으로 주입합니다.
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    //RedissonClient를 사용하기 위해 bean으로 등록합니다.
    // Redis는 서버에서 실행되고 Redis 클라이언트가 Redis 서버에 연결해서 데이터를 조작한다
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 연결할 Redis 서버 정보
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);
        // Redission 라이브러리와 Redis 연결
        return Redisson.create(config);
    }
}
