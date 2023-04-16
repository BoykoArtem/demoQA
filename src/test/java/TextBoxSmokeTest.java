import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class TextBoxSmokeTest extends BaseTest {
    @Test
    public void testSmoke() throws InterruptedException {
        getDriver().get(getUrl());

        WebElement nameTextBox = getNameTextBox();
        WebElement mailTextBox = getMailTextBox();
        WebElement currAddressTextBox = getCurrAddressTextBox();
        WebElement permAddressTextBox = getPermAddressTextBox();
        WebElement submitButton = getSubmitButton();

        nameTextBox.sendKeys("John Doe");
        mailTextBox.sendKeys("name@mail.com");
        currAddressTextBox.sendKeys("Lenina street 15");
        permAddressTextBox.sendKeys("Pushkina street 51");

        submitButton.click();

        WebElement submitName = getSubmitName();
        WebElement submitEmail = getSubmitEmail();
        WebElement submitCurrAddress = getSubmitCurrAddress();
        WebElement submitPermAddress = getSubmitPermAddress();

        String textInName = nameTextBox.getAttribute("value");
        String textInMail = mailTextBox.getAttribute("value");
        String textInCurrAddress = currAddressTextBox.getAttribute("value");
        String textInPermAddress = permAddressTextBox.getAttribute("value");

        assertEquals(textInName, "John Doe");
        assertEquals(textInMail, "name@mail.com");
        assertEquals(textInCurrAddress, "Lenina street 15");
        assertEquals(textInPermAddress, "Pushkina street 51");

        assertEquals(submitName.getText(), "Name:John Doe");
        assertEquals(submitEmail.getText(), "Email:name@mail.com");
        assertEquals(submitCurrAddress.getText(), "Current Address :Lenina street 15");
        assertEquals(submitPermAddress.getText(), "Permananet Address :Pushkina street 51");
    }
}
