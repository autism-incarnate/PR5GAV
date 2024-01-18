package framework.steps;

import framework.managers.DBManager;
import framework.managers.PageManager;
import framework.pages.Page;
import framework.pages.PageGoods;
import framework.util.FoodType;
import framework.util.TableComparator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StepDefs {

    private PageManager pm = PageManager.getPageManagerInstance();

    private ResultSet resSet;
    @Given("^element visible \"([^\"]*)\"$")
    public void element_visible(String a) {
        Page cp = pm.getCurrentPage();
        WebElement e = cp.waitForVisible(a);
    }

    @Then("^click on \"([^\"]*)\"$")
    public void click_on (String a) {
        Page cp = pm.getCurrentPage();
        cp.waitForClickable(a).click();
    }

    @Given("^page opened \"([^\"]*)\" with name \"([^\"]*)\"$")
    public void page_opened (String a, String b) {
        Page cp = pm.getPageByName(a);
        cp.assertPageTitle(a, b);
    }

    @Given("^following fields visible")
    public void following_fields_visible(DataTable data) {
        Page cp = pm.getCurrentPage();
        List<String> arg = data.asList(String.class);
        for(String i : arg) {
            cp.waitForVisible(i);
        }
    }

    @Then("^fill in the fields$")
    public void fill_in_the_fields(Map<String, String> data) {
        PageGoods cp = pm.getGoodsInstance();
        String name = data.get("name");
        String type = data.get("foodType");
        String checkbox = data.get("exotic");

        FoodType foodType = FoodType.getThis(type);

        boolean exotic = Boolean.parseBoolean(checkbox);

        cp.fillItem(name, foodType, exotic);
    }

    @Given("^field values$")
    public void field_values(Map<String, String> data) {
        PageGoods cp = pm.getGoodsInstance();
        String name = data.get("name");
        String type = data.get("foodType");
        String checkbox = data.get("exotic");

        FoodType foodType = FoodType.getThis(type);

        boolean exotic = Boolean.getBoolean(checkbox);

        cp.validateItem(name, foodType, exotic);
    }

    @Then("last row contained in table")
    public void last_row_contained_in_table(List<String> r) {
        PageGoods cp = pm.getGoodsInstance();
        List<String> s = new ArrayList<String>();
        FoodType f = FoodType.getThis(r.get(1));
        s.add(r.get(0)); s.add(f.getFruitNameRus()); s.add(r.get(2));
        cp.updateTable();
        TableComparator.compareToLast(cp.getCurrentTableUI(), s);
    }

    @Given("^goods table equal to bd")
    public void goods_table_equal_to_bd() {
        PageGoods cp = pm.getGoodsInstance();
        TableComparator.compareTables(cp.getCurrentTableUI(), DBManager.getDBInstance().getContents(), true);
    }

    @Then("^add \"([^\"]*)\", (FRUIT|VEGETABLE), (true|false) to DB$")
    public void addToDB(String name, String type, String exotic){
        boolean e = Boolean.parseBoolean(exotic);
        FoodType f = FoodType.getThis(type);
        DBManager.getDBInstance().insertRow(name, f, e);
    }

    @Given("^save current DB state$")
    public void saveDBState(){
        resSet = DBManager.getDBInstance().getContents();
    }

    @Then("^compare current DB state to old DB state$")
    public void compareDBStates() {
        ResultSet curSet = DBManager.getDBInstance().getContents();
        TableComparator.compareTables(resSet, curSet, false);
    }


}
