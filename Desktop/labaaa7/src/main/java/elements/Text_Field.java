package elements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Text_Field extends Element {
    public Text_Field(WebElement nativeElement){
        super(nativeElement);
    }

    public void clear() {
        sendKeys(Keys.CONTROL + "a");
        sendKeys(Keys.DELETE);
    }

    public void setValue(String value){
        clear();
        sendKeys(value);
    }

    public String getValue(){
        return (getText()==null || getText().isEmpty()) ? getAttribute("value") : getText();
    }

    public void addText(String value){
        sendKeys(value);
    }
}