package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReusableFunctions;

public class ProductPage extends BasePage {

    private static final String PRODUCT_BUY_BUTTON = "//button[@id = 'product-buy-button']";
    private static final String STORE_ADDRESS_BUTTON = "//span[@class = 'stores-list__value']";
    private static final String WITHDRAW_TOMORROW_BUTTON = "//div[@class= 'stores-list__store-item stores-list__store-item--select-store']";
    private static final String PRODUCT_IN_STOCK_MESSAGE = "//p[@class = 'p-trade__stock-label']";

    @FindBy(xpath = "//button[@id = 'product-buy-button']")
    private WebElement productBuyButton;

    @FindBy(xpath = "//span[@class = 'stores-list__value']")
    private WebElement storeAddressButton;

    @FindBy(xpath = "//div[@class= 'stores-list__store-item stores-list__store-item--select-store']")
    private WebElement withdrawTomorrowButton;

    @FindBy(xpath = "//p[@class = 'p-trade__stock-label']")
    private WebElement productInStockMessage;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkVisibilityProductBuyButton() {
        log.info("Check visibility of the 'Product Buy' button");
        return ReusableFunctions.verifyElementVisible(productBuyButton);
    }

    public WebElement getProductInStockMessage() {
        ReusableFunctions.getElementByXpath(PRODUCT_IN_STOCK_MESSAGE, driver);
        log.info("Get 'Product in stock' message");
        return productInStockMessage;
    }

    public void clickProductBuyButton() {
        ReusableFunctions.clickElement(productBuyButton, driver);
        log.info("Click on the 'Product Buy' button");
    }

    public void clickStoreAddressButton() {
        ReusableFunctions.clickElement(storeAddressButton, driver);
        log.info("Click on the 'Store Address' button");
    }

    public void clickWithdrawTomorrowButton() {
        ReusableFunctions.clickElement(withdrawTomorrowButton, driver);
        log.info("Click on the 'Withdraw Tomorrow' button");
    }


    public void waitVisibilityBuyButton() {
        ReusableFunctions.waitVisibilityOfElement(productBuyButton, driver);
    }

    public String getTextOfProductInStockMessage() {
        return productInStockMessage.getText();
    }

    public String getTextOfProductBuyButton() { return productBuyButton.getText(); }

    public boolean isPageUrlContainsText(String queryText) {
        return driver.getCurrentUrl().contains(queryText);
    }
}
