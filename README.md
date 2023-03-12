# changebook-redis-sample
### 分布式锁、分布式限流、注解

### 注解
```
// 存取缓存
http://127.0.0.1:8080/city/select-one?id=1

// 删除缓存
http://127.0.0.1:8080/city/update-one?id=1&cityName=bj
```

### 分布式锁
```
// 加锁
http://127.0.0.1:8080/cache-lock/lock?expirationTime=30

// 解锁
http://127.0.0.1:8080/cache-lock/unlock

// 续期
http://127.0.0.1:8080/cache-lock/renewal?expirationTime=30

// 定时续期
http://127.0.0.1:8080/cache-lock/schedule-renewal?delayTime=10&expirationTime=30
```

### 分布式限流，固定时间窗口，x秒内，许可n次
```
// 获取许可
http://127.0.0.1:8080/rate-limiter/acquire
```

### 分布式限流，令牌桶，每秒放入n个令牌
```
// 取出令牌
http://127.0.0.1:8080/token-bucket/acquire?permits=2
```
