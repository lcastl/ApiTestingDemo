package testcases;

import base.TestLogger;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetTest extends TestLogger {

    @Test
    public void getWeatherDetails() {
        testOnStart();
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/Medellin");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is :" + responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assertions.assertEquals(statusCode, 200);

        String statusLine = response.getStatusLine();
        System.out.println("status line is: " + statusLine);
        Assertions.assertEquals(statusLine, "HTTP/1.1 200 OK");
        testResultOnPass();
    }
}
