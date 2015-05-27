package base.utils;

import logger.Logger;
import logger.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class WebDriverProvider
{

    private static Logger logger = LoggerFactory.getLogger();
    private static WebDriver webDriver;

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
    public static WebDriver getWebDriver()
    {
        if (webDriver == null)
        {
            initializeDriver();
        }
        return webDriver;
    }

    private static void initializeDriver()
    {
        if (Utils.browserType == null || Utils.browserType.equalsIgnoreCase(DriverTypes.FIREFOX.actualValue()))
        {
            webDriver = new FirefoxDriver();
        } else if (Utils.browserType.equalsIgnoreCase(DriverTypes.IEXPLORER.actualValue()))
        {
            webDriver = new InternetExplorerDriver();
        } else if (Utils.browserType.equalsIgnoreCase(DriverTypes.CHROME.actualValue()))
        {
//            System.setProperty("webdriver.chrome.driver", "D:\\cdp automation\\Module 3 Java\\CopaGlobal\\webdrivers\\chromedriver.exe");

            webDriver = new RemoteWebDriver(DesiredCapabilities.chrome());

        } else if (Utils.browserType.equalsIgnoreCase(DriverTypes.SAFARI.actualValue()))
        {
            webDriver = new SafariDriver();
        } else
        {
            logger.exception("Unknown browser type: '" + Utils.browserType + "'");
        }
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
