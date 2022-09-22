package training.weather;

import org.json.JSONArray;
import org.json.JSONObject;

public class DailyWeather {
	
	private String httpRequestResult, cityTime, cityWeatherCode;
	private JSONArray dailyResult, weatherCodeResult;
	private static final String DAILY = "daily";
	
	public DailyWeather(String httpRequestResult, String cityTime, String cityWeatherCode) {		
		this.httpRequestResult = httpRequestResult;
		this.cityTime = cityTime;
		this.cityWeatherCode = cityWeatherCode;
		this.dailyResult = new JSONObject(this.httpRequestResult).getJSONObject(DAILY).getJSONArray(this.cityTime);;
		this.weatherCodeResult = new JSONObject(this.httpRequestResult).getJSONObject(DAILY).getJSONArray(this.cityWeatherCode);
	}
	

	public JSONArray getWeatherCode() {
		return weatherCodeResult;
	}


	public JSONArray getDailyResult() {
		return dailyResult;
	}
	
	

}
