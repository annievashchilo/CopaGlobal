package base.pages;

import base.elements.SearchForm;

public class SearchPage extends SearchForm
{


    public SelectPage search(String from, String to)
    {
        super.searchFlights(from, to);
        return new SelectPage();
    }

}