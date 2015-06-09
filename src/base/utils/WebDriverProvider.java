package base.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class WebDriverProvider
{

    private static Logger logger = Logger.getLogger(Handler.class.getName());
    private static RemoteWebDriver webDriver;

    private WebDriverProvider()
    {
    }

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
            logger.error("Unknown browser type: '" + Utils.browserType + "'");
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
