package com.fujitsutask.deliveryapp.weather.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;


import java.util.List;

@Data
@JacksonXmlRootElement(localName = "observations")
public class ObservationsDto {

    @JacksonXmlProperty(isAttribute = true)
    private Long timestamp;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "station")
    private List<StationDto> stations;
}

