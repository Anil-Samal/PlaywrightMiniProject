package listeners;

import driver.DriverManager;

import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static final Logger log =
            LoggerUtil.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {

        log.info("Starting Test : {}", result.getMethod().getMethodName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        log.info("Test Passed : {}", result.getMethod().getMethodName());

    }

    @Override
    public void onTestFailure(ITestResult result) {

        log.error("========================================");
        log.error("Test Failed : {}", result.getMethod().getMethodName());

        String path = ScreenshotUtil.captureScreenshot(
                DriverManager.getPage(),
                result.getMethod().getMethodName());

        log.error("Screenshot saved at : {}", path);
        log.error("========================================");

    }
}