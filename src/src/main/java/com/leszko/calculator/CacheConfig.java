package com.leszko.calculator;

import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/** Cache config with Redis. */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    private final static String REDIS_HOST = "localhost";

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName(REDIS_HOST);
        standaloneConfig.setPort(6379);

        return new JedisConnectionFactory(standaloneConfig);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate =
                new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        return new RedisCacheManager(redisTemplate);
//    }
}
