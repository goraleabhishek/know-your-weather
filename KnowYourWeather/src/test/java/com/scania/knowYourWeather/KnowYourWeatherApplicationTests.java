package com.scania.knowYourWeather;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.scania.knowYourWeather.dto.CurrentWeatherDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class KnowYourWeatherApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@Test
	public void getCityListPositiveFlow() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/getCityList/Mumbai", List.class)).hasSize(1).containsOnly("Mumbai");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getCityListNegativeFlow() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/getCityList/123456789", List.class)).hasSize(0);
	}
	
	@Test
	public void getCurrentWeatherPositiveFlow() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/getCurrentWeather/Mumbai", CurrentWeatherDTO.class).getStatusCode()).isEqualTo("S");
	}

	@Test
	public void getCurrentWeatherNegativeFlow() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/getCurrentWeather/1236789", CurrentWeatherDTO.class).getStatusCode()).isEqualTo("E");
	}
}
