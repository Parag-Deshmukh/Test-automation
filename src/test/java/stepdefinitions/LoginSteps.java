package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("I navigate to the login page")
    public void navigateToLoginPage() {
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://demowebshop.tricentis.com/login");
            loginPage = new LoginPage(driver);
        } catch (Exception e) {
            System.err.println("Error navigating to login page: " + e.getMessage());
            throw new RuntimeException("Unable to navigate to the login page.");
        }
    }

    @When("I login with {string} and {string}")
    public void loginWithCredentials(String email, String password) {
        try {
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            loginPage.clickLogin();
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            throw new RuntimeException("Login attempt failed.");
        }
    }

    @Then("I should see the login message {string}")
    public void verifyLoginMessage(String expectedMessage) {
        try {
            if (expectedMessage.equalsIgnoreCase("Log out")) {
                Assert.assertTrue(loginPage.isLogoutDisplayed(), "Expected 'Log out' link, but it is not displayed.");
            } else {
                String actualMessage = loginPage.getErrorMessage();
                System.out.println("Actual Error Message: " + actualMessage);
                Assert.assertTrue(
                    actualMessage.contains(expectedMessage),
                    "Expected error message to contain: " + expectedMessage + ", but found: " + actualMessage
                );
            }
        } catch (Exception e) {
            System.err.println("Error verifying the message: " + e.getMessage());
            throw new RuntimeException("Verification failed.");
        }
    }
    @Then("close the login browser")
    public void closeLoginBrowser() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Error closing the browser: " + e.getMessage());
        }
    }
}