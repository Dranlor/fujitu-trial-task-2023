package com.fujitsutask.deliveryapp.app.dto.test;

import com.fujitsutask.deliveryapp.app.dto.VehicleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class VehicleDtoTest {

    private VehicleDto vehicleDto;
    
    @BeforeEach
    void setUp() {
        vehicleDto = new VehicleDto();
        vehicleDto.setId(1L);
        vehicleDto.setVehicleType("Bike");
        vehicleDto.setAtefFreezing(new BigDecimal("2.0"));
        vehicleDto.setAtefModerate(new BigDecimal("1.0"));
        vehicleDto.setWsefModerate(new BigDecimal("3.1"));
        vehicleDto.setWpefRain(new BigDecimal("5.0"));
        vehicleDto.setWpefSnowSleet(new BigDecimal("1.12"));
    }
    
    @Test
    void testVehicleDto_Getters() {
        assert vehicleDto.getVehicleType().equals("Bike");
        assert vehicleDto.getAtefFreezing().equals(new BigDecimal("2.0"));
        assert vehicleDto.getAtefModerate().equals(new BigDecimal("1.0"));
        assert vehicleDto.getWsefModerate().equals(new BigDecimal("3.1"));
        assert vehicleDto.getWpefRain().equals(new BigDecimal("5.0"));
        assert vehicleDto.getWpefSnowSleet().equals(new BigDecimal("1.12"));
    }

}
