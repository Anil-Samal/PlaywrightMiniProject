package base;

import org.apache.logging.log4j.Logger;

import utils.LoggerUtil;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;

import factory.PlaywrightFactory;
import utils.ConfigReader;

public class BaseTest {

    protected Page page;

    protected PlaywrightFactory factory;

    protected ConfigReader config;
    
    protected Logger log = LoggerUtil.getLogger(getClass());

    @BeforeMethod
    public void setUp() {

        log.info("Loading configuration");

        config = new ConfigReader();

        log.info("Launching browser: {}", config.getBrowser());

        factory = new PlaywrightFactory();

        page = factory.initBrowser(
                config.getBrowser(),
                config.isHeadless());

        log.info("Navigating to URL: {}", config.getUrl());

        page.navigate(config.getUrl());

        log.info("Application loaded successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        log.info("Closing browser");

        if (factory != null) {
            factory.closeBrowser();
        }

        log.info("Browser closed");
    }
}