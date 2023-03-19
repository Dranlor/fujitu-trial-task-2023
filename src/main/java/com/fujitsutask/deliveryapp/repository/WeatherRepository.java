package com.fujitsutask.deliveryapp.repository;

import com.fujitsutask.deliveryapp.model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherModel, Long> {
}
