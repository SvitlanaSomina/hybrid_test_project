package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ReusableFunctions {

    private static final long TIME_TO_WAIT;
    static Logger log;

    private ReusableFunctions() {
    }

    static {
        TIME_TO_WAIT = ConfigFileReader.getDefaultWaitTime();
        log = LogManager.getLogger();
    }

    public static WebElement getElementByCss(String css, WebDriver driver) {
        List<WebElement> listOfElements = getElementsByCss(css, driver);
        waitVisibilityOfElements(listOfElements, driver);
        return listOfElements.get(0);
    }

    public static List<WebElement> getElementsByCss(String css, WebDriver driver) {
        List<WebElement> listOfElements = driver.findElements(By.cssSelector(css));
        waitVisibilityOfElements(listOfElements, driver);
        return listOfElements;
    }

    public static WebElement getElementByXpath(String xpath, WebDriver driver) {
        List<WebElement> listOfElements = getElementsByXpath(xpath, driver);
        waitVisibilityOfElements(listOfElements, driver);
        return listOfElements.get(0);
    }

    public static List<WebElement> getElementsByXpath(String xpath, WebDriver driver) {
        List<WebElement> listOfElements = driver.findElements(By.xpath(xpath));
        waitVisibilityOfElements(listOfElements, driver);
        return listOfElements;
    }

    public static void waitForElementToBeClickable(WebElement webElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_TO_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void clickElement(WebElement webElement, WebDriver driver) {
        waitForElementToBeClickable(webElement, driver);
        webElement.click();
    }

    public static void clickElements(List<WebElement> listOfElements, WebDriver driver) {
        for (WebElement element : listOfElements) {
            waitForElementToBeClickable(element, driver);
            element.click();
        }
    }

    public static void waitVisibilityOfElement(WebElement webElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_TO_WAIT);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitVisibilityOfElements(List<WebElement> listOfElements, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_TO_WAIT);
        wait.until(ExpectedConditions.visibilityOfAllElements(listOfElements));
    }

    public static void waitInvisibilityOfElement(WebElement webElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_TO_WAIT);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(TIME_TO_WAIT, TimeUnit.SECONDS);
    }

    public static void retryFindClick(String xpath, WebDriver driver) {
        for (int i = 0; i < 3; i++) {
            try {
                driver.findElement(By.xpath(xpath)).click();
                log.info("The element is found and clicked");
                break;
            } catch (StaleElementReferenceException staleElementReferenceException) {
                log.error(staleElementReferenceException.getMessage());
            }
        }
    }
    public static boolean verifyElementVisible(WebElement webElement) {
        return webElement.isDisplayed();
    }

    public static boolean verifyElementVisible(String xpath, WebDriver driver) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        return webElement.isDisplayed();
    }

    public static boolean verifyElementInvisible(WebElement webElement) {
        return !webElement.isDisplayed();
    }

    public static boolean verifyElementInvisible(String xpath, WebDriver driver) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        return !webElement.isDisplayed();
    }

    public static void fillInputSearch(WebElement webElement, String keyword) {
        webElement.sendKeys(keyword);
    }
}
