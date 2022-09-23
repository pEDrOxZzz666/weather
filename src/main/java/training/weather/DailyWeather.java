package training.weather;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author Pedro
 *
 * Class that extracts the time in a given city and the time code
 */
public class DailyWeather {
	
	private String httpRequestResult, cityTime, cityWeatherCode;
	private JSONArray dailyResult, weatherCodeResult;
	private static final String DAILY = "daily";
	
	/**
	 * Constructor
	 * @param httpRequestResult Parameter of type String that contains data of type JSON
	 * @param cityTime String type parameter. Refers to the field to extract. 
	 * @param cityWeatherCode String type parameter. Refers to the field to extract.
	 */
	public DailyWeather(String httpRequestResult, String cityTime, String cityWeatherCode) {		
		this.httpRequestResult = httpRequestResult;
		this.cityTime = cityTime;
		this.cityWeatherCode = cityWeatherCode;
		this.dailyResult = new JSONObject(this.httpRequestResult).getJSONObject(DAILY).getJSONArray(this.cityTime);;
		this.weatherCodeResult = new JSONObject(this.httpRequestResult).getJSONObject(DAILY).getJSONArray(this.cityWeatherCode);
	}
	

	/**
	 * Method to extract weather code
	 * @return Returns a JSONArray object with the codes
	 */
	public JSONArray getWeatherCode() {
		return weatherCodeResult;
	}

	/**
	 * Method to extract daily result
	 * @return Returns a JSONArray object with the daily codes
	 */
	public JSONArray getDailyResult() {
		return dailyResult;
	}
	
	

}
