package contexts;

import org.testng.Assert;
import pages.*;

public class Context_Item_Verification {
    public static int verify_Item_Price(ArticlePage page, int min_Value, int max_Value){
        int actual_Price = page.get_Price();
        Assert.assertTrue(actual_Price >= min_Value);
        Assert.assertTrue(actual_Price <= max_Value);
        return actual_Price;
    }

    public static void verify_Total_Price(BasketPage page, int actual_Price){
        int total_Price = page.getPrice();
        Assert.assertEquals(total_Price, actual_Price);
    }

    public static void verify_Total_Price(DeliveryPage page, int actual_Price){
        int total_Price = page.getPrice();
        Assert.assertEquals(total_Price, actual_Price);
    }

    public static void verify_Purchase_Button_Is_Enabled(DeliveryPage page){
        Assert.assertTrue(page.bbutton.isEnabled());
    }
}