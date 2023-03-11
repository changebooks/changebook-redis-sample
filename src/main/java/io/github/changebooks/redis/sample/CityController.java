package io.github.changebooks.redis.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 注解
 *
 * @author changebooks
 */
@RequestMapping("city")
@RestController
public class CityController {

    @Resource
    private CityService cityService;

    @GetMapping(value = "/select-one")
    public City selectOne(@RequestParam("id") Integer id) {
        return cityService.selectOne(id);
    }

    @GetMapping(value = "/update-one")
    public int updateOne(@RequestParam("id") Integer id, @RequestParam("cityName") String cityName) {
        City record = new City();

        record.setId(id);
        record.setCityName(cityName);

        return cityService.updateOne(record);
    }

}
