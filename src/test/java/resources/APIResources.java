package resources;

//Enum: is a special class. which is a collection of constants or methods. Below is the methods. Also we need to define one costructor.
public enum APIResources {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getplactAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	//Define the constructor of enum class. which will load the resource actual values
	 APIResources(String resource) {
		this.resource = resource;
		 
	}
	 
	 public String getResources() {
		 return resource;
	 }
	 
	 
}

