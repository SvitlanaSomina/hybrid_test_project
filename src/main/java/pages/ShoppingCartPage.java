package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReusableFunctions;

public class ShoppingCartPage extends BasePage {

    private static final String CHECKOUT_BUTTON = "//button[@class='order-now']";
    private static final String CONTINUE_SHOPPING_BUTTON = "//button[@class='comeback']";

    @FindBy(xpath = "//button[@class='order-now']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//button[@class='comeback']")
    private WebElement continueShoppingButton;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartPage waitVisibilityCheckoutButton() {
        ReusableFunctions.waitVisibilityOfElement(checkoutButton, driver);
        return this;
    }

    public boolean checkVisibilityContinueShoppingButton() {
        log.info("Check visibility of the 'Continue Shopping' button");
        return ReusableFunctions.verifyElementVisible(continueShoppingButton);
    }

    public boolean checkVisibilityCheckoutButton() {
        log.info("Check visibility of the 'Checkout' button");
        return ReusableFunctions.verifyElementVisible(checkoutButton);
    }

    public void clickContinueShoppingButton() {
        ReusableFunctions.clickElement(continueShoppingButton, driver);
        log.info("Click on the 'Continue shopping' button");
    }
}
