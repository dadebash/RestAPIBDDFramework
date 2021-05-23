package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

// This class is for precondition and postcondition for delete scenarios
public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//write the code which will give us place id..
		//execute this method when place id is null
		
		StepDefinations m = new StepDefinations();
		
		if(StepDefinations.placeidValue==null) {
			
		
		m.add_place_payload_with("situ", "Oriya", "Asia");
		m.user_calls_with_http_request("addPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("situ", "getplactAPI");
		}
	}

}
