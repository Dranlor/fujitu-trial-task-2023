package com.fujitsutask.deliveryapp.app.service.test;

import com.fujitsutask.deliveryapp.app.dto.CityDto;
import com.fujitsutask.deliveryapp.app.dto.DeliveryFeeDto;
import com.fujitsutask.deliveryapp.app.dto.VehicleDto;
import com.fujitsutask.deliveryapp.app.service.DeliveryFeeService;
import com.fujitsutask.deliveryapp.weather.dto.WeatherDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@SpringBootTest
public class DeliveryFeeServiceTest {

    private final DeliveryFeeService service;
    @Mock
    private CityDto cityDto;
    @Mock
    private VehicleDto vehicleDto;
    @Mock
    private WeatherDto weatherDto;
    private final String errMessage = "Usage of selected vehicle type is forbidden.";

    @BeforeEach
    void setUp() {
        when(cityDto.getCityName()).thenReturn("Tallinn");

        when(cityDto.getBaseFeeScooter()).thenReturn(new BigDecimal("2.0"));
        when(cityDto.getBaseFeeCar()).thenReturn(new BigDecimal("3.0"));
        when(cityDto.getBaseFeeBike()).thenReturn(new BigDecimal("1.0"));

    }

    /**
     * Helper method to set up mock data for scooter's weather extra fees.
     */
    private void setScooterBasePrices() {
        when(vehicleDto.getAtefModerate()).thenReturn(new BigDecimal("0.5"));
        when(vehicleDto.getAtefFreezing()).thenReturn(new BigDecimal("1.0"));
        when(vehicleDto.getWsefModerate()).thenReturn(new BigDecimal("1.0"));
        when(vehicleDto.getWpefRain()).thenReturn(new BigDecimal("1.0"));
        when(vehicleDto.getWpefSnowSleet()).thenReturn(new BigDecimal("2.0"));
    }

    /**
     * Helper method to set up mock data for bike's weather extra fees.
     */
    private void setBikeBasePrices() {
        when(vehicleDto.getAtefModerate()).thenReturn(new BigDecimal("1.0"));
        when(vehicleDto.getAtefFreezing()).thenReturn(new BigDecimal("3.0"));
        when(vehicleDto.getWsefModerate()).thenReturn(new BigDecimal("1.5"));
        when(vehicleDto.getWpefRain()).thenReturn(new BigDecimal("1.0"));
        when(vehicleDto.getWpefSnowSleet()).thenReturn(new BigDecimal("2.5"));
    }

    private void setWeatherCondition(String phenomenon, float windSpeed, float airTemp) {
        when(weatherDto.getWeatherPhenomenon()).thenReturn(phenomenon);
        when(weatherDto.getWindSpeed()).thenReturn(windSpeed);
        when(weatherDto.getAirTemperature()).thenReturn(airTemp);
        when(weatherDto.getTimeStamp()).thenReturn(12345L);
    }

    private void setWeatherConditionNormal() {
        setWeatherCondition(null, 5.0f, 22.5f);
    }

    @Autowired
    public DeliveryFeeServiceTest(DeliveryFeeService service) {
        this.service = service;
    }


