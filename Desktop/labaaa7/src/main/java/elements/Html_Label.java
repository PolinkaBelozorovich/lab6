package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Html_Label extends Element {
    public Html_Label(WebElement htmlLabel) {
        super(htmlLabel);
    }

    public String getLableName(){
        By nestedText = new By.ByClassName("filter-parametrs-i-l-i-default-title");
        return findElement(nestedText).getText() ;
    }
}