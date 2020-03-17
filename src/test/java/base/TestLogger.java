package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public class TestLogger {

    public ExtentTest testLogger;
    public static ExtentReports extentReports;
    public boolean status;

    @BeforeEach
    public void startReport(){
        extentReports = new ExtentReports (System.getProperty("user.dir") +"/reports/suiteReport.html", true);
        extentReports
                .addSystemInfo("Host Name", "SoftwareTestingMaterial")
                .addSystemInfo("Environment", "Api Testing")
                .addSystemInfo("User Name", "Luis Castellanos");
        extentReports.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
    }

    public void testOnStart() {
        testLogger = extentReports.startTest("Test running");
    }

    public void testResultOnPass() {
        testLogger.log(LogStatus.PASS, "Test Case Passed");
    }

    public void testResultOnFail() {
        testLogger.log(LogStatus.FAIL, "Test Case Failed");
    }

    @AfterEach
    public void endReport() {
        extentReports.endTest(testLogger);
    }

    @AfterAll
    public static void closeReport() {
        extentReports.flush();
        extentReports.close();
    }
}

