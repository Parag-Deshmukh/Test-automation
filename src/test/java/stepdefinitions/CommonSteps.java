//package stepdefinitions;
//
//import io.cucumber.java.en.Given;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import pages.LoginPage;
//
//public class CommonSteps {
//    protected WebDriver driver;
//    protected LoginPage loginPage;
//
//    @Given("I am logged in as {string} with password {string}")
//    public void login(String email, String password) {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://demowebshop.tricentis.com/login");
//        loginPage = new LoginPage(driver);
//        loginPage.enterEmail(email);
//        loginPage.enterPassword(password);
//        loginPage.clickLogin();
//    }
//
//    @Given("close the browser")
//    public void closeBrowser() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}