# employee-service

## Swagger

[Swagger UI](http://localhost:8090/swagger-ui/index.html)

## H2 Console

[H2 Console](http://localhost:8090/h2-console)

## Database Queries

```sql
SELECT * FROM EMPLOYEE;
SELECT * FROM PROJECT;
SELECT * FROM ROLE;
```

## Overview

The Employee service is a Spring Boot application that provides RESTful APIs to interact with the employee database. It
supports operations such as creating, updating, retrieving, and deleting employee records.

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Docker (optional, for running the application in a container)

## Setup Instructions

Clone the Repository
git clone https://github.com/shvkpaul/employee-service.git

Build the Project : mvn clean install
Run the Application : mvn spring-boot:run

Running Tests
To run the unit and integration tests, use the following command: mvn test

Steps to build and run the Docker image:

Build the Docker image:  
docker build -t employee-service .
Run the Docker container:
docker run -p 8080:8080 employee-service

Additionally added postman collection, look for employee-service from the root directory of the collection.
