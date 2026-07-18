package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelReader;
import utils.JsonReader;
import utils.TestDataProvider;

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
    
    @Test
    public void verifySuccessfulLoginUsingExcel() {

        LoginPage loginPage = new LoginPage(page);

        ExcelReader excel = new ExcelReader("LoginData.xlsx");

        String username = excel.getCellData("Login", 1, 0);
        String password = excel.getCellData("Login", 1, 1);

        loginPage.login(username, password);

        Assert.assertTrue(loginPage.isDashboardDisplayed());
    }
    
    @Test(dataProvider = "loginData",
    	      dataProviderClass = TestDataProvider.class)
    	public void verifyLogin(String username,
    	                        String password,
    	                        String expected) {

    	    LoginPage loginPage = new LoginPage(page);

    	    loginPage.login(username, password);

    	    if(expected.equalsIgnoreCase("PASS")) {

    	        Assert.assertTrue(loginPage.isDashboardDisplayed(),
    	                "Dashboard should be displayed for valid login.");

    	    } else {

    	        Assert.assertFalse(loginPage.isDashboardDisplayed(),
    	                "Dashboard should not be displayed for invalid login.");

    	    }

    	}
}