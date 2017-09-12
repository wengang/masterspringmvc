package io.metaphor.masterSpringMvc.config;

import org.mapstruct.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.Arrays;


@Configuration
@Profile("redis")
@EnableRedisHttpSession
public class RedisConfig {
//    @Bean(name = "objectRedisTemplate")
//    public RedisTemplate objectRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object,Object> template =new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
    @Primary @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager =new RedisCacheManager(redisTemplate);
        cacheManager.setCacheNames(Arrays.asList("searches"));
        cacheManager.setDefaultExpiration(36_000);
        return cacheManager;
    }
}
