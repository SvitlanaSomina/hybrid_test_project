package ui_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import manager.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;
import pages.HomePage;
import pages.PersonalInfoPage;

public class BaseUiTest {
    public static WebDriver driver;
    WebDriverManager driverManager;
    private static final String APPLICATION_URL = ConfigFileReader.getApplicationUrl();
    static Logger log = LogManager.getLogger();

    @BeforeTest
    public void profileSetUp() {
        driverManager = WebDriverFactory.initDriver();
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = driverManager.create();
        driver.manage().window().maximize();
        driver.get(APPLICATION_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
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
}
