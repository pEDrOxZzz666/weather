package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class WeatherForecastTest {

	@Test
	public void unfinished_test() throws IOException {		
		String forecast = WeatherForecast.getCityWeather("Berlin", new Date(122, 8, 23));
		System.out.println(forecast);
		
		
		forecast = WeatherForecast.getCityWeather("Madrid", new Date());
		System.out.println(forecast);
		
		LocalTime local = LocalTime.now();
		System.out.println(local);
		
		System.out.println(LocalDate.now());
		
		
		System.out.println(ZoneId.systemDefault());
		assertEquals(forecast,"Overcast");
	}
}