package com.fujitsutask.deliveryapp.weather.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Model that maps to the WeatherInfo table entries in H2 database.
 */
@Data
@Entity
@Table(name = "WEATHERINFO")
public class WeatherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TIMESTAMP")
    private Long timeStamp;

    @Column(name = "STATIONNAME")
    private String name;

    @Column(name = "WMO")
    private Integer wmo;

    @Column(name = "AIRTEMPERATURE")
    private Float airTemperature;

    @Column(name = "WINDSPEED")
    private Float windSpeed;

    @Column(name = "WEATHERPHENOMENON")
    private String weatherPhenomenon;

}
