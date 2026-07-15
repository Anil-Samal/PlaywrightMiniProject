package test;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest{

    @Test

    public void Login(){

    	    LoginPage login = new LoginPage(page);

    	    //DashboardPage dashboard = login.login("Admin", "admin123");
    	    login.login(
    	            ConfigReader.getProperty("username"),
    	            ConfigReader.getProperty("password"));
    	    //Assert.assertEquals(dashboard.getTitle(), "OrangeHRM");

    }

}
