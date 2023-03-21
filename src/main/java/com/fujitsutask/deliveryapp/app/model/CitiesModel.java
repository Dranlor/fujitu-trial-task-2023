package com.fujitsutask.deliveryapp.app.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "CITIES")
public class CitiesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CITYNAME")
    private String cityName;

    @Column(name = "WEATHERSTATIONWMO")
    private Integer weatherStationWmo;

    @Column(name = "BASEFEECAR")
    private BigDecimal baseFeeCar;

    @Column(name = "BASEFEESCOOTER")
    private BigDecimal baseFeeScooter;

    @Column(name = "BASEFEEBIKE")
    private BigDecimal baseFeeBike;

    public Integer getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getWeatherStationWmo() {
        return weatherStationWmo;
    }

    public void setWeatherStationWmo(Integer weatherStationWmo) {
        this.weatherStationWmo = weatherStationWmo;
    }

    public BigDecimal getBaseFeeCar() {
        return baseFeeCar;
    }

    public void setBaseFeeCar(BigDecimal baseFeeCar) {
        this.baseFeeCar = baseFeeCar;
    }

    public BigDecimal getBaseFeeScooter() {
        return baseFeeScooter;
    }

    public void setBaseFeeScooter(BigDecimal baseFeeScooter) {
        this.baseFeeScooter = baseFeeScooter;
    }

    public BigDecimal getBaseFeeBike() {
        return baseFeeBike;
    }

    public void setBaseFeeBike(BigDecimal baseFeeBike) {
        this.baseFeeBike = baseFeeBike;
    }

    @Override
    public String toString() {
        return "CitiesModel{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", weatherStationWmo=" + weatherStationWmo +
                ", baseFeeCar=" + baseFeeCar +
                ", baseFeeScooter=" + baseFeeScooter +
                ", baseFeeBike=" + baseFeeBike +
                '}';
    }
}
