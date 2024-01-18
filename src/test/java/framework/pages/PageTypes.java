package framework.pages;

public enum PageTypes {
    PAGEHOME("Главная", "PAGEHOME"),
    PAGEGOODS("Товары", "PAGEGOODS"),
    MENUBAR("Меню", "MENUBAR");

    private final String rus;
    private final String eng;

    PageTypes (String rus, String eng) { this.rus = rus; this.eng = eng; }

    public static PageTypes createEnum (String rus){
        switch (rus) {
            case "Главная":
                return PageTypes.PAGEHOME;
            case "Товары":
                return PageTypes.PAGEGOODS;
            case "Меню":
                return PageTypes.MENUBAR;
        }

        return null;
    }

    public String returnType(String rus) {
        return this.rus.equals(rus) ? eng : null;
    }

    public String returnType(){
        return this.eng;
    }

}
