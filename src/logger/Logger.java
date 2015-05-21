package logger;

import base.utils.Utils;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Logger implements LoggerInterface {
    protected static java.util.logging.Logger perfLogger;
    protected static Logger defaultLogger = new Logger();
    private final int defaultLogLevel = Severity.INFO;
    private final String dataFormat = "[yyyy-MM-dd HH:mm:ss]";
    protected int severity;

    public static String severityToString(int severity) {
        switch (severity) {
            case Severity.ERROR:
                return "ERROR";
            case Severity.WARNING:
                return "WARNING";
            case Severity.INFO:
                return "INFO";
            case Severity.DEBUG:
                return "DEBUG";
            case Severity.ALL:
                return "ALL";
        }

        return null;
    }

    public static String stackTrace(Throwable ex) {
        ByteArrayOutputStream ostr = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintWriter(ostr, true));

        return ostr.toString();
    }

    public static Logger getDefault() {
        return Logger.defaultLogger;
    }


    public void log(IEntry entry) {
        Reporter.log(entry.toString());
        System.out.println(entry.toString());
    }

    public void log(int severity, String message, Throwable exception) {
        String shot;
        if (this.severity < severity)
            return;

        if (severity == Severity.EXCEPTION) {
            shot = " SCREENSHOT: " + Utils.getHandler().takeScreenshot("TestScreenshot");

            defaultLogger.log(defaultLogLevel, shot); //to put HTML tags out of error output, which escapeHTML symbols
            if (exception != null) {
                Assert.fail(message, exception);
            } else {
                Assert.fail(message);
            }

        } else if (severity == Severity.ERROR) {
            shot = " SCREENSHOT: " + Utils.getHandler().takeScreenshot("TestScreenshot");
            log(new Entry(severity, message + shot, exception));
        } else {
            log(new Entry(severity, message, exception));
        }
    }

    public void log(int severity, String message) {
        log(severity, message, null);
    }

    public void log(int severity, Throwable exception) {
        log(severity, null, exception);
    }

    public void error(String message, Throwable exception) {
        log(Severity.ERROR, message, exception);
    }

    public void warning(String message, Throwable exception) {
        log(Severity.WARNING, message, exception);
    }

    public void info(String message, Throwable exception) {
        log(Severity.INFO, message, exception);
    }

    public void all(String message, Throwable exception) {
        log(Severity.ALL, message, exception);
    }

    public void debug(String message, Throwable exception) {
        log(Severity.DEBUG, message, exception);
    }

    public void error(String message) {
        error(message, null);
    }

    public void warning(String message) {
        warning(message, null);
    }

    public void info(String message) {
        info(message, null);
    }

    public void debug(String message) {
        debug(message, null);
    }

    @Override
    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void exception(String message, Throwable exception) {
        log(Severity.EXCEPTION, message, exception);
    }

    public void exception(String message) {
        log(Severity.EXCEPTION, message, null);
    }

    public class Entry implements IEntry {

        protected int severity;

        protected String message;

        protected Throwable exception;

        public Entry(int severity, String message, Throwable exception) {
            this.severity = severity;
            this.message = message;
            this.exception = exception;
        }

        @Override
        public int getSeverity() {
            return severity;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public Throwable getException() {
            return exception;
        }

        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dataFormat);
            Date time = Calendar.getInstance().getTime();
            StringBuilder sb = new StringBuilder(dateFormat.format(time));

            sb.append(severityToString(severity)).append(": ");

            if (message != null) {
                sb.append(message);
            }

            if (exception != null) {
                if (message != null) {
                    sb.append(", ");
                }
                sb.append(exception);
            }

            return sb.toString();
        }
    }

    public class PerfFormatter extends java.util.logging.Formatter {
        private final String lineSeparator = java.security.AccessController.doPrivileged(
                new sun.security.action.GetPropertyAction("line.separator"));

        @Override
        public String format(java.util.logging.LogRecord record) {
            String message = formatMessage(record);
            SimpleDateFormat dateFormat = new SimpleDateFormat(dataFormat);
            Date time = Calendar.getInstance().getTime();
            return dateFormat.format(time) + record.getLevel().getName() + ": " + message + lineSeparator;
        }
    }


}
