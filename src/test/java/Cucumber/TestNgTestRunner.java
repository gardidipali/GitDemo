package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//Cucumber->TestNG,Junit
@CucumberOptions(features="src/test/java/Cucumber",glue="DipaliBhavsarSelenium.stepDefinations",monochrome=true,
tags = "@Regression",plugin= {"html:target/Cucumber.html"})

public class TestNgTestRunner extends AbstractTestNGCucumberTests {

}
