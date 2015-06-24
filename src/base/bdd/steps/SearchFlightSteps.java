package base.bdd.steps;


import base.bdd.bdd_pages.*;
import base.utils.Handler;
import base.utils.Utils;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.testng.Assert;
import tests.TestData;

import java.util.logging.Logger;

public class SearchFlightSteps extends ScenarioSteps {

    private static Logger logger = Logger.getLogger(SearchFlightSteps.class.getName());
    public BDDSearchPage searchPage = new BDDSearchPage();
    public BDDSelectPage selectPage = new BDDSelectPage();
    public BDDReviewPage reviewPage = new BDDReviewPage();
    public BDDGuestsPage guestsPage = new BDDGuestsPage();
    public BDDSelectSeatsPage seatsPage = new BDDSelectSeatsPage();
    protected Handler handler = Utils.getHandler();

    public SearchFlightSteps(Pages pages) {
        super(pages);
        searchPage = getPages().get(BDDSearchPage.class);
    }

    //1
    @Step
    public void isTheStartSearchPage() {
        searchPage.open();
    }

    //2
    @Step
    public void look_for_flight(String fromRoute, String toRoute) {
        searchPage.setTripType(TestData.TripTypes.ROUND_TRIP);
        searchPage.setRoutes(fromRoute, toRoute);
        searchPage.setDates(TestData.TripTypes.ROUND_TRIP);
        searchPage.setTravellers(TestData.TravellersType.ADULT, "1");
        searchPage.submit();
    }

    //3
    @Step
    public void selectPageShouldBeOpened() {
        completeSelectPage();
    }

    //4
    @Step
    public void thenVerifyRoutesPresent() {
        reviewPage.verifyRoutesPresent();
    }


    public void completeSelectPage() {
        Assert.assertTrue(handler.isTextPresent("Select departing flight"),
                "'Select departing flight' Page was not opened");
        this.selectPage.nextPage();
    }

}
