package pages;

        import elements.*;
        import extensions.FireFoxDriverEx;
        import org.openqa.selenium.support.CacheLookup;
        import org.openqa.selenium.support.FindBy;

public class ArticlePage extends Page {
    public ArticlePage(FireFoxDriverEx driver) {
        super(driver);
    }

    @FindBy(id = "price_label")
    @CacheLookup
    public Html_Label price;

    @FindBy(xpath = "//*[@id=\"detail_buy_label\"]/div[4]/div[1]/div/form/span/span/button")
    @CacheLookup
    public Button buy;

    public int get_Price(){
        String string_with_price = price.getText();
        string_with_price = string_with_price.replaceAll(" ","");
        return Integer.parseInt(string_with_price);
    }
}
