Feature: To automate the creation, log in and delation of an account in automationexercise

  Scenario: To be in "home" in automationexercise site

    Given the user launches the automationexercise site
    When the user clicks the home page option in the navbar
    Then Verify that home page is visible successfully
    When the user clicks on the Signup / Login button
    Then to Validate the ‘New User Sign up’ message is visible
        #Next line is a negative test
    Then Validate that the email field in the signup only accepts correct format emails
    And  Type in "name" and "email" on the Sign up Form
    And the user clicks on the Sign up button
    Then to validate the ‘ENTER ACCOUNT INFORMATION’ Message is visible
    Then Fill the details for the registration form
    And Validate the account created message being visible
    And Validate the -Logged in as username- being visible
    Then the user clicks on the button log out
    And Validate the Login to your account message is visible
    #Next line is a negative test
    Then Validate with incorrect data, that the login will not pass without correct email and password
    #Next line is a negative test
    And Validate that the email field in the login page has to have format AtDomaindotcom
    And enter the email and password user before to log in
    Then click on the Login button
    And validate that Logged in as username is visible
    Then Click on the Delete account button
    And Validate that ACCOUNT DELETED message is visible
