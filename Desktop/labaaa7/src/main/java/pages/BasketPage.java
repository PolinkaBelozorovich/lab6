package pages;

import elements.*;
import extensions.FireFoxDriverEx;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends Page {
    public BasketPage(FireFoxDriverEx driver) {
        super(driver);
    }

    @FindBy(id = "popup-checkout")
    @CacheLookup
    public Button validate;

    @FindBy(name = "cost")
    @CacheLookup
    public Html_Label price;

    public int getPrice(){
        String string_with_price = price.getText();
        string_with_price = string_with_price.replaceAll(" ","");
        return Integer.parseInt(string_with_price);
    }
}