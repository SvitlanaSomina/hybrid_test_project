package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String PROPERTY_FILE_PATH = "configs//configuration.properties";

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(PROPERTY_FILE_PATH));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configuration.properties not found at " + PROPERTY_FILE_PATH);
        }
    }

    public String getBrowser() {
        String browser = properties.getProperty("browser");
        if(browser != null) return browser;
        else throw new RuntimeException("browser not specified in the configuration.properties file.");
    }

    public long getDefaultWaitTime() {
        String defaultWaitTime = properties.getProperty("default.wait.sec");
        if(defaultWaitTime != null) return Long.parseLong(defaultWaitTime);
        else throw new RuntimeException("default.wait.sec not specified in the configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if(url != null) return url;
        else throw new RuntimeException("url not specified in the configuration.properties file.");
    }

    public String getEmail() {
        String email = properties.getProperty("email");
        if(email != null) return email;
        else throw new RuntimeException("email not specified in the configuration.properties file.");
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        if(password != null) return password;
        else throw new RuntimeException("password not specified in the configuration.properties file.");
    }
}
