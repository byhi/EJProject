package com.byhi.ejproject.ejdata.meter;

import com.byhi.ejproject.ejdata.properies.EJLoggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Map;

/**
 *This class contain different methods for the logs are written to a file.
 *The required configuration is provided  by EJLoggerProperties
 */
@Component
public class FileLogging {

    @Autowired
    EJLoggerProperties ejLoggerProperties;


    /**Based on the configuration, insert the message as a new line in that file.
     * Creates the file if it does not exist.
     * @param msg - the message
     */
    public void writeMsgToDefault(String msg) {
        String fileName = ejLoggerProperties.getUrl() + ejLoggerProperties.getFileName();

        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(msg + System.lineSeparator() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Based on the configuration, insert the message as a new line in that file.
     * Creates the file if it does not exist.
     * @param className - expands the file name
     * @param msg - the message
     */
    public void writeMsgToSpacialFile(String className,String msg) {
        String fileName = ejLoggerProperties.getUrl() + className + ejLoggerProperties.getFileName();
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(msg + System.lineSeparator() );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Based on the configuration, insert the messages as a new lines in that file.
     * Creates the file if it does not exist.
     * @param className - expands the file name
     * @param methodName - will include the separation of logs
     * @param dataMap -  the messages
     */
    public void writeMsgToSpacialFile(String className, String methodName, Map<String, String> dataMap) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(String.format("%s ---------------------------------------------------------------------- %s %s", Instant.now().toString() , methodName, System.lineSeparator()));
        dataMap.forEach((k, v) -> {
            buffer.append(String.format("%s : %s %s", k , v, System.lineSeparator()));
        });

        this.writeMsgToSpacialFile(className,buffer.toString());
    }

    /**Based on the configuration, insert insert the stack trace of exception as a new lines in that file.
     * Creates the file if it does not exist.
     * @param className - expands the file name
     * @param e - the exception
     */
    public void writeExceptionToSpacialFile(String className, Exception e){
        String fileName = ejLoggerProperties.getUrl() + className + ejLoggerProperties.getFileName();
        PrintWriter pw = null;
        try (FileWriter writer = new FileWriter(fileName, true)){
            writer.write(String.format(" %s---------------------------------------------------------------------- %s", Instant.now().toString(), System.lineSeparator()));
            pw = new PrintWriter(writer);
            e.printStackTrace(pw);
            pw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
