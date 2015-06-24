package base.bdd.bdd_pages;

import base.elements.SearchForm;
import base.elements.SimpleForm;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import tests.TestData;

@DefaultUrl("https://booking.westjet.com/InternetBooking/AirLowFareSearchExternal.do")
public class BDDSearchPage extends PageObject {
    private SearchForm searchForm = new SearchForm(new SimpleForm());

    public BDDSelectPage search(String from, String to, String tripType) {
        searchForm.searchFlights(from, to, tripType);
        return new BDDSelectPage();
    }

    public void submit() {
        searchForm.submitForm();
    }

    public void setRoutes(String from, String to) {
        searchForm.setDestinations(from, to);
    }

    public void setDates(String tripType) {
        searchForm.setDates(TestData.Dates.YEAR, TestData.Dates.DEPARTURE_MONTH, TestData.Dates.DEPARTURE_DAY,
                TestData.Dates.YEAR, TestData.Dates.ARRIVAL_MONTH, TestData.Dates.ARRIVAL_DAY,
                tripType);
    }

    public void setTravellers(String travellersType, String travellersAmount) {
        searchForm.setTravellers(travellersType, travellersAmount);
    }

    public void setTripType(String tripType) {
        searchForm.setTripType(tripType);
    }

}