package framework.pages;

import java.util.Map;

public class PageHome extends BasePage implements Page {

    public void initPage() {
        if(elementMap == null || elementMap.size() == 0) {
            Map<String, String> temp = pageManager.getMenuInstance().getMap();
            for(String key : temp.keySet()) {
                elementMap.put(key, temp.get(key));
            }
        }
    }
}
