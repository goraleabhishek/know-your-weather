package com.scania.knowYourWeather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scania.knowYourWeather.dto.CurrentWeatherDTO;
import com.scania.knowYourWeather.service.WeatherService;

/**
 * WeatherController connects UI with back-end APIs.</br>
 * 
 * @author Abhishek Gorale
 */
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

	/**
	 * This method loads the data required for home page.
	 * 
	 * @param model {@link Model}
	 * @return String {@link String}
	 */
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

	/**
	 * This method return the current weather of the city name passed from UI.
	 * 
	 * @param cityName {@link String}
	 * @return {@linkplain CurrentWeatherDTO}
	 */
	@ResponseBody
	@RequestMapping(path = "/getCurrentWeather/{cityName}", produces = "application/json", method = RequestMethod.GET)
	public CurrentWeatherDTO getCurrentWeather(@PathVariable(name = "cityName") String cityName) {

		return weatherService.getCurrentWeather(cityName);
	}

	/**
	 * This method return list of city names based on the search string passed from UI.
	 * 
	 * @param searchString {@link String}
	 * @return {@link List}
	 */
	@ResponseBody
	@RequestMapping(path = "/getCityList/{searchString}", produces = "application/json", method = RequestMethod.GET)
	public List<String> getCityList(@PathVariable(name = "searchString", required = false) String searchString) {

		return weatherService.getFilteredCity(searchString);
	}
}
