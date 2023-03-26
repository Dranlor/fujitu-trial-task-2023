package com.fujitsutask.deliveryapp.weather.mapper.test;

import com.fujitsutask.deliveryapp.weather.domain.Observations;
import com.fujitsutask.deliveryapp.weather.mapper.WeatherMapper;
import com.fujitsutask.deliveryapp.weather.model.WeatherModel;
import com.fujitsutask.deliveryapp.weather.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WeatherMapperTest {

    private Observations observations;
    @BeforeEach
    void setUp() {
        observations = Utils.getMockObservations();
    }

    @Test
    void testWeatherMapper_mappingObservationsToWeatherModelList() {
        List<WeatherModel> listOfModel = WeatherMapper.mapObservationsToModels(observations);

        for (WeatherModel model: listOfModel) {
            assert model.getTimeStamp().equals(observations.getTimestamp());
            assert  model.getName() != null;
        }
    }
}
