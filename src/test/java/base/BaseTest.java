package base;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;

import config.ConfigManager;
import driver.BrowserFactory;
import driver.DriverManager;
import utils.LoggerUtil;

public class BaseTest {

    protected Page page;

    protected ConfigManager configManager;

    protected Logger log =
            LoggerUtil.getLogger(getClass());

    @BeforeMethod
    public void setup() {

        configManager = new ConfigManager();

        BrowserFactory browserFactory =
                new BrowserFactory();

        page = browserFactory.createBrowser(
                configManager.getBrowser(),
                configManager.isHeadless());

        page.navigate(configManager.getBaseUrl());
        
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
        
        //configManager.testMethod();
    }
    
    
}