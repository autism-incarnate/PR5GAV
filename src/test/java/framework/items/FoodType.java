package framework.items;

public enum FoodType {
    FRUIT ("Фрукт", "FRUIT"),
    VEGETABLE ("Овощ", "VEGETABLE");

    private final String rus;
    private final String eng;
    FoodType(String rus, String eng) { this.rus = rus; this.eng = eng; }

    public static FoodType getThis(String rus) {
        switch (rus) {
            case "Овощ":
            case "VEGETABLE":
                return FoodType.VEGETABLE;
            case "Фрукт":
            case "FRUIT":
                return FoodType.FRUIT;
        }
        return null;
    }

    public String getFruitNameRus() { return rus; }
    public String getFruitNameEng() { return eng; }
}
