package org.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.test.model.Main;
import org.test.model.Weather;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {

    private List<WeatherDto> weather;
    private Integer visibility;
    private MainDto main;
}
