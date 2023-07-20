package com.api.step.definitions;

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
import net.datafaker.Faker;
import io.restassured.module.jsv.JsonSchemaValidator;


public class CreateUserStepDefinition {
	private static final Logger log = LogManager.getLogger(CreateUserStepDefinition.class);
	private Response response;
	Map<String, String> userInputData;
	User user = new User();
	Faker faker = new Faker();
	
	@When("call create user api {string}")
	public void callCreateUserApi(String endpoint){
		response = UserApiCommonUtil.createNewUserApi(endpoint, user);
		response.prettyPrint();
	}
	
	@Then("user should get response code {int}")
	public void userShouldGetResponseCode(Integer code) {
		log.info("in userShouldGetResponseCode");
		response.then().statusCode(code);
	}
	
	@Then("verify create api response")
	public void userShouldBeCreate(){
		log.info("in userShouldBeCreate");
		response.then().body("account.id",CoreMatchers.equalTo(user.getId()));
		response.then().body("account.username",CoreMatchers.equalTo(user.getUsername()));
		response.then().body("account.email",CoreMatchers.equalTo(user.getEmail()));
		String timeStamp = response.then().extract().body().path("account.created");
		LocalDate createDate = DateUtils.getDateFromTimeStamp(timeStamp);
		LocalDate now = LocalDate.now();
		Assert.assertTrue(createDate.isEqual(now));		
	}
	
	@Then("call read api by id and verify user should be created {string}")
	public void verifyUserAfterRead(String endpoint){
		log.info("in verifyUserAfterRead");
		
		response = UserApiCommonUtil.readUserApi(endpoint, user);
		response.prettyPrint();
		response.then().statusCode(200);
		
		response.then().body("account.username",CoreMatchers.equalTo(user.getUsername()));
		response.then().body("account.email",CoreMatchers.equalTo(user.getEmail()));
		response.then().body("account.id",CoreMatchers.equalTo(user.getId()));
		String timeStamp = response.then().extract().body().path("account.created");
		LocalDate createDate = DateUtils.getDateFromTimeStamp(timeStamp);
		LocalDate now = LocalDate.now();
		Assert.assertTrue(createDate.isEqual(now));		
	}
	
	@Then("verify read response schema {string}")
	public void verifyReadResponseSchema(String schemaFileName){
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFileName));
		log.info("Validate schema successful using schema file: "+ schemaFileName);
	}

}
