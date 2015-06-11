package base.pages;

import base.utils.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SelectPage
{
    private static Logger logger = Logger.getLogger(SelectPage.class.getName());
    @FindBy(linkText = "Select departing flight")
    private static WebElement selectPage;
    @FindBy(xpath = "//td[@id='summaryBot_Left_botTotalPrice']")
    private static WebElement totalPrice;
    @FindBy(xpath = "//a[@id='pgButtonNext']")
    private static WebElement buttonNext;


    public SelectPage()
    {
        PageFactory.initElements(Utils.getHandler(), this);
    }

    public void verifySelectPageOpened()
    {
        if (!selectPage.isDisplayed())
        {
            logger.error("Neccessary Flight Selection Page was not opened.");
            Utils.getHandler().quit();
        }
    }

    public String getTotalPrice()
    {
        return totalPrice.getText();
    }

    public ReviewPage nextPage()
    {
        if (buttonNext.isEnabled())
        {
            buttonNext.click();
        }
        Utils.getHandler().waitForPageToLoad();
        return new ReviewPage();
    }
}
