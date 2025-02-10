package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "hooks"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    tags = "@Positive1" // Filter to run only @Positive scenarios
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
