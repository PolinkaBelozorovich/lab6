package contexts;

import pages.*;


public class Context_Buying_Item {

    public static ArticlePage selectElement(MerchandisePage page, int element_Index){
        page.results.get(element_Index).click();
        return new ArticlePage(page.getDriver());
    }

    public static BasketPage buy(ArticlePage page){
        page.buy.click();
        return new BasketPage(page.getDriver());
    }

    public static ContactsPage validate_Purchase(BasketPage page){
        page.validate.click();
        return new ContactsPage(page.getDriver());
    }
}