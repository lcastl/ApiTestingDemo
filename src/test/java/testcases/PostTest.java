package testcases;

import base.ApiController;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.WebServicesConsumer;

public class PostTest {

    ApiController dispatcher = new ApiController();

    @BeforeClass
    public void userRegistration() throws InterruptedException {
        dispatcher.logger.onTestStart("TC02");
        dispatcher.setEndpoint("endPointPost");
        dispatcher.setHeader("Content-type", "application/json; charset=UTF-8");
        dispatcher.setBodyToPostString("FirstName", "usuario");
        dispatcher.setBodyToPostString("LastName", "usuario");
        dispatcher.setBodyToPostString("UserName", "usercorreo9979");
        dispatcher.setBodyToPostString("Password", "user2223");
        dispatcher.setBodyToPostString("Email", "correo9979@correo.com");
        dispatcher.consumeRestPost();
        dispatcher.obtainResponse();
    }

    @Test
    public void validateStatusCode() {
        dispatcher.responseStatusShouldBe(201);
        dispatcher.singleEntryMapString("postResponse");
    }
    @Test
    public void validateResponseBodySingleParam() {
        dispatcher.singleEntryMapString("postResponse");
    }

    @AfterClass
    public void tearDown() {
        dispatcher.logger.onTestFinish("TC02");
    }
}
