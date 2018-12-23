package ua.org.autotest.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ua.org.autotest.pages.RozetkaPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SecondTest {
    private static WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        System.setProperty("webdriver.gecko.driver", "/Users/polinabelozorovich/Downloads/geckodriver");
    }

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get("https://rozetka.com.ua/soft_toys/c99051/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void Test1() {
        RozetkaPage rozetkaPage = new RozetkaPage(driver);
        String Tital = "5000";
        try {
            rozetkaPage.inputText(Tital);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        rozetkaPage.check(Tital);
        rozetkaPage.check2(Tital);
    }



    @After
    public void quit(){
        driver.quit();
    }
}
