package base;

import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;

import factory.PlaywrightFactory;
import utils.ConfigReader;
import utils.ScreenshotUtil;

    public class BaseTest {

        protected Page page;

        protected PlaywrightFactory factory;

        @BeforeMethod

        public void setup(){

            factory=new PlaywrightFactory();

//            page=factory.initBrowser("chromium");
//            page.navigate("https://opensource-demo.orangehrmlive.com/");
            page = factory.initBrowser(
                    ConfigReader.getProperty("browser"));

            page.navigate(
                    ConfigReader.getProperty("url"));

        }
//        @AfterMethod
//        public void tearDown(ITestResult result){
//            if(result.getStatus()==ITestResult.FAILURE){
//                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/"+result.getName()+".png")).setFullPage(true));
//                page.context().tracing().stop(new Tracing.StopOptions().setPath(Paths.get("traces/"+result.getName()+".zip")));
//            }
//        }
        @AfterMethod(alwaysRun = true)
        public void tearDown(ITestResult result) {

            try {

                if (result.getStatus() == ITestResult.FAILURE) {

//                    page.screenshot(
//                            new Page.ScreenshotOptions()
//                                    .setPath(Paths.get("screenshots/" + result.getName() + ".png"))
//                                    .setFullPage(true));
                	ScreenshotUtil.captureScreenshot(page, result.getName());

                    page.context().tracing().stop(
                            new Tracing.StopOptions()
                                    .setPath(Paths.get("traces/" + result.getName() + ".zip")));
                } else {
                    // Stop tracing without saving for passed tests
                    page.context().tracing().stop();
                }

            } finally {
                factory.closeBrowser();
        }

    }
}