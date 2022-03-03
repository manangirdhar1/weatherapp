package com.weather.weatherspringboot.openweatherservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class OpenWeatherDTO implements Serializable {

    private Instant timestamp;
    private double temperature;
    private String weatherMain;
    private double windSpeed;
    public String getWeatherMain() {
        return weatherMain;
    }
    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public double getTemperature() {
        return this.temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherMain((String) weather.get("main"));
    }
    @JsonProperty("wind")
    public void setWind(Map<String, Object> wind) {
        setWindSpeed(Double.parseDouble(wind.get("speed").toString()));
    }
    @JsonProperty("timestamp")
    public Instant getTimestamp() {
        return this.timestamp;
    }
    @JsonSetter("dt")
    public void setTimestamp(long unixTime) {
        this.timestamp = Instant.ofEpochMilli(unixTime * 1000);
    }
    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemperature(Double.parseDouble(main.get("temp").toString()));
    }

}
