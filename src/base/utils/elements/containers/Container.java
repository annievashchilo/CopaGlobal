package base.utils.elements.containers;

import base.utils.elements.Element;
import org.openqa.selenium.WebElement;

public interface Container extends Element
{
    void init(WebElement wrappedElement);
}
