package tests;

import org.testng.annotations.Test;

import utils.ConfigReader;

public class ConfigReaderTest {

    @Test
    public void verifyConfig() {

        ConfigReader config = new ConfigReader();

        System.out.println(config.getBrowser());
        System.out.println(config.getUrl());
        System.out.println(config.getUsername());
        System.out.println(config.getPassword());
        System.out.println(config.isHeadless());
    }
}