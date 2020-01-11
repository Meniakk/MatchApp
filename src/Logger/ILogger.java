package Logger;

import sun.rmi.runtime.Log;

public interface ILogger {

    enum LogLevel {
        INFO,
        WARNING,
        ERROR
    }

    enum LogSubject {
        SERVER,
        USER,
        DATABASE,
        MATCHER,
        VISITOR,
        LOGGER,
        COMMAND
    }

    void WriteToLog(LogLevel logLevel, LogSubject logSubject, String message);
}
