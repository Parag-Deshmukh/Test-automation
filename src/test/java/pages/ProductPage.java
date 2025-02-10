package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage extends BasePage {
    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.cssSelector("input[value='Search']");
    private By productResult = By.cssSelector(".product-item"); // Generic product result locator
    private By noResultsMessage = By.cssSelector(".no-result");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void searchForProduct(String productName) {
        // Enter product name and click search
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public boolean isProductDisplayed(String productName) {
        // Check if the product is visible in results
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[@class='product-title']/a[text()='" + productName + "']")
            )).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoResultsMessage() {
        // Get the "no products found" message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsMessage)).getText();
    }
}