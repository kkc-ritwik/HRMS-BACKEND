# Recruitment Module Backend Application

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Dependencies](#dependencies)

## Introduction
This is a Spring Boot-based recruitment module backend application, which manages recruitment processes such as job requisitions, candidate applications, interview scheduling, and settings configurations. It integrates JWT authentication and MongoDB for persistence.

## Features
- Manage `Job`, `JobRequisition`, and `Candidate` entities with CRUD operations
- Manage interview scheduling and approval processes
- Cross-Origin Resource Sharing (CORS) configuration for integration with frontend applications
- JWT-based authentication and authorization
- Validation for models and request bodies

## Prerequisites
- Java 11 or higher
- Maven
- MongoDB

## Setup and Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/your-repo/recruitment-backend.git
    ```
2. Navigate to the project directory:
    ```sh
    cd recruitment-backend
    ```
3. Install dependencies:
    ```sh
    mvn install
    ```
4. Configure MongoDB connection in `application.properties`:
    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/recruitment
    ```

## Running the Application
1. Start the application:
    ```sh
    mvn spring-boot:run
    ```
2. The application will be available at `http://localhost:8080`.

## API Endpoints
### Job Endpoints
- **GET /v1/jobs**: Get all jobs
- **POST /v1/jobs**: Create a new job
- **PUT /v1/jobs/{id}**: Update an existing job by ID
- **DELETE /v1/jobs/{id}**: Delete a job by ID

### Candidate Endpoints
- **GET /v1/candidates**: Get all candidates
- **POST /v1/candidates**: Submit a new candidate application
- **PUT /v1/candidates/{id}**: Update candidate details by ID
- **DELETE /v1/candidates/{id}**: Delete a candidate by ID

### Interview Scheduling Endpoints
- **GET /v1/interviews**: Get all scheduled interviews
- **POST /v1/interviews**: Schedule a new interview
- **PUT /v1/interviews/{id}**: Update interview details by ID
- **DELETE /v1/interviews/{id}**: Cancel an interview by ID

### Settings Endpoints
- **GET /v1/settings/general**: Get general settings
- **PUT /v1/settings/general**: Update general settings

## Dependencies
Include the following dependencies in your `pom.xml` file:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data MongoDB -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>

    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- JWT Auth -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
    </dependency>

    <!-- Jakarta Validation API -->
    <dependency>
        <groupId>jakarta.validation</groupId>
        <artifactId>jakarta.validation-api</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>

    <!-- Spring Boot Starter Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
