package pages;

import elements.*;
import extensions.FireFoxDriverEx;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ContactsPage extends Page {
    public ContactsPage(FireFoxDriverEx driver) {
        super(driver);
    }

    @FindBy(id = "reciever_name")
    @CacheLookup
    public Text_Field name;

    @FindBy(id = "suggest_locality")
    @CacheLookup
    public Text_Field city;

    @FindBy(id = "reciever_phone")
    @CacheLookup
    public Text_Field phone;

    @FindBy(id = "reciever_email")
    @CacheLookup
    public Text_Field email;

    @FindBy(xpath = "//*[@id=\"step_contacts\"]/div/div[1]/div[2]/div/span/button")
    @CacheLookup
    public Button next_button;
}