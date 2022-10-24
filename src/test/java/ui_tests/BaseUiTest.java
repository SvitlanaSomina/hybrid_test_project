package ui_tests;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import pages.*;
import utils.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

@Listeners(listeners.TestNGListeners.class)
public class BaseUiTest {
    protected static ThreadLocal<ChromeDriver> driver = new ThreadLocal<>();
    private static final String APPLICATION_URL = ConfigFileReader.getApplicationUrl();
    static Logger log = LogManager.getLogger();


    @BeforeMethod
    public void testsSetUp() {
        chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver.set(new ChromeDriver(options));
        options.setHeadless(true);
        options.addArguments("window-size=1920,1080");
        getDriver().get(APPLICATION_URL);
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }

    public WebDriver getDriver() {
        return driver.get();
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
