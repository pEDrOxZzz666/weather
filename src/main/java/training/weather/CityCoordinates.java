package training.weather;
import org.json.JSONObject;

/**
 * 
 * @author Pedro
 * Class that extracts the longitude and latitude through a JSON object parsed to String
 *
 */
public class CityCoordinates {
	
	private String latitude, longitude, httpRequestResult;
	private JSONObject jsonObjectDataCity;

	/**
	 * Constructor
	 * @param latitude String type parameter to extract the latitude
	 * @param longitude String type parameter to extract the longitude
	 * @param httpRequestResult Parameter of type String that contains data of type JSON
	 */
	public CityCoordinates(String latitude, String longitude, String httpRequestResult) {		
		this.latitude = latitude;
		this.longitude = longitude;
		this.httpRequestResult = httpRequestResult;		
		this.jsonObjectDataCity = new JSONObject(this.httpRequestResult);
	}
	
	
	/**
	 * Method to extract latitude
	 * @return Returns a string with the latitude
	 */
	public String getLatitude () {		
		return jsonObjectDataCity.get(this.latitude).toString();		
	}

	/**
	 * Method to extract Longitude
	 * @return Returns a string with the longitude
	 */
	public String getLongitude () {		
		return jsonObjectDataCity.get(this.longitude).toString();		
	}
	

}
