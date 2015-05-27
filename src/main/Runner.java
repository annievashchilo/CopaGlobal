package main;

import base.utils.Handler;
import base.utils.Utils;
import base.utils.WebDriverProvider;
import logger.Logger;
import logger.LoggerFactory;

public class Runner {

    public static Logger logger = LoggerFactory.getLogger();
    protected static Handler handler;

    public static void main(String args[]) {
        System.out.println("Hi!");
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
        Utils.loadProperties();
        WebDriverProvider.getWebDriver();
        handler.open(Utils.URL);
        handler.waitForPageToLoad(Utils.timeout);

    }

}
