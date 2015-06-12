package tests;

import base.pages.*;
import base.utils.Handler;
import base.utils.Utils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class BaseTest
{

    public static Logger logger;
    public SearchPage searchPage = new SearchPage();
    public SelectPage selectPage;
    public ReviewPage reviewPage;
    public GuestsPage guestsPage;
    public SelectSeatsPage seatsPage;
    protected Handler handler = Utils.getHandler();


    @AfterClass(alwaysRun = true)
    public void afterClass()
    {
        // close the browser instance
        handler.close();
    }


    public void verifyTotalPricesEqual(String price1, String price2)
    {
        Assert.assertEquals(price1, price2, "Prices are NOT equal. Price1:" + price1 + ", Price2:" + price2);
        logger.info("Total prices are equal -> OK!");
    }


}
