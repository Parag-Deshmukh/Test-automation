//package stepdefinitions;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import pages.LoginPage;
//import pages.ProductPage;
//
//public class ProductSearchSteps  {
//    private WebDriver driver;
//    private LoginPage loginPage;
//    private ProductPage productPage;
//
//    @Given("I am logged in as {string} with password {string}")
//    public void login(String email, String password) {
//        // Initialize WebDriver and log in
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://demowebshop.tricentis.com/login");
//        loginPage = new LoginPage(driver);
//        loginPage.enterEmail(email);
//        loginPage.enterPassword(password);
//        loginPage.clickLogin();
//        productPage = new ProductPage(driver);
//    }
//
//    @When("I search for {string}")
//    public void searchForProduct(String productName) {
//        // Perform the search
//        productPage.searchForProduct(productName);
//    }
//
//    @Then("I should see {string} in results")
//    public void verifyProductInResults(String productName) {
//        // Verify the product is displayed
//        Assert.assertTrue(
//            productPage.isProductDisplayed(productName),
//            "Product '" + productName + "' not found in results."
//        );
//    }
//
//    @Then("I should see the message {string}")
//    public void verifyNoResultsMessage(String expectedMessage) {
//        // Verify the "no products" message
//        String actualMessage = productPage.getNoResultsMessage();
//        Assert.assertTrue(
//            actualMessage.contains(expectedMessage),
//            "Expected message: " + expectedMessage + ", but found: " + actualMessage
//        );
//    }
//
//    @Then("close the browser")
//    public void closeBrowser() {
//        // Close the browser
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}