package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private final Properties properties = new Properties();

    public ConfigManager() {

        String environment =
                System.getProperty("env", FrameworkConstants.DEFAULT_ENV);

        String filePath = FrameworkConstants.CONFIG_FOLDER
                + environment + ".properties";

        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config file : " + filePath, e);
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(
                properties.getProperty("headless", "false"));
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
    
    public int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
//    public String testMethod() {
//        return "OK";
//    }
}