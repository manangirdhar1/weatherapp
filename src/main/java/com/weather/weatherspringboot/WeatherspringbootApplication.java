package com.weather.weatherspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties(WeatherAppProperties.class)
public class WeatherspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherspringbootApplication.class, args);
	}

	}

