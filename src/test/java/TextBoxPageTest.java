import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import runner.BaseTest;
import static org.testng.Assert.assertEquals;

public class TextBoxPageTest extends BaseTest {

    @Test
    public void testFullNameInput() throws InterruptedException {
        getDriver().get(getUrl());
        WebElement nameTextBox = getDriver().findElement(By.xpath("//*[@id=\"userName\"]"));
        WebElement submitButton = getDriver().findElement(By.xpath("//*[@id=\"submit\"]"));
        WebElement resultField = getDriver().findElement(By.xpath("//*[@id=\"name\"]"));

        nameTextBox.sendKeys("FirstName LastName");
        submitButton.click();

        String textInField = nameTextBox.getAttribute("value");

        assertEquals(textInField, "FirstName LastName");
        assertEquals(resultField.getText(), "Name:FirstName LastName", "Input did not matched");
    }
}