package io.github.changebooks.redis.sample;

import io.github.changebooks.redis.CacheLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.*;

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
     * 加锁、解锁、续期，在同一线程
     */
    private static final ExecutorService THREAD_EXECUTOR = Executors.newSingleThreadExecutor();

    /**
     * 加锁
     *
     * @param expirationTime 过期时间
     * @return 加锁成功？
     */
    @GetMapping(value = "/lock")
    public Boolean lock(@RequestParam("expirationTime") Long expirationTime)
            throws ExecutionException, InterruptedException {
        FutureTask<Boolean> task = new FutureTask<>(() -> cacheLock.lock(expirationTime, TimeUnit.SECONDS));

        THREAD_EXECUTOR.execute(task);
        return task.get();
    }

    /**
     * 解锁
     *
     * @return 解锁成功？
     */
    @GetMapping(value = "/unlock")
    public Boolean unlock() throws ExecutionException, InterruptedException {
        FutureTask<Boolean> task = new FutureTask<>(() -> cacheLock.unlock());

        THREAD_EXECUTOR.execute(task);
        return task.get();
    }

    /**
     * 续期
     *
     * @param expirationTime 过期时间
     * @return 续期成功？
     */
    @GetMapping(value = "/renewal")
    public Boolean renewal(@RequestParam("expirationTime") Long expirationTime)
            throws ExecutionException, InterruptedException {
        FutureTask<Boolean> task = new FutureTask<>(() -> cacheLock.renewal(expirationTime, TimeUnit.SECONDS));

        THREAD_EXECUTOR.execute(task);
        return task.get();
    }

    /**
     * 定时续期
     *
     * @param delayTime      延迟时间
     * @param expirationTime 过期时间
     */
    @GetMapping(value = "/schedule-renewal")
    public void scheduleRenewal(@RequestParam("delayTime") Long delayTime, @RequestParam("expirationTime") Long expirationTime) {
        THREAD_EXECUTOR.execute(() -> cacheLock.scheduleRenewal(delayTime, expirationTime, TimeUnit.SECONDS));
    }

}
