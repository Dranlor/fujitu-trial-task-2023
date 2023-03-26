package com.fujitsutask.deliveryapp.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Maps to CITIES table in H2 database.
 */
@Entity
@Data
@Table(name = "CITIES")
public class CityModel {

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
}
