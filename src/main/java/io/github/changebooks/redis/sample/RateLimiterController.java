package io.github.changebooks.redis.sample;

import io.github.changebooks.redis.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 分布式限流（固定时间窗口）
 *
 * @author changebooks
 */
@RequestMapping("rate-limiter")
@RestController
public class RateLimiterController {

    @Resource
    private RateLimiter rateLimiter;

    /**
     * 获取许可
     *
     * @return 得到许可？
     */
    @GetMapping(value = "/acquire")
    public Boolean acquire() {
        return rateLimiter.acquire();
    }

}
