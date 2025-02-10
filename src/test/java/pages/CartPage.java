package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class CartPage extends BasePage {

    // Locators
    private final By searchInput = By.id("small-searchterms");
    private final By searchButton = By.cssSelector(".search-box-button");
    private final By addToCartButton = By.cssSelector("input[value='Add to cart']");
    private final By cartLink = By.linkText("Shopping cart");
    private final By termsCheckbox = By.id("termsofservice");
    private final By checkoutButton = By.id("checkout");
    private final By billingContinue = By.cssSelector("input[onclick='Billing.save()']");
    private final By shippingContinue = By.cssSelector("input[onclick='Shipping.save()']");
    private final By shippingMethodContinue = By.cssSelector("input[class='button-1 shipping-method-next-step-button']");
    private final By creditCardOption = By.xpath("//input[@id='paymentmethod_2']");
    private final By paymentMethodContinue = By.cssSelector("input[class='button-1 payment-method-next-step-button']");
    private final By cardholderName = By.id("CardholderName");
    private final By cardNumber = By.id("CardNumber");
    private final By expireMonth = By.id("ExpireMonth");
    private final By expireYear = By.id("ExpireYear");
    private final By cardCode = By.id("CardCode");
    private final By paymentInfoContinue = By.xpath("//input[@class='button-1 payment-info-next-step-button']");
    private final By confirmButton = By.cssSelector("input[value='Confirm']");
    private final By orderConfirmation = By.xpath("//strong[normalize-space()='Your order has been successfully processed!']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Searches for a product and adds it to the cart.
     * @param productName The name of the product to search for.
     */
    public void searchAndAddProduct(String productName) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        searchField.clear();
        searchField.sendKeys(productName);
        driver.findElement(searchButton).click();

        By productLocator = By.xpath(String.format("//h2[@class='product-title']/a[text()='%s']", productName));
        wait.until(ExpectedConditions.elementToBeClickable(productLocator)).click();

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addButton.click();
    }

    /**
     * Navigates to the checkout page from the shopping cart.
     */
    public void proceedToCheckout() {
        driver.findElement(cartLink).click();
        WebElement terms = wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox));
        terms.click();
        driver.findElement(checkoutButton).click();
    }

    /**
     * Fills out the billing address form and continues to the next step.
     * @param address A map containing billing address details.
     */
    public void enterBillingAddress(Map<String, String> address) {
        WebElement firstNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("BillingNewAddress_FirstName")));
        firstNameField.clear();
        firstNameField.sendKeys(address.get("FirstName"));
        
        WebElement lastNameField = driver.findElement(By.id("BillingNewAddress_LastName"));
        lastNameField.clear();
        lastNameField.sendKeys(address.get("LastName"));
        
        WebElement emailField = driver.findElement(By.id("BillingNewAddress_Email"));
        emailField.clear();
        emailField.sendKeys(address.get("Email"));
        
        new Select(driver.findElement(By.id("BillingNewAddress_CountryId"))).selectByVisibleText(address.get("Country"));
        
        WebElement cityField = driver.findElement(By.id("BillingNewAddress_City"));
        cityField.clear();
        cityField.sendKeys(address.get("City"));
        
        WebElement addressField = driver.findElement(By.id("BillingNewAddress_Address1"));
        addressField.clear();
        addressField.sendKeys(address.get("Address1"));
        
        WebElement zipCodeField = driver.findElement(By.id("BillingNewAddress_ZipPostalCode"));
        zipCodeField.clear();
        zipCodeField.sendKeys(address.get("ZipCode"));
        
        WebElement phoneField = driver.findElement(By.id("BillingNewAddress_PhoneNumber"));
        phoneField.clear();
        phoneField.sendKeys(address.get("Phone"));
        
        WebElement continueButton = driver.findElement(billingContinue);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    /**
     * Handles shipping and payment method selections.
     */
    public void handleShippingAndPaymentSelection() {
        WebElement shippingContinueButton = wait.until(ExpectedConditions.elementToBeClickable(shippingContinue));
        shippingContinueButton.click();

        WebElement shippingMethodButton = wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinue));
        shippingMethodButton.click();

        WebElement creditCardOptionElement = wait.until(ExpectedConditions.elementToBeClickable(creditCardOption));
        creditCardOptionElement.click();

        WebElement paymentContinueButton = wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinue));
        paymentContinueButton.click();
    }

    /**
     * Enters payment details and confirms the order.
     * @param paymentDetails A map containing payment details.
     */
    public void enterPaymentDetails(Map<String, String> paymentDetails) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardholderName)).sendKeys(paymentDetails.get("CardholderName"));
        driver.findElement(cardNumber).sendKeys(paymentDetails.get("CardNumber"));

        String[] expiration = paymentDetails.get("ExpirationDate").split("/");
        new Select(driver.findElement(expireMonth)).selectByVisibleText(expiration[0]);
        new Select(driver.findElement(expireYear)).selectByVisibleText(expiration[1]);

        driver.findElement(cardCode).sendKeys(paymentDetails.get("CVV"));
        driver.findElement(paymentInfoContinue).click();

        WebElement confirmOrderButton = driver.findElement(confirmButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmOrderButton);
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
    }

    /**
     * Retrieves the order confirmation message.
     * @return The order confirmation message text.
     */
    public String getOrderConfirmation() {
        WebElement confirmationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation));
        return confirmationElement.getText().trim();
    }
}