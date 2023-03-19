package com.fujitsutask.deliveryapp.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "WeatherInfo", catalog = "h2")
public class WeatherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat
    @Column(name = "TimeStamp")
    private Date timeStamp;

    @Column(name = "Name")
    private String name;



    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
