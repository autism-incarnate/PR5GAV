package framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import static framework.util.Props.*;

public class WebDriverManager {
    private static WebDriverManager driver_ref = null;

    public static WebDriver driver;
    public static RemoteWebDriver remoteDriver;
    private WebDriverManager() {
    }

    public static WebDriverManager getDriverInstance() {
        if(driver_ref == null)
            driver_ref = new WebDriverManager();
        return driver_ref;
    }

    public WebDriver getDriver() {
        if(driver==null && remoteDriver == null){
            initDriver();
        }
        if(PropManager.getPropInstance().getProp(DRIVER_TYPE).equals("local"))
            return driver;
        if(PropManager.getPropInstance().getProp(DRIVER_TYPE).equals("remote"))
            return remoteDriver;
        return null;
    }

    private void initDriver() {
        if(PropManager.getPropInstance().getProp(DRIVER_TYPE).equals("local")) {
            System.setProperty("webdriver.chrome.driver", PropManager.getPropInstance().getProp(DRIVER_PATH));
            driver = new ChromeDriver();
        }
        else if(PropManager.getPropInstance().getProp(DRIVER_TYPE).equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(PropManager.getPropInstance().getProp(BROWSER_TYPE));
            capabilities.setVersion(PropManager.getPropInstance().getProp(BROWSER_VERSION));
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", false
            ));
            try {
                remoteDriver = new RemoteWebDriver(
                        URI.create(PropManager.getPropInstance().getProp(SELEN_URL)).toURL(),
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
        if(remoteDriver!=null) {
            remoteDriver.quit();
        }
    }

}
