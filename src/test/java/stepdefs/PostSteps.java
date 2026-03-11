package stepdefs;

import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import models.PostClient;
import models.PostModel;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class PostSteps {
    private final PostClient postClient = new PostClient("https://jsonplaceholder.typicode.com");
    private PostModel request;
    private PostModel response;

    @Given("I prepare a new post with title {string} and body {string} for user {int}")
    public void iPrepareAnewPost(String title, String body, int userID){
        log.info("STEP: Preparing post data - Title: {}, UserID: {}", title, userID);
        request = new PostModel(title, body, userID);
    }

    @When("I send a request to create the post")
    public void iSendARequestToCreateThePost(){
        response = postClient.createPost(request);
        log.info("STEP: Response received. ID: {}", response.getId());
    }

    @Then("the post should be created successfully with a valid ID")
    public void verifyIdExists() {
        log.info("STEP: Verifying that ID exists in response");
        assertNotNull(response.getId(), "Server should return a generated ID");
    }

    @And("the response should match the original post details")
    public void verifyResponseData() {
        log.info("STEP: Verifying Title and User ID in the response");
        assertEquals(request.getTitle(), response.getTitle(), "Title mismatch!");
        assertEquals(request.getUserId(), response.getUserId(), "User ID mismatch!");
        log.info("STEP: Verification successful!");
    }
}
