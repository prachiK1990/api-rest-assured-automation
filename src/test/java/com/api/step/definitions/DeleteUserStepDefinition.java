package com.api.step.definitions;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.junit.Assert;

import com.api.model.User;
import com.api.util.ApiContext;
import com.api.util.DateUtils;
import com.api.util.UserApiCommonUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.datafaker.Faker;;


public class DeleteUserStepDefinition {
	private static final Logger log = LogManager.getLogger(DeleteUserStepDefinition.class);
	private Response response;
	Map<String, String> userInputData;
	User user = new User();
	Faker faker = new Faker();
	
	@When("call create user api for test {string}")
	public void callCreateUserApi(String endpoint){
		response = UserApiCommonUtil.createNewUserApi(endpoint, user);
		response.prettyPrint();
		response.then().statusCode(200);
	}
	
	@Then("call delete api and delete a user account by id {string}")
	public void testDeleteUserById(String endpoint ){
		
		JSONObject reqBody = new JSONObject();
		reqBody.put("id", user.getId());
		response = ApiContext.getRequestBase()
				.body(reqBody.toString())
				.when()
				.log().all()
				.post(endpoint);
		response.prettyPrint();
    	response.then().statusCode(200);

	}
	
	@Then("call read api by id and verify deleted email and username {string}")
	public void readDeletedUserById(String endpoint){
		response = UserApiCommonUtil.readUserApi(endpoint, user);
		
		response.prettyPrint();
		response.then().statusCode(500); 
		//status code and message should be user understandable like "Record does not exist"
		
		response.then().body("detail",CoreMatchers.equalTo("not found"));
		response.then().body("status",CoreMatchers.equalTo("Internal Server Error"));
		
		
	}
	
}
