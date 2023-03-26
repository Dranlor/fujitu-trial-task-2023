package com.fujitsutask.deliveryapp.weather.service.test;

import com.fujitsutask.deliveryapp.weather.domain.Observations;
import com.fujitsutask.deliveryapp.weather.domain.Station;
import com.fujitsutask.deliveryapp.weather.repository.WeatherRepository;
import com.fujitsutask.deliveryapp.weather.service.WeatherDataService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.fujitsutask.deliveryapp.weather.utils.Utils.getMockObservations;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherServiceTest {
    @Mock
    private final WeatherDataService service;


    @Autowired
    public WeatherServiceTest(WeatherDataService service) {
        this.service = service;
    }

    @Test
    void testWeatherService_requestLatestWeatherInfo_RequestedDataSizeIsCorrect() {
        when(service.requestLatestWeatherInfo()).thenReturn(getMockObservations());

        Observations latestInfo = service.requestLatestWeatherInfo();
        assert latestInfo.getStations().size() == 3;
    }


}
