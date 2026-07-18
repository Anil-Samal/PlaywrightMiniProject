package utils;

import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    private static Map<String, Object> jsonData;

    static {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream is = JsonReader.class
                    .getClassLoader()
                    .getResourceAsStream("testdata/login.json");

            jsonData = mapper.readValue(
                    is,
                    new TypeReference<Map<String, Object>>() {});

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    @SuppressWarnings("unchecked")
    public static String get(String user, String key) {

        Map<String, String> values =
                (Map<String, String>) jsonData.get(user);

        return values.get(key);

    }

}