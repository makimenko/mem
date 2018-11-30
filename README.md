# About
Speed-up prototyping of web app development ([Spring-MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc) and [Angular 7](https://angular.io)) using [Swagger code generator](https://swagger.io/tools/swagger-codegen/).
- Manually define [Open API](https://github.com/OAI/OpenAPI-Specification) in ```mem-design\src\main\resources\mem.yaml```
- Automatically generate Java Spring-MVC serve-side and Angular7 client-side code(including Domain and REST API)
- Manually implement server-side and client logic

# Example
This repository includes sample quiz game.
<img src="https://raw.githubusercontent.com/makimenko/files/master/mem/images/Quiz.gif">

# Build and Run
## Pre-requisites
- Maven
- Node.js and npm

## Buld
```
mvn install
```

## Run
```
cd mem-server
java -jar target/mem-server-0.2.0.0-SNAPSHOT.jar
```
Open web browser http://localhost