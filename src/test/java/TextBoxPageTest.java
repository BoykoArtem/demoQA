import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import runner.BaseTest;
import static org.testng.Assert.assertEquals;

public class TextBoxPageTest extends BaseTest {

    String url = "https://demoqa.com/text-box/";

    @Test
    public void testFullNameInput() throws InterruptedException {
        getDriver().get(url);
        WebElement nameTextBox = getDriver().findElement(By.xpath("//*[@id=\"userName\"]"));
        WebElement submitButton = getDriver().findElement(By.xpath("//*[@id=\"submit\"]"));

        nameTextBox.sendKeys("FirstName LastName");
        submitButton.click();

        WebElement resultField = getDriver().findElement(By.xpath("//*[@id=\"name\"]"));

        assertEquals(resultField.getText(), "Name:FirstName LastName", "Input did not matched");
    }
}