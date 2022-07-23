package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private static Properties properties;
    //private static final String PROPERTY_FILE_PATH = "configs//configuration.properties";
    private static final String PROPERTY_FILE_PATH = "configuration.properties";
    private static final String BROWSER = "browser";
    private static final String DEFAULT_WAIT_TIME = "default.wait.sec";
    private static final String URL = "url";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String CONFIGURATION_PROPERTIES_NOT_FOUND = "configuration.properties not found at ";
    private static final String BROWSER_NOT_SPECIFIED = "browser not specified in the configuration.properties file.";
    private static final String DEFAULT_WAIT_TIME_NOT_SPECIFIED = "default.wait.sec not specified in the configuration.properties file.";
    private static final String URL_NOT_SPECIFIED = "url not specified in the configuration.properties file.";
    private static final String EMAIL_NOT_SPECIFIED = "email not specified in the configuration.properties file.";
    private static final String PASSWORD_NOT_SPECIFIED = "password not specified in the configuration.properties file.";

    private ConfigFileReader() {
    }

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH))) {
            properties = new Properties();
            properties.load(reader);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            throw new RuntimeException(CONFIGURATION_PROPERTIES_NOT_FOUND + PROPERTY_FILE_PATH);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String getBrowser() {
        String browser = properties.getProperty(BROWSER);
        if(browser != null) return browser;
        else throw new RuntimeException(BROWSER_NOT_SPECIFIED);
    }

    public static long getDefaultWaitTime() {
        String defaultWaitTime = properties.getProperty(DEFAULT_WAIT_TIME);
        if(defaultWaitTime != null) return Long.parseLong(defaultWaitTime);
        else throw new RuntimeException(DEFAULT_WAIT_TIME_NOT_SPECIFIED);
    }

    public static String getApplicationUrl() {
        String url = properties.getProperty(URL);
        if(url != null) return url;
        else throw new RuntimeException(URL_NOT_SPECIFIED);
    }

    public static String getEmail() {
        String email = properties.getProperty(EMAIL);
        if(email != null) return email;
        else throw new RuntimeException(EMAIL_NOT_SPECIFIED);
    }

    public static String getPassword() {
        String password = properties.getProperty(PASSWORD);
        if(password != null) return password;
        else throw new RuntimeException(PASSWORD_NOT_SPECIFIED);
    }
}
