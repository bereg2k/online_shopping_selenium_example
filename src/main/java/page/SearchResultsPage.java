package page;

import org.openqa.selenium.*;

/**
 * Page with search results.
 * Typically appears after the user invokes a search from the main page
 * using a product's name.
 */
public class SearchResultsPage extends BasePage {
    public static final String PRODUCT_ADDED_TO_CART_MESSAGE = "Товар успешно добавлен в корзину";

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get the dynamic header for the page.
     *
     * @return page's header with a text that depends on the search query
     */
    public WebElement getSearchResultsHeader() {
        return $("[data-apiary-widget-id*='/headline'] h1");
    }

    /**
     * Locate the first product from the search results.
     *
     * @return web element for the first found product
     */
    public WebElement getFirstFoundProduct() {
        return $("[data-autotest-id='product-snippet']");
    }

    /**
     * Click 'Add to the cart' on the first found product on the list.
     */
    public void addFirstProductToTheCart() {
        getFirstFoundProduct().findElement(By.cssSelector("[data-zone-name='cartButton'] button")).click();
    }

    /**
     * Locate the 'Upsale' modal window that appears after the user adds the first product to the cart.
     * It consists of the product's description, quantity selector and action buttons.
     *
     * @return web element for the modal pop-up window
     */
    public WebElement getUpsaleModal() {
        return $("[data-auto='upsale-content']");
    }

    /**
     * Open the cart page from the 'Upsale' modal window.
     */
    public void openCartFromUpsaleModal() {
        $("[data-auto='upsale-content'] a[href*='beru_in_yamarket']").click();
    }
}
