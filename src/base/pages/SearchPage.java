package base.pages;

import base.elements.SearchForm;

public class SearchPage extends SearchForm
{


    public SelectPage search(String from, String to, boolean isOW)
    {
        super.searchFlights(from, to, isOW);
        return new SelectPage();
    }

}