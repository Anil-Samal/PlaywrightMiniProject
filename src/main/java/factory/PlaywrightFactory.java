package factory;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public Page initBrowser(String browserName) {

        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {

            case "firefox":
                browser = playwright.firefox()
                        .launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            case "webkit":
                browser = playwright.webkit()
                        .launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;

            default:
                browser = playwright.chromium()
                        .launch(new BrowserType.LaunchOptions().setHeadless(false));
        }

        context = browser.newContext(
                new Browser.NewContextOptions()
                        .setRecordVideoDir(Paths.get("videos"))
        );

        context.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        page = context.newPage();

        return page;
    }

    // Add this method at the end of the class
    public void closeBrowser() {

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}