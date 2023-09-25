package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageFactory.HomePage;
import pageFactory.RegistrationFormPage;
import pageFactory.loginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;


public class MyStepDefsChallenge {
    public WebDriver driver;

    public HomePage home;
    public loginPage logPage;
    public RegistrationFormPage RegPage;
    private static final Logger logger = LogManager.getLogger(MyStepDefsChallenge.class);



    @Before
    public void initDriver() throws InterruptedException {
        // Specify the desired browser type ("chrome," "firefox," or "edge")
        String browserType = "chrome";

        // Initialize WebDriver for Chrome browser
        if (browserType.equalsIgnoreCase("chrome")) {
            // Initialize WebDriver for Chrome browser
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("edge")) {
            // Initialize WebDriver for Edge browser
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser type: " + browserType);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        // Initialize the 'home' object from the PageFactory
        home = new HomePage(driver);
        logPage = new loginPage(driver);
        RegPage = new RegistrationFormPage(driver);
    }

    @Given("the user launches the automationexercise site")
    public void the_user_launches_the_automationexercise_site() {
        driver.get("https://automationexercise.com/");
    }


    @When("the user clicks the home page option in the navbar")
    public void theUserClicksTheHomePageOptionInTheNavbar() {
        home.clickHome();
    }


    @Then("Verify that home page is visible successfully")
    public void verifyThatHomePageIsVisibleSuccessfully() {
        logger.info("Home page is successfully visible.");
        home.getCSSHomeButton();

    }

    @Then("to Validate the ‘New User Sign up’ message is visible")
    public void toValidateTheNewUserSignUpMessageIsVisible() {
        logPage.registerTextCheck();
        logger.info("New User Sign up message is visible.");

    }

    @And("Type in {string} and {string} on the Sign up Form")
    public void typeInAndOnTheSignUpForm(String arg0, String arg1) {
        logPage.registerNameEmail();
    }

    @And("the user clicks on the Sign up button")
    public void theUserClicksOnTheSignUpButton() {
    }

    @Then("to validate the ‘ENTER ACCOUNT INFORMATION’ Message is visible")
    public void toValidateTheENTERACCOUNTINFORMATIONMessageIsVisible() {
        RegPage.registerFormTextCheck();
        logger.info("ENTER ACCOUNT INFORMATION message is visible.");

    }


    @Then("Fill the details for the registration form")
    public void fillTheDetailsForTheRegistrationForm() throws InterruptedException {
        RegPage.fillRegisterForm();
        logger.info("Filled the registration form with user details.");

    }

    @And("Validate the -Logged in as username- being visible")
    public void validateTheLoggedInAsUsernameBeingVisible() {

    }

    @And("Validate the account created message being visible")
    public void validateTheAccountCreatedMessageBeingVisible() throws InterruptedException {
        home.checkAccountCreated();
        home.continueAfterRegistration();
        logger.info("Account created message is visible.");

    }

    @When("the user clicks on the Signup \\/ Login button")
    public void theUserClicksOnTheSignupLoginButton() {
        home.clickLoginRegisterButton();
        logger.info("Clicked on the Signup / Login button.");

    }

    @Then("the user clicks on the button log out")
    public void theUserClicksOnTheButtonLogOut() {
        home.clickLogoutButton();
        logger.info("Clicked on the Log Out button.");

    }

    @And("Validate the Login to your account message is visible")
    public void validateTheLoginToYourAccountMessageIsVisible() {
        home.validateLoginText();

    }

    @And("enter the email and password user before to log in")
    public void enterTheEmailAndPasswordUserBeforeToLogIn() {
        logPage.login();
    }

    @Then("click on the Login button")
    public void clickOnTheLoginButton() {
        logPage.clickLoginButton();
    }

    @And("validate that Logged in as username is visible")
    public void validateThatLoggedInAsUsernameIsVisible() {
        home.checkLoggedInUsername();
    }

    @Then("Click on the Delete account button")
    public void clickOnTheDeleteAccountButton() throws InterruptedException {
        home.clickDeleteAccountButton();
        logger.info("Clicked on the Delete Account button.");

    }

    @And("Validate that ACCOUNT DELETED message is visible")
    public void validateThatACCOUNTDELETEDMessageIsVisible() {
        home.verifyAccountDeleteText();
        logger.info("Validated that 'ACCOUNT DELETED' message is visible.");

    }

    @Then("Validate with incorrect data, that the login will not pass without correct email and password")
    public void validateWithIncorrectDataThatTheLoginWillNotPassWithoutCorrectEmailAndPassword() {
        logPage.checkLoginIncorrectData();
    }

    @And("Validate that the email field in the login page has to have format AtDomaindotcom")
    public void validateThatTheEmailFieldInTheLoginPageHasToHaveFormatAtDomaindotcom() {
        logPage.checkEmailStructure();
    }

    @Then("Validate that the email field in the signup only accepts correct format emails")
    public void validateThatTheEmailFieldInTheSignupOnlyAcceptsCorrectFormatEmails() {
        logPage.validateNewUserSignupEmail();
    }

        @After
    public void takeScreenshot(Scenario scenario) {
        // Capture a screenshot in case of test failure and attach it to the scenario
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            logger.info("Captured and attached a screenshot due to test failure.");

        } else {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            logger.info("Captured and attached a screenshot.");

        }
        // Close the browser window
        driver.close();
        // Quit the WebDriver instance
        driver.quit();
        logger.info("Closed the browser and quit the WebDriver instance.");

    }
}
