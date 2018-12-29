package pages;

import elements.*;
import extensions.FireFoxDriverEx;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class DeliveryPage extends Page {
    public DeliveryPage(FireFoxDriverEx driver) {
        super(driver);
    }

    @FindBy(id = "total_checkout_amount")
    @CacheLookup
    public Html_Label price;

    @FindBy(id = "make-order")
    @CacheLookup
    public Button bbutton;

    public int getPrice(){
        String stringValue = price.getText();
        stringValue = stringValue.replaceAll(" ","");
        return Integer.parseInt(stringValue);
    }
}