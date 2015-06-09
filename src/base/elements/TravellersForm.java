package base.elements;

import base.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TravellersForm
{
    @FindBy(xpath = "//a[@id='pgButtonNext']")
    public static WebElement buttonNext;
    @FindBy(xpath = "travellersInfo[0].title")
    private static WebElement travelerTitle;

    public TravellersForm()
    {
        PageFactory.initElements(Utils.getHandler(), this);
    }
}
