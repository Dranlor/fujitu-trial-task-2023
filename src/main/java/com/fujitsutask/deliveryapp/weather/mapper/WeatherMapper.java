package com.fujitsutask.deliveryapp.weather.mapper;

import com.fujitsutask.deliveryapp.weather.dto.StationDto;
import com.fujitsutask.deliveryapp.weather.model.WeatherModel;

/**
 * Mapper used to map weather information info a database entity.
 */
public class WeatherMapper {

    private WeatherMapper() {}

    /**
     * Map weather station DTO to a WeatherModel.
     * @return New database entity.
     */
    public static WeatherModel mapToEntity(StationDto dto) {
        WeatherModel model = new WeatherModel();

        model.setName(dto.getName());
        model.setWeatherPhenomenon(dto.getPhenomenon());
        model.setWmo(dto.getWmocode());
        model.setWindSpeed(dto.getWindspeed());
        model.setAirTemperature(dto.getAirtemperature());

        return model;
    }

    /**
     * Map from WeatherModel to a Station DTO.
     * @param model WeatherModel
     * @return StationDto
     */
    public static StationDto mapFromEntity(WeatherModel model) {
        StationDto stationDto = new StationDto();

        stationDto.setName(model.getName());
        stationDto.setAirtemperature(model.getAirTemperature());
        stationDto.setPhenomenon(model.getWeatherPhenomenon());
        stationDto.setWindspeed(model.getWindSpeed());
        stationDto.setWmocode(model.getWmo());

        return stationDto;
    }
}
