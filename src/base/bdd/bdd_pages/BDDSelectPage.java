package base.bdd.bdd_pages;

import base.utils.Utils;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@DefaultUrl("https://booking.westjet.com/InternetBooking/AirLowFareSearchExternal.do")
public class BDDSelectPage extends PageObject {
    private static Logger logger = Logger.getLogger(BDDSelectPage.class.getName());
    @FindBy(linkText = "Select departing flight")
    private static WebElement selectPage;
    @FindBy(xpath = "//td[@id='summaryBot_Left_botTotalPrice']")
    private static WebElement totalPrice;
    @FindBy(xpath = "//a[@id='pgButtonNext']")
    private static WebElement buttonNext;


    public BDDSelectPage() {
        PageFactory.initElements(Utils.getHandler(), this);
    }

    public void verifySelectPageOpened() {
        if (!selectPage.isDisplayed()) {
            logger.error("Neccessary Flight Selection Page was not opened.");
            Utils.getHandler().quit();
        }
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public BDDReviewPage nextPage() {
        if (buttonNext.isEnabled()) {
            buttonNext.click();
        }
        Utils.getHandler().waitForPageToLoad();
        return new BDDReviewPage();
    }
}
