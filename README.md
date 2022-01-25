# Dunder Mifflin Paper Company Customer API
PoC of REST API to perform CRUD (Create, Read, Update and Delete) operations.
Project build using Spring Boot Framework and H2 in-memory database to store records.  
Database schema at src/main/resources/schema.sql is loaded upon application startup.
Project uses [Swagger](https://swagger.io/specification/) to document and interact with the API.

## Prerequisites
 - Java 8 JDK and later
 - Apache Maven (optional)
 
## Building with Maven 
### Linux/Mac OS 
- To build project & run tests
``` $ ./mvnw clean install ``` 
- To run application
``` $ ./mvnw spring-boot:run ```   

### Windows
- To build project & run tests
``` |> ./mvnw.cmd clean install ```
- To run application
``` |> ./mvnw.cmd spring-boot:run ```

Then open web browser at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)