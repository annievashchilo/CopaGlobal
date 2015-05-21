package base.utils.elements.abs;

import base.utils.elements.Button;
import org.openqa.selenium.WebElement;

public class ButtonAbs extends AbstractElement implements Button {

    protected ButtonAbs(final WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public void click() {
        wrappedElement.click();
    }
}