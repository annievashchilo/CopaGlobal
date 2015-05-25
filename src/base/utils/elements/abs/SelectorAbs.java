package base.utils.elements.abs;

import base.utils.elements.Selector;
import org.openqa.selenium.WebElement;

/**
 * Created by Anny on 25.05.15.
 */
public class SelectorAbs extends AbstractElement implements Selector {


    protected SelectorAbs(WebElement wrappedElement) {
        super(wrappedElement);
    }


    @Override
    public void select(String option) {
        wrappedElement.sendKeys(option);
    }
}
