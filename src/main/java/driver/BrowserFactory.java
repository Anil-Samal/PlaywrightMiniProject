package driver;

import com.microsoft.playwright.*;

public class BrowserFactory {

    public Page createBrowser(String browserName, boolean headless) {

        Playwright playwright = Playwright.create();

        DriverManager.setPlaywright(playwright);

        BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions()
                        .setHeadless(headless);

        Browser browser;

        switch (browserName.toLowerCase()) {

            case "firefox":
                browser = playwright.firefox().launch(options);
                break;

            case "webkit":
                browser = playwright.webkit().launch(options);
                break;

            default:
                browser = playwright.chromium().launch(options);
        }

        DriverManager.setBrowser(browser);

        BrowserContext context = browser.newContext();

        DriverManager.setContext(context);

        Page page = context.newPage();

        DriverManager.setPage(page);

        return page;
    }
}