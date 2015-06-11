package base.pages;

import base.elements.TravellersForm;
import base.utils.Utils;

public class GuestsPage extends TravellersForm
{
    public SelectSeatsPage nextPage()
    {
        if (TravellersForm.buttonNext.isEnabled())
        {
            buttonNext.click();
        }
        Utils.getHandler().waitForPageToLoad();
        return new SelectSeatsPage();
    }

}
