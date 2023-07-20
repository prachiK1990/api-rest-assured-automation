package com.api.util;

import org.json.JSONObject;

import com.api.model.User;

import io.restassured.response.Response;
import net.datafaker.Faker;

public class UserApiCommonUtil {
	static Faker faker = new Faker();
	
	public static Response createNewUserApi(String endpoint, User user){
		user.setId(faker.idNumber().valid());
		user.setEmail(faker.internet().emailAddress());
		user.setUsername(faker.name().username());
		user.setPassword(faker.internet().password());
		return ApiContext.getRequestBase()
				.body(user)
				.when()
				.log().all()
				.post(endpoint);
	}
	
	public static Response readUserApi(String endpoint, User user){
		JSONObject reqBody = new JSONObject();
		reqBody.put("id", user.getId());
		
		return ApiContext.getRequestBase()
				.body(reqBody.toString())
				.when()
				.log().all()
				.post(endpoint);
	}

}
