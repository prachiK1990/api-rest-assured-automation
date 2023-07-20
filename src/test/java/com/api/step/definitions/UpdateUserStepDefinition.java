package com.api.step.definitions;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.json.JSONObject;

import com.api.model.User;
import com.api.util.ApiContext;
import com.api.util.UserApiCommonUtil;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.datafaker.Faker;;


public class UpdateUserStepDefinition {
	private static final Logger log = LogManager.getLogger(UpdateUserStepDefinition.class);
	private Response response;
	Map<String, String> userInputData;
	User user = new User();
	User updatedUser;
	Faker faker = new Faker();
	
	@When("create a test user {string}")
	public void createTestUser(String endpoint){
		response = UserApiCommonUtil.createNewUserApi(endpoint, user);
		response.prettyPrint();
		response.then().statusCode(200);
	}

	@When("call update api and update username email {string}")
	public void udpateUser(String endpoint){
		
		updatedUser = new User();
		updatedUser.setId(user.getId());
		updatedUser.setEmail(faker.internet().emailAddress());
		updatedUser.setUsername(faker.name().username());
		
		JSONObject reqBody = new JSONObject();
		reqBody.put("id", updatedUser.getId());
		reqBody.put("email", updatedUser.getEmail());
		reqBody.put("username", updatedUser.getUsername());
		
		response = ApiContext.getRequestBase()
				.body(reqBody.toString())
				.when()
				.log().all()
				.post(endpoint);
		response.prettyPrint();
		response.then().statusCode(200);
		
	}
	
	
	@Then("call read api by id and verify updated email and username {string}")
	public void readUserById(String endpoint){
		
		response = UserApiCommonUtil.readUserApi(endpoint,updatedUser);
		response.prettyPrint();
		response.then().statusCode(200);
		response.then().body("account.username",CoreMatchers.equalTo(updatedUser.getUsername()));
		response.then().body("account.email",CoreMatchers.equalTo(updatedUser.getEmail()));
		
		
	}
	
	
	
	

}
