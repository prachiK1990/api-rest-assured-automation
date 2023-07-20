package com.api.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/resources/features"}, 
		glue= {"com.api.step.definitions"},
		monochrome= true,
		dryRun = false,
		plugin = {"pretty", "junit:target/reports/JUnitReports/report.xml",
		"json:target/reports/JSONReports/report.json",
		"html:target/reports/HtmlReports"}
		)
public class ApiTestSuiteRunner {
}
