package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.playwright.Page;

public class ScreenshotUtil {

    public static String captureScreenshot(Page page, String testName) {

        // Prevent NullPointerException
        if (page == null) {
            System.out.println("Screenshot skipped because Page is null.");
            return "No Screenshot - Page is null";
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());

        String folder = "screenshots";

        new File(folder).mkdirs();

        String path = folder + File.separator
                + testName + "_" + timestamp + ".png";

        try {

            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(java.nio.file.Paths.get(path))
                            .setFullPage(true));

            System.out.println("Screenshot saved : " + path);

        } catch (Exception e) {

            System.out.println("Unable to capture screenshot : " + e.getMessage());

        }

        return path;
    }
}