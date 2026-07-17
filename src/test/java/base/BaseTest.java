package base;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;

import driver.BrowserFactory;
import driver.DriverManager;
import utils.ConfigReader;
import utils.LoggerUtil;

public class BaseTest {

    protected Page page;

    protected ConfigReader config;

    protected Logger log =
            LoggerUtil.getLogger(getClass());

    @BeforeMethod
    public void setup() {

        config = new ConfigReader();

        BrowserFactory browserFactory =
                new BrowserFactory();

        page = browserFactory.createBrowser(
                config.getBrowser(),
                config.isHeadless());

        page.navigate(config.getUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (DriverManager.getContext() != null) {
            DriverManager.getContext().close();
        }

        if (DriverManager.getBrowser() != null) {
            DriverManager.getBrowser().close();
        }

        if (DriverManager.getPlaywright() != null) {
            DriverManager.getPlaywright().close();
        }

        DriverManager.unload();
    }
}