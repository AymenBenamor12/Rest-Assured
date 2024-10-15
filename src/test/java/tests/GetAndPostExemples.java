package tests;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItems;
import java.util.HashMap;
import java.util.Map;

public class GetAndPostExemples {

	
	@Test
	public void testGest() {
		baseURI = "https://reqres.in/api";
		
		given().
		get("users?page=2").
		then().
		statusCode(200)
		.body("data[4].first_name", equalTo("George"))
		.body("data.first_name", hasItems("George","Rachael"))
		
		;
		
		
	}
	
	@Test 
	
	public void testPost() {
		Map<String,Object> map = new HashMap<String,Object>();
		
		//map.put("name","Raghav");
		//map.put("job","teacher");
		//System.out.println(map);
		
		JSONObject request = new JSONObject(map);
		request.put("name","Raghav");
		request.put("job","teacher");
		
		System.out.println(request.toJSONString());
		baseURI = "https://reqres.in/api";
		given().
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		post("/users"). 
		then(). 
		statusCode(201).
		log().all()
		;
	}
}
