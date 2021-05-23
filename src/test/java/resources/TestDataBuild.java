package resources;

import static io.restassured.RestAssured.DEFAULT_BODY_ROOT_PATH;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name, String language, String address) {
		
		AddPlace ap = new AddPlace();

		// Creating spec builder, it will return request specification

		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);

		Location l = new Location();
		l.setLat(-45.2321);
		l.setLng(-23.433423);

		ap.setLocation(l);
		ap.setName(name);
		ap.setPhone_number("98798797");
		ap.setWebsite(DEFAULT_BODY_ROOT_PATH);

		List<String> mylist = new ArrayList<String>();
		mylist.add("Shoe park");
		mylist.add("shop");

		ap.setTypes(mylist);
		
		return ap;
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\"place_id\":\""+placeId+"\"}\r\n";
		
	}
	
}
