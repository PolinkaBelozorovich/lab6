
package pages;

import elements.*;
import extensions.FireFoxDriverEx;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MerchandisePage extends Page {
    public static final String ROZETKA_URL = "https://rozetka.com.ua/skies/c118582/";

    public MerchandisePage(FireFoxDriverEx driver) {
        super(driver);
        open();
    }

    @FindBy(xpath = "//body/div[3]")
    public WebElement filterBar;

    @FindBy(id = "price[min]")
    public Text_Field pricemin;

    @FindBy(id = "price[max]")
    public Text_Field pricemax;

    @FindBy(id = "submitprice")
    public Button submitPrice;

    @FindBy(xpath = "//form[@id=\"filter_parameters_form\"]/div[10]")
    public Check_List countryFilter;

    @FindBy(css = ".g-i-tile-i.available")
    public List<WebElement> results;

    public MerchandisePage open() {
        driver.get(ROZETKA_URL);
        return this;
    }

    public MerchandisePage set_pricemin(int price){
        pricemin.setValue(Integer.toString(price));
        return this;
    }

    public MerchandisePage set_pricemax(int price){
        pricemax.setValue(Integer.toString(price));
        return this;
    }

    public MerchandisePage set_Country(String country){
        countryFilter.selectFrom(country);
        return this;
    }

}