package base.utils.elements;

import base.utils.elements.abs.DefaultElementFactory;
import base.utils.elements.containers.ContainerFactory;
import base.utils.elements.containers.DefaultContainerFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.awt.*;
import java.lang.reflect.Field;

public class ExtentedFieldDecorator extends DefaultFieldDecorator {
    private ElementFactory elementFactory = new DefaultElementFactory();
    private ContainerFactory containerFactory = new DefaultContainerFactory();

    public ExtentedFieldDecorator(final SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(final ClassLoader loader, final Field field) {
        if (Container.class.isAssignableFrom(field.getType())) {
            return decorateContainer(loader, field);
        }
        if (Element.class.isAssignableFrom(field.getType())) {
            return decorateElement(loader, field);
        }
        return super.decorate(loader, field);
    }

    private Object decorateElement(final ClassLoader loader, final Field field) {
        final WebElement wrappedElement = proxyForLocator(loader, createLocator(field));
        return elementFactory.create((Class<? extends Element>) field.getType(), wrappedElement);
    }

    private ElementLocator createLocator(final Field field) {
        return factory.createLocator(field);
    }

    private Object decorateContainer(final ClassLoader loader, final Field field) {
        final WebElement wrappedElement = proxyForLocator(loader, createLocator(field));
        final base.utils.elements.containers.Container container;
        container = containerFactory.create((Class<? extends base.utils.elements.containers.Container>) field.getType(), wrappedElement);

        PageFactory.initElements(new ExtentedFieldDecorator(wrappedElement), container);
        return container;
    }
}