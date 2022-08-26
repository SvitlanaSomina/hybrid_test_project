package ui_tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContentTestsAvic extends BaseUiTestAvic {
    private static final String REQUIRED_WORD = "elektronika";
    private static final String SEARCH_KEYWORD = "холод";

    @Test(priority = 1)
    public void checkThatUrlContainsRequiredWord(){
        getHomePageAvic().clickProductCatalogButton();
        getHomePageAvic().clickComputersButton();
        assertTrue(getDriver().getCurrentUrl().contains(REQUIRED_WORD ));
    }

    @Test(priority = 2)
    public void checkElementsAmountOnSystemUnitsPage(){
        getHomePageAvic().clickComputersButton();
        getHomePageAvic().clickComponentsButton();
        assertEquals(getComponentsPageAvic().getComponentsList().size(), 10);
    }

    @Test(priority = 3)
    public void checkThatAllElementsFromListContainsCategoryWord() {
        getHomePageAvic().clickHouseholdAppliancesButton();
        getFridgePageAvic().clickFridgesButton();
        for (WebElement element : getFridgePageAvic().getFridgesList()) {
            assertTrue(element.getText().toLowerCase().contains(SEARCH_KEYWORD));
        }
    }
}
