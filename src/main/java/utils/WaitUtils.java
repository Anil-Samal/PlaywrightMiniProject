package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitUtils {

    private final Page page;

    public WaitUtils(Page page) {
        this.page = page;
    }

    /**
     * Wait until the locator is visible.
     */
    public void waitForVisible(String selector) {
        page.locator(selector).waitFor(
            new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
        );
    }

   

    /**
     * Wait until the locator is hidden.
     */
    public void waitForHidden(String selector) {
        page.locator(selector).waitFor(
            new Locator.WaitForOptions()
                .setState(WaitForSelectorState.HIDDEN)
        );
    }

    /**
     * Wait for the page to finish loading.
     */
    public void waitForPageLoad() {
        page.waitForLoadState();
    }
}