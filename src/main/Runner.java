package main;

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
    }


    /**
     * Runs webdriver session, launches browser and opens URL
     *
     */
    public static void run()
    {

        handler = Utils.getHandler();
        handler.open(Utils.getURL());
//        handler.waitForPageToLoad(Utils.timeout);
    }

    public static void runGrid()
    {

    }

}