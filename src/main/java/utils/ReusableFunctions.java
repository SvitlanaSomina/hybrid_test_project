package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

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
        WebElement webElement = driver.findElement(By.cssSelector(css));
        waitVisibilityOfElement(webElement, driver);
        return webElement;
    }

    public List<WebElement> getElementsByCss(String css, WebDriver driver) {
        List<WebElement> listOfElements = driver.findElements(By.cssSelector(css));
        waitVisibilityOfElements(listOfElements, driver);
        return listOfElements;
    }

    public static WebElement getElementByXpath(String xpath, WebDriver driver) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        waitVisibilityOfElement(webElement, driver);
        return webElement;
    }

    public List<WebElement> getElementsByXpath(String xpath, WebDriver driver) {
        List<WebElement> listOfElements = driver.findElements(By.xpath(xpath));
        waitVisibilityOfElements(listOfElements, driver);
        return listOfElements;
    }

    public static void waitForElementToBeClickable(WebElement webElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void clickElement(WebElement webElement) {
        webElement.click();
    }

    public static void clickElements(List<WebElement> listOfElements) {
        for (WebElement element : listOfElements) {
            element.click();
        }
    }

    public static void waitVisibilityOfElement(WebElement webElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitVisibilityOfElements(List<WebElement> listOfElements, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT));
        wait.until(ExpectedConditions.visibilityOfAllElements(listOfElements));
    }

    public static void waitInvisibilityOfElement(WebElement webElement, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT));
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_TO_WAIT));
    }

    public static void retryFindClick(String xpath, WebDriver driver) {
        for (int i = 0; i < 3; i++) {
            try {
                driver.findElement(By.xpath(xpath)).click();
                log.info("The element is found and clicked");
                break;
            } catch (StaleElementReferenceException staleElementReferenceException) {
                staleElementReferenceException.printStackTrace();
                log.error("The element is not found");
            }
        }
    }
    public static void verifyElementVisible(WebElement webElement) {
        Assert.assertTrue(webElement.isDisplayed());
    }

    public static void verifyElementVisible(String xpath, WebDriver driver) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertTrue(webElement.isDisplayed());
    }

    public static void verifyElementInvisible(WebElement webElement) {
        Assert.assertFalse(webElement.isDisplayed());
    }

    public static void verifyElementInvisible(String xpath, WebDriver driver) {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        Assert.assertFalse(webElement.isDisplayed());
    }
}
