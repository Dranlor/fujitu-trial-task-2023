package com.fujitsutask.deliveryapp.weather.repository;

import com.fujitsutask.deliveryapp.weather.model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WeatherRepository extends JpaRepository<WeatherModel, Long> {
    /**
     * Find the latest database entry for given weather station based on the station's wmo code.
     * @param wmo WMO number of the weather station.
     * @return Model object with the latest weather information.
     */
    Optional<WeatherModel> findDistinctTopByWmoOrderByTimeStampDesc(Integer wmo);
}
