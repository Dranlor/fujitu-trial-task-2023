package com.fujitsutask.deliveryapp.controller;

import com.fujitsutask.deliveryapp.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller class for handling incoming REST interface requests.
 */
@RestController
public class DeliveryRestController {

    private final WeatherRepository repository;

    @Autowired
    public DeliveryRestController(WeatherRepository repository) {
        this.repository = repository;
    }

    /**
     * Endpoint for requesting the delivery fee.
     * <p/>
     * The delivery fee is calculated based on the given {@code cityId} and {@code vehicleId} path variables.
     * @param cityId The ID that represents the city for which the delivery fee is calculated.
     * @param vehicleId ID of the vehicle used in the delivery.
     * @return The delivery fee for the requested city using the requested vehicle.
     */
    @GetMapping("/cities/{city_id}/vehicles/{vehicle_id}")
    public String getDeliveryFee(@PathVariable("city_id") Integer cityId,
                                 @PathVariable("vehicle_id") Integer vehicleId) {

        return repository.findAll().toString();
    }

}
