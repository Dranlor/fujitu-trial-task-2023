package com.fujitsutask.deliveryapp.controller;

import com.fujitsutask.deliveryapp.model.WeatherModel;
import com.fujitsutask.deliveryapp.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller class for handling incoming REST interface requests.
 */
@RestController
public class DeliveryRestController {

    private final JpaRepository<WeatherModel, Long> weatherRepository;

    @Autowired
    public DeliveryRestController(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    /**
     * Endpoint for requesting the delivery fee.
     * <p/>
     * The delivery fee is calculated based on the given {@code wmoCode} and {@code vehicleId} path variables.
     * @param wmoCode The WMO code of the weather station.
     * @param vehicleId ID of the vehicle used in the delivery.
     * @return The delivery fee for the requested city using the requested vehicle.
     */
    @GetMapping("/cities/{wmo_code}/vehicles/{vehicle_id}")
    public String getDeliveryFee(@PathVariable("wmo_code") Integer wmoCode,
                                 @PathVariable("vehicle_id") Integer vehicleId) {
        return weatherRepository.findAll().toString();
    }

}
