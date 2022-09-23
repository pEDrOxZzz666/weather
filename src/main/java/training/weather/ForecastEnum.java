package training.weather;

import java.util.HashMap;

/**
 * 
 * @author Arteco
 * ENUM containing the time and its code	
 */
public enum ForecastEnum {
    CLEAR_SKY(0, "Clear sky"),
    MAINLY_CLEAR(1, "Mainly clear"),
    PARTLY_CLOUDY(2, "Partly cloudy"),
    OVERCAST(3, "Overcast"),
    FOG(45, "Fog"),
    DEPOSITING_RIME_FOG(48, "Depositing rime fog"),
    LIGHT_DRIZZLE(51, "Light drizzle"),
    MODERATE_DRIZZLE(53, "Moderate drizzle"),
    DENSE_DRIZZLE(55, "Dense drizzle"),
    LIGHT_FREEZING_DRIZZLE(56, "Light freezing drizzle"),
    DENSE_FREEZING_DRIZZLE(57, "Dense freezing drizzle"),
    SLIGHT_RAIN(61, "Slight rain"),
    MODERATE_RAIN(63, "Moderate rain"),
    HEAVY_RAIN(65, "Heavy rain"),
    LIGHT_FREEZING_RAIN(66, "Light freezing rain"),
    HEAVY_FREEZING_RAIN(67, "Heavy freezing rain"),
    SLIGHT_SNOW_FALL(71, "Slight snow fall"),
    MODERATE_SNOW_FALL(73, "Moderate snow fall"),
    HEAVY_SNOW_FALL(75, "Heavy snow fall"),
    SNOW_GRAINS(77, "Snow grains"),
    SLIGHT_RAIN_SHOWERS(80, "Slight rain showers"),
    MODERATE_RAIN_SHOWERS(81, "Moderate rain showers"),
    VIOLENT_RAIN_SHOWERS(82, "Violent rain showers"),
    SLIGHT_SNOW_SHOWER(85, "Slight snow shower"),
    HEAVY_SNOW_SHOWER(86, "Heavy snow shower"),
    THUNDERSTORM(95, "Thunderstorm"),
    THUNDERSTORM_WITH_SLIGHT_HAIL(96, "Thunderstorm with slight hail"),
    THUNDERSTORM_WITH_HEAVY_HAIL(99, "Thunderstorm with heavy hail");

    private int weatherCode;
    private String description;
    private static final HashMap<Integer, ForecastEnum> WEATHER_DESCRIPTION;

    // Enter the ENUM data in a HashMap
    static {
        WEATHER_DESCRIPTION = new HashMap<>();
        for (ForecastEnum e : ForecastEnum.values()) {
            WEATHER_DESCRIPTION.put(e.weatherCode, e);
        }
    }

    /**
     * Constructor
     * @param code int Integer type parameter that contains the code of each climate
     * @param description String type parameter that contains the description of the weather.
     */
    ForecastEnum(int code, String description) {
        this.weatherCode = code;
        this.description = description;
    }

    /**
     * Function that returns an object of type ForecastEnum
     * @param code Integer type parameter that represents the weather code.
     * @return Returns an object of type ForecastEnum
     */
    public static ForecastEnum getEnumByCode(int code) {
        return WEATHER_DESCRIPTION.get(code);
    }

    /**
     * Function that returns the description of the weather.
     * @return Return weather description
     */
    public String getDescription() {
        return description;
    }
}