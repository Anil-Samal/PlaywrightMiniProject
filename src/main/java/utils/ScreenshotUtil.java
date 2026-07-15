package utils;


import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.microsoft.playwright.Page;

public class ScreenshotUtil {

//    public static void captureScreenshot(Page page, String fileName) {
//
//        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots/" + fileName + ".png")).setFullPage(true));

	    public static void captureScreenshot(Page page, String testName) {

	        String timeStamp = LocalDateTime.now()
	                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

	        String fileName = testName + "_" + timeStamp + ".png";

	        page.screenshot(new Page.ScreenshotOptions()
	                .setPath(Paths.get("screenshots/" + fileName))
	                .setFullPage(true));
	    }

}
