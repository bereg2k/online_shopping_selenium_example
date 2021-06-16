import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import page.*;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static page.CartPage.NO_PRODUCTS_MESSAGE;
import static page.SearchResultsPage.PRODUCT_ADDED_TO_CART_MESSAGE;

/**
 * The test with a following flow:
 * search for a product, add it to the cart, remove it from the cart
 * and check that the cart is empty.
 */
@Tag("Smoke")
@Tag("E2E")
public class MarketOrderTest extends BaseTest {

    //  Error message for test's assertions
    private static final String SEARCH_HEADER_ERROR =
            "The search results header is incorrect!";
    private static final String UPSALE_MODAL_PRODUCT_INFO_ERROR =
            "'Upsale' modal window should contain text information about the added product!";
    private static final String UPSALE_MODAL_PRODUCT_ADDED_ERROR =
            "'Upsale' modal window should contain a header with the info 'success' message!";
    private static final String CART_ITEM_NAME_ERROR =
            "The item in the cart should contain the name of the added product!";
    private static final String CART_EMPTY_ERROR =
            "The cart should be empty after removing the cart items!";

    //  Test data
    private final String productName;
    private final String searchResultsHeader;

    //  Used pages
    private final MainPage mainPage;
    private final SearchResultsPage searchResultsPage;
    private final CartPage cartPage;

    public MarketOrderTest() {
        productName = "Apple MacBook Pro 16";
        searchResultsHeader = "Ноутбуки Apple Macbook Pro";

        mainPage = new MainPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    @DisplayName("Add and remove products to/from the Cart")
    @Description("Verify that the product can be found via the search bar, " +
            "added to the cart and removed from the cart")
    public void test() {
        step("1. Open the main page of the online store", () -> {
            mainPage.open();

            waitUntilVisible(mainPage.getSearchField());
        });

        step("2. Search for a product", () -> {
            mainPage.searchProductByName(productName);
            waitUntilVisible(searchResultsPage.getFirstFoundProduct());

            assertEquals(searchResultsHeader, searchResultsPage.getSearchResultsHeader().getText(),
                    SEARCH_HEADER_ERROR);
        });

        step("3. Add the first available product from the search page to the cart ", () -> {
            searchResultsPage.addFirstProductToTheCart();
            waitUntilVisible(searchResultsPage.getUpsaleModal());

            assertTrue(searchResultsPage.getUpsaleModal().getText().contains(productName),
                    UPSALE_MODAL_PRODUCT_INFO_ERROR);
            assertTrue(searchResultsPage.getUpsaleModal().getText().contains(PRODUCT_ADDED_TO_CART_MESSAGE),
                    UPSALE_MODAL_PRODUCT_ADDED_ERROR);
        });

        step("4. Open the cart and check the added product", () -> {
            searchResultsPage.openCartFromUpsaleModal();
            waitUntilVisible(cartPage.getCartHeader());

            assertTrue(cartPage.getFirstCartItem().getText().contains(productName),
                    CART_ITEM_NAME_ERROR);
        });

        step("5. Remove the product from the cart", () -> {
            cartPage.removeFirstCartItem();
            waitUntilElementHasText(cartPage.getCartHeader(), NO_PRODUCTS_MESSAGE);

            assertTrue(cartPage.getAllCartItems().isEmpty(),
                    CART_EMPTY_ERROR);
        });
    }
}
