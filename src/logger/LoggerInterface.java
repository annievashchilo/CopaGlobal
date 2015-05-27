package logger;


public interface LoggerInterface
{
    void setSeverity(int severity);

    void log(IEntry entry);

    interface Severity
    {
        int EXCEPTION = 0;

        int ERROR = 1;

        int WARNING = 2;

        int INFO = 3;

        int PERFOMANCE = 4;

        int DEBUG = 5;

        int ALL = Integer.MAX_VALUE;
    }

    interface IEntry
    {
        int getSeverity();

        String getMessage();

        Throwable getException();
    }
}
