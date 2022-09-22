package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {		

	private static final String DAILY = "daily";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final int ADD_TIME = 1000 * 60 * 60 * 24 * 6;
	

	public static String getCityWeather(String city, Date datetime) throws IOException {
				
		final Date datetimeCheck = checkDatetimeIsNull(datetime);
		
		
		
		if (datetimeCheck.before(new Date(new Date().getTime() + ADD_TIME))) {
			HttpRequestFactory httpRequestFactory = new NetHttpTransport().createRequestFactory();
			HttpRequest httpRequest = httpRequestFactory
				.buildGetRequest(new GenericUrl("https://geocode.xyz/" + city + "?json=1"));
			String httpRequestResultToString = httpRequest.execute().parseAsString();
			
			try {
				Thread.sleep(4500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject jsonObjectDataCity = new JSONObject(httpRequestResultToString);
			String longitude = jsonObjectDataCity.get("longt").toString();
			String latitude = jsonObjectDataCity.get("latt").toString();
			httpRequestFactory = new NetHttpTransport().createRequestFactory();
			httpRequest = httpRequestFactory.buildGetRequest(new GenericUrl("https://api.open-meteo.com/v1/forecast?latitude=" +
                    latitude + "&longitude=" + longitude + "&daily=weathercode&current_weather=true&timezone=Europe%2FBerlin"));
			httpRequestResultToString = httpRequest.execute().parseAsString();
			JSONArray dailyResults = new JSONObject(httpRequestResultToString).getJSONObject(DAILY).getJSONArray("time");
			JSONArray weatherCodeResult = new JSONObject(httpRequestResultToString).getJSONObject(DAILY).getJSONArray("weathercode");
			for (int i = 0; i < dailyResults.length(); i++) {
				if (
				        new SimpleDateFormat(DATE_FORMAT)
                                .format(datetimeCheck)
                                .equals(dailyResults.get(i).toString())
                ) {
					
					double weatherCodeJSONCast = (double) weatherCodeResult.get(i);
					return ForecastEnum.getEnumByCode((int) weatherCodeJSONCast).getDescription();
					
					//return ForecastEnum.getEnumByCode((int) weatherCodeResults.get(i)).getDescription();
				}
			}
		}
		return "";
	}
	
	private static Date checkDatetimeIsNull (Date datetime) {
		if (datetime == null) {
			datetime = new Date();
		} 
		return datetime;
	}
}
