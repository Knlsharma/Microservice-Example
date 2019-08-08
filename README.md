# Microservice-demo

## How to run?

### Build all modules:

`spring-boot-microservices-series> ./mvnw clean package -DskipTests=true`

**Start each microservice either in local or in docker:**

**Local:** `spring-boot-microservices-series/catalog-service> ./mvnw spring-boot:run`

* service-registry:
    * hostname: service-registry
    * Ports: 8761:8761
    * URL: http://localhost:8761/

* Movie_Catalog-service:
    * hostname: catalog-service
    * Ports: 8081:8081
    * URL: http://movie-catalog-service:8081/
    
* Movie_Info-service   
    * hostname: inventory-service
    * Ports: 8082:8082
    * URL: http://movie-info-service:8082/
    
* Movie_Rating-service  
    * hostname: order-service
    * Ports: 8083:8083
    * URL: http://movie-rating-service:8083/
