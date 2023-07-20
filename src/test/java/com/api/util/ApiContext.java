package com.api.util;

import com.api.readers.PropertyLoader;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class ApiContext {
	
	private static final String contentType = PropertyLoader.getProperty("content.type");
	private static final String baseURI = PropertyLoader.getProperty("baseURI");
	private static final String token = PropertyLoader.getProperty("token");
	
	public static RequestSpecification getRequestBase() {	
		RestAssured.reset(); 
		RestAssured.baseURI = baseURI;
		return RestAssured.given()				
				.contentType(contentType)
				.accept(contentType)
				.header("Authorization","Bearer "+token);
	} 

}
