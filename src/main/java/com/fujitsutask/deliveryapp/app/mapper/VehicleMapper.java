package com.fujitsutask.deliveryapp.app.mapper;

import com.fujitsutask.deliveryapp.app.dto.VehicleDto;
import com.fujitsutask.deliveryapp.app.model.VehicleModel;

public class VehicleMapper {

    /**
     * Map vehicleModel to VehicleDto.
     * @param vehicleModel VehicleModel object.
     * @return VehicleDto.
     */
    public static VehicleDto toDto(VehicleModel vehicleModel) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicleModel.getId());
        vehicleDto.setVehicleType(vehicleModel.getVehicleType());
        vehicleDto.setAtefFreezing(vehicleModel.getAtefFreezing());
        vehicleDto.setAtefModerate(vehicleModel.getAtefModerate());
        vehicleDto.setWsefModerate(vehicleModel.getWsefModerate());
        vehicleDto.setWpefSnowSleet(vehicleModel.getWpefSnowSleet());
        vehicleDto.setWpefRain(vehicleModel.getWpefRain());
        return vehicleDto;
    }

}