package base.bdd.steps;


import base.bdd.bdd_pages.*;
import base.utils.Handler;
import base.utils.Utils;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.testng.Assert;
import tests.TestData;

public class SearchFlightSteps extends ScenarioSteps {

    public BDDSearchPage searchPage = new BDDSearchPage();
    public BDDSelectPage selectPage;
    public BDDReviewPage reviewPage;
    public BDDGuestsPage guestsPage;
    public BDDSelectSeatsPage seatsPage;

    protected Handler handler = Utils.getHandler();

    public SearchFlightSteps(Pages pages) {
        super(pages);
        searchPage = getPages().get(BDDSearchPage.class);
    }

    @Step
    public BDDSelectPage fillSearchPage(String fromRoute, String toRoute,
                                        String tripType, String travellerType, String amount) {
        openSearchPage();
        searchPage.setRoutes(fromRoute, toRoute);
        searchPage.setDates(tripType);
        searchPage.setTravellers(travellerType, amount);
        return new BDDSelectPage();
    }

    private void openSearchPage() {
        searchPage.open();
    }


    @Step
    public BDDReviewPage completeSelectPage(BDDSelectPage selectPage) {
        this.selectPage = selectPage;
        Assert.assertTrue(handler.isTextPresent("Select departing flight"),
                "'Select departing flight' Page was not opened");
        this.selectPage.nextPage();
        return new BDDReviewPage();
    }


    @Step
    public BDDGuestsPage completeReviewPage(BDDReviewPage reviewPage) {
        this.reviewPage = reviewPage;
        Assert.assertTrue(handler.isTextPresent("Review Flights"),
                "'Review Flights' Page was not opened");
        reviewPage.verifyRoutesPresent();
        this.reviewPage.nextPage();
        return new BDDGuestsPage();
    }

    @Step
    public BDDSelectSeatsPage completeGuestsPage(BDDGuestsPage guestsPage) {
        this.guestsPage = guestsPage;
        Assert.assertTrue(handler.isTextPresent("Guest information"),
                "'Guest information' Page was not opened");
        this.guestsPage.travelForm.fill();
        this.guestsPage.nextPage();
        return new BDDSelectSeatsPage();
    }

    @Step
    public void completeSeatsPage(BDDSelectSeatsPage seatsPage) {
        this.seatsPage = seatsPage;
        Assert.assertTrue(handler.isTextPresent("Select your seats"),
                "'Select your seats' Page was not opened");
        String expectedGuestName = TestData.TravellerInfo.travellerTitle + " "
                + TestData.TravellerInfo.firstName + " "
                + TestData.TravellerInfo.lastName;
        seatsPage.verifyGuestName(expectedGuestName);
    }


}
