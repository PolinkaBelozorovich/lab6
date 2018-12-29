package contexts;

import pages.*;

public class Context_Filtering {
    public static void filter_by_Price(MerchandisePage page, int minPrice, int maxPrice){
        filter_set_Price(page, minPrice, maxPrice);
        filter_submit_Price(page);
    }

    private static void filter_set_Price(MerchandisePage page, int min_Price, int max_Price){
        page.set_pricemin(min_Price);
        page.set_pricemax(max_Price);
    }

    private static void filter_submit_Price(MerchandisePage page){
        page.submitPrice.click();
    }

    public static void filter_by_Country(MerchandisePage page, String country){
        page.set_Country(country);
    }

}