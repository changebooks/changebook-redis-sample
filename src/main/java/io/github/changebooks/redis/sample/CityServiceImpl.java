package io.github.changebooks.redis.sample;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changebooks@qq.com
 */
@Service
@CacheConfig(cacheNames = "city")
public class CityServiceImpl implements CityService {

    private static final Map<Integer, String> DATA = new HashMap<>(64);

    static {
        DATA.put(1, "Beijing");
        DATA.put(2, "Shanghai");
        DATA.put(3, "Tianjin");
        DATA.put(4, "Chongqing");
    }

    @Cacheable(key = "#id")
    @Override
    public City selectOne(int id) {
        String cityName = DATA.get(id);
        if (cityName == null) {
            return null;
        }

        City result = new City();

        result.setId(id);
        result.setCityName(cityName);

        return result;
    }

    @CacheEvict(key = "#record.id")
    @Override
    public int updateOne(City record) {
        Assert.notNull(record, "record can't be null");

        Integer id = record.getId();
        String cityName = record.getCityName();
        if (id == null || cityName == null) {
            return 0;
        }

        if (DATA.containsKey(id)) {
            DATA.put(id, cityName);
            return 1;
        } else {
            return 0;
        }
    }

}
