package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage extends BasePage {
    private By genderMale = By.id("gender-male");
    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By email = By.id("Email");
    private By password = By.id("Password");
    private By confirmPassword = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By successMessage = By.cssSelector(".result");
    private By errorMessage = By.cssSelector(".validation-summary-errors");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void registerUser(String fName, String lName, String emailId, String pwd) {
        // Fill out the registration form
        driver.findElement(genderMale).click();
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(email).sendKeys(emailId);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(confirmPassword).sendKeys(pwd);
        driver.findElement(registerButton).click();
    }

    public String getRegistrationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Check if success message is visible
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
        } catch (Exception e) {
            try {
                // Check if error message is visible
                return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
            } catch (Exception ex) {
                // Log error and return a default message
                System.out.println("No success or error message found: " + ex.getMessage());
                return "No message displayed";
            }
        }
    }

}

