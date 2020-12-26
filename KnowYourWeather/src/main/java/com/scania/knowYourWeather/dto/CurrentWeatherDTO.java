package com.scania.knowYourWeather.dto;

public class CurrentWeatherDTO {

	private String statusCode;
	private String statusMessage;
	private RequestDTO request;
	private LocationDTO location;
	private CurrentDTO current;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

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
		return "CurrentWeatherDTO [statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", request="
				+ request + ", location=" + location + ", current=" + current + "]";
	}

}
