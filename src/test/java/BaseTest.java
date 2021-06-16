import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Base class for all test with useful common methods and objects.
 * Note: web driver is configured here.
 */
@TestInstance(PER_CLASS)
public abstract class BaseTest {
    protected final WebDriver driver;
    protected final Wait<WebDriver> wait;

    /**
     * Test's basic constructor.
     * <br/>
     * Among other things, it's useful to configure test variables (test data),
     * page objects, web driver, etc...
     */
    public BaseTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 30, 500);
    }

    @AfterAll
    @Step("Close the browser")
    public void tearDownTest() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Wait until the given element becomes visible on the page.
     *
     * @param element element to wait to be visible
     */
    public void waitUntilVisible(WebElement element) {
        wait.until(visibilityOf(element));
    }

    /**
     * Wait until the given element CONTAINS the expected text.
     *
     * @param element      element to check the text of
     * @param expectedText expected text substring to wait in the given element
     */
    public void waitUntilElementHasText(WebElement element, String expectedText) {
        wait.until(textToBePresentInElement(element, expectedText));
    }
}
