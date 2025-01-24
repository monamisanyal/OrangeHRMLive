package Orangehrmlive.testcases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class TestAPIAuomation {
	@Test
	public void getUsers() {
		// Given: Base URL and the request details
        given()
            .baseUri("https://jsonplaceholder.typicode.com") // base URI
            .log().all()  // Logs the request for debugging (optional)
        // When: Make the GET request to /users endpoint
        .when()
            .get("/users")
        // Then: Validate the response
        .then()
            .statusCode(200)  // Check that the status code is 200 (OK)
            .body("size()", greaterThan(0))  // Assert the response has more than 0 users
            .body("[0].name", equalTo("Leanne Graham"))  // Check the name of the first user
            .log().all();  // Logs the response for debugging (optional)
		
	}
	

}
