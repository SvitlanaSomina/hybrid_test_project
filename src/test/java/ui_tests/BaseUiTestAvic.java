package ui_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import pages.ComponentsPageAvic;
import pages.FridgePageAvic;
import pages.HomePage;
import pages.HomePageAvic;
import utils.ConfigFileReader;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

@Listeners(listeners.TestNGListeners.class)
public class BaseUiTestAvic {
    public static WebDriver driver;
    private static final String APPLICATION_URL = "https://avic.ua/";
    static Logger log = LogManager.getLogger();

    @BeforeTest
    public void profileSetUp() {
        chromedriver().setup();
    }

    @BeforeMethod
    public void testsSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.addArguments("window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get(APPLICATION_URL);
    }

    @AfterMethod
    public void tearDown() { driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePageAvic getHomePageAvic() {
        return new HomePageAvic(getDriver());
    }

    public ComponentsPageAvic getComponentsPageAvic() {
        return new ComponentsPageAvic(getDriver());
    }

    public FridgePageAvic getFridgePageAvic() {
        return new FridgePageAvic(getDriver());
    }
}


