package base.utils.elements.abs;

import base.utils.elements.RadioButton;
import org.openqa.selenium.WebElement;

public class RadioButtonAbs extends AbstractElement implements RadioButton {


    protected RadioButtonAbs(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public void click() {
        wrappedElement.click();
    }
}
