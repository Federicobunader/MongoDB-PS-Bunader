# MongoDB-PS-Bunader

# Kitchensink With Spring Boot Application

This project is a Spring Boot application that has been migrated from a JBoss EAP application.

## Requirements

To build and run the application, you will need:

- JDK 11 or higher
- Maven 3.6.0 or higher

## Building the Project

To build the project, follow these steps:

1. Open a terminal at the root of the project.
2. Run the following command to build the project with Maven:

bash
mvn clean install

## Running the Application
1. Open a terminal at the root of the project.
2. Run the following command:
3. You NEED to have a MongoDB instance running on localhost:27017

java -jar target/kitchensink-with-spring-boot-0.0.1-SNAPSHOT.jar

The application will start and be available at http://localhost:8080.

## Testing
To run the tests, you can use the following command at the root of the project:

mvn test

## Endpoints

The application exposes the following endpoints:

1. GET /members: Lists all members.
2. GET /members/{id}: Gets a member by their ID.
3. POST /members: Creates a new member.