package com.fujitsutask.deliveryapp.weather.mapper;

import com.fujitsutask.deliveryapp.weather.dto.StationDto;
import com.fujitsutask.deliveryapp.weather.model.WeatherModel;

/**
 * Mapper used to map weather information info a database entity.
 */
public class WeatherMapper {

    /**
     * Map data object to database entity.
     * @return New database entity.
     */
    public WeatherModel mapToModel(StationDto dto) {
        WeatherModel model = new WeatherModel();
        model.setName(dto.getName());
        model.setWeatherPhenomenon(dto.getPhenomenon());
        model.setWmo(dto.getWmocode());
        model.setWindSpeed(dto.getWindspeed());
        model.setAirTemperature(dto.getAirtemperature());

        return model;
    }
}
