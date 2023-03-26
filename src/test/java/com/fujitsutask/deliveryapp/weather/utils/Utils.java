package com.fujitsutask.deliveryapp.weather.utils;

import com.fujitsutask.deliveryapp.weather.domain.Observations;
import com.fujitsutask.deliveryapp.weather.domain.Station;

import java.util.List;

public class Utils {
    private Utils() {}

    /**
     * Return a populated mock observations object.
     * @return Observations object.
     */
    public static Observations getMockObservations() {
        Observations observations = new Observations();

        Station station1 = new Station();
        Station station2 = new Station();
        Station station3 = new Station();

        station1.setName("Tallinn-Harku");
        station2.setName("Tartu-Tõravere");
        station3.setName("Pärnu");

        station1.setWmocode(12355);
        station2.setWmocode(22222);
        station3.setWmocode(51333);

        station1.setWindspeed(5.0f);
        station1.setAirtemperature(22.0f);
        station1.setPhenomenon(null);

        station2.setWindspeed(15.0f);
        station2.setAirtemperature(-2f);
        station2.setPhenomenon("Light Rain");

        station3.setWindspeed(19f);
        station3.setAirtemperature(-20f);
        station3.setPhenomenon("Snow");

        observations.setStations(List.of(station1, station2, station3));
        observations.setTimestamp(12345L);
        return observations;
    }
}
