package Logger;

import java.io.FileWriter;

public class Logger implements ILogger {

    // Eager initialization: object of class is created when it is loaded to the memory by JVM.
    private static final ILogger instance = new Logger();
    private FileWriter m_fileWriter;

    private Logger() {
        // Create the *.log file
        try
        {
            m_fileWriter = new FileWriter("log.csv", false);
            WriteToLog(LogLevel.INFO, LogSubject.LOGGER, "Log file created.");
        }
        catch (Exception exception)
        {
            HandleExceptions(exception);
        }
    }

    public static ILogger getInstance()
    {
        return instance;
    }

    @Override
    public void WriteToLog(LogLevel logLevel, LogSubject logSubject, String message)
    {
        try
        {
            m_fileWriter.append(String.format("%s,%s,%s\n", logLevel, logSubject, message));
            m_fileWriter.flush();
        }
        catch (Exception exception)
        {
            HandleExceptions(exception);
        }
    }

    private void HandleExceptions(Exception exception)
    {
        System.out.print("An error occurred: ");
        System.out.println(exception.getMessage());
    }
}
