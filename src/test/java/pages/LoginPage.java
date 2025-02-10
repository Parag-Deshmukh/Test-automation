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
    private By errorSpan = By.xpath("//span[contains(text(),'Login was unsuccessful. Please correct the errors')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLogoutDisplayed() {
        try {
            return driver.findElement(logoutLink).isDisplayed();
        } catch (Exception e) {
            System.err.println("Error verifying logout link: " + e.getMessage());
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorSpan)).getText().trim();
        } catch (Exception e) {
            System.err.println("Error retrieving error message: " + e.getMessage());
            return "";
        }
    }
}