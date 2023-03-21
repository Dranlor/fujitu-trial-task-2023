package com.fujitsutask.deliveryapp.weather.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Model that maps to the WeatherInfo table entries in H2 database.
 */
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

    public void setName(String name) {
        this.name = name;
    }

    public void setWmo(Integer wmo) {
        this.wmo = wmo;
    }

    public void setAirTemperature(Float airTemperature) {
        this.airTemperature = airTemperature;
    }

    public void setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWeatherPhenomenon(String weatherPhenomenon) {
        this.weatherPhenomenon = weatherPhenomenon;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", name='" + name + '\'' +
                ", wmo=" + wmo +
                ", airTemperature=" + airTemperature +
                ", windSpeed=" + windSpeed +
                ", weatherPhenomenon='" + weatherPhenomenon + '\'' +
                '}';
    }
}
