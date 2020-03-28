# Getting Started

## Description

Fully reactive Product REST API.

Also see the related projects:
 
- [front-end ReactJs project](https://github.com/davidgfolch/shop-web)
- [shopProduct mss](https://github.com/davidgfolch/shopProduct)

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
- Reactive junit tests.
- Basic auth security implementation & csrf.
- No persistence transaction implementation (https://spring.io/blog/2019/05/16/reactive-transactions-with-spring).
- Sonar gradle plugin (you can run sonarqube gradle task if you have sonar installed on localhost:9000)
  
### Try it
- Run App as a spring-boot app:
    - command line: `gradlew :bootRun`
    - intellij: right button on `App.java` & Run...

- Use `postman_collection.json` (importing the json file in Postman client)
