package base.pages;

import base.elements.SearchForm;

public class SearchPage extends Page
{

    public SelectPage search(String from, String to, boolean isOW)
    {
        new SearchForm().searchFlights(from, to, isOW);
        return new SelectPage();
    }

}