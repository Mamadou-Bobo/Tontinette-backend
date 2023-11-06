package com.bobo.tontinette.authentication.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author Mamadou Bobo on 04/11/2023
 * @project Tontine
 */

@Configuration
@EnableRedisIndexedHttpSession
@Slf4j
@RequiredArgsConstructor
public class RedisConfig extends AbstractHttpSessionApplicationInitializer {
    private final RedisProperties redisProperties;

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(
                    this.redisProperties.getHost(),
                    this.redisProperties.getPort()
                );

        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
