package ui_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pages.ComponentsPageAvic;
import pages.FridgePageAvic;
import pages.HomePageAvic;
import utils.CapabilityFactory;
import java.net.MalformedURLException;
import java.net.URL;


@Listeners(listeners.TestNGListeners.class)
public class BaseUiTestAvic {
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    private static final String APPLICATION_URL = "https://avic.ua/";
    static Logger log = LogManager.getLogger();

    @BeforeMethod
    @Parameters(value = {"browser"})
    public void testsSetUp(@Optional("chrome") String browser) throws MalformedURLException {
        driver.set(new RemoteWebDriver(new URL("http://192.168.0.104:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));
        getDriver().manage().window().maximize();
        getDriver().get(APPLICATION_URL);
    }

    @AfterMethod
    public void tearDown() {
        getDriver().close();
    }

    @AfterClass
    void terminate() {
        driver.remove();
    }

    public WebDriver getDriver() {
        return driver.get();
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


