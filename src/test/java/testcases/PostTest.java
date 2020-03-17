package testcases;

import base.ApiController;
import org.junit.jupiter.api.Test;

public class PostTest {

    @Test
    public void userRegistration() {
        ApiController dispacher = new ApiController();

        dispacher.setEndpoint("http://restapi.demoqa.com/customer/register");
        dispacher.setHeader("Content-type", "application/json; charset=UTF-8");
        dispacher.setBodyToPostString("FirstName", "usuario");
        dispacher.setBodyToPostString("LastName", "usuario");
        dispacher.setBodyToPostString("UserName", "usercorreo9909");
        dispacher.setBodyToPostString("Password", "user2223");
        dispacher.setBodyToPostString("Email", "correo9909@correo.com");
        dispacher.consumeRestPost();
        dispacher.obtainResponse();
        dispacher.responseStatusShouldBe(201);
        dispacher.singleEntryMapString();
        /*
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Luiss");
        requestParams.put("LastName", "Castellanoss");
        requestParams.put("UserName", "lcastellanoss1");
        requestParams.put("Password", "lcastellxcvb");
        requestParams.put("Email", "lcastellanosas1@hotmail.com");

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());

        Response response = httpRequest.request(Method.POST, "/register");

        String responseBody = response.getBody().asString(); //attach above data to the request
        System.out.println("Response Body is :" + responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);

        String successCode = response.jsonPath().get("SuccessCode");
        System.out.println("success code is: " + successCode);
        assertEquals(successCode, "OPERATION_SUCCESS");

         */
    }
}
