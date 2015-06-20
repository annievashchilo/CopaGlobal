package main;

import base.pages.*;
import base.utils.Handler;
import base.utils.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Runner
{

    protected static Handler handler;
    protected static ThreadLocal<RemoteWebDriver> threadDriver = null;
    private static Logger logger = Logger.getLogger(Runner.class.getName());


    public static void main(String args[])
    {
        logger.info("Hi!");
        initPages();
        run();
    }

    public static void initPages()
    {
        Creator[] creators = {new GuestsPageCreator(), new ReviewPageCreator(),
                new SearchPageCreator(), new SelectPageCreator(), new SelectSeatsPageCreator()};
        // iterate over creators and create products
        for (Creator creator : creators)
        {
            Page page = creator.factoryMethod();
            logger.info(String.format("Page created {%s}\n", page.getClass()));
        }
    }


    /**
     * Runs webdriver session, launches browser and opens URL
     */
    public static void run()
    {
        initPages();
        //runGrid();
        handler = Utils.getHandler();
        handler.open(Utils.getURL());
//        handler.waitForPageToLoad(Utils.timeout);
    }

    private static void runGrid()
    {
        threadDriver = new ThreadLocal<RemoteWebDriver>();
        DesiredCapabilities ff = new DesiredCapabilities();
        DesiredCapabilities chr = new DesiredCapabilities();
        FirefoxProfile fp = new FirefoxProfile();
        chr.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
        ff.setCapability(FirefoxDriver.PROFILE, fp);
        ff.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
        try
        {
            threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chr));
            threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), ff));
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

}

abstract class Creator
{
    public abstract Page factoryMethod();
}

class GuestsPageCreator extends Creator
{
    public Page factoryMethod()
    {
        return new GuestsPage();
    }
}

class ReviewPageCreator extends Creator
{
    public Page factoryMethod()
    {
        return new ReviewPage();
    }
}

class SearchPageCreator extends Creator
{
    public Page factoryMethod()
    {
        return new SearchPage();
    }
}

class SelectPageCreator extends Creator
{
    public Page factoryMethod()
    {
        return new SelectPage();
    }
}

class SelectSeatsPageCreator extends Creator
{
    public Page factoryMethod()
    {
        return new SelectSeatsPage();
    }
}
