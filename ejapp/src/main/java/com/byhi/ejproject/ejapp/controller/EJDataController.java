package com.byhi.ejproject.ejapp.controller;

import com.byhi.ejproject.ejapp.meter.FileLogging;
import com.byhi.ejproject.ejapp.meter.LogMethodExecutionTime;
import com.byhi.ejproject.ejapp.model.Product;
import com.byhi.ejproject.ejapp.properties.EJServiceProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 *This class communicate with EJData application
 */
@RestController
public class EJDataController {
    @Autowired
    FileLogging fileLogging;

    @Autowired
    EJServiceProperties ejServiceProperties;

    private final RestTemplate restTemplate;


    /**RestTemplate initialization
     * @param restTemplateBuilder - builder for restTemplate
     */
    public EJDataController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**This method call the endpoint by properties and provid all Product entity in /all endpoint.
     * @param headers - contains the request header information
     * @return - entity array wrapped in ResponseEntity
     */
    @GetMapping("/all")
    @LogMethodExecutionTime
    public ResponseEntity<Product[]> getAllEntity(@RequestHeader Map<String, String> headers) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        fileLogging.writeMsgToSpacialFile(this.getClass().getSimpleName(), methodName,headers);

        String url = ejServiceProperties.getDataurl() + ejServiceProperties.getEndpoint();
        ResponseEntity<String> productEntity = this.restTemplate.getForEntity(url, String.class);

        Product[] product = null;
        try {
            product = this.getBodyFromResponse( productEntity);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(this.getClass().getSimpleName(), e);
        }

        return ResponseEntity.ok(product);
    }

    /**Convert json from Response body to array
     * @param responseEntity - contain entities in json in body
     * @return - Product array
     * @throws JsonProcessingException - throw it if convert is failed
     */
    private Product[] getBodyFromResponse(ResponseEntity<String> responseEntity) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = responseEntity.getBody();
        return objectMapper.readValue(json, Product[].class );
    }
}
