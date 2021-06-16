package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Main page for the online store.
 */
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Open the main page.
     */
    public void open() {
        driver.get("https://market.yandex.ru/");
    }

    /**
     * Locate the main search input field.
     *
     * @return web element for search field
     */
    public WebElement getSearchField() {
        return $("#header-search");
    }

    /**
     * Invoke a search for products via the main search input field.
     *
     * @param searchQuery product's name to search for
     */
    public void searchProductByName(String searchQuery) {
        getSearchField().clear();
        getSearchField().sendKeys(searchQuery);
        $("button[data-r='search-button']").click();
    }
}
