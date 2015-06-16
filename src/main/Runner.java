package main;

import base.pages.*;
import base.utils.Handler;
import base.utils.Utils;
import org.apache.log4j.Logger;

public class Runner
{

    protected static Handler handler;
    private static Logger logger = Logger.getLogger(Runner.class.getName());


    public static void main(String args[])
    {
        logger.info("Hi!");
        initPages();
        run();
    }

    public static void initPages() {
        Creator[] creators = {new GuestsPageCreator(), new ReviewPageCreator(),
                new SearchPageCreator(), new SelectPageCreator(), new SelectSeatsPageCreator()};
        // iterate over creators and create products
        for (Creator creator : creators) {
            Page page = creator.factoryMethod();
            System.out.printf("Page created {%s}\n", page.getClass());
        }
    }


    /**
     * Runs webdriver session, launches browser and opens URL
     *
     */
    public static void run()
    {
        initPages();
        handler = Utils.getHandler();
        handler.open(Utils.getURL());
//        handler.waitForPageToLoad(Utils.timeout);
    }

    public static void runGrid()
    {

    }

}

abstract class Creator {
    public abstract Page factoryMethod();
}

class GuestsPageCreator extends Creator {
    public Page factoryMethod() {
        return new GuestsPage();
    }
}

class ReviewPageCreator extends Creator {
    public Page factoryMethod() {
        return new ReviewPage();
    }
}

class SearchPageCreator extends Creator {
    public Page factoryMethod() {
        return new SearchPage();
    }
}

class SelectPageCreator extends Creator {
    public Page factoryMethod() {
        return new SelectPage();
    }
}

class SelectSeatsPageCreator extends Creator {
    public Page factoryMethod() {
        return new SelectSeatsPage();
    }
}