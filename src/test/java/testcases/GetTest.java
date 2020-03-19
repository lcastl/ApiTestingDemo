package testcases;

import base.TestLogger;
import base.ApiController;
import org.junit.Test;

public class GetTest extends TestLogger {

    @Test
    public void getWeatherDetails() {
        testOnStart();
        ApiController dispatcher = new ApiController();
        dispatcher.setEndpoint("http://restapi.demoqa.com/utilities/weather/city/Medellin");
        dispatcher.consumeRestGet();
        dispatcher.obtainResponse();
        dispatcher.responseStatusShouldBe(200);
        testResultOnPass();
    }
}
