package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import framework.items.FoodType;

import java.util.*;


public class PageGoods extends BasePage implements Page {

    private List<List<String>> initTableUI; //HTML Table initial state
    private List<List<String>> currentTableUI; //HTML Table after we perform some actions

    public void initPage() {
        if(elementMap == null || elementMap.size() == 0) {
            Map<String, String> temp = pageManager.getMenuInstance().getMap();
            for(String key : temp.keySet())
                elementMap.put(key, temp.get(key));

            elementMap.put("Добавить", "//button[@data-toggle='modal']");
            elementMap.put("Товары", "//div[@class='container-fluid']/*[1]");
            elementMap.put("Модальное окно", "//h5[@class='modal-title']");
            elementMap.put("Закрыть", "//button[@class='close']");
            elementMap.put("Наименование", "//input[@id='name']");
            elementMap.put("Тип", "//select[@id='type']");
            elementMap.put("Чекбокс", "//input[@type='checkbox']");
            elementMap.put("Сохранить", "//button[@id='save']");
            elementMap.put("Таблица", "//table/tbody");

            initTableUI = uiParseTable();
            currentTableUI = uiParseTable();
        }
    }

    //Parse the HTML table so we can work with it
    private List<List<String>> uiParseTable() {
        List<List<String>> sRows = new ArrayList<List<String>>();
        waitForVisible("Таблица");
        List<WebElement> eRows = webDriverManager.getDriver().
                findElements(By.xpath("//div[@class='container-fluid']/table/tbody/tr"));

        for(WebElement i : eRows){
            List<WebElement> eCols = i.findElements(By.xpath("./td"));
            List<String> nString = new ArrayList<String>();
            for(WebElement n : eCols)
                nString.add(n.getText());
            sRows.add(nString);
        }

        return sRows;
    }

    public void updateTable () {
        waitForVisible("Таблица");
        currentTableUI = uiParseTable();
    }

    public List<List<String>> getInitTableUI() { return initTableUI; }
    public List<List<String>> getCurrentTableUI() { return  currentTableUI; }


    //Adding a new item, then performing some checks
    public void fillItem(String name, FoodType objType, boolean exotic) {

        WebElement inputName = getElementByString("Наименование");
        Select dropdown = new Select(getElementByString("Тип"));
        WebElement checkbox = getElementByString("Чекбокс");

        fillInput(name, inputName);

        dropdown.selectByValue(objType.getFruitNameEng());

        if(!checkbox.isSelected() && exotic)
            checkbox.click();
        else if (checkbox.isSelected() && !exotic)
            checkbox.click();
    }

    public void validateItem(String name, FoodType objType, boolean exotic) {
        Select dropdown = new Select(getElementByString("Тип"));
        WebElement modalCheckBox = getElementByString("Чекбокс");

        compareText(name, getValueFromInput("Наименование"));
        compareText(objType.getFruitNameRus(), dropdown.getFirstSelectedOption());
    }
}
