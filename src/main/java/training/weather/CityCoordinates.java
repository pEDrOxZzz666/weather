package training.weather;

import org.json.JSONObject;

public class CityCoordinates {
	
	private String latitude, longitude, httpRequestResult;
	private JSONObject jsonObjectDataCity;

	public CityCoordinates(String latitude, String longitude, String httpRequestResult) {		
		this.latitude = latitude;
		this.longitude = longitude;
		this.httpRequestResult = httpRequestResult;		
		this.jsonObjectDataCity = new JSONObject(this.httpRequestResult);
	}
	
	
	public String getLatitude () {		
		return jsonObjectDataCity.get(this.latitude).toString();		
	}

	
	public String getLongitude () {		
		return jsonObjectDataCity.get(this.longitude).toString();		
	}
	

}
