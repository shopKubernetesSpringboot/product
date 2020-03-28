# Getting Started

## Description

Fully reactive Product REST API.

### Prerequisites
- Enable annotation processors (in your IDE for lombok)

### Architecture
- Reactive Layers architecture (spring-framework reactor).
- Java non-blocking functional programming: reactor+streams.
- REST API's with Spring-WebFlux.
    - Using [Functional Programming Model](https://docs.spring.io/spring-framework/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html#_functional_programming_model)
- Persistence with reactive MongoDb.

Notes:
- No security implementation at all.
- No persistence transaction implementation (https://spring.io/blog/2019/05/16/reactive-transactions-with-spring).
- You'll find some reactive junit tests.
- Sonar gradle plugin (you can run sonarqube gradle task if you have sonar installed on localhost:9000)
  
### Try it
- Run App as a spring-boot app:
    - command line: `gradlew :bootRun`
    - intellij: right button on `App.java` & Run...

- Use `postman_collection.json` (importing the json file in Postman client)
