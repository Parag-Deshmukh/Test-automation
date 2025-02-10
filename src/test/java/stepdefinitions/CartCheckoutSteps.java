package stepdefinitions;

import java.util.Map;

import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.LoginPage;

public class CartCheckoutSteps {
    private final LoginPage loginPage;
    private final CartPage cartPage;

    public CartCheckoutSteps() {
        this.loginPage = new LoginPage(Hooks.driver);
        this.cartPage = new CartPage(Hooks.driver);
    }

    @Given("I am logged in as {string} with password {string}")
    public void login(String email, String password) {
        Hooks.driver.get("https://demowebshop.tricentis.com/login");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @When("I search for {string} and add to cart")
    public void searchAndAddToCart(String productName) {
        Hooks.driver.get("https://demowebshop.tricentis.com");
        cartPage.searchAndAddProduct(productName);
    }

    @When("I proceed to checkout with:")
    public void proceedToCheckout(DataTable dataTable) {
        Map<String, String> address = dataTable.asMap(String.class, String.class);
        cartPage.proceedToCheckout();
        cartPage.enterBillingAddress(address);
    }

    @When("I handle shipping and payment methods")
    public void handleShippingAndPaymentMethods() {
        cartPage.handleShippingAndPaymentSelection();
    }

    @When("I enter payment details:")
    public void enterPaymentDetails(DataTable dataTable) {
        Map<String, String> paymentDetails = dataTable.asMap(String.class, String.class);
        cartPage.enterPaymentDetails(paymentDetails);
    }

    @Then("I should see order confirmation {string}")
    public void verifyOrderConfirmation(String expectedMessage) {
        String confirmationMessage = cartPage.getOrderConfirmation();
        Assert.assertEquals(confirmationMessage, expectedMessage, "Order confirmation message mismatch.");
    }
}
