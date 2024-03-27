package org.test;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.test.dto.WeatherResponse;
import org.test.exception.AuthException;
import org.test.mapper.WeatherMapper;
import org.test.model.ErrorMessage;
import org.test.model.WeatherData;
import org.test.service.WeatherService;

@Path("/current")
public class WeatherResource {

    @Inject
    WeatherService weatherService;

    @Inject
    WeatherMapper weatherMapper;

    @GET
    public Response getCurrentWeather(@QueryParam("lat") String lat, @QueryParam("lon") String lon) {
        WeatherResponse currentWeatherResponse;
        try {
            WeatherData currentWeather = weatherService.getCurrentWeather(lat, lon);
            currentWeatherResponse = weatherMapper.toResponse(currentWeather);
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("Weather could not be found")).build();
        } catch (AuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorMessage("Can not access weather resource")).build();
        }
        return Response.ok().entity(currentWeatherResponse).build();
    }
}
