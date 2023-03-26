package com.fujitsutask.deliveryapp.app.mapper;

import com.fujitsutask.deliveryapp.app.dto.CityDto;
import com.fujitsutask.deliveryapp.app.model.CityModel;

public class CityMapper {

    private CityMapper() {}

    /**
     * Map fields from given CityModel object to its equivalent CityDto object.
     * @param model CityModel object.
     * @return CityDto object.
     */
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
}
