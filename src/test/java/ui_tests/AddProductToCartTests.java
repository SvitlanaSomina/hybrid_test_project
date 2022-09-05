package ui_tests;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class AddProductToCartTests extends BaseUiTest {

    private static final String PRODUCT_IN_STOCK_MESSAGE_TEXT = "✓ У магазинах Алло";
    private static final String EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART = "1";
    private static final String PRODUCT_BUY_BUTTON_TEXT = "У кошику";

    @Test
    public void checkAddProductToCart() {
        getHomePage().clickCatalogButton();
        getCatalogPage().moveToSmartphonesAndPhonesButton();
        getCatalogPage().clickXiaomiButton();
        getProductListPage().clickProductCardTitle();
        getProductPage().waitVisibilityBuyButton();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(getProductPage().getTextOfProductInStockMessage()).isEqualTo(PRODUCT_IN_STOCK_MESSAGE_TEXT);
        softAssertions.assertThat(getProductPage().checkVisibilityProductBuyButton()).isTrue();
        getProductPage().clickProductBuyButton();
        getProductPage().clickStoreAddressButton();
        getProductPage().clickWithdrawTomorrowButton();
        getShoppingCartPage().waitVisibilityCheckoutButton();
        softAssertions.assertThat(getShoppingCartPage().checkVisibilityCheckoutButton()).isTrue();
        softAssertions.assertThat(getShoppingCartPage().checkVisibilityContinueShoppingButton()).isTrue();
        getShoppingCartPage().clickContinueShoppingButton();
        softAssertions.assertThat(getProductPage().getTextOfProductBuyButton()).isEqualTo(PRODUCT_BUY_BUTTON_TEXT);
        softAssertions.assertThat(getHomePage().getTextOfAmountProductsInCart()).isEqualTo(EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART);
        softAssertions.assertAll();
    }
}
