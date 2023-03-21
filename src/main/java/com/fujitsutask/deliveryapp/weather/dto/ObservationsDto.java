package com.fujitsutask.deliveryapp.weather.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


import java.util.List;

@JacksonXmlRootElement(localName = "observations")
public class ObservationsDto {

    @JacksonXmlProperty(isAttribute = true)
    private Long timestamp;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "station")
    private List<StationDto> stations;

    public ObservationsDto() {}


    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public List<StationDto> getStations() {
        return stations;
    }

    public void setStations(List<StationDto> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "ObservationsDto{" +
                "timestamp=" + timestamp +
                ", stations=" + stations +
                '}';
    }
}

