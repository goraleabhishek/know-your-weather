package com.scania.knowYourWeather.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.scania.knowYourWeather.dto.CurrentWeatherDTO;

@Controller
public class WeatherController {

    
    @Value("${title}")
    private String title;
    
    @Value("${weatherstack.api.key}")
    private String apiKey;
    
    @Value("${weatherstack.api.url.currentWeather}")
    private String apiUrlCurrentWeather;
    
    @Value("#{'${weather.city.list}'.split(',')}") 
    private List<String> cityList;

    @GetMapping("/getCurrentWeather/{cityName}")
    public String getCurrentWeather(@PathVariable(name = "cityName") String cityName,  Model model) throws URISyntaxException, UnsupportedEncodingException {
    	
    	if(cityName.contains(" ")) {
    		cityName = URLEncoder.encode(cityName, "UTF-8");
    	}
    	
    	String apiUrl = apiUrlCurrentWeather.replace("YOUR_ACCESS_KEY", apiKey).replace("YOUR_CITY", cityName);
    	
    	URI uri = new URI(apiUrl);
    
    	RestTemplate restTemplate = new RestTemplate();
    	
    	ResponseEntity<CurrentWeatherDTO> response = restTemplate.getForEntity(uri, CurrentWeatherDTO.class);
    	
    	model.addAttribute("message", response.getBody().toString());
    	model.addAttribute("title", title); 
    
    	return "welcome";
    }
    
    @GetMapping("/getCityList")
    public String getCityList(Model model) {
    	
    	model.addAttribute("cityList", cityList);
    
    	return "welcome";
    }
}
