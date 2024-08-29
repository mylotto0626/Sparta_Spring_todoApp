package com.sparta.taejuspartatodoapp.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;

    public String getTodayWeather() {
        String url = "https://f-api.github.io/f-api/weather.json";
        return restTemplate.getForObject(url, String.class);
    }
}
