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
     * @param host        selenium host
     * @param port        selenium port
     * @param browserType browser type
     * @param URL         website url
     */
    public static void run(
            String host,
            String port,
            String browserType,
            String URL)
    {

        handler = Utils.getHandler();
        handler.open(URL);
//        handler.waitForPageToLoad(Utils.timeout);

    }

}