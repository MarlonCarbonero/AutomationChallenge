import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BookingAPI {

    private Object bookingId;
    private String expectedResponseBody; // Define it as a class-level variable
    private final String urlTest = "https://restful-booker.herokuapp.com";
    private static final Logger logger = LoggerFactory.getLogger(BookingAPI.class);


    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

    }

    @Test(priority = 1)
    //In this negative test scenario, we intentionally use an invalid JSON request payload

    public void createBookingWithInvalidJSON() {
        logger.info("Starting the createBookingWithInvalidJSON test...");

        // Define an invalid JSON request payload
        String invalidRequestBody = "{"
                + "\"firstname\": \"Marlon\","
                + "\"lastname\": \"Carbonero\""; // Missing closing brace

        // Perform the POST request to create a booking with invalid JSON
        Response response = given()
                .contentType(ContentType.JSON)
                .body(invalidRequestBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(400) // Expect a Bad Request status code for invalid JSON
                .extract()
                .response();

        // Assert that the response contains an error message about invalid JSON
        Assert.assertTrue(response.getBody().asString().contains("Bad Request"));

        logger.info("createBookingWithInvalidJSON test completed.");
    }

    @Test(priority = 2)
    public void createBooking() {
        logger.info("Starting the createBooking test...");

        // Define the request payload
        String requestBody = "{"
                + "\"firstname\": \"Marlon\","
                + "\"lastname\": \"Carbonero\","
                + "\"totalprice\": 100,"
                + "\"depositpaid\": true,"
                + "\"bookingdates\": {"
                + "    \"checkin\": \"2023-09-20\","
                + "    \"checkout\": \"2023-09-22\""
                + "},"
                + "\"additionalneeds\": \"Extra pillows\""
                + "}";

        // Perform the POST request to create a booking
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Verify the existence of the booking ID property in the response
        Assert.assertNotNull(response.getBody().jsonPath().get("bookingid"));

        Assert.assertEquals(response.getBody().jsonPath().get("booking.firstname"), "Marlon", "firstname response is incorrect");
        Assert.assertEquals(response.getBody().jsonPath().get("booking.lastname"), "Carbonero", "lastname response is incorrect");
        Assert.assertEquals((Integer) response.getBody().jsonPath().get("booking.totalprice"), 100, "totalprice response is incorrect");
        Assert.assertEquals(response.getBody().jsonPath().get("booking.depositpaid"), true, "depositpaid response is incorrect");
        Assert.assertEquals(response.getBody().jsonPath().get("booking.bookingdates.checkin"), "2023-09-20", "checkin response is incorrect");
        Assert.assertEquals(response.getBody().jsonPath().get("booking.bookingdates.checkout"), "2023-09-22", "checkout response is incorrect");
        Assert.assertEquals(response.getBody().jsonPath().get("booking.additionalneeds"), "Extra pillows", "additionalneeds response is incorrect");

        // Capture the booking ID for future use
        bookingId = response.getBody().jsonPath().get("bookingid");


        // Define the expected response body based on the data sent during booking creation
        expectedResponseBody = "{" +
                "\"firstname\":\"Marlon\"," +
                "\"lastname\":\"Carbonero\"," +
                "\"totalprice\":100," +
                "\"depositpaid\":true," +
                "\"bookingdates\":{" +
                "\"checkin\":\"2023-09-20\"," +
                "\"checkout\":\"2023-09-22\"" +
                "}," +
                "\"additionalneeds\":\"Extra pillows\"" +
                "}";
        logger.info("createBooking test completed.");

    }

    @Test(priority = 3)
    public void verifyBookingDetails() {
        logger.info("Starting the verifyBookingDetails test...");

        // Perform the GET request to retrieve the booking details using the booking ID
        Response getResponse = RestAssured
                .given()
                .when()
                .get("/booking/" + bookingId);

        //Assert the status code for the GET request
        Assert.assertEquals(getResponse.getStatusCode(), 200);

        // Assert deep equality of the response body against the expected body
        Assert.assertEquals(getResponse.getBody().asString(), expectedResponseBody);
        // Log the response status code
        logger.info("Response Status Code: {}", getResponse.getStatusCode());
        logger.info("verifyBookingDetails test completed.");

    }

    @Test(priority = 4)
    // Verifies that attempting to retrieve details of a non-existent booking returns a 404 status code.
    public void verifyNonExistentBookingDetails() {
        logger.info("Starting the verifyNonExistentBookingDetails test...");

        // Attempt to fetch details of a non-existent booking with an invalid booking ID
        String nonExistentBookingId = "9999"; // Replace with a non-existent booking ID

        // Perform the GET request to retrieve the non-existent booking details
        Response getResponse = RestAssured
                .given()
                .when()
                .get("/booking/" + nonExistentBookingId);

        // Verify that the GET request returns a 404 status code (Not Found)
        Assert.assertEquals(getResponse.getStatusCode(), 404);

        // Log the response status code
        logger.info("Response Status Code: {}", getResponse.getStatusCode());
        logger.info("verifyNonExistentBookingDetails test completed.");
    }

    @Test(priority = 5)
    public void updateAdditionalNeeds() {
        logger.info("Starting the updateAdditionalNeeds test...");

        // Define the request payload to update the "additionalneeds" property
        String updateRequestBody = "{" +
                "\"firstname\":\"Marlon\"," +
                "\"lastname\":\"Carbonero\"," +
                "\"totalprice\":100," +
                "\"depositpaid\":true," +
                "\"bookingdates\":{" +
                "\"checkin\":\"2023-09-20\"," +
                "\"checkout\":\"2023-09-22\"" +
                "}," +
                "\"additionalneeds\":\"no more pillows\"" + // Updated value
                "}";


        // Perform the PUT request to update the booking with the specified ID
        Response updateResponse = given()
                .contentType(ContentType.JSON)
                .accept("application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Cookie", "token=abc123")
                .body(updateRequestBody)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Verify that the booking was updated successfully
        Assert.assertEquals(updateResponse.getBody().asString(), updateRequestBody);

        // Perform a GET request to retrieve the updated booking details
        Response getResponse = RestAssured
                .given()
                .when()
                .get("/booking/" + bookingId);


        // Define the expected response body after the update
        String expectedResponseBody = "{" +
                "\"firstname\":\"Marlon\"," +
                "\"lastname\":\"Carbonero\"," +
                "\"totalprice\":100," +
                "\"depositpaid\":true," +
                "\"bookingdates\":{" +
                "\"checkin\":\"2023-09-20\"," +
                "\"checkout\":\"2023-09-22\"" +
                "}," +
                "\"additionalneeds\":\"no more pillows\"" + // Updated value
                "}";

        // Assert deep equality of the response body against the expected body
        Assert.assertEquals(getResponse.getBody().asString(), expectedResponseBody);
        // Log the response status code
        logger.info("Response Status Code: {}", updateResponse.getStatusCode());

        logger.info("updateAdditionalNeeds test completed.");


    }

    @Test(priority = 6)
    public void deleteBooking() {
        logger.info("Starting the deleteBooking test...");


        // Perform the DELETE request to delete the booking with the specified ID
        Response deleteResponse = given()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Cookie", "token=abc123")
                .when()
                .delete("/booking/" + bookingId);

        // Verify that the booking was deleted successfully (status code 201)
        Assert.assertEquals(deleteResponse.getStatusCode(), 201);

        // Perform a GET request to check if the booking exists
        Response getResponse = RestAssured
                .given()
                .when()
                .get("/booking/" + bookingId);

        // Verify that the booking no longer exists (status code 404)
        Assert.assertEquals(getResponse.getStatusCode(), 404);

        // Log the response status code after deletion check
        logger.info("Response Status Code (after deletion check): {}", getResponse.getStatusCode());
        logger.info("deleteBooking test completed.");

    }

    @Test(priority = 7)

    //This test case verifies the behavior of the 'deleteBooking' API endpoint when attempting to delete
    public void deleteNonExistentBooking() {
        logger.info("Starting the deleteNonExistentBooking test...");

        // Attempt to delete a booking with a non-existent booking ID
        String nonExistentBookingId = "99999"; // Replace with a non-existent booking ID

        // Perform the DELETE request to delete the non-existent booking
        Response deleteResponse = given()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Cookie", "token=abc123")
                .when()
                .delete("/booking/" + nonExistentBookingId);

        // Verify that the deletion attempt returns a 405 status code (Method Not Allowed)
        Assert.assertEquals(deleteResponse.getStatusCode(), 405);

        // Log the response status code
        logger.info("Response Status Code: {}", deleteResponse.getStatusCode());
        logger.info("deleteNonExistentBooking test completed.");
    }


}


