package pages;

import com.microsoft.playwright.Page;

import base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    private final String txtUsername = "input[name='username']";

    private final String txtPassword = "input[name='password']";

    private final String btnLogin = "button[type='submit']";

    //private final String dashboard = "h6:has-text('Dashboard')";

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

//    public boolean isDashboardDisplayed() {
//
//        page.waitForLoadState();
//
//        page.locator("h6").first().waitFor();
//
//        System.out.println("Current URL : " + page.url());
//        System.out.println("Heading : " + page.locator("h6").first().textContent());
//
//        return page.locator("h6").first().textContent().trim().equals("Dashboard");
//    }
    
    public boolean isDashboardDisplayed() {
        page.locator("//h6[text()='Dashboard']").waitFor();
        return page.locator("//h6[text()='Dashboard']").isVisible();
    }
}