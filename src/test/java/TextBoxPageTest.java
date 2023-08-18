import jdk.jfr.Description;
import model.MainPage;
import org.testng.Assert;
import org.testng.annotations.*;
import runner.BaseTest;

@Test(dependsOnGroups = {"smoke"})
public class TextBoxPageTest extends BaseTest {
    /*
        @DataProvider(name = "valid-names")
        public Object[][] provideValidNames() {
            return new Object[][]
                    {{"!"}, {"?"}}; //расширить потом пул
        }

        @Test(dataProvider = "wrong-chars")
        public void testInvalidChars() {

        }
        @Test(dependsOnMethods = "testFullNameInput")
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
        */
    private static final String name = "John Doe";
    private static final String mail = "name@mail.com";
    private static final String invalidMail = "name@mail.comm";
    private static final String currAddress = "Lenina street 15";
    private static final String permAddress = "Pushkina street 51";

    @Description("tb-1 Общий smoke test")
    @Test(groups = {"smoke"})
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
        Assert.assertEquals(mainPage.getSubmittedNameText(), "Name:" + name);
        Assert.assertEquals(mainPage.getSubmittedMail(), "Email:" + mail);
        Assert.assertEquals(mainPage.getSubmittedCurrAddress(), "Current Address :" + currAddress);
        Assert.assertEquals(mainPage.getSubmittedPermAddress(), "Permananet Address :" + permAddress);
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
    @Description("Проверка плейсхолдеров полей")
    @Test
    public void testPlaceholders() {
        String namePlaceholder = new MainPage(getDriver())
                .getNamePlaceholder();

        Assert.assertEquals(namePlaceholder,"Full Name");

        String mailPlaceholder = new MainPage(getDriver())
                .getMailPlaceholder();

        Assert.assertEquals(mailPlaceholder,"name@example.com");

        String currentAddressPlaceholder = new MainPage(getDriver())
                .getCurrAddressPlaceholder();

        Assert.assertEquals(currentAddressPlaceholder,"Current Address");

        String permanentAddressPlaceholder = new MainPage(getDriver())
                .getPermAddressPlaceholder();

        Assert.assertEquals(permanentAddressPlaceholder,"");
    }
    @Description("Проверка текста в хедере")
    @Test
    public void testHeader() {
        String headerText = new MainPage(getDriver()).getHeaderText();

        Assert.assertEquals(headerText, "Text Box");
    }
    @Description("Проверка url картинки в хедере")
    @Test
    public void testHeaderBackgroundImage() {
        String headerBackgroundSrc = new MainPage(getDriver())
                .getBackgroundImage();

        Assert.assertEquals(headerBackgroundSrc, "url(\"https://demoqa.com/images/gplaypattern.jpg\")");
    }

    @Description("Проверка подсветки активного поля ввода имени")
    @Test
    public void testActiveNameInputHighlight() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .clickNameInput();

        Thread.sleep(200);

        String actualColor = mainPage.getColorOfNameField();

        Assert.assertEquals(actualColor, "rgb(128, 189, 255)");
    }

    @Description("Проверка подсветки активного поля ввода gjxns")
    @Test
    public void testActiveMailInputHighlight() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .clickMailInput();

        Thread.sleep(200);

        String actualColor = mainPage.getColorOfMailField();

        Assert.assertEquals(actualColor, "rgb(128, 189, 255)");
    }

    @Description("Проверка подсветки активного поля ввода текущего адреса")
    @Test
    public void testActiveCurrentAddressInputHighlight() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .clickCurrAddressInput();

        Thread.sleep(200);

        String actualColor = mainPage.getColorOfCurrAddressField();

        Assert.assertEquals(actualColor, "rgb(128, 189, 255)");
    }

    @Description("Проверка подсветки активного поля ввода прописки")
    @Test
    public void testActivePermanentAddressInputHighlight() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .clickPermAddressInput();

        Thread.sleep(200);

        String actualColor = mainPage.getColorOfPermAddressField();

        Assert.assertEquals(actualColor, "rgb(128, 189, 255)");
    }
}