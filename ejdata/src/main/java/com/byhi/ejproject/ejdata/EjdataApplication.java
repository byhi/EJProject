package com.byhi.ejproject.ejdata;

import com.byhi.ejproject.ejdata.properies.EJLoggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

@SpringBootApplication
public class EjdataApplication {

    @Autowired
    EJLoggerProperties ejLoggerProperties;

    public static void main(String[] args) {
        SpringApplication.run(EjdataApplication.class, args);
    }

    /**
     * Check file path for logger when start the application
     */
    @Bean
    public void checkLogProperies(){
        if (Files.notExists( Paths.get(ejLoggerProperties.getUrl()))) {
            throw new NoSuchElementException();
        }
    }

}
