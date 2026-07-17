package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.playwright.Page;

public class ScreenshotUtil {

    public static String captureScreenshot(Page page, String testName) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        String folder = "screenshots";

        new File(folder).mkdirs();

        String path = folder + File.separator +
                testName + "_" + timestamp + ".png";

        try {

            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(java.nio.file.Paths.get(path))
                            .setFullPage(true));

        } catch (Exception e) {

            e.printStackTrace();

        }

        return path;
    }
}