package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationFormPage {
    WebDriver driver;
    HomePage home = new HomePage(driver);

    //register form fields
    @FindBy(xpath = "//b[text()='Enter Account Information']")
    WebElement registerFormText;
    @FindBy(xpath = "(//input[@name='title'])[2]")
    WebElement titleGender;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordRegistration;
    @FindBy(css = "select#days")
    WebElement dobDay;
    @FindBy(css = "select#months")
    WebElement dobMonth;
    @FindBy(css = "select#years")
    WebElement dobYear;
    @FindBy(id = "newsletter")
    WebElement acceptNewsletter;
    @FindBy(id = "optin")
    WebElement acceptOffers;
    @FindBy(xpath = "//label[text()='First name ']/following::input")
    WebElement firstnameRegistration;
    @FindBy(xpath = "//label[text()='Last name ']/following::input")
    WebElement lastnameRegistration;
    @FindBy(xpath = "//label[text()='Company']/following::input")
    WebElement companyRegistration;
    @FindBy(xpath = "//label[text()='Address ']/following::input")
    WebElement addressRegistration;
    @FindBy(xpath = "//label[text()='Address 2']/following::input")
    WebElement addressTwoRegistration;
    @FindBy(xpath = "//p[@class='required form-group']//select[1]")
    WebElement countryRegistration;
    @FindBy(xpath = "//label[text()='State ']/following::input")
    WebElement stateRegistration;
    @FindBy(xpath = "//input[@data-qa='city']")
    WebElement cityRegistration;
    @FindBy(xpath = "//label[text()='Zipcode ']/following::input")
    WebElement zipcodeRegistration;
    @FindBy(xpath = "//label[text()='Mobile Number ']/following::input")
    WebElement mobileNumberRegistration;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitRegistration;


    public RegistrationFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void startPage() {

    }

    public void fillRegisterForm() throws InterruptedException {
        // Select the title gender
        titleGender.click();
        // Enter a password
        passwordRegistration.sendKeys(home.passwordToBeUsed);


        try {
            // Initialize the Select object with the WebElement for dobDay
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Maximum wait time of 10 seconds
            Select select = new Select(wait.until(ExpectedConditions.visibilityOf(dobDay)));

            // Select "21" by visible text
            select.selectByVisibleText("21");

            // Initialize the Select object with the WebElement for dobMonth
            Select selectMonth = new Select(wait.until(ExpectedConditions.visibilityOf(dobMonth)));

            // Select the desired month by visible text
            selectMonth.selectByVisibleText("July"); // Replace with the month you want

            // Initialize the Select object with the WebElement for dobYear
            Select selectYear = new Select(wait.until(ExpectedConditions.visibilityOf(dobYear)));

            // Select the desired year by visible text
            selectYear.selectByVisibleText("1990");
        } catch (Exception e) {
            // Handle any exceptions or add appropriate error handling
        }

        // Check the newsletter and offers checkboxes
        acceptNewsletter.click();
        acceptOffers.click();

        // Enter personal information
        firstnameRegistration.sendKeys("Marlon");
        lastnameRegistration.sendKeys("Carbonero");
        companyRegistration.sendKeys("Freelancer");
        addressRegistration.sendKeys("Where a street is");
        addressTwoRegistration.sendKeys("just next to the other street");

        // Initialize the Select object for Country
        Select selectCountry = new Select(countryRegistration);
        // Select the desired country by visible text
        selectCountry.selectByVisibleText("Canada");

        stateRegistration.sendKeys("Ottawa");
        cityRegistration.sendKeys("InsideOttawa");
        zipcodeRegistration.sendKeys("K1A");
        mobileNumberRegistration.sendKeys("22577777");
        // Click the submit button to complete registration
        submitRegistration.click();


    }

    public void registerFormTextCheck() {
// Get the actual text of the element
        String actualText = registerFormText.getText();

        // Define the expected text
        String expectedText = "ENTER ACCOUNT INFORMATION"; // Replace with your expected text

        // Perform the assertion
        Assert.assertEquals(actualText, expectedText, "ENTER ACCOUNT INFORMATION' text is not as expected");
    }

}
