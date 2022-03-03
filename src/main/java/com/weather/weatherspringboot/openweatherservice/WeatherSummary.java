package com.weather.weatherspringboot.openweatherservice;


import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

class WeatherSummary {

    private final String country;
    private final String city;
    private final OpenWeather weather;
    private String temperature;
    private String currentTime;
    WeatherSummary(String country, String city, OpenWeather weather) {
        this.country = country;
        this.city = city;
        this.weather = weather;
    }
    public String getCountry() {
        return this.country;
    }
    public String getCity() {
        return this.city;
    }
    public String getTemperature() {
        double tempF = (this.weather.getTemperature() * 1.8) - 459.67;
        double tempC = this.weather.getTemperature() - 273.15;
        temperature = String.format("%4.2f", tempC) + "˚C" + " | " + String.format("%4.2f", tempF) + "˚F";
        return temperature;
    }

    public String getWeatherMain() {
        return this.weather.getWeatherMain();
    }
    public String getWindSpeed() {
        return String.format("%4.2f", weather.getWindSpeed()) + " m/s";
    }
    public String getCurrentTime() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                        .withLocale(Locale.UK)
                        .withZone(ZoneId.systemDefault());
        currentTime = formatter.format(this.weather.getTimestamp());
        return currentTime;
    }
}
