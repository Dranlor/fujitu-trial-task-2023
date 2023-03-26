package com.fujitsutask.deliveryapp.app.mapper.test;

import com.fujitsutask.deliveryapp.app.dto.CityDto;
import com.fujitsutask.deliveryapp.app.mapper.CityMapper;
import com.fujitsutask.deliveryapp.app.model.CityModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class CityMapperTest {

    /**
     * Test if after the mapping the new DTO object has all the same fields as the model did.
     */
    @Test
    void testCityMapperToDto_AllFields() {
        CityModel model = new CityModel();

        model.setId(1);
        model.setCityName("Tallinn");
        model.setBaseFeeCar(new BigDecimal("3.0"));
        model.setBaseFeeScooter(new BigDecimal("2.5"));
        model.setBaseFeeBike(new BigDecimal("2.0"));
        model.setWeatherStationWmo(12345);

        CityDto dto = CityMapper.toDto(model);

        assert dto.getId() == 1;
        assert dto.getCityName().equals("Tallinn");
        assert dto.getWeatherStationWmo() == 12345;
        assert dto.getBaseFeeCar().equals(new BigDecimal("3.0"));
        assert dto.getBaseFeeScooter().equals(new BigDecimal("2.5"));
        assert dto.getBaseFeeBike().equals(new BigDecimal("2.0"));
    }

    /**
     * Test if the mapping is successful when all the fields in model are null.
     */
    @Test
    void testCityMapperToDto_AllFieldsNull() {
        CityModel model = new CityModel();

        CityDto cityDto = CityMapper.toDto(model);
        assert cityDto.getId() == null;
        assert cityDto.getCityName() == null;
        assert cityDto.getWeatherStationWmo() == null;
        assert cityDto.getBaseFeeCar() == null;
        assert cityDto.getBaseFeeScooter() == null;
        assert cityDto.getBaseFeeBike() == null;
    }
}
