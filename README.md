# About
Speed-up prototyping of web app development (Spring MVC + Angular 7)
- Manually define Open API in (mem-design\src\main\resources\mem.yaml)
- Automatically generate Java Spring-MVC serve-side and Angular7 client-side code(including Domain and REST API)
- Manually implement server-side and client logic

# Example
This repository includes sample quiz game. 

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