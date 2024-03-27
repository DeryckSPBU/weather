package org.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.client.WeatherMapClient;
import org.test.exception.NotFoundException;
import org.test.model.Main;
import org.test.model.Weather;
import org.test.model.WeatherData;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private WeatherMapClient client;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    void shouldReturnCurrentWeather() {
        //given
        WeatherData weatherData = WeatherData.builder()
            .weather(List.of(Weather.builder().description("Rain").build()))
            .main(Main.builder().temp(211.1).pressure(800).build())
            .visibility(1000)
            .build();
        when(client.getCurrentWeather(anyString(), anyString(), any())).thenReturn(weatherData);

        //when
        WeatherData currentWeather = weatherService.getCurrentWeather("44", "45");

        //then
        assertEquals(currentWeather, weatherData);
    }

    @Test
    void shouldThrowException() {
        //given
        NotFoundException notFoundException = new NotFoundException("Couldn't find any data");
        when(client.getCurrentWeather(anyString(), anyString(), any())).thenThrow(notFoundException);

        //then
        assertThrows(NotFoundException.class, () -> weatherService.getCurrentWeather("44", "45"));
    }
}
