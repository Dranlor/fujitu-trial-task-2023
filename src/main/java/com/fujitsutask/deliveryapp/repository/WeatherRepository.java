package com.fujitsutask.deliveryapp.repository;

import com.fujitsutask.deliveryapp.model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherModel, Long> {
    /**
     * Find the latest database entry for given weather station.
     * @param wmo WMO number of the weather station.
     * @return Model object with the latest weather information.
     */
    WeatherModel findTopByWmo(Integer wmo);
}
