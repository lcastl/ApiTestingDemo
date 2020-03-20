package testcases;

import base.TestLogger;
import base.ApiController;
import org.junit.jupiter.api.Test;

public class GetTest extends TestLogger {

    @Test
    public void getWeatherDetails() {
        testOnStart();
        ApiController dispatcher = new ApiController();
        dispatcher.setEndpoint("endPointGet");
        dispatcher.consumeRestGet();
        dispatcher.obtainResponse();
        dispatcher.responseStatusShouldBe(200);
        testResultOnPass();
    }
}
