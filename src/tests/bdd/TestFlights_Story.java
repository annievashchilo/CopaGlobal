package tests.bdd;

import base.bdd.steps.SearchFlightSteps;
import base.pages.GuestsPage;
import base.requirements.SearchFlightsApplication;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.TestData;


@RunWith(ThucydidesRunner.class)
@Story(SearchFlightsApplication.SearchFlights.class)
public class TestFlights_Story extends BaseTest_BDD {


    @Managed(driver = "chrome")
    public RemoteWebDriver webDriver;

    @ManagedPages(defaultUrl = "https://booking.westjet.com/InternetBooking/AirLowFareSearchExternal.do")
    public Pages pages;

    @Steps
    public SearchFlightSteps steps;

    @Test
    public void testRoundTripFlight(String flightType, String from, String to,
                                    String travellersType, String travellersAmount) {
//        testSearchFlight(flightType);
        searchFlight(flightType, from, to, travellersType, travellersAmount);
    }

    @Test
    public void testOneWayFlight(String flightType, String from, String to,
                                 String travellersType, String travellersAmount) {
//        testSearchFlight(flightType);
        searchFlight(flightType, from, to, travellersType, travellersAmount);
    }

    private void testSearchFlight(String flightType) {
        logger.info("Starting testcase TestWestJetFlight_" + flightType);
        selectPage = searchPage.search(TestData.Destinations.ROUTE_FROM, TestData.Destinations.ROUTE_TO, flightType);

        Assert.assertTrue(handler.isTextPresent("Select departing flight"), "'Select departing flight' Page was not opened");
        String totalPriceSelectPage = selectPage.getTotalPrice();
        reviewPage = selectPage.nextPage();

        Assert.assertTrue(handler.isTextPresent("Review Flights"), "'Review Flights' Page was not opened");
        reviewPage.verifyRoutesPresent();
        String totalPriceReviewPage = reviewPage.getTotalPrice();
        verifyTotalPricesEqual(totalPriceSelectPage, totalPriceReviewPage);
        guestsPage = reviewPage.nextPage();

        Assert.assertTrue(handler.isTextPresent("Guest information"), "'Guest information' Page was not opened");
        GuestsPage.travelForm.fill();
        seatsPage = guestsPage.nextPage();

        Assert.assertTrue(handler.isTextPresent("Select your seats"), "'Select your seats' Page was not opened");
        String expectedGuestName = TestData.TravellerInfo.travellerTitle + " " + TestData.TravellerInfo.firstName + " " + TestData.TravellerInfo.lastName;
        seatsPage.verifyGuestName(expectedGuestName);
        logger.info("Test passed successfully -> OK!");

    }

    private void searchFlight(String flightType, String from, String to,
                              String travellersType, String travellersAmount) {
        logger.info("Starting testcase TestWestJetFlight_" + flightType);
        selectPage = steps.fillSearchPage(from, to, flightType, travellersType, travellersAmount);

        reviewPage = steps.completeSelectPage(selectPage);
        guestsPage = steps.completeReviewPage(reviewPage);
        seatsPage = steps.completeGuestsPage(guestsPage);
        steps.completeSeatsPage(seatsPage);
        logger.info("Test passed successfully -> OK!");
    }

}
