package tests.bdd;

import base.bdd.bdd_pages.*;
import base.utils.Handler;
import base.utils.Utils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class BaseTest_BDD {
    public static Logger logger;
    public BDDSearchPage searchPage = new BDDSearchPage();
    public BDDSelectPage selectPage;
    public BDDReviewPage reviewPage;
    public BDDGuestsPage guestsPage;
    public BDDSelectSeatsPage seatsPage;
    protected Handler handler = Utils.getHandler();


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        // close the browser instance
        handler.close();
    }


    public void verifyTotalPricesEqual(String price1, String price2) {
        Assert.assertEquals(price1, price2, "Prices are NOT equal. Price1:" + price1 + ", Price2:" + price2);
        logger.info("Total prices are equal -> OK!");
    }


}

