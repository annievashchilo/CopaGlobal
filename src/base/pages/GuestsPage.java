package base.pages;

import base.elements.TravellersForm;
import base.utils.Utils;

public class GuestsPage extends Page
{

    public static TravellersForm travelForm = new TravellersForm();
    public SelectSeatsPage nextPage()
    {
        if (travelForm.buttonNext.isEnabled())
        {
            travelForm.buttonNext.click();
        }
        Utils.getHandler().waitForPageToLoad();
        return new SelectSeatsPage();
    }

}


