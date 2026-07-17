package base;

import java.io.File;
import java.nio.file.Paths;

import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;

import factory.PlaywrightFactory;
import utils.ConfigReader;
import utils.LoggerUtil;

public class BaseTest {

    protected Page page;

    protected PlaywrightFactory factory;

    protected ConfigReader config;
    
    protected Logger log = LoggerUtil.getLogger(getClass());
    
    protected static Page currentPage;

    @BeforeMethod
    public void setUp() {

        log.info("Loading configuration");

        config = new ConfigReader();

        log.info("Launching browser: {}", config.getBrowser());

        factory = new PlaywrightFactory();

        page = factory.initBrowser(config.getBrowser(), config.isHeadless());
        factory.getContext().tracing().start(
        	    new Tracing.StartOptions()
        	        .setScreenshots(true)
        	        .setSnapshots(true)
        	        .setSources(true)
        	);
        currentPage = page;

        log.info("Navigating to URL: {}", config.getUrl());

        page.navigate(config.getUrl());

        log.info("Application loaded successfully");
    }
    
    public static Page getPage() {
        return currentPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        try {
            if (result.getStatus() == ITestResult.FAILURE) {

                new File("traces").mkdirs();

                factory.getContext().tracing().stop(
                    new Tracing.StopOptions()
                        .setPath(Paths.get(
                            "traces/"
                            + result.getMethod().getMethodName()
                            + "_"
                            + System.currentTimeMillis()
                            + ".zip"))
                );

            } else {

                factory.getContext().tracing().stop();
            }

        } finally {

            if (factory != null) {
                factory.closeBrowser();
            }
        }
    }
}