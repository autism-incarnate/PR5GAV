package framework.pages;

public class PFactory {
    public Page getPage(String PAGETYPE) {
        if(PAGETYPE == null){
            return null;
        }
        else if(PAGETYPE.equalsIgnoreCase(PageTypes.PAGEHOME.returnType())) {
            PageHome p = new PageHome();
            p.initPage();
            return p;
        }
        else if(PAGETYPE.equalsIgnoreCase(PageTypes.PAGEGOODS.returnType())) {
            PageGoods p = new PageGoods();
            p.initPage();
            return p;
        }
        else if(PAGETYPE.equalsIgnoreCase(PageTypes.MENUBAR.returnType())) {
            MenuBar p = new MenuBar();
            p.initPage();
            return p;
        }

        return null;
    }
}
