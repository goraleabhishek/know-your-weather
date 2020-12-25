$(document)
			.ready(
					function() {
						$("#myCity")
								.autocomplete(
										{
											source : function(request, response) {
												$
														.ajax({
															url : "http://localhost:7070/getCityList/"
																	+ $(
																			'#myCity')
																			.val(),
															type : "GET",
															success : function(
																	data) {
																response(data);
															}
														});
											}
										});

						$("#getWeather")
								.click(
										function() {
											var cityName = $('#myCity').val();
											$
													.ajax({
														url : "http://localhost:7070/getCurrentWeather/"
																+ cityName,
														success : function(
																result) {
															$("#cityAndCountry")
																	.html(
																			result.location.name
																					+ ", "
																					+ result.location.country);
															$("#cityWeatherDesc")
																	.html(result.current.weather_descriptions);
															$("#cityTemperature")
																	.html(result.current.temperature+"&#8451;");
															$("#weatherIcon")
																	.attr(
																			"alt",
																			result.current.weather_descriptions);
															$("#weatherIcon")
																	.attr(
																			"src",
																			result.current.weather_icons);
														}
													});
										});
					});