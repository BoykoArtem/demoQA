import Data.TextBoxPageData;
import jdk.jfr.Description;
import model.MainPage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

@Test(dependsOnGroups = {"smoke"})
public class TextBoxPageTest extends BaseTest {

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
    public void testInvalidMail()  {
        MainPage mainPage = new MainPage(getDriver())
                .inputName(name)
                .inputMail(invalidMail)
                .inputCurrAddress(currAddress)
                .inputPermAddress(permAddress)
                .clickSubmitButton();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getMailTextBox(), "border-color", "rgb(255, 0, 0)"));

        Assert.assertEquals(mainPage.getTextInNameField(), name);
        Assert.assertEquals(mainPage.getTextInMailField(), invalidMail);
        Assert.assertEquals(mainPage.getTextInCurrAddressField(), currAddress);
        Assert.assertEquals(mainPage.getTextInPermAddressField(), permAddress);
        Assert.assertFalse(mainPage.submittedFieldsIsDisplayed());
        Assert.assertEquals(mainPage.getColorOfMailField(), "rgb(255, 0, 0)");
    }

    @Description("tb-3 После обновления страницы все введенные данные очищаются")
    @Test
    public void testDataAfterPageRefresh()  {
        MainPage mainPage = new MainPage(getDriver())
                .inputName(name)
                .inputMail(invalidMail)
                .inputCurrAddress(currAddress)
                .inputPermAddress(permAddress)
                .clickSubmitButton();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getMailTextBox(), "border-color", "rgb(255, 0, 0)"));

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


    @Description("Проверка названий полей")
    @Test
    public void testLabelsText() {
        List<String> expectedLabelsTexts = List.of("Full Name", "Email", "Current Address", "Permanent Address");

        List<String> actualLabelsTexts = new MainPage(getDriver())
                .getLabelsText();

        Assert.assertEquals(actualLabelsTexts, expectedLabelsTexts);
    }

    @Description("Проверка плейсхолдеров полей")
    @Test
    public void testPlaceholders() {
        String namePlaceholder = new MainPage(getDriver())
                .getNamePlaceholder();

        Assert.assertEquals(namePlaceholder, "Full Name");

        String mailPlaceholder = new MainPage(getDriver())
                .getMailPlaceholder();

        Assert.assertEquals(mailPlaceholder, "name@example.com");

        String currentAddressPlaceholder = new MainPage(getDriver())
                .getCurrAddressPlaceholder();

        Assert.assertEquals(currentAddressPlaceholder, "Current Address");

        String permanentAddressPlaceholder = new MainPage(getDriver())
                .getPermAddressPlaceholder();

        Assert.assertEquals(permanentAddressPlaceholder, "");
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

    @Description("Проверка включения/выключения подсветки активного поля ввода имени")
    @Test
    public void testNameInputHighlight() {
        MainPage mainPage = new MainPage(getDriver())
                .clickNameInput();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getNameTextBox(), "box-shadow", "rgba(0, 123, 255, 0.25) 0px 0px 0px 3.2px"));

        String focusedColor = mainPage.getHighlightColorOfNameField();

        Assert.assertEquals(focusedColor, "rgba(0, 123, 255, 0.25) 0px 0px 0px 3.2px");

        mainPage
                .clickNameInput()
                .clickMailInput();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getNameTextBox(), "box-shadow", "none"));

        String unfocusedColor = mainPage.getHighlightColorOfNameField();

        Assert.assertEquals(unfocusedColor, "none");
    }

    @Description("Проверка включения/выключения подсветки активного поля ввода почты")
    @Test
    public void testMailInputHighlight() {
        MainPage mainPage = new MainPage(getDriver())
                .clickMailInput();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getMailTextBox(), "box-shadow", "rgba(0, 123, 255, 0.25) 0px 0px 0px 3.2px"));

        String focusedColor = mainPage.getHighlightColorOfMailField();

        Assert.assertEquals(focusedColor, "rgba(0, 123, 255, 0.25) 0px 0px 0px 3.2px");

        mainPage
                .clickMailInput()
                .clickCurrAddressInput();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getMailTextBox(), "box-shadow", "none"));

        String unfocusedColor = mainPage.getHighlightColorOfMailField();

        Assert.assertEquals(unfocusedColor, "none");
    }

    @Description("Проверка включения/выключения подсветки активного поля ввода текущего адреса")
    @Test
    public void testCurrentAddressInputHighlight() {
        MainPage mainPage = new MainPage(getDriver())
                .clickCurrAddressInput();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getCurrAddressTextBox(), "box-shadow", "rgba(0, 123, 255, 0.25) 0px 0px 0px 3.2px"));

        String focusedColor = mainPage.getHighlightColorOfCurrAddressField();

        Assert.assertEquals(focusedColor, "rgba(0, 123, 255, 0.25) 0px 0px 0px 3.2px");

        mainPage
                .clickCurrAddressInput()
                .clickPermAddressInput();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getCurrAddressTextBox(), "box-shadow", "none"));

        String unfocusedColor = mainPage.getHighlightColorOfCurrAddressField();

        Assert.assertEquals(unfocusedColor, "none");
    }

    @Description("Проверка включения/выключения подсветки активного поля ввода прописки")
    @Test
    public void testPermanentAddressInputHighlight() {
        MainPage mainPage = new MainPage(getDriver())
                .clickPermAddressInput();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getPermAddressTextBox(), "box-shadow", "rgba(0, 123, 255, 0.25) 0px 0px 0px 3.2px"));

        String focusedColor = mainPage.getHighlightColorOfPermAddressField();

        Assert.assertEquals(focusedColor, "rgba(0, 123, 255, 0.25) 0px 0px 0px 3.2px");

        mainPage
                .clickPermAddressInput()
                .clickNameInput();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getPermAddressTextBox(), "box-shadow", "none"));

        String unfocusedColor = mainPage.getHighlightColorOfPermAddressField();

        Assert.assertEquals(unfocusedColor, "none");
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
    public void testInvalidMails(String invalidMails) {
        MainPage mainPage = new MainPage(getDriver())
                .inputMail(invalidMails)
                .clickSubmitButton();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getMailTextBox(), "border-color", "rgb(255, 0, 0)"));

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

        Assert.assertEquals(mainPage.getSubmittedNameText(), "Name:" + spaceBeforeText);
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

        Assert.assertEquals(mainPage.getSubmittedNameText(), "Name:" + spaceAfterText.trim());
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

    @Description("Проверка пустого инпута")
    @Test
    public void testEmptyInputs() {
        MainPage mainPage = new MainPage(getDriver())
                .clickSubmitButton();

        Assert.assertFalse(mainPage.submittedFieldsIsDisplayed());
    }

    @Description("Проверка цвета и ховера на кнопку Submit")
    @Test
    public void testSubmitButtonHover() {
        String defaultColor = "rgba(0, 123, 255, 1)";
        String hoveredColor = "rgba(0, 105, 217, 1)";

        MainPage mainPage = new MainPage(getDriver());
        Actions actions = new Actions(getDriver());

        Assert.assertEquals(mainPage.getColorOfSubmitButton(), defaultColor);

        actions.moveToElement(mainPage
                .getSubmitButton())
                .perform();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getSubmitButton(), "background-color", "rgba(0, 105, 217, 1)"));

        Assert.assertEquals(mainPage.getColorOfSubmitButton(), hoveredColor);
    }

    @Description("Проверка включения/выключения подсветки кнопки Submit")
    @Test
    public void testSubmitButtonHighlight() {
        MainPage mainPage = new MainPage(getDriver())
                .clickSubmitButton();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getSubmitButton(), "box-shadow", "rgba(38, 143, 255, 0.5) 0px 0px 0px 3.2px"));

        String focusedColor = mainPage.getHighlightColorOfSubmitButton();

        Assert.assertEquals(focusedColor, "rgba(38, 143, 255, 0.5) 0px 0px 0px 3.2px");

        mainPage
                .clickSubmitButton()
                .clickNameInput();

        getWait2().until(ExpectedConditions
                .attributeToBe(mainPage
                .getSubmitButton(), "box-shadow", "none"));

        String unfocusedColor = mainPage.getHighlightColorOfSubmitButton();

        Assert.assertEquals(unfocusedColor, "none");
    }
}