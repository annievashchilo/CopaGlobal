package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestFlights_RT extends BaseTest
{

    protected ThreadLocal<RemoteWebDriver> threadDriver = null;

    @BeforeClass(alwaysRun = true)
    public void setUp()
    {
        logger = Logger.getLogger(BaseTest.class.getName());
        handler.start();
    }


    @Test
    public void testRoundTripFlight()
    {
        logger.info("Starting testcase TestWestJetFlight_RoundTrip");
        selectPage = searchPage.search(TestData.Destinations.ROUTE_FROM, TestData.Destinations.ROUTE_TO, false);

        Assert.assertTrue(handler.isTextPresent("Select departing flight"), "'Select departing flight' Page was not opened");
        String totalPriceSelectPage = selectPage.getTotalPrice();
        reviewPage = selectPage.nextPage();

        Assert.assertTrue(handler.isTextPresent("Review Flights"), "'Review Flights' Page was not opened");
        reviewPage.verifyRoutesPresent();
        String totalPriceReviewPage = reviewPage.getTotalPrice();
        verifyTotalPricesEqual(totalPriceSelectPage, totalPriceReviewPage);
        guestsPage = reviewPage.nextPage();

        Assert.assertTrue(handler.isTextPresent("Guest information"), "'Guest information' Page was not opened");
        guestsPage.travelForm.fill();
        seatsPage = guestsPage.nextPage();

        Assert.assertTrue(handler.isTextPresent("Select your seats"), "'Select your seats' Page was not opened");
        String expectedGuestName = TestData.TravellerInfo.travellerTitle + " " + TestData.TravellerInfo.firstName + " " + TestData.TravellerInfo.lastName;
        seatsPage.verifyGuestName(expectedGuestName);
        logger.info("Test passed successfully -> OK!");
    }


}
