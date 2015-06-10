package tests;

import base.pages.*;
import base.utils.Handler;
import base.utils.Utils;
import org.apache.log4j.Logger;
import org.testng.Assert;



public class BaseTest
{

    public static Logger logger = Logger.getLogger(BaseTest.class.getName());
    public SearchPage searchPage = new SearchPage();
    public SelectPage selectPage = new SelectPage();
    public ReviewPage reviewPage = new ReviewPage();
    public GuestsPage guestsPage = new GuestsPage();
    public SelectSeatsPage seatsPage = new SelectSeatsPage();
    protected Handler handler = Utils.getHandler();


    public void verifyTotalPricesEqual(String price1, String price2)
    {
        Assert.assertEquals(price1, price2, "Prices are NOT equal. Price1:" + price1 + ", Price2:" + price2);
        logger.info("Total prices are equal -> OK!");
    }
}
