package tests.bdd;

import base.bdd.steps.SearchFlightSteps;
import base.requirements.SearchFlightsApplication;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.RemoteWebDriver;


@RunWith(ThucydidesRunner.class)
@Story(SearchFlightsApplication.SearchFlights.class)
public class TestFlights_Story extends BaseTest_BDD {


    @Managed(driver = "chrome")
    public RemoteWebDriver webDriver;

    @ManagedPages(defaultUrl = "https://booking.westjet.com/InternetBooking/AirLowFareSearchExternal.do")
    public Pages pages;

    @Steps
    SearchFlightSteps searchFlight;

    @Given("The user opened the first search page")
    public void givenTheStartSearchPageIsOpened() {
        searchFlight.isTheStartSearchPage();
    }

    @When("the user is looking for flight from '$from' route and to '$to' destination and '$tripType' trip type")
    public void whenTheUserIsLookingForFlight(String from, String to, String tripType) {
        searchFlight.look_for_flight(from, to);
    }

    @Then("the user should see select page is opened after submit form")
    public void thenUserShouldSeeSelectPageOpened() {
        searchFlight.selectPageShouldBeOpened();
    }

    @Then("Verify the routes are present")
    public void thenVerifyRoutesPresent() {
        searchFlight.thenVerifyRoutesPresent();
    }

}
