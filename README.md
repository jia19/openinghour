
openinghour is a demo spring boot application which displays opening hours for a mocked restaurants. 

The implementation has four modules: 
* openinghour-common contains the domain model classes(the improved version of the mock data provided in this demo).
* openinghour-integration contains the integration module, which can be used to integrate input data. 
* openinghour-service contains shared service functionalities across endpoints.
* opeinghour-web contains endpoints.

# Build and Run

## Build project:

```mvn
mvn clean install
```

### Run with Spring Boot Maven plugin

```sh
cd openinghour-web
mvn spring-boot:run
```

### Run locally with Docker

Build Docker image:

```sh
docker build --build-arg JAR_FILE=/openinghour-web/target/openinghour-web-1.0.0-SNAPSHOT.jar -t openinghour .
```

Start container:

```sh
docker run -p 8080:8080 openinghour:latest
```

### Test

```sh
curl localhost:8080/openinghour/v1/restaurants/mock
```
Here 'mock' is the restaurant id for the mock data provided. One exception will be thrown for other ids as no real data provided in this demo.

# Openinghour - by default provided functionality

## Logging

Logging is performed by using standard [SLF4J](http://www.slf4j.org/) loggers. "Simple Logging Facade for Java" (SLF4J) is an abstraction and therefore it needs a concrete implementation, which in our case is Logback. See logback.xml in openinghour-web for the configurations.

## Caching

The mocked data are written in a json file and mapped to MockedOpeningHours.java in openinghour-integration. Mocked data are converted to RestaurantOpeningHours.java and put in Spring cache when the first call is made. 

```java
@Cacheable(value = CacheConfig.CACHE_PROPERTY_NAME,
    key = "#id")
public RestaurantOpeningHours getOpeningHours(String id) {
    return openingHourClient.getOpeningHours();
}
```

## OpenAPI documents

```sh
http://localhost:8080/openinghour/v1/api-docs
```
See OpenApiConfig.java in openinghour-web.


# Openinghour - To be improved

## Circuit Breaker

Circuit breaker pattern is a way of making an application fault tolerant and highly available. The provided library is [Resilience4j Circuit Breaker](https://resilience4j.readme.io/docs/circuitbreaker).

## Bulkhead

The [Bulkhead pattern](https://docs.microsoft.com/en-us/azure/architecture/patterns/bulkhead) implemented by [Resilience4j Bulkhead](https://resilience4j.readme.io/docs/bulkhead) can restrict the amount of calls that can be made to a method.

## Unit test

The unit tests are ignored in this demo. More test cases should be added.

## Profiles
Different profiles should be provided, e.g. dev, prod.

# A better data format for the input opening hours
Defined in openinghour-common module. One endpoint is provided to display the mocked data.

```sh
curl http://localhost:8080/openinghour/v1/restaurants/json/mock
```

```json
{
    "openingHours": [
        {
            "weekday": "Monday",
            "actions": []
        },
        {
            "weekday": "Tuesday",
            "actions": [
                {
                    "type": "open",
                    "value": 36000
                },
                {
                    "type": "close",
                    "value": 64800
                }
            ]
        },
        {
            "weekday": "Wednesday",
            "actions": []
        },
        {
            "weekday": "Thursday",
            "actions": [
                {
                    "type": "open",
                    "value": 37800
                },
                {
                    "type": "close",
                    "value": 64800
                }
            ]
        },
        {
            "weekday": "Friday",
            "actions": [
                {
                    "type": "open",
                    "value": 36000
                }
            ]
        },
        {
            "weekday": "Saturday",
            "actions": [
                {
                    "type": "close",
                    "value": 3600
                },
                {
                    "type": "open",
                    "value": 36000
                }
            ]
        },
        {
            "weekday": "Sunday",
            "actions": [
                {
                    "type": "close",
                    "value": 3600
                },
                {
                    "type": "open",
                    "value": 43200
                },
                {
                    "type": "close",
                    "value": 75600
                }
            ]
        }
    ]
}
```
