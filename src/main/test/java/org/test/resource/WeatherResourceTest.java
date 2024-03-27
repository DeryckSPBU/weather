package org.test.resource;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.test.client.WeatherMapClient;
import org.test.model.WeatherData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
public class WeatherResourceTest {

    @InjectMock
    @RestClient
    WeatherMapClient client;

    @Test
    public void shouldGetCurrentWeather() {
        WeatherData weatherData = WeatherData.builder().visibility(10000).build();
        when(client.getCurrentWeather("44", "45", "11")).thenReturn(weatherData);
        given()
            .when().get("/current?lat=44&lon=45")
            .then()
            .statusCode(200)
            .body("visibility", is(10000));
    }
}
