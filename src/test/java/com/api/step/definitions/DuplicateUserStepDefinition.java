package com.api.step.definitions;

import java.time.LocalDate;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import com.api.model.User;
import com.api.util.ApiContext;
import com.api.util.DateUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.datafaker.Faker;;


public class DuplicateUserStepDefinition {
	private static final Logger log = LogManager.getLogger(DuplicateUserStepDefinition.class);
	private Response response;
	Map<String, String> userInputData;
	User user;

	@When("call create duplicate user api {string}")
	public void callCreateDuplicateUserApi(String endpoint, DataTable data){
		userInputData = data.asMaps().get(0);
		user = new User();
		user.setId(userInputData.get("id"));
		user.setEmail(userInputData.get("email"));
		user.setUsername(userInputData.get("username"));
		user.setPassword(userInputData.get("password"));
		response = ApiContext.getRequestBase()
				.body(user)
				.when()
				.log().all()
				.post(endpoint);
		response.prettyPrint();
	}
	
	@Then("user should get response code {int} for duplicate record")
	public void userShouldGetResponseCodeForDuplicateRecord(Integer code) {
		log.info("in userShouldGetResponseCodeForDuplicateRecord");
		response.then().statusCode(code);
	}
	
	@Then("user verifies error message for duplicate record")
	public void userVerifyErrorMessageForDuplicateRecord() {
		log.info("in userVerifyErrorMessageForDuplicateRecord");
		response.then().body("detail",CoreMatchers.equalTo("account already exists"));
		response.then().body("status",CoreMatchers.equalTo("Bad Request"));
	}
	
	
	

}
