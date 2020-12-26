$(document)
			.ready(
					function() {

						var index = 0;
						$("#cityWeatherDiv").hide();

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
															var latestIndex = ++index;
															var weatherIconURL = result.current.weather_icons;
															weatherIconURL = weatherIconURL
																	.toString()
																	.replaceAll(
																			'/',
																			'\/');
															var weatherDesc = result.current.weather_descriptions;

															$("#cityWeatherRow")
																	.append(
																			"<td border= '1'><img id='weatherIcon_"+ latestIndex +"' src='"+weatherIconURL+"' alt='"+weatherDesc+"' width='70' height='70' /></td>"
																					+ "<td border= '1'>"
																					+ "<div id='cityTemperature_"+ latestIndex +"'></div>"
																					+ "<div id='cityWeatherDesc_"+ latestIndex +"'></div>"
																					+ "<div id='cityAndCountry_"+ latestIndex +"'></div>"
																					+ "</td>");

															$(
																	"#cityAndCountry_"
																			+ latestIndex
																			+ "")
																	.html(
																			result.location.name
																					+ ", "
																					+ result.location.country);
															$(
																	"#cityWeatherDesc_"
																			+ latestIndex
																			+ "")
																	.html(
																			result.current.weather_descriptions);
															$(
																	"#cityTemperature_"
																			+ latestIndex
																			+ "")
																	.html(
																			result.current.temperature
																					+ "&#8451;");

															$("#cityWeatherDiv")
																	.show();
														}
													});
										});
					});