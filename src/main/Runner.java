package main;

import base.utils.Handler;
import base.utils.Utils;
import base.utils.WebDriverProvider;
import logger.Logger;
import logger.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

        Properties props = null;
        if (handler == null) {
            props = new Properties();
            try {
                props.load(new FileInputStream(Utils.configFile));
            } catch (IOException e) {
                e.printStackTrace();
                logger.exception(e.getLocalizedMessage());
            }
            initSeleniumSession(props, host, port, browserType, URL);

        }
        WebDriverProvider.getWebDriver();
        handler.open(URL);
        handler.waitForPageToLoad(Utils.timeout);

    }


    private static void initSeleniumSession(Properties props, String host, String port, String browserType, String URL) {
        if ("".equals(host)) {
            Utils.host = props.getProperty("host");
        } else {
            Utils.host = host;
        }
        if ("".equals(port)) {
            Utils.port = props.getProperty("port");
        } else {
            Utils.port = port;
        }
        if ("".equals(browserType)) {
            Utils.browserType = props.getProperty("browserType");
        } else {
            Utils.browserType = browserType;
        }
        if ("".equals(URL)) {
            Utils.URL = props.getProperty("url");
        } else {
            Utils.URL = URL;
        }
        Utils.runType = props.getProperty("runType");
        Utils.timeout = Integer.parseInt(props.getProperty("timeout"));
    }
}
