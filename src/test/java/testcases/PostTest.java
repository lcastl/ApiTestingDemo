package testcases;

import base.TestLogger;
import org.junit.jupiter.api.Test;
import base.ApiController;

public class PostTest extends TestLogger {

    @Test
    public void userRegistration() {
        ApiController dispatcher = new ApiController();

        dispatcher.setEndpoint("http://restapi.demoqa.com/customer/register");
        dispatcher.setHeader("Content-type", "application/json; charset=UTF-8");
        dispatcher.setBodyToPostString("FirstName", "usuario");
        dispatcher.setBodyToPostString("LastName", "usuario");
        dispatcher.setBodyToPostString("UserName", "usercorreo9909");
        dispatcher.setBodyToPostString("Password", "user2223");
        dispatcher.setBodyToPostString("Email", "correo9909@correo.com");
        dispatcher.consumeRestPost();
        dispatcher.obtainResponse();
        dispatcher.responseStatusShouldBe(201);
        dispatcher.singleEntryMapString();
        testResultOnFail();
    }
}
