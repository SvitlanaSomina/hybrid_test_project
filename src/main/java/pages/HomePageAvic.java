package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageAvic extends BasePage{

    private static final String PRODUCT_CATALOG_BUTTON = "//span[@class = 'sidebar-item']";
    private static final String COMPUTER_BUTTON = "//li[@class = 'parent js_sidebar-item'] [3]";
    private static final String COMPONENTS_BUTTON = "//img[@alt= 'Комплектующие']";
    private static final String HOUSE_HOLD_APPLIANCES_BUTTON = "//span[contains(text(), 'Побутова техніка')]";
    private static final String MONITORS_BUTTON = "//div[@class = 'brand-box__title'] / a[contains(text(),'Мониторы')]";

    @FindBy(xpath = "//span[@class = 'sidebar-item']")
    private WebElement productCatalogButton;

    @FindBy(xpath = "//span[contains(text(), 'Комп')]")
    private WebElement computersButton;

    @FindBy(xpath = "//img[@alt= 'Комплектуючі']")
    private WebElement componentsButton;

    @FindBy(xpath = "//span[contains(text(), 'Побутова техніка')]")
    private WebElement householdAppliancesButton;

    @FindBy(xpath = "//div[@class = 'brand-box__title'] / a[contains(text(),'Мониторы')]")
    private WebElement monitorsButton;

    public HomePageAvic(WebDriver driver) {
        super(driver);
    }

    public void clickProductCatalogButton() {
        productCatalogButton.click();
    }

    public void clickComputersButton() {
        computersButton.click();
    }

    public void clickComponentsButton() {
        componentsButton.click();
    }

    public void clickHouseholdAppliancesButton(){
        householdAppliancesButton.click();
    }

    public void clickMonitorsButton(){
        monitorsButton.click();
    }
}
