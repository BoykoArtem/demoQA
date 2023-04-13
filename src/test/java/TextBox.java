import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class TextBox {

    WebDriver driver;
    String url = "https://demoqa.com/text-box/";

    @BeforeSuite
    public void setUp() {

    }
    @BeforeTest
    public void profileSetup() {
         ChromeOptions chromeOptions = new ChromeOptions();
         chromeOptions.addArguments("--window-size=1920,1080");

         driver = new ChromeDriver(chromeOptions);
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @BeforeClass
    public void goToURL() {
        driver.get(url);
    }
    @AfterClass
    public void closeUp () {
        driver.quit();
    }
    @Test
    public void fullNameInput() throws InterruptedException {

        WebElement nameTextBox = driver.findElement(By.xpath("//*[@id=\"userName\"]"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"submit\"]"));

        nameTextBox.sendKeys("FirstName LastName");
        submitButton.click();

        WebElement resultField = driver.findElement(By.xpath("//*[@id=\"name\"]"));

        assertEquals(resultField.getText(), "Name:FirstName LastName", "Input did not matched");

        }
    }
