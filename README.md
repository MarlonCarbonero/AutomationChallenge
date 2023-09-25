# AutomationChallenge


*NOTES: 
-for web tests: As a find, slowness might be because of the ads that are shown, as in each screen that does not have ads, the process goes fast, but in the screens that have ads, the process gets slow
-For the warning below, selenium version 4.13.0 repairs (as mentioned in their github) but hasn't been released yet
WARNING: Unable to find an exact match for CDP version 117, so returning the closest version found: 116
ChallengeExercise is a Java-based automation project that utilizes Maven, Selenium WebDriver, TestNG, Cucumber, and Log4j2 to automate 
interactions with the Automation Exercise website.

For the best results, all projects in this repository should be run in Intellij, as this was the IDE where it was developed.

## Table of Contents

- [Project Description](#project-description)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started) 
 - [Prerequisites](#prerequisites) 
 - [Installation](#installation)
- [Running the Tests](#running-the-tests)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Project Description

This project aims to automate the creation, login, and deletion of user accounts on the Automation Exercise website. It utilizes various technologies and 
frameworks to achieve these automation tasks.

## Technologies Used

The following technologies and libraries are used in this project:
- Maven
- Selenium WebDriver
- TestNG
- Cucumber
- Log4j2

## Getting Started

Follow these steps to set up the project on your local machine.

### Prerequisites

Before you begin, ensure you have the following software installed:
- Java Development Kit (JDK)
- Maven
- Chrome WebDriver (Ensure it matches your Chrome browser version)

### Installation
Using Intellij:
?Clone the repository to your local machine:
 git clone https://github.com/your-username/ChallengeExercise.git

Navigate to the project directory:
?cd .\AutomationChallenge\

?It is a Maven project, right click on the project tree, and load it as a Maven project
?In the Maven window, reload the dependecies


For more help: https://www.jetbrains.com/idea/guide/tutorials/working-with-maven/importing-a-project/

Running the Tests
To run the automated tests, follow this:

Go to AutomationChallenge\src\main\java\runner and run Runner.java
This will run the tests, show logs in the console and at the end of the console will be the link to the report link

Project Structure

The project follows this directory structure:
ChallengeExercise/
¦
+- src/
¦   +- main/
¦   ¦   +- java/
¦   ¦   ¦   +- ...
¦   ¦   +- resources/
¦   +- test/
¦       +- java/
¦       ¦   +- ...
¦       +- resources/
¦           +- Features/
¦               +- ...
¦
+- target/
+- pom.xml
+- ...


src/main/java: Contains the main Java source code.
src/test/java: Contains the test automation code.
src/test/resources: Contains test-specific resources, including feature files for Cucumber tests.
target: Contains build artifacts (compiled code, reports, etc.).


# AutomationChallenge

API Testing with RestAssured and TestNG
This project demonstrates how to perform API testing using RestAssured and TestNG. It includes two test classes: BookingAPI for testing a booking API and 
PokeAPI for testing the PokeAPI. The tests cover various aspects of API testing, including HTTP request methods, response validation, and JSON schema validation.

Prerequisites
Before you can run the tests in this project, ensure you have the following prerequisites installed:

Java (JDK 11 or later)
Maven (for managing dependencies)

Project Structure

\src\test\java\: Contains the test classes BookingAPI and PokeAPI.
\src\test\resources\schema.json: JSON schema file used for JSON schema validation in the PokeAPI test.

Test Execution
1. Navigate to the project directory.
2. Find test-suite.xml and run this file so all the test run


Contributing
Contributions to this project are welcome. If you'd like to contribute, please follow these guidelines:
1.Fork the repository.
2.Create a new branch for your feature or bug fix.
3.Make your changes and commit them.
4.Push your changes to your fork.
5.Create a pull request to merge your changes into the main repository.

