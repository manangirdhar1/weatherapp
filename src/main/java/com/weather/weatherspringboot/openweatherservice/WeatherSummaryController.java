package com.weather.weatherspringboot.openweatherservice;


import com.weather.weatherspringboot.WeatherAppProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Weather summary controller.
 */
@Controller
public class WeatherSummaryController {

    private final WeatherAppProperties properties;
    private static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";
    private final RestTemplate restTemplate;
    private final String apiKey;
    public WeatherSummaryController(WeatherAppProperties properties, RestTemplateBuilder restTemplateBuilder) {
        this.properties = properties;
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = properties.getApi().getKey();
    }
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ModelAndView getCityWeather(@RequestParam String selectedLocation) {
        Map<String, Object> cityWeather = new LinkedHashMap<>();
        cityWeather.put("summary", getSummary(selectedLocation));
        return new ModelAndView("summary", cityWeather);
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getLocations() {
        Map<String, Object> locationsModel = new LinkedHashMap<>();
        List<Location> locations = new ArrayList<>();
        this.properties.getLocations().forEach(value -> {
            String country = value.split("/")[1];
            String city = value.split("/")[0];
            locations.add(new Location(country, city));
        });
        locationsModel.put("locations", locations);
        return new ModelAndView("locations", locationsModel);
    }

    private Object getSummary(String location) {
        String country = location.split(",")[1];
        String city = location.split(",")[0];
        OpenWeather weather = this.getWeather(country, city);
        return new WeatherSummary(country, city, weather);
    }

    private OpenWeather getWeather(String country, String city) {
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
