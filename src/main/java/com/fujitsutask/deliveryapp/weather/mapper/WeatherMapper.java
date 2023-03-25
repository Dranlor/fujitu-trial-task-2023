package com.fujitsutask.deliveryapp.weather.mapper;

import com.fujitsutask.deliveryapp.weather.domain.Observations;
import com.fujitsutask.deliveryapp.weather.domain.Station;
import com.fujitsutask.deliveryapp.weather.dto.WeatherDto;
import com.fujitsutask.deliveryapp.weather.model.WeatherModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper used to map weather information info a database entity.
 */
public class WeatherMapper {
    public static WeatherDto toDto(WeatherModel model) {
        WeatherDto dto = new WeatherDto();
        dto.setId(model.getId());
        dto.setTimeStamp(model.getTimeStamp());
        dto.setName(model.getName());
        dto.setWmo(model.getWmo());
        dto.setAirTemperature(model.getAirTemperature());
        dto.setWindSpeed(model.getWindSpeed());
        dto.setWeatherPhenomenon(model.getWeatherPhenomenon());
        return dto;
    }

    public static WeatherModel fromDto(WeatherDto dto) {
        WeatherModel model = new WeatherModel();
        model.setId(dto.getId());
        model.setTimeStamp(dto.getTimeStamp());
        model.setName(dto.getName());
        model.setWmo(dto.getWmo());
        model.setAirTemperature(dto.getAirTemperature());
        model.setWindSpeed(dto.getWindSpeed());
        model.setWeatherPhenomenon(dto.getWeatherPhenomenon());
        return model;
    }

    private static WeatherModel mapStationToModel(Station station) {
        WeatherModel model = new WeatherModel();

        model.setName(station.getName());
        model.setWmo(station.getWmocode());
        model.setAirTemperature(station.getAirtemperature());
        model.setWindSpeed(station.getWindspeed());
        model.setWeatherPhenomenon(station.getPhenomenon());

        return model;
    }

    /**
     * Given an Observations object, map it to a list of WeatherModels.
     * @param observations Observations object.
     * @return List of WeatherModel objects.
     */
    public static List<WeatherModel> mapObservationsToModels(Observations observations) {
        List<WeatherModel> models = new ArrayList<>();

        for (Station station: observations.getStations()) {
            WeatherModel newModel = WeatherMapper.mapStationToModel(station);
            newModel.setTimeStamp(observations.getTimestamp());
            models.add(newModel);
        }

        return models;
    }

}

