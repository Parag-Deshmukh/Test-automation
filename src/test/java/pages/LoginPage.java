package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By loginButton = By.cssSelector("input[value='Log in']");
    private By logoutLink = By.linkText("Log out");
    private By errorMessage = By.cssSelector(".validation-summary-errors");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLogoutDisplayed() {
        // Check if the "Log out" link is displayed
        try {
            return driver.findElement(logoutLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        // Get the error message after a failed login attempt
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public void attemptLoginMultipleTimes(String email, String password, int attempts) {
        // Attempt to login multiple times with invalid credentials
        for (int i = 0; i < attempts; i++) {
            enterEmail(email);
            enterPassword(password);
            clickLogin();
        }
    }
}