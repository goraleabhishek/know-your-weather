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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scania.knowYourWeather.dto.CurrentWeatherDTO;
import com.scania.knowYourWeather.service.WeatherService;

@Controller
public class WeatherController {

	@Value("${title}")
	private String title;
	
	@Value("${cssPath}")
	private String cssPath;
	
	@Value("${javscriptPath}")
	private String javscriptPath;
	
	@Value("${plusIconPath}")
	private String plusIconPath;
	
	@Value("${weatherIconPath}")
	private String weatherIconPath;
	
	@Value("${header}")
	private String header;
	
	@Value("${footer}")
	private String footer;
	
	@Value("${heading}")
	private String heading;

	@Autowired
	private WeatherService weatherService;

	@GetMapping("/")
	public String getWelcomePage(Model model) {
		model.addAttribute("title", title);
		model.addAttribute("cssPath", cssPath);
		model.addAttribute("javscriptPath", javscriptPath);
		model.addAttribute("plusIconPath", plusIconPath);
		model.addAttribute("weatherIconPath", weatherIconPath);
		model.addAttribute("header", header);
		model.addAttribute("footer", footer);
		model.addAttribute("heading", heading);

		return "weather";
	}

	@ResponseBody
	@RequestMapping(path = "/getCurrentWeather/{cityName}", produces = "application/json", method = RequestMethod.GET)
	public CurrentWeatherDTO getCurrentWeather(@PathVariable(name = "cityName") String cityName) {

		return weatherService.getCurrentWeather(cityName);
	}

	@ResponseBody
	@RequestMapping(path = "/getCityList/{searchString}", produces = "application/json", method = RequestMethod.GET)
	public List<String> getCityList(@PathVariable(name = "searchString", required = false) String searchString) {

		return weatherService.getFilteredCity(searchString);
	}
}
