Feature: validating place API's

@AddPlace
Scenario Outline: verify if place is being Successfully added using AssPlaceAPI

	Given Add place payload with "<name>" "<languag>" "address"
	When user calls "addPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" is response body is "OK"
	And "scope" is response body is "APP"
	And verify place_Id created maps to "<name>" using "getplactAPI"
	
Examples:
	|name 	| language 	| address  	 |
	|Deb  	| English  	|Laxmisagar  |
#	|Deb2  	| English2  |Laxmisagar2 |

## below scenario formed based on the above scenario. So that we dont have to prepare extra step defination. 

@DeletePlace @Regression
Scenario: verify if Delete Place functinality is working
     Given DeletePlace Payload
     When user calls "deletePlaceAPI" with "POST" http request
     Then the API call got success with status code 200
	 And "status" is response body is "OK"
	 	 