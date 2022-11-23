package ui_tests;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pages.*;
import utils.CapabilityFactory;
import utils.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;


@Listeners(listeners.TestNGListeners.class)
public class BaseUiTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    private static final String APPLICATION_URL = ConfigFileReader.getApplicationUrl();
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

    public WebDriver getDriver() {
        return driver.get();
    }

    @AfterClass
    void terminate() {
        driver.remove();
    }

    public HomePage getHomePage() {
        return new HomePage(getDriver());
    }

    public LoginPage getLoginPage() {
        return new LoginPage(getDriver());
    }

    public PersonalInfoPage getPersonalInfoPage() {
        return new PersonalInfoPage(getDriver());
    }

    public CatalogPage getCatalogPage() {
        return new CatalogPage(getDriver());
    }

    public ProductListPage getProductListPage() { return new ProductListPage(getDriver()); }

    public ProductPage getProductPage() {
        return new ProductPage(getDriver());
    }

    public ShoppingCartPage getShoppingCartPage() {
        return new ShoppingCartPage(getDriver());
    }

    public void failedScreenshot(String testMethodName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        String timeStampDate = date.toString().replace(":", "_").replace(" ", "_");
        try {
            FileUtils.copyFile(srcFile, new File("C:/Users/user/IdeaProjects/hybrid_test_project/src/test/resources/screenshots/" +
                    testMethodName + "_" + timeStampDate + ".png"));
        } catch (IOException ioException) {
            log.warn(ioException.getMessage());
        }
    }
}

