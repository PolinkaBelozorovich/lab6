package ua.org.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class RozetkaPage {
    public RozetkaPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public static WebDriver driver;

    @FindBy(id = "price[min]")
    private WebElement minline;


    public void inputText(String text) throws IOException {
        minline.clear();
        minline.click();
        minline.sendKeys(text);
        driver.findElement(By.id("submitprice")).click();

    }


    public void check(String price) {
        if (minline.getAttribute("value").compareTo(price) != 0) {
            System.out.println("Test 1 failed");
        }
    }

    public void check2(String price) {
        for(WebElement element : driver.findElements(By.xpath("//*[@class='g-price-uah']")))
        {
            if (Integer.valueOf(element.getText().replaceAll("[^0-9]", "")) < 5000)
            {
                System.out.println("Test 2 failed");
                break;
            }
        }
    }
}

