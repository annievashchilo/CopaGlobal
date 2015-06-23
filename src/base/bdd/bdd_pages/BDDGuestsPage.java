package base.bdd.bdd_pages;

import base.elements.SimpleForm;
import base.elements.TravellersForm;
import base.utils.Utils;
import net.thucydides.core.pages.PageObject;

public class BDDGuestsPage extends PageObject {

    public static TravellersForm travelForm = new TravellersForm(new SimpleForm());

    public BDDSelectSeatsPage nextPage() {
        if (TravellersForm.buttonNext.isEnabled()) {
            TravellersForm.buttonNext.click();
        }
        Utils.getHandler().waitForPageToLoad();
        return new BDDSelectSeatsPage();
    }

}


