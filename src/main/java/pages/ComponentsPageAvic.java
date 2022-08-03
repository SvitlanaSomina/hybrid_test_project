package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ComponentsPageAvic extends BasePage {

    private static final String COMPONENTS_LIST = "//div[@class='height brand-box']";

    @FindBy(xpath = "//div[@class='height brand-box']")
    private List<WebElement> componentsList;

    public ComponentsPageAvic(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getComponentsList(){
        return componentsList;
    }
}
