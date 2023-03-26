package com.fujitsutask.deliveryapp.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fujitsutask.deliveryapp.app.dto.DeliveryFeeDto;
import com.fujitsutask.deliveryapp.app.dto.VehicleDto;
import com.fujitsutask.deliveryapp.app.exception.DeliveryFeeException;
import com.fujitsutask.deliveryapp.app.model.CityModel;
import com.fujitsutask.deliveryapp.app.model.VehicleModel;
import com.fujitsutask.deliveryapp.app.repository.CitiesRepository;
import com.fujitsutask.deliveryapp.app.repository.VehicleRepository;
import com.fujitsutask.deliveryapp.app.service.DeliveryFeeService;
import com.fujitsutask.deliveryapp.app.mapper.CityMapper;
import com.fujitsutask.deliveryapp.app.mapper.VehicleMapper;
import com.fujitsutask.deliveryapp.weather.domain.Observations;
import com.fujitsutask.deliveryapp.weather.mapper.WeatherMapper;
import com.fujitsutask.deliveryapp.weather.model.WeatherModel;
import com.fujitsutask.deliveryapp.weather.repository.WeatherRepository;
import com.fujitsutask.deliveryapp.weather.service.WeatherDataService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * Main controller class for handling incoming REST interface requests.
 */
@RestController
@AllArgsConstructor
public class DeliveryFeeController {

    private final WeatherRepository weatherRepository;
    private final CitiesRepository citiesRepository;
    private final VehicleRepository vehicleRepository;

    private final WeatherDataService weatherDataService;
    private final DeliveryFeeService deliveryFeeService;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * Endpoint for requesting the delivery fee.
     * <p/>
     * The delivery fee is calculated based on the given {@code wmoCode} and {@code vehicleId} path variables.
     *
     * @param cityId    The WMO code of the weather station.
     * @param vehicleId ID of the vehicle used in the delivery.
     * @return The delivery fee for the requested city using the requested vehicle.
     */
    @GetMapping("api/v1/delivery/cities/{city_id}/vehicles/{vehicle_id}")
    public ResponseEntity<String> getDeliveryFee(@PathVariable("city_id") Long cityId,
                                                 @PathVariable("vehicle_id") Long vehicleId) {
        DeliveryFeeDto outputDto = new DeliveryFeeDto();
        try {
            CityModel cityModel = citiesRepository.findById(cityId).orElseThrow(
                    () -> new DeliveryFeeException("Invalid city ID.", DeliveryFeeException.Reason.INVALID_CITY_ID)
            );
            VehicleModel vehicleModel = vehicleRepository.findById(vehicleId).orElseThrow(
                    () -> new DeliveryFeeException("Invalid vehicle ID.", DeliveryFeeException.Reason.INVALID_VEHICLE_ID)
            );
            WeatherModel weatherModel = weatherRepository.findDistinctTopByWmoOrderByTimeStampDesc(cityModel.getWeatherStationWmo())
                    .orElseThrow(
                            () -> new DeliveryFeeException("No valid weather entry for given city.", DeliveryFeeException.Reason.NO_WEATHER_ENTRY)
                    );

            outputDto = deliveryFeeService.calculateDeliveryFee(CityMapper.toDto(cityModel), VehicleMapper.toDto(vehicleModel),
                    WeatherMapper.toDto(weatherModel));
        // If the calculation failed.
        } catch (DeliveryFeeException e) {
            outputDto.setError(e.getMessage());
        }

        try {
            return getJsonResponseEntity(jsonMapper.writeValueAsString(outputDto), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return all the available cities with their respective weather station's WMO code and vehicle base fees.
     * @return Response entity with JSON encoded string.
     */
    @GetMapping("api/v1/delivery/cities")
    public ResponseEntity<String> getCities() {
        List<CityModel> citiesList = citiesRepository.findAll();
        try {
            return getJsonResponseEntity(jsonMapper.writeValueAsString(citiesList), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return all the available vehicle types with extra fees pricing list per vehicle type.
     * @return Response entity with JSON encoded string.
     */
    @GetMapping("api/v1/delivery/vehicles")
    public ResponseEntity<String> getVehiclesForCity() {
        List<VehicleModel> vehiclesList = vehicleRepository.findAll();
        List<VehicleDto> vehicleDtos = new ArrayList<>();
        try {
            for (VehicleModel model: vehiclesList) {
                VehicleDto vehicleDto = VehicleMapper.toDto(model);
                vehicleDtos.add(vehicleDto);
            }

            return getJsonResponseEntity(jsonMapper.writeValueAsString(vehicleDtos), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("api/v1/weather")
    public ResponseEntity<String> getWeather() {
        Observations observations = weatherDataService.requestLatestWeatherInfo();
        weatherDataService.saveWeatherData(observations);

        try {
            return getJsonResponseEntity(jsonMapper.writeValueAsString(observations), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Return a response entity with given JSON data and http status code.
     * @param jsonData Data serialized as JSON.
     * @param status Http status code.
     * @return JSON response entity.
     */
    @NotNull
    private ResponseEntity<String> getJsonResponseEntity(String jsonData, HttpStatus status) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        return new ResponseEntity<>(jsonData, httpHeaders, status);
    }

}
