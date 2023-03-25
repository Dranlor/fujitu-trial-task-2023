package com.fujitsutask.deliveryapp.mapper;

import com.fujitsutask.deliveryapp.app.dto.CityDto;
import com.fujitsutask.deliveryapp.app.model.CityModel;

public class CityMapper {

    private CityMapper() {}

    public static CityDto toDto(CityModel model) {
        CityDto dto = new CityDto();

        dto.setId(model.getId());
        dto.setCityName(model.getCityName());
        dto.setBaseFeeBike(model.getBaseFeeBike());
        dto.setBaseFeeScooter(model.getBaseFeeScooter());
        dto.setBaseFeeCar(model.getBaseFeeCar());
        dto.setWeatherStationWmo(model.getWeatherStationWmo());

        return dto;
    }

    public static CityModel fromDto(CityDto dto) {
        CityModel model = new CityModel();

        model.setId(dto.getId());
        model.setCityName(dto.getCityName());
        model.setBaseFeeBike(dto.getBaseFeeBike());
        model.setBaseFeeScooter(dto.getBaseFeeScooter());
        model.setBaseFeeCar(dto.getBaseFeeCar());
        model.setWeatherStationWmo(dto.getWeatherStationWmo());

        return model;
    }
}
