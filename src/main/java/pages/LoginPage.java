package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

	private final Page page;
    private final Locator username;
    private final Locator password;
    private final Locator loginBtn;

	public LoginPage(Page page) {
		this.page = page;

		username = page.locator("input[name='username']");
		password = page.locator("input[name='password']");
		loginBtn = page.locator("button[type='submit']");
	}

	public DashboardPage login(String user, String pass) {

	    username.fill(user);
	    password.fill(pass);
	    loginBtn.click();

	    page.waitForURL("**/dashboard/**");

	    System.out.println("Current URL : " + page.url());
	    System.out.println("Page Title  : " + page.title());

	    return new DashboardPage(page);

	}
}
