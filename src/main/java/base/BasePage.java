package base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import utils.WaitUtils;

public class BasePage {

    protected Page page;

    protected WaitUtils wait;

    public BasePage(Page page) {
        this.page = page;
        this.wait = new WaitUtils(page);
    }

    protected Locator locator(String selector) {
        return page.locator(selector);
    }

    protected void click(String selector) {
    	
        wait.waitForVisible(selector);
        
        locator(selector).click();
    }

    protected void type(String selector, String text) {

        wait.waitForVisible(selector);

        locator(selector).fill(text);
    }

    protected String getText(String selector) {
        return locator(selector).textContent();
    }

    protected boolean isVisible(String selector) {

        wait.waitForVisible(selector);

        return locator(selector).isVisible();
    }

    protected void hover(String selector) {
        locator(selector).hover();
    }

    protected void clear(String selector) {
        locator(selector).clear();
    }

    protected String getTitle() {
        return page.title();
    }

    protected String getCurrentUrl() {
        return page.url();
    }
}