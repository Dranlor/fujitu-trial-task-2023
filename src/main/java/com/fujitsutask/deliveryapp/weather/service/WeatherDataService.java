package com.fujitsutask.deliveryapp.weather.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fujitsutask.deliveryapp.app.model.CityModel;
import com.fujitsutask.deliveryapp.app.repository.CitiesRepository;
import com.fujitsutask.deliveryapp.weather.domain.Observations;
import com.fujitsutask.deliveryapp.weather.domain.Station;
import com.fujitsutask.deliveryapp.weather.exception.WeatherServiceException;
import com.fujitsutask.deliveryapp.weather.mapper.WeatherMapper;
import com.fujitsutask.deliveryapp.weather.model.WeatherModel;
import com.fujitsutask.deliveryapp.weather.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service for fetching the latest weather information.
 */
@Service
@AllArgsConstructor
public class WeatherDataService {

    private final String weatherRequestEndpoint = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";
    private final CitiesRepository citiesRepository;
    private final WeatherRepository weatherRepository;
    private final OkHttpClient httpClient;
    private final XmlMapper xmlMapper;

    /**
     * Request the latest weather information from the given endpoint.
     *
     * @return A DTO object representing the received XML.
     */
    public Observations requestLatestWeatherInfo() {
        Observations observations;
        List<CityModel> allCities = citiesRepository.findAll();
        List<Station> filteredStations = new ArrayList<>();
        Observations observationsFromFilter = new Observations();


        Request.Builder builder = new Request.Builder();
        Request request = builder.addHeader("Accept", "application/xml")
                .url(weatherRequestEndpoint)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (response.body() != null) {
                InputStream responseBodyStream = response.body().byteStream();
                observations = xmlMapper.readValue(responseBodyStream, Observations.class); // Map XML to Observations.
                observationsFromFilter.setTimestamp(observations.getTimestamp());

                // Filter the stations in Observations to get only the weather stations for the cities in the database.
                for (CityModel city : allCities) {
                    List<Station> stations =  observations.getStations().stream()
                            .filter(station -> Objects.equals(station.getWmocode(), city.getWeatherStationWmo()))
                            .toList();

                    filteredStations.addAll(stations);
                }
                observationsFromFilter.setStations(filteredStations);
            }
        } catch (IOException e) {
            throw new WeatherServiceException("IOException: Could not execute request.", e);
        } catch (IllegalStateException e) {
            throw new WeatherServiceException("IllegalStateException: The request has been executed before.", e);
        }
        return observationsFromFilter;
    }

    /**
     * Save the given weather observations into the database.
     */
    public void saveWeatherData(Observations observations) {
        List<WeatherModel> weatherModels = WeatherMapper.mapObservationsToModels(observations);
        weatherRepository.saveAll(weatherModels);
    }

}
