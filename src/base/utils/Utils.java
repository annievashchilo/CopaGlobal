package base.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Utils {

    public static String configFile = "";
    public static String URL;
    public static String host;

    public static volatile Handler handler;
    public static String port;
    public static String browserType;
    public static String runType;
    public static String POS;
    public static long timeout = 60000;
    public static int loggerSeverity;
    public static String pathToScreenshots;
    public static String pathToTestData;
    private static Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new FileInputStream(configFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Handler getHandler() {
        return handler;
    }

    public static String getConfigFile() {
        return configFile;
    }

    public static String getURL() {
        return PROPERTIES.getProperty("url");
    }

    public static void setURL(String URL) {
        Utils.URL = URL;
    }

    public static String getHost() {
        return PROPERTIES.getProperty("host");
    }

    public static void setHost(String host) {
        Utils.host = host;
    }

    public static String getPort() {
        return PROPERTIES.getProperty("port");
    }

    public static void setPort(String port) {
        Utils.port = port;
    }

    public static String getBrowserType() {
        return PROPERTIES.getProperty("browserType");
    }

    public static void setBrowserType(String browserType) {
        Utils.browserType = browserType;
    }

    public static String getRunType() {
        return PROPERTIES.getProperty("runType");
    }

    public static void setRunType(String runType) {
        Utils.runType = runType;
    }

    public static String getPOS() {
        return PROPERTIES.getProperty("POS");
    }

    public static void setPOS(String POS) {
        Utils.POS = POS;
    }

    public static long getTimeout() {
        return Long.parseLong(PROPERTIES.getProperty("timeout"));
    }

    public static void setTimeout(long timeout) {
        Utils.timeout = timeout;
    }

    public static int getLoggerSeverity() {
        return Integer.parseInt(PROPERTIES.getProperty("severity"));
    }

    public static void setLoggerSeverity(int loggerSeverity) {
        Utils.loggerSeverity = loggerSeverity;
    }

    public static String getPathToScreenshots() {
        return PROPERTIES.getProperty("pathToScreenshots");
    }

    public static void setPathToScreenshots(String pathToScreenshots) {
        Utils.pathToScreenshots = pathToScreenshots;
    }

    public static String getPathToTestData() {
        return PROPERTIES.getProperty("pathToData");
    }

    public static void setPathToTestData(String pathToTestData) {
        Utils.pathToTestData = pathToTestData;
    }


}
