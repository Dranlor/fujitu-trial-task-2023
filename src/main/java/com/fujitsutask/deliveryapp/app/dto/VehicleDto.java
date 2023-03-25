package com.fujitsutask.deliveryapp.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VehicleDto {
    private Long id;
    private String vehicleType;
    private BigDecimal atefFreezing;
    private BigDecimal atefModerate;
    private BigDecimal wsefModerate;
    private BigDecimal wpefSnowSleet;
    private BigDecimal wpefRain;
}

