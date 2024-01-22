package framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import static framework.util.Props.BROWSER_TYPE;
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
        if(BROWSER_TYPE == "local") {
            System.setProperty("webdriver.chrome.driver", PropManager.getPropInstance().getProp(DRIVER_PATH));
            driver = new ChromeDriver();
        }
        else if(BROWSER_TYPE == "remote") {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("109.0");
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", false
            ));
            try {
                RemoteWebDriver driver = new RemoteWebDriver(
                        URI.create("http://selenoid:4444/wd/hub").toURL(),
                        capabilities);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public void quitDriver() {
        if(driver!=null) {
            driver.quit();
            driver = null;
        }
    }

}
