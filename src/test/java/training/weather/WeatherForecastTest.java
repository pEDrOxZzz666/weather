package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WeatherForecastTest {
	
	private static final Date NEW_DATETIME = new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 6);	
	
	
	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("*************************  THE TESTS BEGIN  *************************");
	}
	
	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("*************************  COMPLETED TESTS  *************************");
	}	

	@BeforeEach
	void tearDown()  {
		System.out.print("  --> TEST COMPLETED   ");	
	}
	

	//************************* TESTS  *************************
	
	/**
	 * Function that tests the getCityWeather method 
	 * @throws IOException Can throw exceptions of type IOEXception
	 */
	@Test
	public void test_Get_City_Weather() throws IOException {		
		System.out.println("*************  TEST Get_City_Weather");		
		Random citiesRandom = new Random();
		List<String> cities = new ArrayList<String>();
		cities.add("Paris");
		cities.add("Madrid");
		cities.add("Berlin");				
		String city = cities.get(citiesRandom.nextInt(3));		
		String forecast = WeatherForecast.getCityWeather(city, new Date());
		System.out.println("El tiempo en " + city + " en fecha y hora " 
		+ LocalDate.now() 
		+ " " 
		+ LocalTime.now()		
		+ " es: " + forecast);		
		assertTrue(forecast != "");		
	}
	
	
	/**
	 * Function that tests the getCityWeather
	 * @throws IOException
	 */
	@Test
	public void test_Get_CityWeather_Date_Before_Today() throws IOException {
		System.out.println("*************  TEST Get_CityWeather_Date_Before_Today");		
		String forecast = WeatherForecast.getCityWeather("Madrid", new Date(122, 8, 22));
		assertTrue(forecast == "");
	}
	
	
	/**
	 * Function that tests the checkDatetimeIsNull method
	 */
	@Test
	public void test_check_Datetime_IsNull() {
		System.out.println("*************  TEST check_Datetime_IsNull");
		Date date = WeatherForecast.checkDatetimeIsNull(null);
		assertTrue(date != null);		
	}
		
	
	/**
	 * Function that tests the checkDateBeforeParameter method
	 */
	@Test
	public void test_Check_Date_Before_Parameter() {
		System.out.println("*************  TEST Check_Date_Before_Parameter");		
		boolean dateAfter = WeatherForecast.checkDateBeforeParameter(new Date(), new Date(122, 8, 22));
		assertFalse(dateAfter);		
		boolean dateBefore = WeatherForecast.checkDateBeforeParameter(new Date(), NEW_DATETIME);
		assertTrue(dateBefore);
	}
	
	/**
	 * Function that tests the getDescriptionWeather method
	 * @throws IOException Can throw exceptions of type IOEXception
	 */
	@Test
	public void test_Get_Description_Weather() throws IOException {		
		System.out.println("*************  TEST Get_Description_Weather");		
		String coordenadas = WeatherForecast.getHttpRequest("https://geocode.xyz/Paris?json=1&auth=/113725055223685e15811337x25633");		
		CityCoordinates cityCoordinates = new CityCoordinates("latt", "longt", coordenadas);		
		String weatherForecast = WeatherForecast.getHttpRequest(
				"https://api.open-meteo.com/v1/forecast?latitude=" 
				+ cityCoordinates.getLatitude() 
				+ "&longitude=" + cityCoordinates.getLongitude() 
				+ "&daily=weathercode&current_weather=true&timezone=Europe%2FParis");		
		DailyWeather dailyWeather = new DailyWeather(weatherForecast, "time", "weathercode");		
		String forecast = WeatherForecast.getDescriptionWeather(dailyWeather.getDailyResult(), dailyWeather.getWeatherCode(), new Date());
		
		System.out.println("El tiempo en Paris en fecha y hora " 
				+ LocalDate.now() 
				+ " " 
				+ LocalTime.now()		
				+ " es: " + forecast);		
		assertTrue(!forecast.equals(""));		
	}
	
	
	/**
	 * Function that tests the getHttpRequest method by passing it a wrong URL
	 */
	@Test
	public void test_Wrong_URL_Argument() {
		System.out.println("*************  TEST Wrong_URL_Argument");
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {			
			WeatherForecast.getHttpRequest("asdfasdfadf");		
		});
	}	
	
}