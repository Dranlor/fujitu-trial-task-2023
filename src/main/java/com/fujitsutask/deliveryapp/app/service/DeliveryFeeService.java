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

    /**
     * Calculates the total delivery fee based on the given city, vehicle type and weather conditions.
     * @param city City DTO.
     * @param vehicle Vehicle DTO.
     * @param weatherData Weather DTO.
     * @return DeliveryFee DTO with the total delivery fee or an error message if the fee can't be calculated.
     */
    public DeliveryFeeDto calculateDeliveryFee(CityDto city, VehicleDto vehicle, WeatherDto weatherData) {
        DeliveryFeeDto resultDto = new DeliveryFeeDto();
        if (city.getCityName() == null) {
            throw new DeliveryFeeException("Invalid city ID.", DeliveryFeeException.Reason.INVALID_CITY_ID);
        }
        resultDto.setCityName(city.getCityName());
        resultDto.setVehicleType(vehicle.getVehicleType());
        resultDto.setWeatherDataTimestamp(weatherData.getTimeStamp());

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

                    totalPrice = totalPrice.add(city.getBaseFeeScooter());
                    totalPrice = totalPrice.add(airTempExtraFee);
                    totalPrice = totalPrice.add(weatherPhenomenonExtraFee);
                } catch (DeliveryFeeException e) {
                    resultDto.setError(e.getMessage());
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

                    totalPrice = totalPrice.add(city.getBaseFeeBike());
                    totalPrice = totalPrice.add(airTempExtraFee);
                    totalPrice = totalPrice.add(weatherPhenomenonExtraFee);
                    totalPrice = totalPrice.add(windSpeedExtraFee);
                } catch (DeliveryFeeException e) {
                    resultDto.setError(e.getMessage());
                    break;
                }

                resultDto.setTotalPrice(totalPrice);
                break;

            default:
                throw new DeliveryFeeException("Invalid vehicle ID.", DeliveryFeeException.Reason.INVALID_VEHICLE_ID);
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
