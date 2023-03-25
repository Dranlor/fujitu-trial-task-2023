package com.fujitsutask.deliveryapp.weather.dto;

import lombok.Data;

@Data
public class WeatherDto {
    private Integer id;
    private Long timeStamp;
    private String name;
    private Integer wmo;
    private Float airTemperature;
    private Float windSpeed;
    private String weatherPhenomenon;
}
