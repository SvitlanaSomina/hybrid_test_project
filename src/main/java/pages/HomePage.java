package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ReusableFunctions;

public class HomePage extends BasePage {

    private static final String PROFILE_BUTTON = "//button[@class='mh-button mh-button--open']";
    private static final String PERSONAL_INFO_BUTTON = "//a[@class = 'mh-button info']";
    private static final String CATALOG_BUTTON = "//div[@class = 'ct-button']";
    private static final String SEARCH_INPUT = "//input[@id='search-form__input']";
    private static final String SEARCH_BUTTON = "//button[@type = 'submit']";

    @FindBy(xpath = "//button[@class='mh-button mh-button--open']")
    private WebElement profileButton;

    @FindBy(xpath = "//a[@class = 'mh-button info']")
    private WebElement personalInfoButton;

    @FindBy(xpath = "//div[@class = 'ct-button']")
    private WebElement catalogButton;

    @FindBy(xpath = "//span[@class = 'c-counter__text']")
    private WebElement amountOfProductsInCart;

    @FindBy(xpath = "//input[@id='search-form__input']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getProfileButton() {
        ReusableFunctions.getElementByXpath(PROFILE_BUTTON, driver);
        log.info("Get 'Profile' button");
        return profileButton;
    }

    public void clickProfileButtonBeforeLogin() {
        ReusableFunctions.clickElement(profileButton, driver);
        log.info("Click on the 'Profile' button before login");
    }

    public void clickProfileButtonAfterLogin() {
        ReusableFunctions.retryFindClick(PROFILE_BUTTON, driver);
        log.info("Click on the 'Profile' button after login");
    }

    public WebElement getPersonalInfoButton() {
        ReusableFunctions.getElementByXpath(PERSONAL_INFO_BUTTON, driver);
        log.info("Get 'Personal Info' button");
        return personalInfoButton;
    }

    public void clickPersonalInfoButton() {
        ReusableFunctions.clickElement(personalInfoButton, driver);
        log.info("Click on the 'Personal Info' button");
    }

    public void clickCatalogButton() {
        ReusableFunctions.clickElement(catalogButton, driver);
        log.info("Click on the 'Catalog' button");
    }

    public void clickSearchButton() {
        ReusableFunctions.clickElement(searchButton, driver);
        log.info("Click on the 'Search' button");
    }

    public String getTextOfAmountProductsInCart() {
        return amountOfProductsInCart.getText();
    }

    public HomePage enterTextIntoInputSearch(String keyword) {
        ReusableFunctions.fillInputSearch(searchInput, keyword);
        return this;
    }
}
