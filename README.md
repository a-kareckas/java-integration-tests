## Java Integration Tests

This project contains examples and training material regarding Java integration testing.

### Modules: 

- **domain**: core business domain

- **spring-inside-server-it**: Spring context loaded, mocked web server, in-memory H2 database, simple bean mock example

- **spring-outside-server-it**: Spring context loaded, web server started, Spring test rest template used, Wiremock example

- **spring-outside-server-test-container-it**: Spring context loaded, web server started, Rest Assured used, Test container Postgresql database started

### Execution:

 - Execute command on parent pom or on any specific child pom: 
 
    > mvn clean install
 
### Tools:

 - Java 14
 - Maven 3
 - Spring Boot 2
 - JUnit5
 - Hamcrest
 - Wiremock
 - RestAssured