package org.test.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.test.dto.MainDto;
import org.test.dto.WeatherDto;
import org.test.dto.WeatherResponse;
import org.test.model.Main;
import org.test.model.Weather;
import org.test.model.WeatherData;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class WeatherMapper {

    public WeatherResponse toResponse(WeatherData weatherData) {
        Main main = weatherData.getMain();
        MainDto mainDto = null;
        if (main != null) {
            mainDto = MainDto.builder()
                .humidity(main.getHumidity())
                .temp(main.getTemp())
                .recommendation(mapRecommendation(main.getTemp()))
                .pressure(main.getPressure())
                .build();
        }
        List<Weather> weatherList = weatherData.getWeather();
        List<WeatherDto> weatherDtoList = new ArrayList<>();
        if (weatherList != null) {
            weatherDtoList = weatherList.stream()
                .map(weather -> WeatherDto.builder()
                    .icon(weather.getIcon())
                    .description(weather.getDescription())
                    .main(weather.getMain())
                    .build())
                .collect(toList());
        }
        return WeatherResponse.builder()
            .visibility(weatherData.getVisibility())
            .main(mainDto)
            .weather(weatherDtoList)
            .build();
    }

    private String mapRecommendation(Double temp) {
        if (temp > 280 && temp < 300) {
            return "Good weather for a walk";
        } else if (temp > 300 && temp < 310) {
            return "Consider wearing a aht and bring water with you";
        } else {
            return "Better stay home";
        }
    }
}
