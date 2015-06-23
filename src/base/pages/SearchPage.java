package base.pages;

import base.elements.SearchForm;
import base.elements.SimpleForm;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://booking.westjet.com/InternetBooking/AirLowFareSearchExternal.do")
public class SearchPage extends Page
{
    private SearchForm searchForm = new SearchForm(new SimpleForm());

    public SelectPage search(String from, String to, String tripType)
    {
        searchForm.searchFlights(from, to, tripType);
        return new SelectPage();
    }

}