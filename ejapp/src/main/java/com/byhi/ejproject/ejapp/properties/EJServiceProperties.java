package com.byhi.ejproject.ejapp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 *This class provides the basic configurations for communication with other server from properties file.
 */
@Component
@ConfigurationProperties(prefix = "ejservice")
@PropertySource("classpath:service.properties")
@Validated
public class EJServiceProperties {

    @NotEmpty
    private String  dataurl;

    @NotEmpty
    private String endpoint;

    public String getDataurl() {
        return dataurl;
    }

    public void setDataurl(String dataurl) {
        this.dataurl = dataurl;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "EJServiceProperties{" +
                "dataurl='" + dataurl + '\'' +
                ", endpoints=" + endpoint +
                '}';
    }

}
