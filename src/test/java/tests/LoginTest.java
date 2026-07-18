package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.JsonReader;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login(
                JsonReader.get("validUser", "username"),
                JsonReader.get("validUser", "password")
        );

        Assert.assertTrue(loginPage.isDashboardDisplayed());
    }

    @Test
    public void verifyInvalidLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.login(
                JsonReader.get("invalidUser", "username"),
                JsonReader.get("invalidUser", "password")
        );

        Assert.assertFalse(loginPage.isDashboardDisplayed());
    }
}