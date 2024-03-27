package org.test.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.test.exception.RestExceptionMapper;
import org.test.model.WeatherData;

@Path("/data/2.5/weather")
@RegisterRestClient
@RegisterProvider(RestExceptionMapper.class)
public interface WeatherMapClient {

    @GET
    WeatherData getCurrentWeather(@QueryParam("lat") String lat,
                                  @QueryParam("lon") String lon,
                                  @QueryParam("appid") String appId);
}
