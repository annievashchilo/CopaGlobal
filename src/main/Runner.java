package main;

import base.pages.*;
import base.utils.Handler;
import base.utils.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
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
    public static void run(String browserType)
    {
        initPages();
        runGrid(browserType);
        handler = Utils.getHandler();
        handler.open(Utils.getURL());
//        handler.waitForPageToLoad(Utils.timeout);
    }

    private static void runGrid(String browserType)
    {
        threadDriver = new ThreadLocal<RemoteWebDriver>();
        DesiredCapabilities dc = new DesiredCapabilities();
        DesiredCapabilities chr = new DesiredCapabilities();

        if (browserType.contains("firefox"))
        {
            FirefoxProfile fp = new FirefoxProfile();
            dc.setCapability(FirefoxDriver.PROFILE, fp);
            dc.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
        } else if (Utils.browserType.contains("chrome"))
        {
            dc.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
        }

        dc.setPlatform(Platform.ANY);

        try
        {
            threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc));
            threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc));
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
