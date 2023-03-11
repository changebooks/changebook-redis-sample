package io.github.changebooks.redis.sample;

import io.github.changebooks.redis.TokenBucket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 分布式限流（令牌桶）
 *
 * @author changebooks
 */
@RequestMapping("token-bucket")
@RestController
public class TokenBucketController {

    @Resource
    private TokenBucket tokenBucket;

    /**
     * 取出令牌
     *
     * @param permits 待取令牌数
     * @return 实取令牌数
     */
    @GetMapping(value = "/acquire")
    public Long acquire(@RequestParam("permits") Integer permits) {
        return tokenBucket.acquire(permits);
    }

}
