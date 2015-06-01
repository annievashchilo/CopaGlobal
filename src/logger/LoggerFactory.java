package logger;

import base.utils.Utils;

import java.io.FileInputStream;
import java.util.Properties;


public class LoggerFactory {

    private static Integer severity = null;

    public static Logger getLogger() {
        Logger logger = Logger.getDefault();
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(Utils.configFile));
            int sev = Utils.getLoggerSeverity();
            logger.setSeverity(sev);
            severity = sev;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logger;
    }


}
