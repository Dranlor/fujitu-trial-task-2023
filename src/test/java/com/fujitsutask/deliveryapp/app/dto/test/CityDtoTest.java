package com.fujitsutask.deliveryapp.app.dto.test;

import com.fujitsutask.deliveryapp.app.dto.CityDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class CityDtoTest {

    private CityDto cityDto;
    @BeforeEach
    void setUp() {
        cityDto = new CityDto();
        cityDto.setId(1);
        cityDto.setCityName("Tallinn");
        cityDto.setWeatherStationWmo(12345);
        cityDto.setBaseFeeCar(new BigDecimal("2.0"));
        cityDto.setBaseFeeBike(new BigDecimal("1.0"));
        cityDto.setBaseFeeScooter(new BigDecimal("1.5"));
    }

    @Test
    void testCityDto_Getters() {
        assert cityDto.getId() == 1;
        assert cityDto.getCityName().equals("Tallinn");
        assert cityDto.getWeatherStationWmo() == 12345;
        assert cityDto.getBaseFeeCar().equals(new BigDecimal("2.0"));
        assert cityDto.getBaseFeeScooter().equals(new BigDecimal("1.5"));
        assert cityDto.getBaseFeeBike().equals(new BigDecimal("1.0"));
    }

}
