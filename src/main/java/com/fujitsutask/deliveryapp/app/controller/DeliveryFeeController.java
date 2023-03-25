package com.fujitsutask.deliveryapp.app.controller;

import com.fujitsutask.deliveryapp.app.model.CityModel;
import com.fujitsutask.deliveryapp.weather.domain.Observations;
import com.fujitsutask.deliveryapp.weather.domain.Station;
import com.fujitsutask.deliveryapp.weather.mapper.WeatherMapper;
import com.fujitsutask.deliveryapp.weather.model.WeatherModel;
import com.fujitsutask.deliveryapp.weather.repository.WeatherRepository;
import com.fujitsutask.deliveryapp.weather.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Main controller class for handling incoming REST interface requests.
 */
@RestController
public class DeliveryFeeController {

    private final JpaRepository<WeatherModel, Long> weatherRepository;
    private final JpaRepository<CityModel, Long> citiesRepository;
    private final WeatherDataService weatherDataService;

    @Autowired
    public DeliveryFeeController(WeatherRepository weatherRepository, JpaRepository<CityModel, Long> citiesRepository,
                                 WeatherDataService weatherDataService) {
        this.weatherRepository = weatherRepository;
        this.citiesRepository = citiesRepository;
        this.weatherDataService = weatherDataService;
    }

    /**
     * Endpoint for requesting the delivery fee.
     * <p/>
     * The delivery fee is calculated based on the given {@code wmoCode} and {@code vehicleId} path variables.
     * @param cityId The WMO code of the weather station.
     * @param vehicleId ID of the vehicle used in the delivery.
     * @return The delivery fee for the requested city using the requested vehicle.
     */
    @GetMapping("api/v1/delivery/cities/{city_id}/vehicles/{vehicle_id}")
    public String getDeliveryFee(@PathVariable("city_id") Integer cityId,
                                 @PathVariable("vehicle_id") Integer vehicleId) {
        return weatherRepository.findAll().toString();
    }

    @GetMapping("api/v1/delivery/cities")
    public String getCities() {
        return citiesRepository.findAll().toString();
    }


    @GetMapping("api/v1/weather")
    public String getWeather() {
        Observations observations = weatherDataService.requestLatestWeatherInfo();
        List<CityModel> allCities = citiesRepository.findAll();

        Observations newDto = new Observations();
        newDto.setTimestamp(observations.getTimestamp());

        List<Station> filteredStations = new ArrayList<>();
        for (CityModel cityModel : allCities) {
            Station cityObservation = observations.getStations().stream()
                    .filter(station -> Objects.equals(station.getWmocode(), cityModel.getWeatherStationWmo()))
                    .findFirst().orElseThrow();
            filteredStations.add(cityObservation);
        }
        newDto.setStations(filteredStations);

        weatherRepository.saveAll(WeatherMapper.mapObservationsToModels(newDto));

        return newDto.toString();



    }

}
