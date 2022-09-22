package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherForecastTest {

	@Test
	public void unfinished_test() throws IOException {		
		String forecast = WeatherForecast.getCityWeather("Berlin", new Date());
		System.out.println(forecast);
		
		//assertEquals(forecast,"");
	}
}