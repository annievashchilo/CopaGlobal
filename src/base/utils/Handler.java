package base.utils;

import logger.Logger;
import logger.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Handler {

    private static Logger logger = LoggerFactory.getLogger();

    private WebDriver m_driver;

    public Handler(WebDriver driver) {
        m_driver = driver;
    }

    public String takeScreenshot(String fileName) {
        StringBuilder msg = new StringBuilder("Trying perform captureScreenshot action with parameter(s): ").append(fileName);
        StringBuilder screenshotPath = new StringBuilder(Utils.getPathToScreenshots()).append(System.currentTimeMillis());

        File scrFile = ((TakesScreenshot) m_driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(screenshotPath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath.toString();
    }

    public Boolean isElementPresent(By xpath) {
        return m_driver.findElements(xpath).size() > 0;
    }

    public boolean isTextPresent(String txtValue) {
        boolean b = false;
        try {
            b = m_driver.getPageSource().contains(txtValue);
            return b;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return b;
        }
    }
}
