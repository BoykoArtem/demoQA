package runner;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public abstract class BaseTest {
    private WebDriver driver;

    private WebDriverWait wait2;

    protected WebDriver getDriver() {
        return driver;
    }

    protected String getUrl() {
        return "https://demoqa.com/text-box/";
    }

    @BeforeMethod
    protected void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        //40 секунд занимает неудачная подгрузка рекламы от гугла, нам такое не надо
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get(getUrl());
    }

    @AfterMethod
    protected void afterMethod() {
        driver.quit();
    }

    protected WebDriverWait getWait2() {
        if (wait2 == null) {
            wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
        }
        return wait2;
    }
}