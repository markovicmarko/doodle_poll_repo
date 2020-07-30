# Doodle Polls project

## Requirements
- Java 8

## Includes
- Spring Boot 2.2.4
  - spring-boot-starter-web basic web starter
  - spring-boot-starter-data-mongodb for communication with Mongo db
  - spring-boot-starter-test for testing
 - Lombok for reducing boilerplate code
 - Swagger for api documentation
 - Modelmapper for mapping domain objects to DTOs
 - Docker for populated mongo db container
 - Actuator for application health check
 

## Important properties
application.properties

## Mongo docker image
Create docker image with mongo database populated with polls.json data
````
docker-compose -f docker-compose-doodle-polls.yml up
````
## Application build
Built with maven:
````
mvn clean install
````

## Application run
````
mvn spring-boot:run
````

## Implementation details
The application is showcase for a simple api.
Database used for the project is MongoDB with Text indicies on String fields.
Basic checks are performed and appropriate exceptions are thrown inside controllers which are handled by Controller advice.
Docker image with mongo db populated with poll.json example is created and used for testing the application.
Swagger is used for api documentation
Actuator is used for app health check

## If there was more time I would
 - Add more api endpoints for various search criteria
 - Improve mongo search to utilize full text search 
 - Add logging
 - Add more tests

