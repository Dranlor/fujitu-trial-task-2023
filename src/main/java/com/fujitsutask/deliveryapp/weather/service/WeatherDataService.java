package com.fujitsutask.deliveryapp.weather.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fujitsutask.deliveryapp.weather.dto.ObservationsDto;
import com.fujitsutask.deliveryapp.weather.exception.WeatherServiceException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Service for fetching the latest weather information.
 */
@Service
public class WeatherDataService {

    private final String weatherRequestEndpoint = "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";
    private final OkHttpClient httpClient;
    private final XmlMapper xmlMapper;

    @Autowired
    public WeatherDataService(OkHttpClient client, XmlMapper mapper) {
        httpClient = client;
        xmlMapper = mapper;
    }

    /**
     * Request the latest weather information from the given endpoint.
     */
    public ObservationsDto requestLatestWeatherInfo() {
        ObservationsDto observations = null;

        Request.Builder builder = new Request.Builder();
        Request request = builder.addHeader("Accept", "application/xml")
                .url(weatherRequestEndpoint)
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (response.body() != null) {
                InputStream responseBodyStream = response.body().byteStream();
                observations = xmlMapper.readValue(responseBodyStream, ObservationsDto.class);

            }
        } catch (IOException e) {
            throw new WeatherServiceException("IOException: Could not execute request.", e);
        } catch (IllegalStateException e) {
            throw new WeatherServiceException("IllegalStateException: The request has been executed before.", e);
        }
        return observations;
    }

    // weatherCronJob.java
    // execute()
    //
    //  var observations = weatherService.getLatestWeatherInfo()
    //  var tallinnHarkuStation = observations.stations.stream()
    //      .filter(it -> it.name.equals('Tallinn/Harku'))
    //      .getFirstOrOptionalEmpty()
    // var paernuStation = obs.stations...getFirstOrEmpty()
    // var tartuStations = obs.staions.stream...getFirstOrEmpty()
    //
    // var tallinnWeatherModel = WeatherModelMapper.toEntity(tallinnHarkuStation)
    //
    // weatherRepository.save(tallinnWeatherModel) 3x


    // CalculateDeliveryFeeCommand.execute(Car car, City city)
    //  var station = cityToStationMapper(city)
    // var latestCityInfo = weatherRepository.findLatestAndByCity(station)
    // var fee = deliveryFeeService.calculateByCarCityWeather(latestCityInfo, car, city)
    // return fee
}
