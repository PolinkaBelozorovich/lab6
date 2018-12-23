package ua.org.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class GooglePage {
    public GooglePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public static WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchline;


    public void inputText(String text) throws IOException{
        searchline.clear();
        searchline.click();
        searchline.sendKeys(text);
        searchline.sendKeys(Keys.ENTER);
    }

    public static void Screen(String tital, int number) {

        try {
            String targetUrl = driver.getCurrentUrl();
            String targetImg="google.png" + tital + number + ".png";
            String command = "/Users/polinabelozorovich/Downloads/phantomjs-2.1.1-macosx/bin/phantomjs /Users/polinabelozorovich/Downloads/phantomjs-2.1.1-macosx/examples/rasterize.js "+targetUrl + " " +targetImg;
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void PageLinkSearch(String tit, String CSS){
        for (int pageNumber = 1; pageNumber <= 19; pageNumber++) {
            if (driver.findElements(By.xpath(CSS)).size() > 0) {
                System.out.println("page_number is " + pageNumber);
                GooglePage.Screen(tit, pageNumber);
                break;
            } else {
                driver.findElement(By.cssSelector("#pnnext > span:nth-child(2)")).click();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
        }
    }


    public void PageLinkSearch1(String tit, String CSS){
        for (int pageNumber = 1; pageNumber <= 19; pageNumber++) {
            if (driver.findElements(By.xpath(CSS)).size() > 0) {
                System.out.println("page_number is " + pageNumber);
                GooglePage.Screen(tit, pageNumber);
                break;
            } else {
                driver.findElement(By.cssSelector("#pnnext > span:nth-child(2)")).click();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                GooglePage.Screen(tit, pageNumber);
            }
        }
    }
}
