package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ui_tests.BaseUiTest;

public class TestNGListeners extends BaseUiTest implements ITestListener {
    static Logger log = LogManager.getLogger();

    @Override
    public void onTestStart(ITestResult result) {
        log.info(result.getName() + " test case started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("The name of the testcase passed is : "  + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.warn("The name of the testcase failed is : " + result.getName());
        try {
            failedScreenshot(result.getName());
            log.info("The screenshot of failed test is made");
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("The name of the testcase Skipped is : " + result.getName());
    }
}
