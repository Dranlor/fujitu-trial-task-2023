package com.fujitsutask.deliveryapp.repository;

import com.fujitsutask.deliveryapp.model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherModel, Long> {
    List<WeatherModel> findAllByName(String name);
}
