package com.fujitsutask.deliveryapp.weather.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
