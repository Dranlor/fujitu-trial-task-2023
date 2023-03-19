package com.fujitsutask.deliveryapp.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "WEATHERINFO")
public class WeatherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat
    @Column(name = "TIMESTAMP")
    private Date timeStamp;

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

    public Integer getId() {
        return id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getName() {
        return name;
    }

    public Integer getWmo() {
        return wmo;
    }

    public Float getAirTemperature() {
        return airTemperature;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public String getWeatherPhenomenon() {
        return weatherPhenomenon;
    }
}
