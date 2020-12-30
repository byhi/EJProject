package com.byhi.ejproject.ejdata.properies;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Component
@ConfigurationProperties(prefix = "ejlogger")
@PropertySource("classpath:logging.properties")
@Validated
public class EJLoggerProperties {
    /**
     *This class provides the basic logger configurations from properties file.
     */

    @NotEmpty
    private String url;

    @Size(max = 10)
    @NotEmpty
    private String fileName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
