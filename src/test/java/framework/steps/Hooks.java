package framework.steps;

import framework.managers.InitManager;
import framework.managers.PageManager;
import framework.managers.PropManager;
import framework.managers.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

import static framework.util.Props.BASE_URL;

public class Hooks {

    private static final WebDriverManager webDriverManager = WebDriverManager.getDriverInstance();

    protected PageManager pageManager = PageManager.getPageManagerInstance();

    @BeforeAll
    public static void before_all(){
        InitManager.init();
        webDriverManager.getDriver().get(PropManager.getPropInstance().getProp(BASE_URL));
    }

    @AfterAll
    public static void after_all(){
        InitManager.quit();
    }
}
