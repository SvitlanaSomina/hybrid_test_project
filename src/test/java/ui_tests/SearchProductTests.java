package ui_tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SearchProductTests extends BaseUiTest {
    private static final String SEARCH_KEYWORD = "Xiaomi Redmi Note 11";
    private static final String QUERY_TEXT = "xiaomi-redmi-note-11";
    private static final int EXPECTED_PRODUCTS_LIST_SIZE = 28;

    @Test
    public void checkThatUrlContainsSearchWord() {
        getHomePage()
                .enterTextIntoInputSearch(SEARCH_KEYWORD)
                .clickSearchButton();
        getProductListPage().clickProductCardTitle();
        Assert.assertTrue(getProductPage().isPageUrlContainsText(QUERY_TEXT));
        Assert.assertTrue(false);
    }

    @Test
    public void checkElementsAmountOnProductListPage() {
        getHomePage().clickCatalogButton();
        getCatalogPage().moveToSmartphonesAndPhonesButton();
        getCatalogPage().clickXiaomiButton();
        assertEquals(getProductListPage().getProductsListSize(), EXPECTED_PRODUCTS_LIST_SIZE );
    }

    @Test
    public void checkThatSearchResultsContainsSearchWord() {
        getHomePage().clickCatalogButton();
        getCatalogPage().moveToSmartphonesAndPhonesButton();
        getCatalogPage().clickXiaomiButton();
        Assert.assertTrue(getProductListPage().isSearchResultsContainsSearchWord());
        throw new SkipException("This test is skipped");
    }
}
