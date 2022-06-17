package manager;

import utils.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;

import static io.github.bonigarcia.wdm.WebDriverManager.edgedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.iedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;

public class WebDriverFactory {

    private static String driver = ConfigFileReader.getBrowser();

    public static WebDriverManager initDriver() {
        switch (driver) {
            case "chrome": return chromedriver();
            case "firefox": return firefoxdriver();
            case "edge": return edgedriver();
            case "ie": return iedriver();
            default: throw new RuntimeException("Unsupported webdriver: " + driver);
        }
    }
}
