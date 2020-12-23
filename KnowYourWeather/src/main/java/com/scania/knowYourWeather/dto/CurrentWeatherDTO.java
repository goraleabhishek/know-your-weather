package com.scania.knowYourWeather.dto;

public class CurrentWeatherDTO {

	private RequestDTO request;
	private LocationDTO location;
	private CurrentDTO current;

	public RequestDTO getRequest() {
		return request;
	}

	public void setRequest(RequestDTO request) {
		this.request = request;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	public CurrentDTO getCurrent() {
		return current;
	}

	public void setCurrent(CurrentDTO current) {
		this.current = current;
	}

	@Override
	public String toString() {
		return "CurrentWeatherDTO [request=" + request + ", location=" + location + ", current=" + current + "]";
	}

}
