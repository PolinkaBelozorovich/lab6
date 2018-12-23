package ua.org.autotest.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.org.autotest.pages.GooglePage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        System.setProperty("webdriver.gecko.driver", "/Users/polinabelozorovich/Downloads/geckodriver");

    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test // 1 page
    public void Test1() {
        GooglePage googlePage = new GooglePage(driver);
        String Tital = "pony";
        try {
            googlePage.inputText(Tital);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String Css = "//a[contains(@href, 'http://www.ponyexpress-ua.com/')]";
        googlePage.PageLinkSearch(Tital, Css);
    }

    @Test // n page
    public void Test2() {
        GooglePage googlePage = new GooglePage(driver);
        String Tital = "pony";
        try {
            googlePage.inputText(Tital);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String ss = "//a[contains(@href,'https://www.amazon.com/My-Little-Pony-Ponies-Collection/dp/B076QSZBNH')]";
        googlePage.PageLinkSearch(Tital, ss);
    }

    @Test // search(no)
    public void Test3() {
            GooglePage googlePage = new GooglePage(driver);
            String Tital = "майка";
            try {
                googlePage.inputText(Tital);
            } catch (IOException e) {
                e.printStackTrace();
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String gg = "//a[contains(@href,'https://www.hm.com/')]";
        googlePage.PageLinkSearch1(Tital, gg);

    }

    @After
    public void quit(){
        driver.quit();
    }

}