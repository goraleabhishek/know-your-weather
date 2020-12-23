package com.scania.knowYourWeather.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.scania.knowYourWeather.dto.CurrentWeatherDTO;

@Controller
public class WeatherController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;
    
    @Value("${title}")
    private String title;
    
    @Value("${weatherstack.api.key}")
    private String apiKey;
    
    @Value("${weatherstack.api.url.currentWeather}")
    private String apiUrlCurrentWeather;
    
    @Value("#{'${weather.city.list}'.split(',')}") 
    private List<String> cityList;

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);

        return "welcome"; //view
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") 
            String name, Model model) {

        model.addAttribute("message", name);

        return "welcome"; //view
    }

    @GetMapping("/getCurrentWeather")
    public String getCurrentWeather(Model model) throws URISyntaxException {
    	
    	String apiUrl = apiUrlCurrentWeather.replace("YOUR_ACCESS_KEY", apiKey).replace("YOUR_CITY", cityList.get(1));
    	
    	URI uri = new URI(apiUrl);
    
    	RestTemplate restTemplate = new RestTemplate();
    	
    	ResponseEntity<CurrentWeatherDTO> response = restTemplate.getForEntity(uri, CurrentWeatherDTO.class);
    	
    	model.addAttribute("message", response.getBody().toString());
    	model.addAttribute("title", title); 
    
    	return "welcome";
    }
    
    @GetMapping("/getCityList")
    public String getCityList(Model model) {
    	
    	model.addAttribute("message", cityList+" | "+cityList.size());
    
    	return "welcome";
    }
}
