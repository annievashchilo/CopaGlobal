package base.pages;

import base.elements.SearchForm;

public class SearchPage extends SearchForm
{


    public void search(String from, String to)
    {

        super.search(from, to);
    }

    public SearchForm getSearchForm()
    {
        return new SearchForm();
    }
}