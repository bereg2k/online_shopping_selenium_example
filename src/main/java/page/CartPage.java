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

    public WebElement getCartHeader() {
        return $("[data-apiary-widget-name*='CartHeader']");
    }

    public WebElement getFirstCartItem() {
        return $("[data-auto='CartItem']");
    }

    public List<WebElement> getAllCartItems() {
        return $$("[data-auto='CartItem']");
    }

    public void removeFirstCartItem() {
        getFirstCartItem().findElement(By.cssSelector(".b_2g2hYadSGc")).click();
    }
}
