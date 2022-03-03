package com.weather.weatherspringboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@ConfigurationProperties("app.weather")
public class WeatherAppProperties {

    @Valid
    private final WeatherAppProperties.OpenWeahtherApi api = new OpenWeahtherApi();
    private List<String> locations = new ArrayList<>();

    public OpenWeahtherApi getApi() {
        return this.api;
    }
    public List<String> getLocations() {
        return this.locations;
    }
    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
    public static class OpenWeahtherApi {
        @NotNull
        private String key;
        public String getKey() {
            return this.key;
        }
        public void setKey(String key) {
            this.key = key;
        }

    }

}

