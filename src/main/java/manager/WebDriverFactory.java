package manager;

import utils.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class WebDriverFactory {

    private static String driver = ConfigFileReader.getBrowser();

    public static WebDriverManager initDriver() {
        switch (driver) {
            case "chrome": return chromedriver();
            case "firefox": return firefoxdriver();
            case "edge": return edgedriver();
            case "ie": return iedriver();
            case "opera": return operadriver();
            default: throw new RuntimeException("Unsupported webdriver: " + driver);
        }
    }
}
