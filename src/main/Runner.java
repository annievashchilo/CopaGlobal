package main;

import base.utils.Handler;
import base.utils.Utils;
import logger.Logger;
import logger.LoggerFactory;

public class Runner {

    public static Logger logger = LoggerFactory.getLogger();
    protected static Handler handler;

    public static void main(String args[]) {
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
            String URL) {

        handler = Utils.getHandler();
        handler.open(URL);
//        handler.waitForPageToLoad(Utils.timeout);

    }

}
