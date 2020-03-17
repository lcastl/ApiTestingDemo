package testcases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {

    @Test
    public void userRegistration() {
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Luiss");
        requestParams.put("LastName", "Castellanoss");
        requestParams.put("UserName", "lcastellanoss");
        requestParams.put("Password", "lcastellxcvb");
        requestParams.put("Email", "lcastellanosas@hotmail.com");

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
    }
}
