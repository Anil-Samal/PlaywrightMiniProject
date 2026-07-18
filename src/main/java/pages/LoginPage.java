package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    private final String txtUsername = "input[name='username']";

    private final String txtPassword = "input[name='password']";

    private final String btnLogin = "button[type='submit']";

    public void enterUsername(String username) {
        type(txtUsername, username);
    }

    public void enterPassword(String password) {
        type(txtPassword, password);
    }

    public void clickLogin() {
        click(btnLogin);
        page.waitForLoadState();
    }

    public void login(String username, String password) {

        log.info("Entering username");

        enterUsername(username);

        log.info("Entering password");

        enterPassword(password);

        log.info("Clicking Login button");

        clickLogin();
    }
    
    public boolean isDashboardDisplayed() {

        try {

            page.locator("//h6[text()='Dashboard']")
                .waitFor(new Locator.WaitForOptions().setTimeout(5000));

            return true;

        } catch (Exception e) {

            return false;

        }
    }
}