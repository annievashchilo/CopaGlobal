package tests;

import base.utils.Utils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestFlights_RT extends BaseTest
{

    private static Logger logger;

    @Parameters({"host", "port", "browserType", "URL"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("") String host, @Optional("") String port,
                      @Optional("") String browserType, @Optional("") String URL)
    {
        logger = Logger.getLogger(TestFlights_RT.class.getName());

        // The Firefox driver supports javascript

        handler.start(host, port, browserType, Utils.URL);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass()
    {
        // close the browser instance
        handler.close();
    }


    @Test
    public void testRoundTripFlight()
    {
        logger.info("Starting testcase TestWestJetFlight_RoundTrip");
        searchPage.search(TestData.Destinations.ROUTE_FROM, TestData.Destinations.ROUTE_TO);

        Assert.assertTrue(handler.isTextPresent("Select Flights"), "'Select Flights' Page was not opened");
        String totalPriceSelectPage = selectPage.getTotalPrice();
        selectPage.nextPage();

        Assert.assertTrue(handler.isTextPresent("Review Flights"), "'Review Flights' Page was not opened");
        reviewPage.verifyRoutesPresent();
        String totalPriceReviewPage = reviewPage.getTotalPrice();
        verifyTotalPricesEqual(totalPriceSelectPage, totalPriceReviewPage);
        reviewPage.nextPage();

        Assert.assertTrue(handler.isTextPresent("Guest information"), "'Guest information' Page was not opened");
        guestsPage.fill();
        guestsPage.nextPage();

        Assert.assertTrue(handler.isTextPresent("Select your seats"), "'Select your seats' Page was not opened");
        String expectedGuestName = TestData.TravellerInfo.travellerTitle + " " + TestData.TravellerInfo.firstName + " " + TestData.TravellerInfo.lastName;
        seatsPage.verifyGuestName(expectedGuestName);
        logger.info("Test passed successfully -> OK!");
    }


}
