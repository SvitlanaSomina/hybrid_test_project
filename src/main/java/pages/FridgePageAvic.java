package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class FridgePageAvic extends BasePage {

    private static final String FRIDGES_BUTTON = "//a[contains(@class, 'brand-box__item') and normalize-space(text()) = 'Холодильники']";
    private static final String COMPONENTS_LIST = "//div[@class='item-prod col-lg-3']";

    @FindBy(xpath = "//a[contains(@class, 'brand-box__item') and normalize-space(text()) = 'Холодильники']")
    private WebElement fridgesButton;

//    @FindBy(xpath = "//div[@class='item-prod col-lg-3']")
//    private List<WebElement> fridgesList;
    @FindBy(xpath = "//div[@class='prod-cart__descr']")
    private List<WebElement> fridgesList;


    public FridgePageAvic(WebDriver driver) {
        super(driver);
    }

    public void clickFridgesButton(){
        fridgesButton.click();
    }

    public List<WebElement> getFridgesList(){
        return fridgesList;
    }
}
