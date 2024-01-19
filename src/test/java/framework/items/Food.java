package framework.items;

public class Food {
    private String name;
    private String type;
    private boolean exotic;

    public String getName() { return name; }
    public String getType() { return type; }
    public boolean getExotic() { return exotic; }

    public void setName(String name) {this.name = name; }
    public void setType(String type) {this.type = type; }
    public void setExotic(boolean exotic) {this.exotic = exotic;}
}
