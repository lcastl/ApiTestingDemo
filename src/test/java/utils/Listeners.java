package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    public void onStart(ITestContext testContext) {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/suiteReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("API Testing Rest Assured Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Project name", "Smile Builder");
        extentReports.setSystemInfo("Host name", "localhost");
        extentReports.setSystemInfo("Environment", "SQA");
        extentReports.setSystemInfo("user", "lui.castellanos");
    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.PASS, "Test case PASSED IS " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
        extentTest.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.SKIP, "Test case SKIPPED IS " + result.getName());
    }

    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();
    }
}