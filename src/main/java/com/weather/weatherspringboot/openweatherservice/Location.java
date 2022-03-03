package com.weather.weatherspringboot.openweatherservice;
public class Location {

    private final String country;
    private final String city;
    private String text;

    public Location(String country, String city) {
        this.country = country;
        this.city = city;
    }
    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getText() {
        text = toString();
        return text;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}
