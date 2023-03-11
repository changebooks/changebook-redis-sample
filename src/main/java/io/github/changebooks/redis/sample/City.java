package io.github.changebooks.redis.sample;

import java.io.Serializable;

/**
 * City
 *
 * @author changebooks@qq.com
 */
public final class City implements Serializable {
    /**
     * ID
     */
    private Integer id;

    /**
     * City Name
     */
    private String cityName;

    public City() {
    }

    @Override
    public String toString() {
        return "{" +
                "\"id:\": " + getId() + ", " +
                "\"cityName\": \"" + getCityName() + "\"" +
                "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
