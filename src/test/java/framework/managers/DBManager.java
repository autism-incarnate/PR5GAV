package framework.managers;

import framework.util.Props;
import framework.util.FoodType;
import io.qameta.allure.Step;

import java.sql.*;

public class DBManager {

    private static DBManager dbMan_ref = null;

    private static final PropManager PROP_MANAGER = PropManager.getPropInstance();

    private Connection conn = null;

    //Strings for prepared statements
    private final String selectQuery = "SELECT FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC FROM FOOD";

    private final String insertQuery = "INSERT INTO FOOD(FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?,?,?)";

    //Try connecting to DB upon manager instantiation
    private DBManager() {
        try { conn = DriverManager.getConnection(
                PROP_MANAGER.getProp(Props.DB_PATH),
                PROP_MANAGER.getProp(Props.DB_USER),
                PROP_MANAGER.getProp(Props.DB_PASS)); }
        catch (SQLException e) { e.printStackTrace(); }
    }

    //Utility method to check if DB connection is still active
    private boolean checkConn() {
        try{ if(!(conn == null) && conn.isValid(1)) return true; }
        catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    //Self-explanatory
    public void closeConnection() {
        if(checkConn()) {
            try { conn.close(); }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public static DBManager getDBInstance() {
        if(dbMan_ref == null)
            dbMan_ref = new DBManager();
        return dbMan_ref;
    }

    @Step("Getting DB contents as ResultSet")
    public ResultSet getContents() {
        try {
            PreparedStatement getAll = conn.prepareStatement(selectQuery);
            return getAll.executeQuery();
        }
        catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Step("Inserting row to DB with values: {name}, {type}, {exotic}")
    public boolean insertRow(String name, FoodType type, Boolean exotic) {
        try {
            PreparedStatement insertRow = conn.prepareStatement(insertQuery);
            insertRow.setString(1, name);
            insertRow.setString(2, type.getFruitNameEng());
            if(exotic)
                insertRow.setInt(3, 1);
            else
                insertRow.setInt(3, 0);
            int a = insertRow.executeUpdate();
            return a > 0;
        }
        catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}
