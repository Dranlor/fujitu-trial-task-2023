package com.fujitsutask.deliveryapp.app.dto.test;

import com.fujitsutask.deliveryapp.app.dto.DeliveryFeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class DeliveryFeeDtoTest {

    private DeliveryFeeDto deliveryFeeDto;

    @BeforeEach
    void setUp() {
        deliveryFeeDto = new DeliveryFeeDto();

        deliveryFeeDto.setCityName("Tallinn");
        deliveryFeeDto.setWeatherDataTimestamp(123456L);
        deliveryFeeDto.setVehicleType("Bike");
        deliveryFeeDto.setTotalPrice(new BigDecimal("3.0"));
    }

    /**
     * Test getters.
     */
    @Test
    void testDeliveryFeeDto_Getters() {
        assert deliveryFeeDto.getError() == null;
        assert deliveryFeeDto.getCityName().equals("Tallinn");
        assert deliveryFeeDto.getTotalPrice().equals(new BigDecimal("3.0"));
        assert deliveryFeeDto.getVehicleType().equals("Bike");
        assert deliveryFeeDto.getWeatherDataTimestamp().equals(123456L);
    }

}
