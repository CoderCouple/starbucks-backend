package com.starbucks;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(monochrome = true, plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json"})
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
