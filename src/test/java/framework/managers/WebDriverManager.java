package framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static framework.util.Props.DRIVER_PATH;

public class WebDriverManager {
    private static WebDriverManager driver_ref = null;

    public static WebDriver driver;
    private WebDriverManager() {
    }

    public static WebDriverManager getDriverInstance() {
        if(driver_ref == null)
            driver_ref = new WebDriverManager();
        return driver_ref;
    }

    public WebDriver getDriver() {
        if(driver==null){
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        System.setProperty("webdriver.chrome.driver", PropManager.getPropInstance().getProp(DRIVER_PATH));
        driver = new ChromeDriver();
    }

    public void quitDriver() {
        if(driver!=null) {
            driver.quit();
            driver = null;
        }
    }

}
