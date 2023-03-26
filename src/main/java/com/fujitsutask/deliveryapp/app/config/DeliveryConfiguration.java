package com.fujitsutask.deliveryapp.app.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration file for external dependencies.
 */
@Configuration
public class DeliveryConfiguration {

    /**
     * Return a singleton object of OkHttpClient object.
     * @return OkHttpClient object.
     */
    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }

    /**
     * Return a jackson XML mapper object.
     * @return XmlMapper object.
     */
    @Bean
    public XmlMapper xmlMapper() {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }
}
