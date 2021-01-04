# EJProject

This is a **simple CRUD project** in Spring boot , where two application communicate with each other. Applications implement custom logging. Which includes storage into file.

**EJData** project contain REST services with CRUD methods and connect to MSSQL database.

**EJApp** project contain REST service, which communicate with Data.





## Database

This simple database contain two tables with one-to-many connection.

![](C:\Users\Byhi\IdeaProjects\EJProject\ejdata\documentation\ejdb_diagram.png)

## Configuration and Run

If you want to try out the applications then all you have to do is clone the project and create the database.

**Git - clone**

```
$ git clone https://github.com/byhi/EJProject.git
$ cd EJProject
$ mvn clean install
```

**Database**

Install an MSSQL server and then import the SQL file that contains the database.

**Path** 

```
ejdata/documetation/ejdbscript.sql 
```

Then make sure that the settings in the properties files in the applications are correct.

The application may not be able to create log files in that path (*ejlogger.url*). You may want to create the specified path or specify an existing one in the logging.properties files.

 **logging.properties**

```
ejlogger.url=c:\\Users\\Byhi\\ejdata\\log\\
ejlogger.fileName=ejlog.txt
```



Contains the EJData address and the required endpoint that returns all Products that include Users

 **service.properties**

```
ejservice.dataurl=http://localhost:8081
ejservice.endpoint=/product/all
```

Database and server settings can be found in application.properties.

 **application.properties**

```
server.port=8081
.....
spring.datasource.url=jdbc:sqlserver://localhost;databaseName=ejdb
```

## Properties

#### Running the application with Maven

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```
$ cd ejapp
$ mvn spring-boot:run
```



## Logger

Logging can be separated two path.



**Method Time**
Accounting for runtime of methods. Also, This shows provides a record of service time.



**Other logging options**
Log exceptions and other information.



## Other

For testing, the following importable  options are available in the  documentation folder in root of maven project.

- JMeter
- Postman



## Futures 

Below I list possible further improvements

- Getting property files out of the jar for the pleasure of operators
- Handler larger log files
- Implementation for MongoDB
- Make Unit test for services and more JMeter sampler 

