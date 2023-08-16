package model;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }
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
    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[6]/div/p[3]")
    private WebElement submittedCurrAddress;
    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[2]/form/div[6]/div/p[4]")
    private WebElement submittedPermAddress;

    public MainPage inputName(String name) {
        nameTextBox.sendKeys(name);
        return this;
    }
    public MainPage inputMail(String mail) {
        mailTextBox.sendKeys(mail);
        return this;

    }
    public WebElement getMailTextBox() {
        return mailTextBox;
    }
    public MainPage inputCurrAddress(String currAddress) {
        currAddressTextBox.sendKeys(currAddress);
        return this;
    }
    public MainPage inputPermAddress(String permAddress) {
        permAddressTextBox.sendKeys(permAddress);
        return this;
    }
    public WebElement getSubmitName() {
        return submittedName;
    }
    public String getSubmitNameText() {
        return submittedName.getText();
    }
    public String getSubmitMail() {
        return submittedMail.getText();
    }
    public String getSubmitCurrAddress() {
        return submittedCurrAddress.getText();
    }
    public String getSubmitPermAddress() {
        return submittedPermAddress.getText();
    }
    public String getTextInNameField() {
        return nameTextBox.getAttribute("value");
    }
    public String getTextInMailField() {
        return mailTextBox.getAttribute("value");
    }
    public String getColorOfMailField() {
        return mailTextBox.getCssValue("border-color");
    }
    public String getTextInCurrAddressField() {
        return currAddressTextBox.getAttribute("value");
    }
    public String getTextInPermAddressField() {
        return permAddressTextBox.getAttribute("value");
    }
    public MainPage clickSubmitButton() {
        submitButton.click();
        return new MainPage(getDriver());
    }

    public boolean submittedFieldsIsDisplayed() {
        try {
            return submittedName.isDisplayed() || submittedMail.isDisplayed() ||
                   submittedCurrAddress.isDisplayed() || submittedPermAddress.isDisplayed();
        } catch (NoSuchElementException e) {

            return false;
        }
    }

}