package com.scania.knowYourWeather.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.scania.knowYourWeather.dto.CurrentWeatherDTO;

@Service
public class WeatherService {

	@Value("${weatherstack.api.key}")
    private String apiKey;
    
    @Value("${weatherstack.api.url.currentWeather}")
    private String apiUrlCurrentWeather;
	
	public ResponseEntity<CurrentWeatherDTO> getCurrentWeather(String cityName)
			throws UnsupportedEncodingException, URISyntaxException {
		if(cityName.contains(" ")) {
    		cityName = URLEncoder.encode(cityName, "UTF-8");
    	}
    	
    	String apiUrl = apiUrlCurrentWeather.replace("YOUR_ACCESS_KEY", apiKey).replace("YOUR_CITY", cityName);
    	
    	URI uri = new URI(apiUrl);
    
    	RestTemplate restTemplate = new RestTemplate();
    	
    	ResponseEntity<CurrentWeatherDTO> response = restTemplate.getForEntity(uri, CurrentWeatherDTO.class);
		return response;
	}
}
