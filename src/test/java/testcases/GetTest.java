package testcases;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTest {

    @Test
    public void getWeatherDetails() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "/Medellin");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is :" + responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        assertEquals(statusCode, 200);

        String statusLine = response.getStatusLine();
        System.out.println("status line is: " + statusLine);
        assertEquals(statusLine, "HTTP/1.1 200 OK");
    }
}
