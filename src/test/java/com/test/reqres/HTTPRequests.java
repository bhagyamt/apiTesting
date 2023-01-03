package com.test.reqres;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {
	
	int id;

	@Test(priority = 1)
	void getRequest() {
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test(priority = 2)
	void postRequest() {
		HashMap data = new HashMap();
		data.put("name", "basu");
		data.put("job", "engineer");
		
		id = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		/*.then()
			.statusCode(201)
			.log().all();*/
	}
	
	@Test(priority = 3,dependsOnMethods = {"postRequest"})
	void putRequest() {
		HashMap data = new HashMap();
		data.put("name", "nikita");
		data.put("job", "tester");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
		 .then() 
		 	.statusCode(200) 
		 	.log().all();
	}
	
	@Test(priority = 4)
	void deleteRequest(){
		given()
			.contentType("application/json")
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then() 
		 	.statusCode(204) 
		 	.log().all();
	}
}

/*
 * given() Headers (content-type,set cookies,add auth), Request Body, Parameters
 * when() HTTP Requests - get, post, put, delete 
 * then() validate status code, extract response extract headers, response body.
 */