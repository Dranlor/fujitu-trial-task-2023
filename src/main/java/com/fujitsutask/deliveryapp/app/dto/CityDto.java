package com.fujitsutask.deliveryapp.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CityDto {
    private Integer id;
    private String cityName;
    private Integer weatherStationWmo;
    private BigDecimal baseFeeCar;
    private BigDecimal baseFeeScooter;
    private BigDecimal baseFeeBike;
}
