package base.elements;

import base.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tests.TestData;


public class TravellersForm
{
    @FindBy(xpath = "//a[@id='pgButtonProceed']/span/span")
    public static WebElement buttonNext;
    @FindBy(xpath = "//select[@id='travellersInfo[0].title']")
    private static WebElement travelerTitle;
    @FindBy(xpath = "//input[@id='travellersInfo[0].firstName']")
    private static WebElement firstName;
    @FindBy(xpath = "//input[@id='travellersInfo[0].lastName']")
    private static WebElement lastName;
    @FindBy(css = "#emailAddress")
    private static WebElement emailAddress;
    @FindBy(css = "#confirmEmail")
    private static WebElement confirmEmail;
    @FindBy(xpath = "//input[@id='travellersInfo[0].homePhone.phoneNumber']")
    private static WebElement mobileNum;

    public TravellersForm()
    {
        PageFactory.initElements(Utils.getHandler(), this);
    }


    public void fill()
    {

        Select dropdownADT = new Select(travelerTitle);
        dropdownADT.selectByValue(TestData.TravellerInfo.travellerTitle);

        firstName.sendKeys(TestData.TravellerInfo.firstName);
        lastName.sendKeys(TestData.TravellerInfo.lastName);
        emailAddress.sendKeys(TestData.TravellerInfo.email);
        confirmEmail.sendKeys(TestData.TravellerInfo.confirmEmail);
        mobileNum.sendKeys(TestData.TravellerInfo.mobileNumber);
    }

    public void nextPage()
    {
        if (buttonNext.isEnabled())
        {
            buttonNext.click();
        }
        Utils.getHandler().waitForNextPageToLoad();
    }
}
