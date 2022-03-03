package com.weather.weatherspringboot.openweatherservice;


import com.weather.weatherspringboot.WeatherAppProperties;
import com.weather.weatherspringboot.openweatherservice.OpenWeather;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@RestController
@RequestMapping("/api/weather")
public class WeatherServiceController {

    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";
    private final RestTemplate restTemplate;
    private final String apiKey;
    private final WeatherAppProperties properties;
    public WeatherServiceController(RestTemplateBuilder restTemplateBuilder,
                                    WeatherAppProperties properties) {
        this.properties = properties;
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = properties.getApi().getKey();
    }

    @RequestMapping("/now/{country}/{city}")
    public OpenWeather getWeather(@PathVariable String country,
                                  @PathVariable String city) {
        return this.getWeatherForCountry(country, city);
    }
    private OpenWeather getWeatherForCountry(String country, String city) {
        URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
        return invoke(url, OpenWeather.class);
    }

    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate
                .exchange(request, responseType);
        return exchange.getBody();
    }
}
