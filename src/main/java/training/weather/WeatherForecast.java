package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;


/**
 * 
 * @author Pedro
 *
 * Class that extracts a description of the weather in a given city on a given date.
 */
public class WeatherForecast {
	
	public static void main (String[] args) throws IOException  {
		String forecast = getCityWeather("",null);
		System.out.println(forecast);
	}
	
	// CONSTANTS
	private static final String URL_GEOCODE = "https://geocode.xyz/";
	private static final String GEOCODE_URL_PARAMS = "?json=1&auth=";	
	private static final String OPEN_METEO_URL_PARAMS = "&daily=weathercode&current_weather=true&timezone=Europe%2F";
	private static final String OPEN_METEO_URL = "https://api.open-meteo.com/v1/forecast?latitude=";		
	private static final String OPEN_METEO_LONGITUDE_PARAM = "&longitude=";		
	private static final String LONGITTUDE_FIELD = "longt";
	private static final String LATITUDE_FIELD = "latt";	
	private static final String WEATHER_CODE_FIELD = "weathercode";
	private static final String TIME_FIELD = "time";	
	private static final String DATE_FORMAT = "yyyy-MM-dd";	
	private static final Date NEW_DATETIME = new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 6);	
	private static final String API_KEY = "113725055223685e15811337x25633";
	
			
	/**
	 * Function that obtains the weather of a city on a given date
	 * @param city String type parameter that refers to the city.
	 * @param datetime String type parameter that refers to date and time.
	 * @return Returns a text string with the time in the city passed by parameter.
	 * @throws IOException Can throw exceptions of type IOException.
	 */
	public static String getCityWeather(String city, Date datetime) throws IOException  {
		final Date datetimeCheck = checkDatetimeIsNull(datetime);	
		String httpRequestResult;
		if (checkDateBeforeParameter(datetimeCheck, NEW_DATETIME)) {			
			//try {
				httpRequestResult = 
						getHttpRequest(URL_GEOCODE + city + GEOCODE_URL_PARAMS + API_KEY);			
				CityCoordinates cityCoordinates = 
						new CityCoordinates(LATITUDE_FIELD, LONGITTUDE_FIELD, httpRequestResult);			
				httpRequestResult = 
						getHttpRequest(OPEN_METEO_URL + cityCoordinates.getLatitude() 
						+ OPEN_METEO_LONGITUDE_PARAM + cityCoordinates.getLongitude() 
						+ OPEN_METEO_URL_PARAMS + city);			
				DailyWeather dailyWeather = new DailyWeather(httpRequestResult, TIME_FIELD, WEATHER_CODE_FIELD);			
				return getDescriptionWeather(dailyWeather.getDailyResult(), dailyWeather.getWeatherCode(), datetimeCheck);
			/*} catch (Exception e) {
				return e.getMessage();
			}*/
		}
		return "";
	}
	
	
	/**
	 * Method that returns the description of the climate of a city.
	 * @param dailyResults Parameter of type JSONArray
	 * @param weatherCodeResult Parameter of type JSONArray
	 * @param datetime Parameter of type DATE
	 * @return Returns a text string with the weather description.
	 */
	protected static String getDescriptionWeather (JSONArray dailyResults, JSONArray weatherCodeResult, Date datetime) {		
		for (int i = 0; i < dailyResults.length(); i++) {
			if (
			        new SimpleDateFormat(DATE_FORMAT)
                            .format(datetime)
                            .equals(dailyResults.get(i).toString())
            ) {				
				// We do the first casting to double
				double weatherCodeJSONCast = (double) weatherCodeResult.get(i);
				return ForecastEnum.getEnumByCode((int) weatherCodeJSONCast).getDescription();				
			}
		}		
		return "";
	}
	
	
	/**
	 * Method that makes a web request to the url address indicated
	 * in the String type parameter. Returns a text string of type JSON.
	 * @param url Url address
	 * @return Return a String
	 * @throws IOException You can throw exceptions of type IOException.
	 */
	protected static String getHttpRequest (String url) throws IOException  {
		HttpRequestFactory httpRequestFactory = new NetHttpTransport().createRequestFactory();
		HttpRequest httpRequest = httpRequestFactory.buildGetRequest(new GenericUrl(url));
		String httpRequestResultToString = httpRequest.execute().parseAsString();		
		return httpRequestResultToString;
	}
	
	/**
	 * Checks whether the date is earlier than the specified date or not.
	 * @param datetimeCheck Old Date
	 * @param newDate new Date
	 * @return Returns true or false
	 */
	protected static boolean checkDateBeforeParameter (Date datetimeCheck, Date newDate) {		
		boolean checkDateBefore = datetimeCheck.before(newDate) ? true : false;
		return checkDateBefore;
	}
	
	/**
	 * Checks if the date has been passed as null. In such a case it assigns newDate
	 * @param datetime Parameter of type Date that will be evaluated.
	 * @return Return a Date object
	 */
	protected static Date checkDatetimeIsNull (Date datetime) {		
		Date datetimecheck = datetime == null ? new Date() : datetime;
		return datetimecheck;
	}
}
