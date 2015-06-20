package base.pages;

import base.elements.SearchForm;
import base.elements.SimpleForm;

public class SearchPage extends Page
{

    public SelectPage search(String from, String to, String isOW)
    {
        new SearchForm(new SimpleForm()).searchFlights(from, to, isOW);
        return new SelectPage();
    }

}