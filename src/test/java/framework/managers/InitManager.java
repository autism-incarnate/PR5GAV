package framework.managers;

import framework.util.Props;

import java.util.concurrent.TimeUnit;

public class InitManager {
    private static final WebDriverManager WEB_DRIVER_MANAGER = WebDriverManager.getDriverInstance();
    private static final DBManager DB_MANAGER = DBManager.getDBInstance();
    private static final PropManager PROP_MANAGER = PropManager.getPropInstance();

    public static void init() {
        WEB_DRIVER_MANAGER.getDriver().manage().window().maximize();
        WEB_DRIVER_MANAGER.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(PROP_MANAGER.getProp(Props.IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        WEB_DRIVER_MANAGER.getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(PROP_MANAGER.getProp(Props.LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quit() {
        WEB_DRIVER_MANAGER.quitDriver();
        DB_MANAGER.closeConnection();
    }
}
