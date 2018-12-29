package contexts;

import pages.*;

public class Context_Input_User_Data {
    public static DeliveryPage inputContacts(ContactsPage page, String name, String city, String phone, String email){
        page.phone.setValue(phone);
        page.email.setValue(email);
        page.name.setValue(name);
        if (!page.city.getValue().equals(city))
            page.city.setValue(city);
        page.next_button.click();
        return new DeliveryPage(page.getDriver());
    }
}