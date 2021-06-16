package page;

import org.openqa.selenium.*;

import java.util.List;

/**
 * Cart page with the added products that the user is willing to purchase.
 */
public class CartPage extends BasePage {
    public static final String NO_PRODUCTS_MESSAGE = "В корзине нет товаров";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Locate the cart header.
     * Usually contains the info message about the cart state
     * (e.g. 'no items in the cart', 'there are 2 items in the cart'...).
     *
     * @return web element for the cart's header
     */
    public WebElement getCartHeader() {
        return $("[data-apiary-widget-name*='CartHeader']");
    }

    /**
     * Locate the first item in the cart.
     * Note: to access all the items, see {@link #getAllCartItems()}.
     *
     * @return web element for the first cart item
     */
    public WebElement getFirstCartItem() {
        return $("[data-auto='CartItem']");
    }

    /**
     * Locate the list of all the items in the cart.
     * Note: to access only the first item, see {@link #getFirstCartItem()}.
     *
     * @return list of web elements for all the cart items
     */
    public List<WebElement> getAllCartItems() {
        return $$("[data-auto='CartItem']");
    }

    /**
     * Click on the 'delete' button of the first cart item.
     * Useful when there's only 1 item, and you need to empty the cart.
     */
    public void removeFirstCartItem() {
        getFirstCartItem().findElement(By.cssSelector(".b_2g2hYadSGc")).click();
    }
}
