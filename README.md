# it-summit-meetup
Sample demo for IT Summit Meetup

# Overview
There are three main projects under this repository.
* Eureka Server: as a service discovery
* Hello Service: a service to return a message
* Zuul: an API Gateway for routing/forwarding request path to Hello Service

# Run the Demo

## Prepare the server
### Eureka

    cd it-summit-meetup/eureka
    mvn spring-boot:run


### HelloService


     cd it-summit-meetup/helloservice
     mvn spring-boot:run -DPORT=8090
     mvn spring-boot:run -DPORT=8091


### Zuul


     cd it-summit-meetup/zuul
     mvn spring-boot:run


## Test

     curl -XGET http://localhost:7777/hello-service/hello