package com.api.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features={"src/test/resources/features"}, 
		glue= {"com.api.step.definitions"},
		plugin= {"pretty"},
		monochrome= true,
		dryRun = false
		)
public class ApiTestSuiteRunnerSerenity {
}
