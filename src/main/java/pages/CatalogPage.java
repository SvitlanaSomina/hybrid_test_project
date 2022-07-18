package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.ReusableFunctions;

public class CatalogPage extends BasePage {

    private static final String XIAOMI_BUTTON = "a[@class = 'mm__a3']";
    private static final String SMARTPHONES_AND_PHONES_BUTTON = "//a[@class = 'mm__a']";

    @FindBy(xpath = "//a[@class = 'mm__a3']")
    private WebElement xiaomiButton;

    @FindBy(xpath = "//a[@class = 'mm__a']")
    private WebElement smartphonesAndPhonesButton;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public CatalogPage waitVisibilityXiaomiButton() {
        ReusableFunctions.waitVisibilityOfElement(xiaomiButton, driver);
        return this;
    }

    public void clickXiaomiButton() {
        ReusableFunctions.clickElement(xiaomiButton, driver);
        log.info("Click on the 'Xiaomi' button");
    }

    public void moveToSmartphonesAndPhonesButton() {
        Actions action = new Actions(driver);
        WebElement webElement = ReusableFunctions.getElementByXpath(SMARTPHONES_AND_PHONES_BUTTON, driver);
        action.moveToElement(webElement).build().perform();
    }
}
