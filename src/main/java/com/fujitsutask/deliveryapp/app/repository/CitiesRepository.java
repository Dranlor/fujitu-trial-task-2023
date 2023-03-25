package com.fujitsutask.deliveryapp.app.repository;

import com.fujitsutask.deliveryapp.app.model.CitiesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for accessing entries in CITIES table in H2 database.
 * <p/>
 * The entries in CITIES table show all available cities for delivery and which weather station
 * corresponds to each city.
 */
@Repository
public interface CitiesRepository extends JpaRepository<CitiesModel, Long> {
    CitiesModel findByWeatherStationWmo(Integer wmo);
}
