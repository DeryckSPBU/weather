package org.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainDto {

    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private String recommendation;
}
