package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login(
                config.getUsername(),
                config.getPassword());

        Assert.assertTrue(
                loginPage.isDashboardDisplayed(),
                "Dashboard is not displayed after login.");
        
        Assert.assertTrue(false, "Intentional failure");
    }
}