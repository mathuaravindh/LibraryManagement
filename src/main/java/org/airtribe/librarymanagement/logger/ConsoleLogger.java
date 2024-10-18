package org.airtribe.librarymanagement.logger;

public class ConsoleLogger implements Logger {
    public void LogInfo(String message)
    {
        System.out.println("Info : " + message);
    }

    public void LogError(String message)
    {
        System.out.println("Error : " + message);
    }
}
