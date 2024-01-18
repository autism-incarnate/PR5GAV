package framework.pages;

import org.openqa.selenium.WebElement;

public interface Page {
    void fillInput(String text, WebElement e);
    void compareText(String text, WebElement e);
    void compareText(String a, String b);
    void assertPageTitle(WebElement e, String title);
    void assertPageTitle(String name, String title);
    void addToMap(String key, String xpath);
    void initPage();
    WebElement waitForClickable(String name);

    WebElement stubbornWait(String name);

    WebElement waitForVisible(String name);

    WebElement waitForVisible(WebElement e);
    WebElement getElementByString(String name);

    String getValueFromInput(String name);
}
