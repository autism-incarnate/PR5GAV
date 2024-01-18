package framework.pages;

import java.util.Map;

public class MenuBar extends BasePage implements Page {

    private final String title = "QualIT";
    public void initPage() {
        if(elementMap == null || elementMap.size() == 0) {
            addToMap("Главная", "//a[@class='navbar-brand']");
            addToMap("Песочница", "//a[@id='navbarDropdown']");
            addToMap("Товары", "//a[@class='dropdown-item'][@href='/food']['Товары']");
            addToMap("Сбросить", "//a[@class='dropdown-item'][@id='reset']");
        }
    }
    protected Map<String, String> getMap() {
        return elementMap;
    }

}
