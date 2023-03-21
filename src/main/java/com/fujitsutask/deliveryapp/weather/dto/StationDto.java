package com.fujitsutask.deliveryapp.weather.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "station")
public class StationDto {


    private String name;


    private String wmocode;


    private String phenomenon;


    private double airtemperature;


    private double windspeed;


    public StationDto() {
    }

    public String getName() {
        return name;
    }

    @JacksonXmlProperty(localName = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getWmocode() {
        return wmocode;
    }

    @JacksonXmlProperty(localName = "wmocode")
    public void setWmocode(String wmocode) {
        this.wmocode = wmocode;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    @JacksonXmlProperty(localName = "phenomenon")
    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public double getAirtemperature() {
        return airtemperature;
    }

    @JacksonXmlProperty(localName = "airtemperature")
    public void setAirtemperature(double airtemperature) {
        this.airtemperature = airtemperature;
    }

    public double getWindspeed() {
        return windspeed;
    }

    @JacksonXmlProperty(localName = "windspeed")
    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    @Override
    public String toString() {
        return "StationDto{" +
                "name='" + name + '\'' +
                ", wmocode='" + wmocode + '\'' +
                ", phenomenon='" + phenomenon + '\'' +
                ", airtemperature=" + airtemperature +
                ", windspeed=" + windspeed +
                '}';
    }
}
