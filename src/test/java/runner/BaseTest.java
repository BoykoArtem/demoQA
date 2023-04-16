package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(chromeOptions);
    }
    @AfterMethod
    protected void afterMethod() {
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected String getUrl() {
        return "https://demoqa.com/text-box/";
    }

    protected WebElement getNameTextBox() {
        return getDriver().findElement(By.xpath("//*[@id=\"userName\"]"));
    }

    protected WebElement getMailTextBox() {
        return getDriver().findElement(By.xpath("//*[@id=\"userEmail\"]"));
    }

    protected WebElement getCurrAddressTextBox() {
        return getDriver().findElement(By.xpath("//*[@id=\"currentAddress\"]"));
    }

    protected WebElement getPermAddressTextBox() {
        return getDriver().findElement(By.xpath("//*[@id=\"permanentAddress\"]"));
    }

    protected WebElement getSubmitButton() {
        return getDriver().findElement(By.xpath("//*[@id=\"submit\"]"));
    }

    protected  WebElement getSubmitName() {
        return getDriver().findElement(By.xpath("//*[@id=\"name\"]"));
    }

    protected WebElement getSubmitEmail() {
        return getDriver().findElement(By.xpath("//*[@id=\"email\"]"));
    }

    protected WebElement getSubmitCurrAddress() {
        return getDriver().findElement(By.xpath("//div[2]//div[2]/div[2]/div[2]//div[6]//p[3]"));
    }

    protected WebElement getSubmitPermAddress() {
        return getDriver().findElement(By.xpath("//div[2]//div[2]/div[2]/div[2]//div[6]//p[4]"));
    }
}