package io.github.changebooks.redis.sample;

import io.github.changebooks.redis.*;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author changebooks@qq.com
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties({CacheProperties.class})
public class CachingConfigurerSupportImpl extends CachingConfigurerSupport {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CacheProperties cacheProperties;

    @Bean
    public CacheLock cacheLock(CacheDistributedSupport cacheDistributedSupport) {
        return cacheDistributedSupport.cacheLock("cache-lock-sample", UUID.randomUUID().toString());
    }

    @Bean
    public RateLimiter rateLimiter(CacheDistributedSupport cacheDistributedSupport) {
        return cacheDistributedSupport.rateLimiter("rate-limiter-sample", 10, 2);
    }

    @Bean
    public TokenBucket tokenBucket(CacheDistributedSupport cacheDistributedSupport) {
        return cacheDistributedSupport.tokenBucket("token-bucket-sample", 10, 2);
    }

    @Bean
    public CacheManager cacheManager(CacheManagerSupport cacheManagerSupport) {
        return cacheManagerSupport.cacheManager();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheExceptionHandler();
    }

    @Bean
    public CacheManagerSupport cacheManagerSupport(CachePrefixNameTtl cachePrefixNameTtl) {
        if (cachePrefixNameTtl == null) {
            return new CacheManagerSupport(redisConnectionFactory);
        } else {
            return new CacheManagerSupport(redisConnectionFactory, cachePrefixNameTtl);
        }
    }

    @Bean
    public CacheDistributedSupport cacheDistributedSupport(CachePrefixNameTtl cachePrefixNameTtl) {
        if (cachePrefixNameTtl == null) {
            return new CacheDistributedSupport(stringRedisTemplate);
        } else {
            return new CacheDistributedSupport(stringRedisTemplate, cachePrefixNameTtl);
        }
    }

    @Bean
    public CachePrefixNameTtl cachePrefixNameTtl(CachePrefixNameTtlSupport cachePrefixNameTtlSupport) {
        return cachePrefixNameTtlSupport.cachePrefixNameTtl(cacheProperties);
    }

    @Bean
    public CachePrefixNameTtlSupport cachePrefixNameTtlSupport() {
        return new CachePrefixNameTtlSupport();
    }

}
