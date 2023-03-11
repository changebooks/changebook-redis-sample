package io.github.changebooks.redis.sample;

import io.github.changebooks.redis.CacheLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author changebooks
 */
@RequestMapping("cache-lock")
@RestController
public class CacheLockController {

    @Resource
    private CacheLock cacheLock;

    /**
     * 加锁
     *
     * @param expirationTime 过期时间
     * @return 加锁成功？
     */
    @GetMapping(value = "/lock")
    public Boolean lock(@RequestParam("expirationTime") Long expirationTime) {
        return cacheLock.lock(expirationTime, TimeUnit.SECONDS);
    }

    /**
     * 解锁
     *
     * @return 解锁成功？
     */
    @GetMapping(value = "/unlock")
    public Boolean unlock() {
        return cacheLock.unlock();
    }

    /**
     * 续期
     *
     * @param expirationTime 过期时间
     * @return 续期成功？
     */
    @GetMapping(value = "/renewal")
    public Boolean renewal(@RequestParam("expirationTime") Long expirationTime) {
        return cacheLock.renewal(expirationTime, TimeUnit.SECONDS);
    }

}
