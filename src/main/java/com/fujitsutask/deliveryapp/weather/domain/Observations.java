package com.fujitsutask.deliveryapp.weather.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;


import java.util.List;

/**
 * Data model that describes the layout of the top-level xml element that is received through
 * the HTTP request to ilmateenistus.ee
 */
@Data
@JacksonXmlRootElement(localName = "observations")
public class Observations {

    @JacksonXmlProperty(isAttribute = true)
    private Long timestamp;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "station")
    private List<Station> stations;
}

