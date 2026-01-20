package tests;

import models.PostModel;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiTest {
    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);
    private static String BASE_URL;
    private static final String POSTS_ENDPOINT = "/posts";

    @BeforeAll
    static void setUp(){
        //Set variable 'environment.file' for maven
        String environmentFileName = System.getProperty("environment.file", "dev.properties");

        //Read properties file
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("target/test-classes/" + environmentFileName)) {
            properties.load(fileInputStream);

            //Set BASE_URL using the value from properties file
            BASE_URL = properties.getProperty("baseUrl");
            //System.out.println("API Tests will run against: " + BASE_URL);
            log.info("API Tests will run against: {}", BASE_URL);
        } catch (IOException e) {
            //System.err.println("Could not load properties file" + environmentFileName);
            log.info("Could not load properties file: {}", environmentFileName);
        }

    }

    //Test case to verify successful creation of a new post resource using POJO
    @Test
    void shouldCreateNewPostUsingPojoSuccessfully() {
        //Create the POJO object (PostModel) which will be sent as the request body
        PostModel requestPayLoad = new PostModel(
                "Test Automation Assigment",
                "Testing API resource creation with Rest Assured and POJO.",
                101
        );
        //Execution and Validation by Rest Assured
        PostModel responseBody = given()                            //Set the base parameters for the request
                .baseUri(BASE_URL)
                .contentType("application/json")                 // Specify that the request body is in JSON format
                .body(requestPayLoad)                               // Rest Assured automatically serializes the requestPayload object to JSON
                .when()                                             //Execute the POST request to the specified endpoint
                .post(POSTS_ENDPOINT)
                .then()                                             // Assertions on the server response
                .statusCode(201).body("id", notNullValue())    // Data Validation (Hamcrest Matchers): Ensure the response contains a generated 'id'
                .extract().as(PostModel.class);                     // Deserialize the JSON response body back into a PostModel object

        //Assert that the server successfully generated an ID
        assertNotNull(responseBody.getId(), "Generated ID should not be null in the response.");

        //Assert that the title we sent matches the title returned in the response
        assertEquals(requestPayLoad.getTitle(), responseBody.getTitle(), "Returned title must match the sent title.");

        //Assert that the userId was also correctly echoed back
        assertEquals(requestPayLoad.getUserId(), responseBody.getUserId(), "Returned userId must match the sent userId");
    }
}
