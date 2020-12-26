package com.scania.knowYourWeather.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.scania.knowYourWeather.dto.CurrentWeatherDTO;
import com.scania.knowYourWeather.util.ApplicationConstant;

/**
 * WeatherService connects with API to get the weather of the cities and provide
 * city names to the UI from properties file.</br>
 * 
 * @author Abhishek Gorale
 */
@Service
public class WeatherService {

	@Value("${weatherstack.api.key}")
	private String apiKey;

	@Value("${weatherstack.api.url.currentWeather}")
	private String apiUrlCurrentWeather;

	@Value("#{'${weather.city.list}'.split(',')}")
	private List<String> cityList;

	/**
	 * This method retrieves the current weather of the provided city name.</br>
	 * Exception handling is also done.
	 * 
	 * @param cityName {@link String}
	 * @return {@link CurrentWeatherDTO}
	 */
	public CurrentWeatherDTO getCurrentWeather(String cityName) {

		CurrentWeatherDTO response = new CurrentWeatherDTO();

		try {

			if (cityName.contains(ApplicationConstant.SPACE)) {
				cityName = URLEncoder.encode(cityName, ApplicationConstant.UTF_8);
			}

			String apiUrl = apiUrlCurrentWeather.replace(ApplicationConstant.YOUR_ACCESS_KEY, apiKey)
					.replace(ApplicationConstant.YOUR_CITY, cityName);

			URI uri = new URI(apiUrl);

			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<CurrentWeatherDTO> apiResponse = restTemplate.getForEntity(uri, CurrentWeatherDTO.class);

			if (apiResponse == null || apiResponse.getBody() == null || apiResponse.getBody().getRequest() == null
					|| apiResponse.getBody().getLocation() == null || apiResponse.getBody().getCurrent() == null) {
				response.setStatusCode(ApplicationConstant.STATUS_CODE_ERROR);
				response.setStatusMessage(ApplicationConstant.STATUS_MSG_ERROR_CITY_NOT_FOUND);
			} else {
				response = apiResponse.getBody();
				response.setStatusCode(ApplicationConstant.STATUS_CODE_SUCCESS);
				response.setStatusMessage(ApplicationConstant.STATUS_MSG_SUCCESS);
			}

		} catch (UnsupportedEncodingException exception) {
			response.setStatusCode(ApplicationConstant.STATUS_CODE_ERROR);
			response.setStatusMessage(ApplicationConstant.STATUS_MSG_ERROR_SYSTEM_ERROR);
		} catch (URISyntaxException exception) {
			response.setStatusCode(ApplicationConstant.STATUS_CODE_ERROR);
			response.setStatusMessage(ApplicationConstant.STATUS_MSG_ERROR_SYSTEM_ERROR);
		} catch (Exception exception) {
			response.setStatusCode(ApplicationConstant.STATUS_CODE_ERROR);
			response.setStatusMessage(ApplicationConstant.STATUS_MSG_ERROR_SYSTEM_ERROR);
		}

		return response;
	}

	/**
	 * This method filters all the cities from the configured city names & return
	 * list of city names which starts with the search string passed from UI.
	 * 
	 * @param searchString {@link String}
	 * @return {@link List}
	 */
	public List<String> getFilteredCity(String searchString) {
		return cityList.stream().filter(city -> city.strip().toLowerCase().startsWith(searchString.toLowerCase()))
				.collect(Collectors.toList());
	}
}
