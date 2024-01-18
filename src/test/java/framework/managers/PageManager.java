package framework.managers;

import framework.pages.*;

public class PageManager {
    private static PageManager pageManager = null;

    private static PFactory pFactory = new PFactory();

    private Page currentPage = null;

    private PageHome pageHome = null;

    private PageGoods pageGoods = null;

    private MenuBar menuBar = null;

    public static PageManager getPageManagerInstance() {
        if(pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public Page getPageByName(String pageName) {
        PageTypes c = PageTypes.createEnum(pageName);
        assert c != null;
        Page p = pFactory.getPage(c.returnType(pageName));
        p.initPage();
        currentPage = p;
        return p;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public MenuBar getMenuInstance() {
        if(menuBar == null) {
            menuBar = new MenuBar();
            menuBar.initPage();
        }
        return menuBar;
    }

    public PageGoods getGoodsInstance() {
        if(pageGoods == null) {
            pageGoods = new PageGoods();
            pageGoods.initPage();
        }
        return pageGoods;
    }

    public PageHome getHomeInstance() {
        if(pageHome == null) {
            pageHome = new PageHome();
            pageHome.initPage();
        }
        return pageHome;
    }
}
