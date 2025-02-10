package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegistrationPage;

public class RegistrationSteps  {
    private WebDriver driver;
    private RegistrationPage regPage;

    @Given("I navigate to the registration page")
    public void navigateToRegistration() {
        try {
            // Initialize WebDriver and navigate to the registration page
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://demowebshop.tricentis.com/register");
            regPage = new RegistrationPage(driver);
        } catch (Exception e) {
            System.err.println("Failed to initialize WebDriver or navigate to the registration page: " + e.getMessage());
            throw new RuntimeException("Driver initialization or navigation failed.");
        }
    }

    @When("I register with:")
    public void registerWithDetails(DataTable dataTable) {
        try {
            // Extract registration details from the DataTable
            List<Map<String, String>> data = dataTable.asMaps();
            if (!data.isEmpty()) {
                Map<String, String> userDetails = data.get(0); // Use the first row of data
                regPage.registerUser(
                    userDetails.get("FirstName"),
                    userDetails.get("LastName"),
                    userDetails.get("Email"),
                    userDetails.get("Password")
                );
            } else {
                throw new IllegalArgumentException("No registration data provided.");
            }
        } catch (Exception e) {
            System.err.println("Failed to register with provided details: " + e.getMessage());
            throw new RuntimeException("Registration action failed.");
        }
    }

    @When("I register with existing email {string}")
    public void registerWithExistingEmail(String email) {
        try {
            regPage.registerUser("John", "Doe", email, "Pass123!");
        } catch (Exception e) {
            System.err.println("Failed to register with existing email: " + e.getMessage());
            throw new RuntimeException("Registration with existing email failed.");
        }
    }

    @When("I leave all fields empty and submit")
    public void registerWithEmptyFields() {
        try {
            regPage.registerUser("", "", "", "");
        } catch (Exception e) {
            System.err.println("Failed to submit empty registration form: " + e.getMessage());
            throw new RuntimeException("Empty fields submission failed.");
        }
    }

    @When("I enter invalid email {string}")
    public void registerWithInvalidEmail(String email) {
        try {
            regPage.registerUser("John", "Doe", email, "Pass123!");
        } catch (Exception e) {
            System.err.println("Failed to register with invalid email: " + e.getMessage());
            throw new RuntimeException("Registration with invalid email failed.");
        }
    }

    @Then("I should see the registration message {string}")
    public void verifyRegistrationMessage(String expectedMessage) {
        try {
            String actualMessage = regPage.getRegistrationMessage();
            Assert.assertTrue(
                actualMessage.contains(expectedMessage),
                "Expected message: " + expectedMessage + ", but found: " + actualMessage
            );
        } catch (Exception e) {
            System.err.println("Failed to verify the message: " + e.getMessage());
            throw new RuntimeException("Message verification failed.");
        }
    }

    @Then("I should see the registration errors {string}, {string}, {string}, and {string}")
    public void verifyMultipleRegistrationErrors(String error1, String error2, String error3, String error4) {
        try {
            String actualMessage = regPage.getRegistrationMessage();
            Assert.assertTrue(actualMessage.contains(error1), "Error message not found: " + error1);
            Assert.assertTrue(actualMessage.contains(error2), "Error message not found: " + error2);
            Assert.assertTrue(actualMessage.contains(error3), "Error message not found: " + error3);
            Assert.assertTrue(actualMessage.contains(error4), "Error message not found: " + error4);
        } catch (Exception e) {
            System.err.println("Failed to verify multiple error messages: " + e.getMessage());
            throw new RuntimeException("Error messages verification failed.");
        }
    }

    @Then("close the registration browser")
    public void closeRegistrationBrowser() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Failed to close the browser: " + e.getMessage());
        }
    }
}