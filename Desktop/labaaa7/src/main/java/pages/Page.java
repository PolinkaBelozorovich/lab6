package pages;

import extensions.FireFoxDriverEx;
import extensions.custon_decorator.CustomFieldDecorator;
import org.openqa.selenium.support.PageFactory;


public class Page {
    protected FireFoxDriverEx driver;

    public Page(FireFoxDriverEx driver){
        this.driver = driver;
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public FireFoxDriverEx getDriver() {
        return driver;
    }

}