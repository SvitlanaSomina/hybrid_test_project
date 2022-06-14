package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalInfoPage extends BasePage {

    @FindBy(xpath = "//span[@class = 'personal-info__edit']")
    private WebElement emailField;

    public PersonalInfoPage(WebDriver driver) {
        super(driver);
    }

    public String getEmail() {
        return emailField.getText();
    }
}
