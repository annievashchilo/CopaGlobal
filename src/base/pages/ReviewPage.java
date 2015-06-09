package base.pages;

import base.utils.Utils;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.TestData;

import java.util.logging.Logger;


public class ReviewPage
{
    @FindBy(css = "tr.rowLast.rowOdd > td.colLast.colPrice > div > b")
    private static WebElement totalFare;
    @FindBy(css = "#summaryBot_Left_botTotalPrice")
    private static WebElement totalPrice;
    @FindBy(css = "#airItineraryComponentTotal")
    private static WebElement totalAirFate;
    @FindBy(xpath = "//a[@id='pgButtonNext']")
    private static WebElement buttonNext;

    private static Logger logger = Logger.getLogger(ReviewPage.class.getName());

    public ReviewPage()
    {
        PageFactory.initElements(Utils.getHandler(), this);
    }


    public String getTotalFares()
    {
        return totalFare.getText();
    }

    public String getTotalPrice()
    {
        return totalPrice.getText();
    }

    public String getTotalAirFares()
    {
        return totalAirFate.getText();
    }


    public void verifyRoutesPresent()
    {
        Assert.assertTrue(Utils.getHandler().findElementByPartialLinkText(TestData.Destinations.ROUTE_FROM).isDisplayed());
        logger.info("Route FROM is present on Review Page");
        Assert.assertTrue(Utils.getHandler().findElementByPartialLinkText(TestData.Destinations.ROUTE_TO).isDisplayed());
        logger.info("Route TO is present on Review Page");
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
