package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    WebDriver driver;

    //All the selectors will be here

    //Home button located in the nav bar
    @FindBy(xpath = "//header[@id='header']/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[1]/a[1]")
    WebElement homeButton;

    //the login/register button located in the nav bar
    @FindBy(xpath = "//a[@href='/login']")
    WebElement loginRegisterButton;

    //text after account creation
    @FindBy(xpath = "//b[text()='Account Created!']")
    WebElement accountCreatedText;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement continueAfterRegistrationButton;

    //home logged in as
    @FindBy(xpath = "//header[@id='header']/div[1]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[10]/a[1]")
    WebElement loggedInUsername;

    //Logout button
    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutButton;

    //Login to the account screen
    @FindBy(xpath = "//h2[text()='Login to your account']")
    WebElement loginText;


    //Delete account button
    @FindBy(xpath = "//a[@href='/delete_account']")
    WebElement deleteAccountButton;

    //account deleted text confirmation
    @FindBy(xpath = "//b[text()='Account Deleted!']")
    WebElement accountDeletedText;

    //created to store the user,email and password in the future
    public String usernameLogged = "MarlonChallenge";
    public String emailToBeUsed = "tq112135t23@gmail.com";
    public String passwordToBeUsed = "JustTryingMyBest";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void startPage() {

    }

    public void clickHome() {
        // Click on the Home button in the navigation bar
        homeButton.click();
    }

    public void getCSSHomeButton() {
        // Get the CSS property value of the Home button
        String actualValue = homeButton.getCssValue("color");
        // Define the expected value
        String expectedValue = "rgba(255, 165, 0, 1)"; // Replace with the expected CSS property value
        // Assert that the actual value matches the expected value
        Assert.assertEquals(actualValue, expectedValue, "The home button is not selected");
    }

    public void clickLoginRegisterButton() {
        // Click on the Login/Register button
        loginRegisterButton.click();
    }

    public void checkAccountCreated() {
        // Get the actual text of the 'ACCOUNT CREATED!' element
        String actualText = accountCreatedText.getText();

        // Define the expected text
        String expectedText = "ACCOUNT CREATED!"; // Replace with your expected text

        // Perform the assertion
        Assert.assertEquals(actualText, expectedText, "ACCOUNT CREATED!' text is not as expected");
    }

    public void continueAfterRegistration() throws InterruptedException {
        // Click the 'Continue' button after registration
        continueAfterRegistrationButton.click();

        driver.get("https://automationexercise.com/");
    }

    public void checkLoggedInUsername() {
        // Assert that the logged-in username matches the previously saved username
        String userText = loggedInUsername.getText();
        Assert.assertEquals(userText, "Logged in as " + usernameLogged, "the username logged in is not the same we used");
    }

    public void clickLogoutButton() {
        // Click the logout button to log out of the account
        logoutButton.click();
    }

    public void validateLoginText() {

        // Get the actual text of the 'Login to your account' element
        String actualText = loginText.getText();

        // Define the expected text
        String expectedText = "Login to your account"; // Replace with your expected text

        // Perform the assertion
        Assert.assertEquals(actualText, expectedText, "Login to your account' text is not as expected");
    }


    public void clickDeleteAccountButton() throws InterruptedException {
        // Click the delete account button to delete the account
        deleteAccountButton.click();
        Thread.sleep(3000);
    }

    public void verifyAccountDeleteText() {
        // Get the actual text of the 'ACCOUNT DELETED!' element
        String actualText = accountDeletedText.getText();

        // Define the expected text
        String expectedText = "ACCOUNT DELETED!"; // Replace with your expected text

        // Perform the assertion
        Assert.assertEquals(actualText, expectedText, "ACCOUNT DELETED! text is not as expected");
    }
}

