import jdk.jfr.Description;
import model.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import runner.BaseTest;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import static org.testng.Assert.assertEquals;

public class TextBoxPageTest extends BaseTest {

    @DataProvider(name = "valid-names")
    public Object[][] provideValidNames() {
        return new Object[][]
                {{"!"}, {"?"}}; //расширить потом пул
    }

    @Test(dataProvider = "wrong-chars")
    public void testInvalidChars() {

    }
/*    @Test(dependsOnMethods = "testFullNameInput")
    public void testCss() throws InterruptedException {
        WebElement nameTextBox = getNameTextBox();
        // так можно узнать координаты элемента:
        ((RemoteWebElement) nameTextBox).getCoordinates().inViewPort().getX();
        System.out.println(((RemoteWebElement) nameTextBox).getCoordinates().inViewPort().getY());
        System.out.println(((RemoteWebElement) nameTextBox).getCoordinates().inViewPort().getX());
        System.out.println(nameTextBox.getCssValue("border-color"));
        System.out.println(nameTextBox.getCssValue("box-shadow"));
        nameTextBox.sendKeys("123");
        getWait2().until(ExpectedConditions.textToBePresentInElementValue(nameTextBox, "123"));
        System.out.println(nameTextBox.getCssValue("border-color"));
        System.out.println(nameTextBox.getCssValue("box-shadow"));
    }

 */
    @Test
    public void testResponse() throws IOException {
        WebElement image = getDriver().findElement(By.xpath("//*[@id=\"app\"]/header/a/img"));
        String imageSrc = image.getAttribute("src");
        URL imageUrl = new URL(imageSrc);
        URLConnection urlConnection = imageUrl.openConnection();
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
        httpsURLConnection.connect();
        httpsURLConnection.setConnectTimeout(5000);
        assertEquals(httpsURLConnection.getResponseCode(), 200);
    }
    String name = "John Doe";
    String mail = "name@mail.com";
    String invalidMail = "name@mail.comm";
    String currAddress = "Lenina street 15";
    String permAddress = "Pushkina street 51";
    @Description("tb-1 Общий smoke test")
    @Test
        public void testSmoke() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage
                .inputName(name)
                .inputMail(mail)
                .inputCurrAddress(currAddress)
                .inputPermAddress(permAddress);

        Assert.assertEquals(mainPage.getTextInNameField(), name);
        Assert.assertEquals(mainPage.getTextInMailField(), mail);
        Assert.assertEquals(mainPage.getTextInCurrAddressField(), currAddress);
        Assert.assertEquals(mainPage.getTextInPermAddressField(), permAddress);

        mainPage.clickSubmitButton();

        Assert.assertEquals(mainPage.getTextInNameField(), name);
        Assert.assertEquals(mainPage.getTextInMailField(), mail);
        Assert.assertEquals(mainPage.getTextInCurrAddressField(), currAddress);
        Assert.assertEquals(mainPage.getTextInPermAddressField(), permAddress);
        Assert.assertEquals(mainPage.getSubmitNameText(), "Name:" + name);
        Assert.assertEquals(mainPage.getSubmitMail(), "Email:" + mail);
        Assert.assertEquals(mainPage.getSubmitCurrAddress(), "Current Address :" + currAddress);
        Assert.assertEquals(mainPage.getSubmitPermAddress(), "Permananet Address :" + permAddress);
    }
    @Description("tb-2 Данные не отправляются если поле Email заполнено некорректно")
    @Test
    public void testInvalidMail() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .inputName(name)
                .inputMail(invalidMail)
                .inputCurrAddress(currAddress)
                .inputPermAddress(permAddress)
                .clickSubmitButton();

        Thread.sleep(200);

        Assert.assertEquals(mainPage.getTextInNameField(), name);
        Assert.assertEquals(mainPage.getTextInMailField(), invalidMail);
        Assert.assertEquals(mainPage.getTextInCurrAddressField(), currAddress);
        Assert.assertEquals(mainPage.getTextInPermAddressField(), permAddress);
        Assert.assertFalse(mainPage.submittedFieldsIsDisplayed());
        Assert.assertEquals(mainPage.getColorOfMailField(), "rgb(255, 0, 0)");
    }
    @Description("tb-3 После обновления страницы все введенные данные очищаются")
    @Test
    public void testDataAfterPageRefresh() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .inputName(name)
                .inputMail(invalidMail)
                .inputCurrAddress(currAddress)
                .inputPermAddress(permAddress)
                .clickSubmitButton();

        Thread.sleep(200);

        Assert.assertEquals(mainPage.getTextInNameField(), name);
        Assert.assertEquals(mainPage.getTextInMailField(), invalidMail);
        Assert.assertEquals(mainPage.getTextInCurrAddressField(), currAddress);
        Assert.assertEquals(mainPage.getTextInPermAddressField(), permAddress);
        Assert.assertFalse(mainPage.submittedFieldsIsDisplayed());
        Assert.assertEquals(mainPage.getColorOfMailField(), "rgb(255, 0, 0)");

        getDriver().navigate().refresh();

        Assert.assertEquals(mainPage.getTextInNameField(), "");
        Assert.assertEquals(mainPage.getTextInMailField(), "");
        Assert.assertEquals(mainPage.getTextInCurrAddressField(), "");
        Assert.assertEquals(mainPage.getTextInPermAddressField(), "");
        Assert.assertFalse(mainPage.submittedFieldsIsDisplayed());
    }
}