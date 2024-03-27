package org.test.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.test.client.WeatherMapClient;
import org.test.model.WeatherData;

@ApplicationScoped
public class WeatherService {

    @Inject
    @RestClient
    WeatherMapClient client;

    @ConfigProperty(name = "weather.apiKey")
    String apiKey;

    private static final Logger LOG = Logger.getLogger(WeatherService.class);

    public WeatherData getCurrentWeather(String lat, String lon) {
        LOG.info("Requesting current weather data");
        WeatherData currentWeather = client.getCurrentWeather(lat, lon, apiKey);
        LOG.info("Successfully received current weather data");
        return currentWeather;
    }
}
