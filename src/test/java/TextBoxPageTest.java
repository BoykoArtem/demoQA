import Data.TextBoxPageData;
import jdk.jfr.Description;
import model.MainPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import runner.BaseTest;

//@Test(dependsOnGroups = {"smoke"})
public class TextBoxPageTest extends BaseTest {
    /*

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
    private static final String spaceBeforeText = " john@doe.com";
    private static final String spaceAfterText = "john@doe.com ";



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

        mainPage.getPageRefresh();
        getWait2().until(ExpectedConditions.visibilityOf(mainPage.getNameTextBox()));

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

        Thread.sleep(250);

        String actualColor = mainPage.getColorOfNameField();

        Assert.assertEquals(actualColor, "rgb(128, 189, 255)");
    }

    @Description("Проверка подсветки активного поля ввода почты")
    @Test
    public void testActiveMailInputHighlight() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .clickMailInput();

        Thread.sleep(250);

        String actualColor = mainPage.getColorOfMailField();

        Assert.assertEquals(actualColor, "rgb(128, 189, 255)");
    }

    @Description("Проверка подсветки активного поля ввода текущего адреса")
    @Test
    public void testActiveCurrentAddressInputHighlight() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .clickCurrAddressInput();

        Thread.sleep(250);

        String actualColor = mainPage.getColorOfCurrAddressField();

        Assert.assertEquals(actualColor, "rgb(128, 189, 255)");
    }

    @Description("Проверка подсветки активного поля ввода прописки")
    @Test
    public void testActivePermanentAddressInputHighlight() throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .clickPermAddressInput();

        Thread.sleep(250);

        String actualColor = mainPage.getColorOfPermAddressField();

        Assert.assertEquals(actualColor, "rgb(128, 189, 255)");
    }

    @Description("Валидация поля ввода имени")
    @Test(dataProvider = "validNames", dataProviderClass = TextBoxPageData.class)
    public void testValidNames(String validNames) {
        MainPage mainPage = new MainPage(getDriver())
                .inputName(validNames)
                .clickSubmitButton();

        Assert.assertEquals(mainPage.getSubmittedNameText(), "Name:" + validNames.trim());
    }

    @Description("Валидация поля ввода почты. Валидные адреса")
    @Test(dataProvider = "validMails", dataProviderClass = TextBoxPageData.class)
    public void testValidMails(String validMails) {
        MainPage mainPage = new MainPage(getDriver())
                .inputMail(validMails)
                .clickSubmitButton();

        Assert.assertEquals(mainPage.getSubmittedMail(), "Email:" + validMails.trim());
    }

    @Description("Валидация поля ввода почты. Невалиддные адреса")
    @Test(dataProvider = "invalidMails", dataProviderClass = TextBoxPageData.class)
    public void testInvalidMails(String invalidMails) throws InterruptedException {
        MainPage mainPage = new MainPage(getDriver())
                .inputMail(invalidMails)
                .clickSubmitButton();

        Thread.sleep(200);

        Assert.assertFalse(mainPage.submittedFieldsIsDisplayed());
        Assert.assertEquals(mainPage.getColorOfMailField(), "rgb(255, 0, 0)");
    }

    @Description("Валидация поля ввода текущего адреса")
    @Test(dataProvider = "validNames", dataProviderClass = TextBoxPageData.class)
    public void testValidCurrentAddresses(String validNames) {
        MainPage mainPage = new MainPage(getDriver())
                .inputCurrAddress(validNames)
                .clickSubmitButton();

        Assert.assertEquals(mainPage.getSubmittedCurrAddress(), "Current Address :" + validNames.trim());
    }

    @Description("Валидация поля ввода прописки")
    @Test(dataProvider = "validNames", dataProviderClass = TextBoxPageData.class)
    public void testValidPermanentAddresses(String validNames) {
        MainPage mainPage = new MainPage(getDriver())
                .inputPermAddress(validNames)
                .clickSubmitButton();

        Assert.assertEquals(mainPage.getSubmittedPermAddress(), "Permananet Address :" + validNames.trim());
    }

    @Description("Проверка обрезания пробелов перед текстом в поля ввода имени, адреса и прописки")
    @Test
    public void testSpacesBeforeTextTrim() {
        MainPage mainPage = new MainPage(getDriver())
                .inputName(spaceBeforeText)
                .inputCurrAddress(spaceBeforeText)
                .inputPermAddress(spaceBeforeText)
                .clickSubmitButton();

        Assert.assertEquals(mainPage.getSubmittedNameText(),"Name:" + spaceBeforeText);
        Assert.assertEquals(mainPage.getSubmittedCurrAddress(), "Current Address :" + spaceBeforeText);
        Assert.assertEquals(mainPage.getSubmittedPermAddress(), "Permananet Address :" + spaceBeforeText);
    }

    @Description("Проверка обрезания пробелов после текста в поля ввода имени, адреса и прописки")
    @Test
    public void testSpacesAfterTextTrim() {
        MainPage mainPage = new MainPage(getDriver())
                .inputName(spaceAfterText)
                .inputCurrAddress(spaceAfterText)
                .inputPermAddress(spaceAfterText)
                .clickSubmitButton();

        Assert.assertEquals(mainPage.getSubmittedNameText(),"Name:" + spaceAfterText.trim());
        Assert.assertEquals(mainPage.getSubmittedCurrAddress(), "Current Address :" + spaceAfterText.trim());
        Assert.assertEquals(mainPage.getSubmittedPermAddress(), "Permananet Address :" + spaceAfterText.trim());
    }

    @Description("Проверка обрезания пробелов в поле ввода почты")
    @Test(dataProvider = "Spaces", dataProviderClass = TextBoxPageData.class)
    public void testMailInputSpacesTrim(String textWithSpaces) {
        MainPage mainPage = new MainPage(getDriver())
                .inputMail(textWithSpaces)

                .clickSubmitButton();

        Assert.assertEquals(mainPage.getSubmittedMail(), "Email:" + textWithSpaces.trim());
    }

}