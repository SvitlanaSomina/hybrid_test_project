package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReusableFunctions;
import java.util.List;

public class ProductListPage extends BasePage {

    private static final String PRODUCT_CARD_TITLE = "//a[@title = 'Xiaomi Redmi Note 11 4/128 Gr. Gray(2201117TY)' and @class = 'product-card__title']";
    private static final String PRODUCTS_LIST = "//a[@class = 'product-card__title']";
    private static final String SEARCH_WORD = "Xiaomi";

    @FindBy(xpath = "//a[@title = 'Xiaomi Redmi Note 11 4/128 Gr. Gray(2201117TY)' and @class = 'product-card__title']")
    private WebElement productCardTitle;

    @FindBy(xpath = "//a[@class = 'product-card__title']")
    private WebElement productsList;

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage waitVisibilityProductCardTitle() {
        ReusableFunctions.waitVisibilityOfElement(productCardTitle, driver);
        return this;
    }

    public void clickProductCardTitle() {
        ReusableFunctions.clickElement(productCardTitle, driver);
        log.info("Click on the product card title");
    }

    public int getProductsListSize() {
        return ReusableFunctions.getElementsByXpath(PRODUCTS_LIST, driver).size();
    }

    public boolean isSearchResultsContainsSearchWord() {
        List<WebElement> elementList = ReusableFunctions.getElementsByXpath(PRODUCTS_LIST, driver);
        for (WebElement webElement : elementList) {
            return (webElement.getText().contains(SEARCH_WORD));
        }
       return false;
    }
}
