package page;

import org.openqa.selenium.*;

import java.util.List;

/**
 * Base page with some useful common methods for other page object classes.
 */
public abstract class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Locate a web element using CSS selector.
     *
     * @param cssSelector CSS selector to find an element
     *                    (e.g. "#someId", "div.some_class")
     * @return web element located by a given CSS selector
     */
    public WebElement $(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    /**
     * Locate a list of web elements using CSS selector.
     *
     * @param cssSelector CSS selector to find elements
     *                    (e.g. "#someId", "div.some_class")
     * @return list of web elements located by a given CSS selector
     */
    public List<WebElement> $$(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector));
    }
}
