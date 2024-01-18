package framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import framework.managers.WebDriverManager;
import framework.managers.PageManager;
import org.junit.jupiter.api.Assertions;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;


public class BasePage implements Page {

    protected final WebDriverManager webDriverManager = WebDriverManager.getDriverInstance();

    protected PageManager pageManager = PageManager.getPageManagerInstance();

    protected WebDriverWait wait = new WebDriverWait(webDriverManager.getDriver(), 5L);

    protected Map<String, String> elementMap = new HashMap<String, String>();


    @Step("Fill input field {e} with string {text}")
    public void fillInput(String text, WebElement e) {
        waitForClickable(e).click();
        e.sendKeys(text);
    }

    @Step("Compare string to {e} - text")
    public void compareText(String text, WebElement e) {
        Assertions.assertEquals(text, e.getText(), "Element's text is different from string provided!");
    }

    public void compareText(String a, String b) {
        Assertions.assertEquals(a, b, "Element's text is different from string provided!");
    }

    @Step("Verify that the page has loaded by matching {e} element title to {expected} string")
    public void assertPageTitle(WebElement e, String title) {
        Assertions.assertEquals(e.getText(), title,
                "Invalid page title / Incorrect page opened");
    }

    public void initPage() {}
    public void assertPageTitle(String name, String title) {
        Assertions.assertEquals(getElementByString(name).getText(), title,
                "Page title and element title are different!");
    }

    public void addToMap(String key, String xpath) {
        elementMap.put(key, xpath);
    }

    @Step("Wait for element {e} to become clickable")
    public WebElement waitForClickable(String name ) {
        WebElement e = webDriverManager.getDriver().findElement(By.xpath(elementMap.get(name)));
        return wait.until(ExpectedConditions.elementToBeClickable(e)); }

    @Step("Wait for element {e} to become clickable")
    public WebElement waitForClickable(WebElement e) {
        return wait.until(ExpectedConditions.elementToBeClickable(e)); }

    @Step("Wait for element {e} to become visible")
    public WebElement waitForVisible(String name) {
        WebElement e = stubbornWait(name);
        return e;
    }

    @Step("Wait for element {e} to become visible")
    public WebElement waitForVisible(WebElement e) {
        return wait.until(ExpectedConditions.visibilityOf(e));
    }

    public WebElement getElementByString(String name) {
        if(elementMap!=null && elementMap.size() > 0) {
            return webDriverManager.getDriver().findElement(By.xpath(elementMap.get(name)));
        }
        else
            return null;
    }

    public WebElement stubbornWait(String name) {
        Wait<WebDriver> stubbornWait = new FluentWait<WebDriver>(webDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(99999999))
                .pollingEvery(Duration.ofSeconds(999999))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        WebElement e = stubbornWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver e) {
                return webDriverManager.getDriver().findElement(By.xpath(
                        elementMap.get(name)));
            }
        });

        return null;
    }

    public String getValueFromInput(String name) {
        JavascriptExecutor js = (JavascriptExecutor) webDriverManager.getDriver();

        WebElement inpElement =
                WebDriverManager.getDriverInstance().getDriver().findElement(By.xpath(elementMap.get(name)));
        String text = (String) js.executeScript("return arguments[0].value", inpElement);
        return text;
    }

}
