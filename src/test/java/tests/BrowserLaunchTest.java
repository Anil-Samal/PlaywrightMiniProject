//package tests;
//
//import org.testng.annotations.Test;
//
//import com.microsoft.playwright.Page;
//
//import factory.PlaywrightFactory;
//import utils.ConfigReader;
//
//public class BrowserLaunchTest {
//
//    @Test
//    public void verifyBrowserLaunch() {
//
//        ConfigReader config = new ConfigReader();
//
//        PlaywrightFactory factory = new PlaywrightFactory();
//
//        Page page = factory.initBrowser(
//                config.getBrowser(),
//                config.isHeadless());
//
//        page.navigate(config.getUrl());
//
//        System.out.println(page.title());
//
//        factory.closeBrowser();
//    }
//}