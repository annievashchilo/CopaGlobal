package base.pages;

import base.elements.TravellersForm;
import base.utils.Utils;

public class GuestsPage extends TravellersForm
{
    public void nextPage()
    {
        if (TravellersForm.buttonNext.isEnabled())
        {
            buttonNext.click();
        }
        Utils.getHandler().waitForNextPageToLoad();
    }

}
