package com.java.auth.helpers;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import static com.java.auth.constants.FrameworkConstants.TEST_DATA_JSON_PATH;


public class JsonHelpers {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Map<String, Object> testData = JsonHelpers.readJsonFile(TEST_DATA_JSON_PATH);


    public static Map<String, Object> readJsonFile(String filePath) {
            try {
                return mapper.readValue(new File(filePath), Map.class);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON file: " + filePath, e);
            }
        }
    public static Map<String, Object> getObject(String key) {
        Object value = testData.get(key);
        if (value instanceof Map) {
            return (Map<String, Object>) value;
        } else {
            throw new RuntimeException("Key '" + key + "' does not map to an object.");
        }
    }

    // Get specific field inside the object
    public static String getValue(String objectKey, String fieldKey) {
        Map<String, Object> nestedObject = getObject(objectKey);
        Object value = nestedObject.get(fieldKey);
        if (value == null) {
            throw new RuntimeException("Field '" + fieldKey + "' not found under key '" + objectKey + "'.");
        }
        return value.toString();
    }
    }




