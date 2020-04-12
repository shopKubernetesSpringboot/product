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
- Reactive junit tests.
- Basic auth security implementation & csrf.
- No persistence transaction implementation (https://spring.io/blog/2019/05/16/reactive-transactions-with-spring).
- Sonar gradle plugin (you can run sonarqube gradle task if you have sonar installed on localhost:9000)
  
### Run
#### Spring boot application
- Run ShopProductApp as a spring-boot app:
    - command line: `gradlew :bootRun`
    - intellij: right button on `ShopProductApp.java` & Run...
#### With docker

```shell script
#optional
docker system prune

#build & run image
#profile prod to exclude embedded mongo, also don't execute tests
./gradlew build -Pprofile=prod -x test
docker build -t techtests/shopproduct .
#run mongo & check connection
docker run -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=password --name shop-mongo --network-alias shop-mongo mongo
mongo --host mongodb://localhost:27017
#get docker mongo ip
MONGO_IP=$(docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' shop-mongo)
# or docker inspect <containerId> | grep "IPAddress"
#run microservice
docker run -p 8081:8081 -e "SPRING_PROFILES_ACTIVE=prod" -e "MONGO_IP=$MONGO_IP" -t techtests/shopproduct
```

#### With kubernetes/minikube

## Kubernetes
Compile, publish to docker and deploy to kubernetes

    ./publishToDocker.sh && bash -c '../kubernetes/publish.sh $0' product && bash -c '../kubernetes/publish.sh $0' mongo 
