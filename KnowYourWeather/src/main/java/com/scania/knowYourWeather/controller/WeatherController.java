package com.scania.knowYourWeather.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.scania.knowYourWeather.dto.CurrentWeatherDTO;
import com.scania.knowYourWeather.service.WeatherService;

@Controller
public class WeatherController {

    
    @Value("${title}")
    private String title;
    
    @Value("#{'${weather.city.list}'.split(',')}") 
    private List<String> cityList;
    
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/getCurrentWeather/{cityName}")
    public String getCurrentWeather(@PathVariable(name = "cityName") String cityName,  Model model) throws URISyntaxException, UnsupportedEncodingException {
    	
    	ResponseEntity<CurrentWeatherDTO> response = weatherService.getCurrentWeather(cityName);
    	
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
