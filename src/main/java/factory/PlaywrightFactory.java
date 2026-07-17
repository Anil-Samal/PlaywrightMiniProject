package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    /**
     * Initializes Playwright and launches the requested browser.
     *
     * @param browserName Browser name (chromium, firefox, webkit)
     * @param headless true = headless, false = headed
     * @return Playwright Page object
     */
    public Page initBrowser(String browserName, boolean headless) {

        playwright = Playwright.create();

        BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions()
                        .setHeadless(headless);

        switch (browserName.toLowerCase()) {

            case "firefox":
                browser = playwright.firefox().launch(options);
                break;

            case "webkit":
                browser = playwright.webkit().launch(options);
                break;

            case "chromium":
            default:
                browser = playwright.chromium().launch(options);
                break;
        }

        context = browser.newContext();

        page = context.newPage();

        return page;
    }

    public Page getPage() {
        return page;
    }

    public BrowserContext getContext() {
        return context;
    }

    public Browser getBrowser() {
        return browser;
    }

    public Playwright getPlaywright() {
        return playwright;
    }

    /**
     * Close everything in reverse order.
     */
    public void closeBrowser() {

        if (context != null)
            context.close();

        if (browser != null)
            browser.close();

        if (playwright != null)
            playwright.close();
    }
}