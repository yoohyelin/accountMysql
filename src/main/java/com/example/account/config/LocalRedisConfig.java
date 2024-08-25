package com.example.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class LocalRedisConfig {
    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    // @PostConstruct, @PreDestory를 활용하여 redisServer를 start, stop 시킵니다.
    // Embedded Redis를 사용하면 애플리케이션 내에서 Redis를 실행하기 때문에 Redis가 애플리케이션에 의존하도록 할 수 있다.
    //테스트할 때 굉장히 유용한데, 마치 테스트를 할 때마다 Redis를 직접 껐다 킬 필요 없이 H2처럼 쉽게 Redis를 시작하고 중지할 수 있다.
    // 이렇게 외부 설치 없이 실행할 수 있는 애플리케이션은 누가 어디서든 프로젝트를 cloen 받으면 별도 설정 없이 바로 개발을 시작할 수 있게 되는 장점이 있다.
    @PostConstruct
    public void startRedis() {
        //redisServer = new RedisServer(redisPort); // 변경 도전
        redisServer = RedisServer.builder()
                        .port(6379)
                                .setting("maxmemory 128M")
                                        .build();
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
