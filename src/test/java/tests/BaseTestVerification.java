package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BaseTestVerification extends BaseTest {

    @Test
    public void verifyHomePageTitle() {

        System.out.println(page.title());

        Assert.assertTrue(
                page.title().contains("OrangeHRM"));
    }
}