package io.metaphor.masterSpringMvc.config;

import io.metaphor.masterSpringMvc.search.TweetSource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("searches")));
        return simpleCacheManager;
    }
    @Bean
    public TweetSource tweetSource() {
        TweetSource tweetSource=new TweetSource();
        return  tweetSource;
    }
}
