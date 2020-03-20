package testcases;

import org.testng.annotations.*;
import utils.WebServicesConsumer;

public class GetTest extends WebServicesConsumer {

    @BeforeClass
    public void getWeatherDetails() throws InterruptedException {
        logger.onTestStart("TC01");
        setEndpoint("endPointGet");
        consumeRestGet();
        obtainResponse();
    }

    @Test()
    public void validateStatusCode() {
        responseStatusShouldBe(200);
    }

    @Test

    public void validateResponseBodySingleParam() {
        System.out.println(response.getBody().asString());
    }

    @AfterTest
    public void tearDown() {
        logger.onTestFinish("TC01");
    }
}
