package com.byhi.ejproject.ejdata.controller;

import com.byhi.ejproject.ejdata.meter.FileLogging;
import com.byhi.ejproject.ejdata.meter.LogMethodExecutionTime;
import com.byhi.ejproject.ejdata.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *This abstraction contain the typical CRUD operations.
 * Currently, methods log requests, body data, and run time with the LogMethod Execution Time annotation.
 * @param <T> - can only be a service that implements CrudService
 * @param <E> - the entity to be served
 */
public abstract class AbstractCrudController<T extends CrudService<E>, E> {

    FileLogging fileLogging;

    T service;

    /**This method provid all entity in /all endpoint. Returns with entities wrapped in ResponseEntity
     * @param headers - contains the request header information
     * @return - represents the whole HTTP response: status code, headers, and body
     */
    @GetMapping("/all")
    @LogMethodExecutionTime
    public ResponseEntity<List<E>> getAllEntity(@RequestHeader Map<String, String> headers ) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        fileLogging.writeMsgToSpacialFile(service.getClass().getSimpleName(), methodName,headers);
        return ResponseEntity.ok(service.getAllEntity());

    }

    /**This method provid an entity by id in /{id} endpoint. Returns an entity wrapped in ResponseEntity
     * @param id - the id of the requested entity
     * @param headers - contains the request header information
     * @return - represents the whole HTTP response: status code, headers, and body
     */
    @GetMapping("/{id}")
    @LogMethodExecutionTime
    public ResponseEntity<E> getEntityById(@PathVariable("id") Long id, @RequestHeader Map<String, String> headers) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        fileLogging.writeMsgToSpacialFile(service.getClass().getSimpleName(),methodName,headers);
        return  ResponseEntity.ok(service.getEntityById(id));
    }

    /**This method create an entity in / endpoint. Returns with 200 http status.
     * @param entity - entity to create
     * @param headers - contains the request header information
     * @return - represents the whole HTTP response: status code, headers, and body
     */
    @LogMethodExecutionTime
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEntity(@RequestBody E entity, @RequestHeader Map<String, String> headers) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        fileLogging.writeMsgToSpacialFile(service.getClass().getSimpleName(),methodName,headers);
        try {
            service.saveEntity(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException e) {
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(service.getClass().getSimpleName(), e);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(service.getClass().getSimpleName(), e);
        } catch (Exception e) {
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(service.getClass().getSimpleName(), e);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**This method update an entity in /{id} endpoint by id. Returns with  http status.
     * @param entity - entity to update
     * @param id - the id of the entity to be updated
     * @param headers - contains the request header information
     * @return - represents the whole HTTP response: status code, headers, and body
     */
    @LogMethodExecutionTime
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<E> updateEntity(@RequestBody E entity, @PathVariable Long id, @RequestHeader Map<String, String> headers) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        fileLogging.writeMsgToSpacialFile(service.getClass().getSimpleName(),methodName,headers);
        try {
            service.updateEntity(entity, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e){
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(service.getClass().getSimpleName(), e);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(service.getClass().getSimpleName(), e);
        }catch (Exception e){
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(service.getClass().getSimpleName(), e);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**This method delete an entity in /{id} endpoint by id. Returns with  http status.
     * @param id - the id of the entity to be deleted
     * @param headers - contains the request header information
     * @return - represents the whole HTTP response: status code, headers, and body
     */
    @LogMethodExecutionTime
    @DeleteMapping("/{id}")
    public ResponseEntity<E> deleteEntity(@PathVariable Long id, @RequestHeader Map<String, String> headers) {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        fileLogging.writeMsgToSpacialFile(service.getClass().getSimpleName(),methodName,headers);
        try {
            service.deleteEntity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NullPointerException e){
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(service.getClass().getSimpleName(), e);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(service.getClass().getSimpleName(), e);
        }catch (Exception e){
            e.printStackTrace();
            fileLogging.writeExceptionToSpacialFile(service.getClass().getSimpleName(), e);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
