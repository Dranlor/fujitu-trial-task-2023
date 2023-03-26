package com.fujitsutask.deliveryapp.weather.dto.test;

import com.fujitsutask.deliveryapp.weather.dto.WeatherDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class WeatherDtoTest {
    private WeatherDto weatherDto;

    @BeforeEach
    void setUp() {
        weatherDto = new WeatherDto();
        weatherDto.setId(1);
        weatherDto.setTimeStamp(12345L);
        weatherDto.setName("Test City");
        weatherDto.setWmo(12345);
        weatherDto.setAirTemperature(20.5f);
        weatherDto.setWindSpeed(10.0f);
        weatherDto.setWeatherPhenomenon("Sunny");
    }

    @Test
    void testWeatherDto_Getters() {
        assert 1 == weatherDto.getId();
        assert weatherDto.getTimeStamp() == 12345L;
        assert weatherDto.getName().equals("Test City");
        assert weatherDto.getWmo() == 12345;
        assert weatherDto.getAirTemperature() == 20.5f;
        assert weatherDto.getWindSpeed() == 10.0f;
        assert weatherDto.getWeatherPhenomenon().equals("Sunny");
    }
}

