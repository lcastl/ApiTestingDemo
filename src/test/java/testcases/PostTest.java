package testcases;

import base.ApiController;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.WebServicesConsumer;

public class PostTest extends WebServicesConsumer {

    ApiController dispatcher = new ApiController();

    @BeforeClass
    public void userRegistration() throws InterruptedException {
        logger.onTestStart("TC02");
        setEndpoint("http://restapi.demoqa.com/customer/register");
        setHeader("Content-type", "application/json; charset=UTF-8");
        setBodyToPostString("FirstName", "usuario");
        setBodyToPostString("LastName", "usuario");
        setBodyToPostString("UserName", "usercorreo9919");
        setBodyToPostString("Password", "user2223");
        setBodyToPostString("Email", "correo9919@correo.com");
        consumeRestPost();
        obtainResponse();
    }

    @Test
    public void validateStatusCode() {
        responseStatusShouldBe(201);
        dispatcher.singleEntryMapString();
    }

    @Test
    public void validateResponseBodySingleParam() {
        dispatcher.singleEntryMapString();
    }

    @AfterClass
    public void tearDown() {
        logger.onTestFinish("TC02");
    }
}
