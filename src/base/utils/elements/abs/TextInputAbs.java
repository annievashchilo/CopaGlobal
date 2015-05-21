package base.utils.elements.abs;

import base.utils.elements.TextInput;
import org.openqa.selenium.WebElement;

public class TextInputAbs extends AbstractElement implements TextInput {
    protected TextInputAbs(final WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public void type(final String text) {
        wrappedElement.sendKeys(text);
    }

    @Override
    public void clear() {
        wrappedElement.clear();
    }

    @Override
    public void clearAndType(final String text) {
        clear();
        type(text);
    }
}