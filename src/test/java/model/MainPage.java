package model;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]/div")
    private WebElement header;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[1]")
    private WebElement backgroundImage;

    @FindBy(xpath = "//*[@class=\"form-label\"]")
    private List<WebElement> inputLabels;

    @FindBy(xpath = "//*[@id=\"userName\"]")
    private WebElement nameTextBox;

    @FindBy(xpath = "//*[@id=\"userEmail\"]")
    private WebElement mailTextBox;

    @FindBy(xpath = "//*[@id=\"currentAddress\"]")
    private WebElement currAddressTextBox;

    @FindBy(xpath = "//*[@id=\"permanentAddress\"]")
    private WebElement permAddressTextBox;

    @FindBy(xpath = "//*[@id=\"submit\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement submittedName;

    @FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement submittedMail;

    @FindBy(xpath = "//*[@id=\"output\"]/div//*[@id=\"currentAddress\"]")
    private WebElement submittedCurrAddress;

    @FindBy(xpath = "//*[@id=\"output\"]/div//*[@id=\"permanentAddress\"]")
    private WebElement submittedPermAddress;

    public WebElement getNameTextBox() {
        return nameTextBox;
    }

    @Step("check the header")
    public String getHeaderText() {
        return header.getText();
    }

    @Step("check url of background image")
    public String getBackgroundImage() {
        return backgroundImage.getCssValue("background-image");
    }

    public static List<String> listLabels(List<WebElement> elementList) {
        List<String> stringList = new ArrayList<>();
        for (WebElement element : elementList) {
            stringList.add(element.getText());
        }
        return stringList;
    }

    @Step("get labels text")
    public List<String> getLabelsText() {
        return listLabels(inputLabels);
    }

    @Step("input full name '{name}' in the name input field on Main page")
    public MainPage inputName(String name) {
        nameTextBox.sendKeys(name);
        return this;
    }

    @Step("input email '{mail}' in the email input field on Main page")
    public MainPage inputMail(String mail) {
        mailTextBox.sendKeys(mail);
        return this;
    }

    @Step("input current address '{currAddress}' in the Current Address input field on Main page")
    public MainPage inputCurrAddress(String currAddress) {
        currAddressTextBox.sendKeys(currAddress);
        return this;
    }

    @Step("input permanent address '{permAddress}' in the Permanent Address input field on Main page")
    public MainPage inputPermAddress(String permAddress) {
        permAddressTextBox.sendKeys(permAddress);
        return this;
    }

    @Step("check text in Submitted Name field at the bottom of the page")
    public String getSubmittedNameText() {
        return submittedName.getText();
    }

    @Step("check text in Submitted Email field at the bottom of the page")
    public String getSubmittedMail() {
        return submittedMail.getText();
    }

    @Step("check text in Submitted Current Address field at the bottom of the page")
    public String getSubmittedCurrAddress() {
        return submittedCurrAddress.getText();
    }

    @Step("check text in Submitted Permanent Address field at the bottom of the page")
    public String getSubmittedPermAddress() {
        return submittedPermAddress.getText();
    }

    @Step("check entered text in Full Name input field on Main page")
    public String getTextInNameField() {
        return nameTextBox.getAttribute("value");
    }

    @Step("check entered text in Email input field on Main page")
    public String getTextInMailField() {
        return mailTextBox.getAttribute("value");
    }

    @Step("check current highlight color of Full Name input field")
    public String getHighlightColorOfNameField() {
        return nameTextBox.getCssValue("box-shadow");
    }

    @Step("check current highlight color of Email input field")
    public String getHighlightColorOfMailField() {
        return mailTextBox.getCssValue("box-shadow");
    }

    @Step("check current border color of Email input field")
    public String getColorOfMailField() {
        return mailTextBox.getCssValue("border-color");
    }

    @Step("check current highlight color of Current Address input field")
    public String getHighlightColorOfPermAddressField() {
        return permAddressTextBox.getCssValue("box-shadow");
    }

    @Step("check current highlight color of Permanent Address input field")
    public String getHighlightColorOfCurrAddressField() {
        return currAddressTextBox.getCssValue("box-shadow");
    }

    @Step("check entered text in Current Address input field on Main page")
    public String getTextInCurrAddressField() {
        return currAddressTextBox.getAttribute("value");
    }

    @Step("check entered text in Permanent Address input field on Main page")
    public String getTextInPermAddressField() {
        return permAddressTextBox.getAttribute("value");
    }

    @Step("click 'Submit' button")
    public MainPage clickSubmitButton() {
        submitButton.click();
        return new MainPage(getDriver());
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    @Step("get current color of Submit button")
    public String getColorOfSubmitButton() {
        return submitButton.getCssValue("background-color");
    }

    @Step("get current highlight color of Submit button")
    public String getHighlightColorOfSubmitButton() {
        return submitButton.getCssValue("box-shadow");
    }

    @Step("click on Name input")
    public MainPage clickNameInput() {
        nameTextBox.click();
        return new MainPage(getDriver());
    }

    @Step("click on Email input")
    public MainPage clickMailInput() {
        mailTextBox.click();
        return new MainPage(getDriver());
    }

    @Step("click on Current Address input")
    public MainPage clickCurrAddressInput() {
        currAddressTextBox.click();
        return new MainPage(getDriver());
    }

    @Step("click on Permanent Address input")
    public MainPage clickPermAddressInput() {
        permAddressTextBox.click();
        return new MainPage(getDriver());
    }
    @Step("check if submitted fields are NOT displayed at the bottom of the page")
    public boolean submittedFieldsIsDisplayed() {
        try {
            return submittedName.isDisplayed() | submittedMail.isDisplayed() &
                    submittedCurrAddress.isDisplayed() | submittedPermAddress.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("check Full Name input field placeholder")
    public String getNamePlaceholder() {
        return nameTextBox.getAttribute("placeholder");
    }

    @Step("check Email input field placeholder")
    public String getMailPlaceholder() {
        return mailTextBox.getAttribute("placeholder");
    }

    @Step("check Current Address input field placeholder")
    public String getCurrAddressPlaceholder() {
        return currAddressTextBox.getAttribute("placeholder");
    }

    @Step("check Permanent Address input field placeholder")
    public String getPermAddressPlaceholder() {
        return permAddressTextBox.getAttribute("placeholder");
    }

    @Step("refresh page")
    public void getPageRefresh() {
        getDriver().navigate().refresh();
    }

}