    /**
     * Test total fee calculation when vehicle used is Car and weather conditions are normal
     * (no extra fees).
     */
    @Test
    void testCalculateDeliveryFee_CarNoWeatherFees() {
        when(vehicleDto.getVehicleType()).thenReturn("Car");
        when(weatherDto.getTimeStamp()).thenReturn(123456L);

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("3.0"));
    }

    @Test
    void testCalculateDeliveryFee_ScooterNoWeatherFees() {
        when(vehicleDto.getVehicleType()).thenReturn("Scooter");
        setWeatherConditionNormal();
        setScooterBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("2.0"));
    }

    /**
     * Test scooter pricing in moderate winds.
     * <p/>
     * Price should not be different from the base price, since scooter's fee is not affected by wind.
     */
    @Test
    void testCalculateDeliveryFee_ScooterModerateWind() {
        when(vehicleDto.getVehicleType()).thenReturn("Scooter");
        setWeatherCondition(null, 12.2f, 20.0f);
        setScooterBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("2.0"));
    }

    /**
     * Test scooter pricing in extreme winds.
     * <p/>
     * Price should not be different from the base price, since scooter's fee is not affected by wind.
     */
    @Test
    void testCalculateDeliveryFee_ScooterExtremeWind() {
        when(vehicleDto.getVehicleType()).thenReturn("Scooter");
        setWeatherCondition(null, 40.3f, 20.2f);
        setScooterBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("2.0"));
    }

    @Test
    void testCalculateDeliveryFee_ScooterModerateCold() {
        when(vehicleDto.getVehicleType()).thenReturn("Scooter");
        setWeatherCondition(null, 5.0f, -2.3f);
        setScooterBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("2.5"));
    }

    @Test
    void testCalculateDeliveryFee_ScooterFreezingCold() {
        when(vehicleDto.getVehicleType()).thenReturn("Scooter");
        setWeatherCondition(null, 5.0f, -22.0f);
        setScooterBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("3.0"));
    }

    @Test
    void testCalculateDeliveryFee_ScooterPhenomenonRain() {
        when(vehicleDto.getVehicleType()).thenReturn("Scooter");
        setWeatherCondition("Light Rain", 2.0f, 10.0f);
        setScooterBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("3.0"));
    }

    @Test
    void testCalculateDeliveryFee_ScooterPhenomenonSnowAndFreezingCold() {
        when(vehicleDto.getVehicleType()).thenReturn("Scooter");
        setWeatherCondition("Snowstorm", 9.0f, -30f);
        setScooterBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("5.0"));
    }

    /**
     * Test scooter with hail and freezing cold.
     * <p/>
     * Should return an error and no total price.
     */
    @Test
    void testCalculateDeliveryFee_ScooterPhenomenonHailAndFreezingCold() {
        when(vehicleDto.getVehicleType()).thenReturn("Scooter");
        setWeatherCondition("Hail", 2.0f, -22f);
        setScooterBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError().equals(errMessage);
        assert resultDto.getTotalPrice() == null;
    }

    @Test
    void testCalculateDeliveryFee_BikeNormalWeather() {
        when(vehicleDto.getVehicleType()).thenReturn("Bike");
        setWeatherConditionNormal();
        setBikeBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("1.0"));
    }

    @Test
    void testCalculateDeliveryFee_BikeWindModerate() {
        when(vehicleDto.getVehicleType()).thenReturn("Bike");
        setWeatherCondition(null, 12.2f, 20.0f);
        setBikeBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("2.5"));
    }

    @Test
    void testCalculateDeliveryFee_BikeExtremeWind() {
        when(vehicleDto.getVehicleType()).thenReturn("Bike");
        setWeatherCondition(null, 30.2f, 20.0f);
        setBikeBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError().equals(errMessage);
        assert resultDto.getTotalPrice() == null;
    }

    @Test
    void testCalculateDeliveryFee_BikeColdTemp() {
        when(vehicleDto.getVehicleType()).thenReturn("Bike");
        setWeatherCondition(null, 5.0f, -5.0f);
        setBikeBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("2.0"));
    }

    @Test
    void testCalculateDeliveryFee_BikeFreezingTemp() {
        when(vehicleDto.getVehicleType()).thenReturn("Bike");
        setWeatherCondition(null, 5.0f, -25.0f);
        setBikeBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError() == null;
        assert resultDto.getTotalPrice().equals(new BigDecimal("4.0"));
    }

    @Test
    void testCalculateDeliveryFee_BikePhenomenonThunder() {
        when(vehicleDto.getVehicleType()).thenReturn("Bike");
        setWeatherCondition("Thunderstorm", 8.2f, 20.0f);
        setBikeBasePrices();

        DeliveryFeeDto resultDto = service.calculateDeliveryFee(cityDto, vehicleDto, weatherDto);

        assert resultDto.getError().equals(errMessage);
        assert resultDto.getTotalPrice() == null;
    }
}
