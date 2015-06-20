package base.elements;

import base.utils.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tests.TestData;


public class TravellersForm extends FormDecorator
{

    @FindBy(xpath = "//a[@id='pgButtonProceed']/span/span")
    public static WebElement buttonNext;
    private static Logger logger = Logger.getLogger(TravellersForm.class.getName());
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

    protected final String pageName = "Guest information";


    public TravellersForm(Form decoratedForm)
    {
        super(decoratedForm);
        PageFactory.initElements(Utils.getHandler(), this);
    }


    @Deprecated
    @Override
    public void fill(String param1, String param2, String param3) {

    }

    @Override
    public void fill()
    {

        logger.info("Fill the page:\n" + getPageName());
        Select dropdownADT = new Select(travelerTitle);
        dropdownADT.selectByVisibleText(TestData.TravellerInfo.travellerTitle);

        firstName.sendKeys(TestData.TravellerInfo.firstName);
        lastName.sendKeys(TestData.TravellerInfo.lastName);
        emailAddress.sendKeys(TestData.TravellerInfo.email);
        confirmEmail.sendKeys(TestData.TravellerInfo.confirmEmail);
        mobileNum.sendKeys(TestData.TravellerInfo.mobileNumber);
    }

    @Override
    public String getPageName() {
        return pageName;
    }

}
