package com.fujitsutask.deliveryapp.app.service;

import com.fujitsutask.deliveryapp.app.dto.CityDto;
import com.fujitsutask.deliveryapp.app.dto.DeliveryFeeDto;
import com.fujitsutask.deliveryapp.app.dto.VehicleDto;
import com.fujitsutask.deliveryapp.app.exception.DeliveryFeeException;
import com.fujitsutask.deliveryapp.weather.domain.Observations;
import com.fujitsutask.deliveryapp.weather.dto.WeatherDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Service for calculating the total delivery fee.
 */
@Service
public class DeliveryFeeService {

    public DeliveryFeeDto calculateDeliveryFee(CityDto city, VehicleDto vehicle, WeatherDto weatherData) {
        /*
         * Vehicle type: Scooter, Bike
         *   air temp. = <-10C -> AETF = 1
         *   air temp. = -10C -- 0C -> AETF = 0.5
         *
         * Vehicle type: Bike
         *   wind speed = 10 - 20 -> WSEF 0.5
         *   wind speed = >20 -> ERR
         *
         * Vehicle type: Scooter, Bike
         *   phenom. = snow, sleet -> WPEF 1
         *   phenom. = rain -> WPEF 0.5
         *   phenom. = hail, glaze, thunder -> ERR
         */

        DeliveryFeeDto resultDto = new DeliveryFeeDto();
        resultDto.setCityName(city.getCityName());
        resultDto.setVehicleType(vehicle.getVehicleType());

        BigDecimal totalPrice = new BigDecimal(0);

        switch (vehicle.getVehicleType()) {
            case "Car":
                // The car does not have any extra fees, so just add the base fee.
                resultDto.setTotalPrice(city.getBaseFeeCar());
                break;

            case "Scooter":
                try {
                    final BigDecimal airTempExtraFee = getAirTempExtraFee(weatherData.getAirTemperature(), vehicle);
                    final BigDecimal weatherPhenomenonExtraFee = getWeatherPhenomenonExtraFee(
                            weatherData.getWeatherPhenomenon(), vehicle);

                    totalPrice = totalPrice.add(airTempExtraFee);
                    totalPrice = totalPrice.add(weatherPhenomenonExtraFee);

                } catch (DeliveryFeeException e) {
                    resultDto.setError(e);
                    break;
                }
                resultDto.setTotalPrice(totalPrice);
                break;

            case "Bike":
                try {
                    final BigDecimal airTempExtraFee = getAirTempExtraFee(weatherData.getAirTemperature(), vehicle);
                    final BigDecimal weatherPhenomenonExtraFee = getWeatherPhenomenonExtraFee(
                            weatherData.getWeatherPhenomenon(), vehicle);
                    final BigDecimal windSpeedExtraFee = getWindSpeedExtraFee(weatherData.getWindSpeed(), vehicle);

                    totalPrice = totalPrice.add(airTempExtraFee);
                    totalPrice = totalPrice.add(weatherPhenomenonExtraFee);
                    totalPrice = totalPrice.add(windSpeedExtraFee);

                } catch (DeliveryFeeException e) {
                    resultDto.setError(e);
                    break;
                }

                resultDto.setTotalPrice(totalPrice);
                break;

            default:
                resultDto.setError(new DeliveryFeeException("Invalid vehicle type.",
                        DeliveryFeeException.Reason.INVALID_VEHICLE_TYPE));
                break;
        }
        return resultDto;
    }

    // TODO: Account for negative numbers.
    private BigDecimal getAirTempExtraFee(float airTemp, VehicleDto vehicleDto) {
        final boolean conditionForFreezingTemp = airTemp < -10;
        final boolean conditionForModerateTemp = -10 <= airTemp && airTemp <= 0;

        if (conditionForFreezingTemp) return vehicleDto.getAtefFreezing();
        if (conditionForModerateTemp) return vehicleDto.getAtefModerate();
        else return new BigDecimal(0);
    }

    private BigDecimal getWindSpeedExtraFee(float windSpeed, VehicleDto vehicleDto)
            throws DeliveryFeeException {
        final boolean conditionWindSpeedModerate = 10 <= windSpeed && windSpeed <= 20;
        final boolean conditionWindSpeedExtreme = 20 < windSpeed;

        if (conditionWindSpeedModerate) return vehicleDto.getWsefModerate();
        if (conditionWindSpeedExtreme) throw new DeliveryFeeException("Usage of selected vehicle type is forbidden",
                DeliveryFeeException.Reason.UNFIT_CONDITIONS_FOR_VEHICLE_TYPE);
        else return new BigDecimal(0);
    }

    private BigDecimal getWeatherPhenomenonExtraFee(String phenomenon, VehicleDto vehicleDto)
            throws DeliveryFeeException {

        final boolean conditionSnowSleet = phenomenon.toLowerCase().matches("snow|sleet");
        final boolean conditionRain = phenomenon.toLowerCase().matches("rain");
        final boolean conditionHailGlazeThunder = phenomenon.toLowerCase().matches("hail|glaze|thunder");

        if (conditionSnowSleet) return vehicleDto.getWpefSnowSleet();
        if (conditionRain) return vehicleDto.getWpefRain();
        if (conditionHailGlazeThunder) throw new DeliveryFeeException("Usage of selected vehicle type is forbidden",
                DeliveryFeeException.Reason.UNFIT_CONDITIONS_FOR_VEHICLE_TYPE);
        else return new BigDecimal(0);
    }
}
