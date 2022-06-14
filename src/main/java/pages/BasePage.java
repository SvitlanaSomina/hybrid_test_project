package pages;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    private static final long TIME_TO_WAIT = ConfigFileReader.getDefaultWaitTime();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitVisibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
