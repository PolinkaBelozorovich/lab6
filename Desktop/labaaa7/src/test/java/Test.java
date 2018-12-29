import contexts.*;
import extensions.FireFoxDriverEx;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import pages.*;
//import waiters.Wait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Test {

    private static FireFoxDriverEx driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        System.setProperty("webdriver.gecko.driver", "/Users/polinabelozorovich/Downloads/geckodriver");
    }

    @Before
    public void setUp() throws Exception {
        driver = new FireFoxDriverEx();
    }

    @org.junit.Test
    public void test() throws Exception {


        int min = 200;
        int max = 5000;
        String country = "Украина";
        String name = "Polinka Belozorovich";
        String city = "Киев";
        String phone = "+380953974755";
        String email = "anilop18@ukr.net";


        MerchandisePage merchandisePage = new MerchandisePage(driver);


        Context_Filtering.filter_by_Price(merchandisePage, min, max);
        Context_Filtering.filter_by_Country(merchandisePage, country);


        ArticlePage articlePage = Context_Buying_Item.selectElement(merchandisePage, 0);
        int currentPrice = Context_Item_Verification.verify_Item_Price(articlePage, min, max);

        BasketPage basketPage = Context_Buying_Item.buy(articlePage);
        Context_Item_Verification.verify_Total_Price(basketPage, currentPrice);
        ContactsPage contactsPage = Context_Buying_Item.validate_Purchase(basketPage);

        DeliveryPage deliveryPage = Context_Input_User_Data.inputContacts(contactsPage, name, city, phone, email);
        Context_Item_Verification.verify_Total_Price(deliveryPage, currentPrice);
        Context_Item_Verification.verify_Purchase_Button_Is_Enabled(deliveryPage);

    }

    @After
    public void quit(){
        driver.quit();
    }
}