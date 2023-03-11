package io.github.changebooks.redis.sample;

/**
 * 注解
 *
 * @author changebooks@qq.com
 */
public interface CityService {
    /**
     * 通过主键，获取一条记录
     *
     * @param id 主键
     * @return 城市
     */
    City selectOne(int id);

    /**
     * 通过主键，修改一条记录
     *
     * @param record 城市
     * @return 影响行数
     */
    int updateOne(City record);

}
