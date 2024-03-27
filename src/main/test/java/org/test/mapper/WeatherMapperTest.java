package org.test.mapper;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.dto.MainDto;
import org.test.dto.WeatherDto;
import org.test.dto.WeatherResponse;
import org.test.model.Main;
import org.test.model.Weather;
import org.test.model.WeatherData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WeatherMapperTest {

    private WeatherMapper weatherMapper = new WeatherMapper();

    @Test
    void shouldMapToWeatherResponse() {
        //given
        WeatherData weatherData = WeatherData.builder()
            .weather(List.of(Weather.builder().description("Rain").build()))
            .main(Main.builder().temp(211.1).pressure(800).build())
            .visibility(1000)
            .build();
        WeatherResponse expectedWeatherResponse = WeatherResponse.builder()
            .weather(List.of(WeatherDto.builder().description("Rain").build()))
            .main(MainDto.builder().temp(211.1).pressure(800).recommendation("Better stay home").build())
            .visibility(1000)
            .build();

        //when
        WeatherResponse weatherResponse = weatherMapper.toResponse(weatherData);

        //then
        assertEquals(expectedWeatherResponse, weatherResponse);
    }
}
