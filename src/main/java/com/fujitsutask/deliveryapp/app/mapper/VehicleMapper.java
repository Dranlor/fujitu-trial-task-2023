package com.fujitsutask.deliveryapp.app.mapper;

import com.fujitsutask.deliveryapp.app.dto.VehicleDto;
import com.fujitsutask.deliveryapp.app.model.VehicleModel;

public class VehicleMapper {

    // Maps VehicleModel to VehicleDto
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

    // Maps VehicleDto to VehicleModel
    public static VehicleModel fromDto(VehicleDto vehicleDto) {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(vehicleDto.getId());
        vehicleModel.setVehicleType(vehicleDto.getVehicleType());
        vehicleModel.setAtefFreezing(vehicleDto.getAtefFreezing());
        vehicleModel.setAtefModerate(vehicleDto.getAtefModerate());
        vehicleModel.setWsefModerate(vehicleDto.getWsefModerate());
        vehicleModel.setWpefSnowSleet(vehicleDto.getWpefSnowSleet());
        vehicleModel.setWpefRain(vehicleDto.getWpefRain());
        return vehicleModel;
    }
}