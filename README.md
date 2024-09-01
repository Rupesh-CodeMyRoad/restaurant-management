# Restaurant Management System

A Spring Boot application for managing restaurant reservations. This application uses PostgreSQL as the database, with Java 17 and Spring Boot 3.3.3.

## Features

- Reservation Management: Create, update, and cancel reservations.
- Table Management: Allocate tables based on capacity and availability.
- Reservation Log: Track changes and status of reservations.

## Getting Started

### Prerequisites

- Java 17
- PostgreSQL
- Maven

### Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/Rupesh-CodeMyRoad/resturant-management.git
    cd resturant-management
    ```

2. **Configure the database:**

    - Update the `application.properties` file with your PostgreSQL database credentials.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

4. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

### Swagger Documentation

Once the application is running, you can access the Swagger documentation at:

[http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#)

### Dependencies

- **Spring Boot DevTools**: For automatic restarts and live reload.
- **Lombok**: To reduce boilerplate code.
- **Spring Web**: For RESTful APIs.
- **Spring Data JPA**: For data access and repository support.
- **PostgreSQL Driver**: For PostgreSQL database connectivity.
