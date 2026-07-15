package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {

        try {

            prop = new Properties();

            FileInputStream fis = new FileInputStream(
                    "src/main/resources/config.properties");

            prop.load(fis);

        } catch (IOException e) {

            e.printStackTrace();
            throw new RuntimeException("Unable to load config.properties");

        }

    }

    public static String getProperty(String key) {

        return prop.getProperty(key);

    }

}