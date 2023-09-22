import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class PokeAPI {

    private static final Logger logger = LoggerFactory.getLogger(BookingAPI.class);

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://pokeapi.co/api/v2/berry/";
    }

    @Test(priority = 1)
    public void getBerries() {
        logger.info("Starting the getBerries test...");

        // Send a GET request
        Response response = RestAssured.given()
                .when()
                .get();

        // Validate the status code (should be 200 OK)
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            logger.info("Status code validation passed (200 OK)");
        } else {
            logger.error("Status code validation failed. Expected 200, but got " + statusCode);
            Assert.assertEquals(statusCode, 200, "Status code is not 200");
        }

        // Validate that the response body is not empty
        String responseBody = response.getBody().asString();
        if (responseBody != null && !responseBody.isEmpty()) {
            logger.info("Response body validation passed (not empty)");
        } else {
            logger.error("Response body validation failed. Response body is empty.");
            Assert.assertTrue(responseBody != null && !responseBody.isEmpty(), "Response body is empty");
        }

        // Define the JSON schema file path (modify it based on your schema location)
        File schemaFile = new File("src/test/resources/schema.json");

        // Perform JSON schema validation
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaFile));

        logger.info("JSON schema validation completed.");

        logger.info("getBerries test completed.");
    }

    @Test(priority = 2)
    //In this negative test scenario, we intentionally set an invalid base URI ("https://pokeapi.co/api/v2/invalid_path/")
    // to simulate a request to a non-existent endpoint. The test checks for a 404 Not Found status code and ensures that the response body is empty for
    // this invalid request. This negative test will help verify how your code handles invalid API endpoints.

    public void getInvalidBerries() {
        logger.info("Starting the getInvalidBerries test...");

        // Set an invalid base URI to simulate a negative test
        RestAssured.baseURI = "https://pokeapi.co/api/v2/invalid_path/";

        // Send a GET request to an invalid path
        Response response = RestAssured.given()
                .when()
                .get();

        // Validate the status code (should be 404 Not Found)
        int statusCode = response.getStatusCode();
        if (statusCode == 404) {
            logger.info("Status code validation passed (404 Not Found)");
        } else {
            logger.error("Status code validation failed. Expected 404, but got " + statusCode);
            Assert.assertEquals(statusCode, 404, "Status code is not 404");
        }


        logger.info("getInvalidBerries test completed.");
    }
}







