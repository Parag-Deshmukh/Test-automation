package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps  {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("I navigate to the login page")
    public void navigateToLoginPage() {
        try {
            // Initialize WebDriver and navigate to the login page
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://demowebshop.tricentis.com/login");
            loginPage = new LoginPage(driver);
        } catch (Exception e) {
            System.err.println("Failed to initialize WebDriver or navigate to login page: " + e.getMessage());
            throw new RuntimeException("Driver initialization or page navigation failed.");
        }
    }

    @When("I login with {string} and {string}")
    public void loginWithCredentials(String email, String password) {
        try {
            // Enter email, password, and click login
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLogin();
        } catch (Exception e) {
            System.err.println("Failed to login with provided credentials: " + e.getMessage());
            throw new RuntimeException("Login action failed.");
        }
    }

    @When("I attempt to login {int} times with invalid credentials")
    public void attemptLoginMultipleTimes(int attempts) {
        try {
            // Attempt to login multiple times with invalid credentials
            loginPage.attemptLoginMultipleTimes("invalid@test.com", "wrongpass", attempts);
        } catch (Exception e) {
            System.err.println("Failed to perform multiple login attempts: " + e.getMessage());
            throw new RuntimeException("Multiple login attempts failed.");
        }
    }

    @Then("I should see the login message {string}")
    public void verifyLoginMessage(String expectedMessage) {
        try {
            // Verify the success or error message
            if (expectedMessage.equalsIgnoreCase("Log out")) {
                Assert.assertTrue(loginPage.isLogoutDisplayed(), "Log out link is not displayed");
            } else {
                String actualMessage = loginPage.getErrorMessage();
                Assert.assertTrue(actualMessage.contains(expectedMessage),
                    "Expected message: " + expectedMessage + ", but found: " + actualMessage);
            }
        } catch (Exception e) {
            System.err.println("Failed to verify the message: " + e.getMessage());
            throw new RuntimeException("Message verification failed.");
        }
    }

    @Then("close the login browser")
    public void closeLoginBrowser() {
        try {
            // Close the browser after each scenario
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Failed to close the browser: " + e.getMessage());
        }
    }
}