package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;

import factory.PlaywrightFactory;
import utils.ConfigReader;

public class BaseTest {

    protected Page page;

    protected PlaywrightFactory factory;

    protected ConfigReader config;

    @BeforeMethod
    public void setUp() {

        config = new ConfigReader();

        factory = new PlaywrightFactory();

        page = factory.initBrowser(
                config.getBrowser(),
                config.isHeadless());

        page.navigate(config.getUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (factory != null) {
            factory.closeBrowser();
        }
    }
}