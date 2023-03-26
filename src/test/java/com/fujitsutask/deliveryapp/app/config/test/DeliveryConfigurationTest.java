package com.fujitsutask.deliveryapp.app.config.test;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
public class DeliveryConfigurationTest {

    private final OkHttpClient httpClient;
    private final XmlMapper xmlMapper;

    @Autowired
    public DeliveryConfigurationTest(OkHttpClient httpClient, XmlMapper xmlMapper) {
        this.httpClient = httpClient;
        this.xmlMapper = xmlMapper;
    }

    /**
     * Test if the dependency injection loads the objects correctly.
     */
    @Test
    void testBeanHttpClient_Initialization() {
        assert Objects.nonNull(httpClient);
        assert Objects.nonNull(xmlMapper);
    }
}
