package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Random;

public class loginPage {

    WebDriver driver;
    HomePage home = new HomePage(driver);

    //Register fields and button
    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement newName;
    @FindBy(xpath = "//input[@data-qa='signup-name']/following-sibling::input[1]")
    WebElement newEmail;
    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement registerButton;

    //email and password to log in
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailToLogin;
    @FindBy(xpath = "//input[@data-qa='login-password']")
    WebElement passwordToLogin;
    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement loginButton;

    //New User Signup text
    @FindBy(xpath = "//h2[text()='New User Signup!']")
    WebElement newUserSignupText;

    @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
    WebElement emailpasswordIncorrect;
    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    WebElement emailAlreadyExist;

    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void startPage() {

    }

    public void registerNameEmail() {
        // Enter name and email for registration
        newName.sendKeys(home.usernameLogged);
        newEmail.sendKeys(home.emailToBeUsed);
        registerButton.click();

        try {
            // Attempt to locate the new element
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Maximum wait time of 10 seconds
            WebElement elementWithText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Email Address already exist!']")));

            // If the element is found, perform actions on it
            // Generate three random numbers
            Random rand = new Random();
            int randomNumber1 = rand.nextInt(10);
            int randomNumber2 = rand.nextInt(10);
            int randomNumber3 = rand.nextInt(10);

            // Modify the emailToBeUsed property
            home.emailToBeUsed = home.emailBeforeAt + randomNumber1 + randomNumber2 + randomNumber3 + home.emailAfterAt;
            newEmail.clear();
            newEmail.sendKeys(home.emailToBeUsed);
            registerButton.click();

            // Continue with other test steps

        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Continue with other test steps or take appropriate action
        }
    }




    public void registerTextCheck() {
        // Get the actual text of the element
        String actualText = newUserSignupText.getText();

        // Define the expected text
        String expectedText = "New User Signup!"; // Replace with your expected text

        // Perform the assertion
        Assert.assertEquals(actualText, expectedText, "The 'New User Signup!' text is not as expected");
    }

    public void login() {
        // Enter the saved email and password for login
        emailToLogin.sendKeys(home.emailToBeUsed);
        passwordToLogin.sendKeys(home.passwordToBeUsed);

    }

    public void clickLoginButton() {
        // Click the login button to log into the account
        loginButton.click();
    }

    public void checkLoginIncorrectData() {
        emailToLogin.sendKeys("noexistentmmmmasmdaosdnaoxdcaisn@gmail.com");
        passwordToLogin.sendKeys("123123!");
        loginButton.click();
        // Perform the assertion
        String actualTextemailPasswordInc = emailpasswordIncorrect.getText();
        Assert.assertEquals(actualTextemailPasswordInc, "Your email or password is incorrect!", " text is not as expected");
        emailToLogin.clear();
        passwordToLogin.clear();
    }

    public void checkEmailStructure() {

        String[] testEmails = {
                "tq112135t23",
                "tq112135t23@",
                "tq112135t23@domain",
                "tq112135t23@.com",
                "tq112135t23.email@domain@.com"
        };
        passwordToLogin.sendKeys(home.passwordToBeUsed);

        for (String email : testEmails) {

            loginButton.click();
            emailToLogin.clear();
            emailToLogin.sendKeys(email);
            loginButton.click();

        }

        emailToLogin.clear();
        passwordToLogin.clear();

    }

    public void validateNewUserSignupEmail(){
        String[] testEmails = {
                "tq112135t23",
                "tq112135t23@",
                "tq112135t23@domain",
                "tq112135t23@.com",
                "tq112135t23.email@domain@.com"
        };
        newName.sendKeys("test");

        for (String email : testEmails) {

            registerButton.click();
            emailToLogin.clear();
            emailToLogin.sendKeys(email);
            loginButton.click();

        }
        newName.clear();
    }
}
