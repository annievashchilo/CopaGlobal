package base.utils;

import logger.Logger;
import logger.LoggerFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class WebDriverProvider
{

    private static Logger logger = LoggerFactory.getLogger();
    private static RemoteWebDriver webDriver;


    /**
     * *firefox     firefox web driver
     * *iexplorer   internet explorer web driver
     * *safari      safari web driver
     * *chrome      google chrome web driver
     *
     * @return WebDriver instance
     */
    public static RemoteWebDriver getWebDriver()
    {
        if (webDriver == null)
        {
            webDriver = initializeDriver();
        }
        return webDriver;
    }

    private static RemoteWebDriver initializeDriver()
    {
        if (Utils.browserType == null || Utils.browserType.equalsIgnoreCase(DriverTypes.FIREFOX.actualValue()))
        {
            webDriver = new RemoteWebDriver(DesiredCapabilities.firefox());
        } else if (Utils.browserType.equalsIgnoreCase(DriverTypes.IEXPLORER.actualValue()))
        {
            webDriver = new RemoteWebDriver(DesiredCapabilities.internetExplorer());
        } else if (Utils.browserType.equalsIgnoreCase(DriverTypes.CHROME.actualValue()))
        {
            webDriver = new RemoteWebDriver(DesiredCapabilities.chrome());

        } else if (Utils.browserType.equalsIgnoreCase(DriverTypes.SAFARI.actualValue()))
        {
            webDriver = new RemoteWebDriver(DesiredCapabilities.safari());
        } else
        {
            logger.exception("Unknown browser type: '" + Utils.browserType + "'");
        }
        return webDriver;
    }

    private enum DriverTypes
    {
        FIREFOX("*firefox"), IEXPLORER("*iexplorer"), SAFARI("*safari"), CHROME("*chrome");

        private String driverType;

        DriverTypes(String driverType)
        {
            this.driverType = driverType;
        }

        public String actualValue()
        {
            return this.driverType;
        }
    }
}
