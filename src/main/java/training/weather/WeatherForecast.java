package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;


public class WeatherForecast {
	
	// CONSTANTS
	private static final String WEATHERCODE_FIELD = "weathercode";
	private static final String TIME_FIElD = "time";	
	private static final String DATE_FORMAT = "yyyy-MM-dd";	
	private static final Date NEW_DATETIME = new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 6);	
	

	/**
	 * Function that obtains the weather of a city on a given date
	 * @param city
	 * @param datetime
	 * @return
	 * @throws IOException
	 */
	public static String getCityWeather(String city, Date datetime) throws IOException {
				
		final Date datetimeCheck = checkDatetimeIsNull(datetime);	
		String httpRequestResult;
		
		if (checkDateBeforeParameter(datetimeCheck, NEW_DATETIME)) {						
			try {
				httpRequestResult = getHttpRequest("https://geocode.xyz/" + city + "?json=1");
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
								
				CityCoordinates cityCoordinates = new CityCoordinates(
						"latt", "longt", httpRequestResult	);				
				
				httpRequestResult = getHttpRequest(
						"https://api.open-meteo.com/v1/forecast?latitude=" + cityCoordinates.getLatitude() 
						+ "&longitude=" + cityCoordinates.getLongitude() 
						+ "&daily=weathercode&current_weather=true&timezone=Europe%2FBerlin");				
				
				DailyWeather dailyWeather = new DailyWeather(httpRequestResult, TIME_FIElD, WEATHERCODE_FIELD);			
				
				return getDescriptionWeather(dailyWeather.getDailyResult(), dailyWeather.getWeatherCode(), datetimeCheck);				
				
			} catch (JSONException e) {				
				return "";
			}			
		}
		return "";
	}
	
	
	
	private static String getDescriptionWeather (JSONArray dailyResults, JSONArray weatherCodeResult, Date datetime) {		
		for (int i = 0; i < dailyResults.length(); i++) {
			if (
			        new SimpleDateFormat(DATE_FORMAT)
                            .format(datetime)
                            .equals(dailyResults.get(i).toString())
            ) {				
				double weatherCodeJSONCast = (double) weatherCodeResult.get(i);
				return ForecastEnum.getEnumByCode((int) weatherCodeJSONCast).getDescription();				
			}
		}		
		return "";
	}
	
	
	private static String getHttpRequest (String url) throws IOException  {				
		HttpRequestFactory httpRequestFactory = new NetHttpTransport().createRequestFactory();
		HttpRequest httpRequest = httpRequestFactory.buildGetRequest(new GenericUrl(url));
		String httpRequestResultToString = httpRequest.execute().parseAsString();		
		return httpRequestResultToString;
	}
	
	
	private static boolean checkDateBeforeParameter (Date datetimeCheck, Date newDate) {		
		boolean checkDateBefore = datetimeCheck.before(newDate) ? true : false;
		return checkDateBefore;
	}
	
	
	private static Date checkDatetimeIsNull (Date datetime) {		
		Date datetimecheck = datetime == null ? new Date() : datetime;
		return datetimecheck;
	}
}
