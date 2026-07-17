package factory;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

//    private Playwright playwright;
//    private Browser browser;
//    private BrowserContext context;
//    private Page page;
	
	private static ThreadLocal<Playwright> playwright =
	        new ThreadLocal<>();

	private static ThreadLocal<Browser> browser =
	        new ThreadLocal<>();

	private static ThreadLocal<BrowserContext> context =
	        new ThreadLocal<>();

	private static ThreadLocal<Page> page =
	        new ThreadLocal<>();

    /**
     * Initializes Playwright and launches the requested browser.
     *
     * @param browserName Browser name (chromium, firefox, webkit)
     * @param headless true = headless, false = headed
     * @return Playwright Page object
     */
    public Page initBrowser(String browserName, boolean headless) {

        //playwright = Playwright.create();
    	playwright.set(Playwright.create());
    	
        BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions()
                        .setHeadless(headless);

        switch (browserName.toLowerCase()) {

            case "firefox":
               // browser = playwright.firefox().launch(options);
            	browser.set(
            		    playwright.get()
            		              .firefox()
            		              .launch(options));
                break;

            case "webkit":
                //browser = playwright.webkit().launch(options);
            	browser.set(
            		    playwright.get()
            		              .webkit()
            		              .launch(options));
                break;

            case "chromium":
            default:
                //browser = playwright.chromium().launch(options);
                browser.set(
                	    playwright.get()
                	              .chromium()
                	              .launch(options));
                break;
        }

        context.set(browser.get().newContext(

        	    new Browser.NewContextOptions()

        	        .setRecordVideoDir(Paths.get("videos"))

        	        .setRecordVideoSize(1280,720)));

        page.set(
        	    context.get().newPage()
        	);

        return page.get();
    }

    public Page getPage() {

        return page.get();

    }

    public BrowserContext getContext() {
        return context.get();
    }

    public Browser getBrowser() {
        return browser.get();
    }

    public Playwright getPlaywright() {
        return playwright.get();
    }

    /**
     * Close everything in reverse order.
     */
    public void closeBrowser() {

    	if(context.get()!=null){

    	    context.get().close();

    	}

    	if(browser.get()!=null){

    	    browser.get().close();

    	}

    	if(playwright.get()!=null){

    	    playwright.get().close();
    	}
    	page.remove();

    	context.remove();

    	browser.remove();

    	playwright.remove();
    }
}