# Pedeai API Pedidos

A microservice for managing orders in the Pedeai food delivery system built with Quarkus.

## Overview

This service handles:
- Order creation and management
- Status tracking
- Payment integration
- Client validation

## Prerequisites

- JDK 17+
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 14+

## Quick Start

1. Start dependencies:
    ```sh
    docker-compose up -d
    ```

2. Run in dev mode:
    ```sh
    ./mvnw quarkus:dev
    ```

3. Access Swagger UI: [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)

## Running the Application with Docker Compose

You can run the application using Docker Compose. This will set up the necessary services and run the application in a containerized environment.

### Steps

1. Clone the repository:
    ```sh
    git clone git@github.com:joaomarcos11/pedeai-api-pedidos.git
    cd pedeai-api-pedidos
    ```

2. Build the Docker images and start the services:
    ```sh
    docker-compose up --build
    ```

3. The application should now be running. You can access it at `http://localhost:8080`.

## Building the Application

You can build the application using Maven. This will compile the code, run the tests, and package the application.

### Steps

1. Build the application:
    ```sh
    mvn clean package
    ```

2. The packaged application will be available in the `target` directory.

## Architecture

This project follows the principles of Hexagonal Architecture (also known as Ports and Adapters Architecture). The main goal of this architecture is to create loosely coupled application components that can be easily tested and maintained.

### Key Concepts

- **Domain Layer**: Contains the core business logic and domain entities. This layer is independent of any external systems or frameworks.
- **Application Layer**: Contains the application services that orchestrate the business logic. This layer interacts with the domain layer and external systems through ports.
- **Ports**: Interfaces that define the input and output boundaries of the application. Ports are implemented by adapters.
- **Adapters**: Implementations of the ports that interact with external systems (e.g., databases, messaging systems, external APIs).

### Project Structure
```sh
src/
├── main/
│   └── java/
│       └── org/jfm/
│           ├── domain/         # Business logic
│           ├── controller/     # REST endpoints
│           └── infra/          # External adapters
└── test/                       # Test suite
```

# Configuration

```sh
# application.properties
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/pedidos
mp.rest.client.cliente-api.url=http://localhost:8081
mp.rest.client.pagamento-api.url=http://localhost:8082
```

# API Endpoints

- POST /pedidos - Create order
- GET /pedidos - List orders
- GET /pedidos/{id} - Get order details
- PUT /pedidos/{id} - Update order status

# Testing
Run tests:
```sh
./mvnw test
```

Generate coverage:
```sh
./mvnw verify -Pcoverage
```

# Build & Deploy
Package application:
```sh
./mvnw package
```
Run as container:
```sh
docker build -t pedeai/pedidos .
docker run -p 8080:8080 pedeai/pedidos
```
# Health Checks
- Liveness: /q/health/live
- Readiness: /q/health/ready
- Metrics: /q/metrics
