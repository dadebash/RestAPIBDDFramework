package stepDefinitions;

import static io.restassured.RestAssured.DEFAULT_BODY_ROOT_PATH;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils {
	RequestSpecification reqSpec2;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	// APIResources resourceAPI;
	static String placeidValue;

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		reqSpec2 = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
	}

//	@Given("Add place payload")
//	public void add_place_payload() throws IOException {
//			reqSpec2 = given().spec(requestSpecification()).body(data.addPlacePayLoad());
//	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpmethod) {
		// Write code here that turns the phrase above into concrete actions

		// invoking constructor with valueof() method which is noting but addPlacceAPI
		// resource
		// constructor will be called with value of resource which we pass.
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResources());
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (httpmethod.equalsIgnoreCase("POST"))

			response = reqSpec2.when().post(resourceAPI.getResources());
		// then().spec(resSpec).extract().response();
		else if (httpmethod.equalsIgnoreCase("GET")) {
			response = reqSpec2.when().get(resourceAPI.getResources());

		}
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} is response body is {string}")
	public void is_response_body_is(String keyValue, String ExceptedValue) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(response, keyValue), ExceptedValue);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String exceptedName, String resource) throws IOException {
		;
		// require request spec

		// placeIdval= jp.get("place_id");
		placeidValue = getJsonPath(response, "place_id");
		reqSpec2 = given().spec(requestSpecification()).queryParam("place_id", placeidValue);

		// reusing existing method
		user_calls_with_http_request(resource, "GET");

		String actualName = getJsonPath(response, "name");

		assertEquals(actualName, exceptedName);

	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {

		// Request specification with the body.

		reqSpec2 = given().spec(requestSpecification()).body(data.deletePlacePayload(placeidValue));

		// response = reqSpec2.when().post(resourceAPI.getResources());
	}
}
