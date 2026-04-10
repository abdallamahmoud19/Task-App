# Tasks - REST API Task Management Application

A modern Spring Boot application for managing tasks with full CRUD operations, priority levels, and status tracking. Features a clean RESTful API architecture with PostgreSQL database integration and Docker support.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
  - [API Endpoints](#api-endpoints)
  - [Request Examples](#request-examples)
  - [Response Examples](#response-examples)
- [Project Structure](#project-structure)
- [Testing](#testing)
- [Docker Deployment](#docker-deployment)
- [Error Handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)

## Overview

Tasks is a comprehensive task tracking backend application built with Spring Boot 4.0.3. It provides a complete REST API for creating, reading, updating, and deleting tasks. Each task can be assigned a priority level (HIGH, MEDIUM, LOW) and tracked with status indicators (OPEN, COMPLETE). The application includes validation, exception handling, and seamless database integration with PostgreSQL.

## Features

- **CRUD Operations**: Create, retrieve, update, and delete tasks
- **Priority Management**: Assign HIGH, MEDIUM, or LOW priority levels to tasks
- **Status Tracking**: Track task status (OPEN or COMPLETE)
- **Due Date Management**: Set and manage task due dates
- **UUID-based Identification**: Globally unique task identifiers
- **Comprehensive Validation**: Input validation with detailed error messages
- **Exception Handling**: Global exception handler for consistent error responses
- **Data Persistence**: PostgreSQL database with automatic schema generation
- **Docker Support**: Full Docker and Docker Compose configuration
- **Modern API Design**: RESTful endpoints with proper HTTP status codes
- **Timestamp Tracking**: Automatic creation and update date tracking

## Tech Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| **Framework** | Spring Boot | 4.0.3 |
| **Java Version** | OpenJDK | 17 |
| **Database** | PostgreSQL | Latest |
| **Build Tool** | Maven | 3.x+ |
| **ORM** | Spring Data JPA/Hibernate | Auto-configured |
| **Validation** | Jakarta Validation | Auto-configured |
| **Containerization** | Docker & Docker Compose | Latest |

### Dependencies

- `spring-boot-starter-web-mvc` - Web framework
- `spring-boot-starter-data-jpa` - Data persistence
- `spring-boot-starter-validation` - Input validation
- `postgresql` - Database driver
- `spring-boot-h2console` - H2 console (development)
- `h2` - H2 database for testing

## Architecture

The application follows a layered architecture pattern:

```
┌─────────────────────────────────────┐
│      REST Controller Layer          │
│  (TaskController, ExceptionHandler) │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│       Service Layer                 │
│    (TaskService, TaskServiceImpl)    │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│    Mapper Layer                     │
│  (TaskMapper, TaskMapperImpl)        │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│    Repository Layer                 │
│      (TaskRepository)               │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│    Database Layer                   │
│      (PostgreSQL)                   │
└─────────────────────────────────────┘
```

## Prerequisites

- **Java 17** or higher
- **Maven 3.6+** or use the included `mvnw` script
- **Docker & Docker Compose** (for containerized deployment)
- **PostgreSQL 12+** (or use Docker Compose)
- **Git** (for version control)

## Installation

### Option 1: Local Development (with Docker Database)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd tasks
   ```

2. **Start the PostgreSQL database with Docker Compose**
   ```bash
   docker-compose up -d db
   ```
   This starts a PostgreSQL container on port 5432 with default credentials.

3. **Build the project**
   ```bash
   ./mvnw clean package
   ```
   On Windows:
   ```bash
   mvnw.cmd clean package
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
   On Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

   The application will start on `http://localhost:8080`

### Option 2: Full Docker Deployment

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd tasks
   ```

2. **Start all services with Docker Compose**
   ```bash
   docker-compose up
   ```
   This will start:
   - PostgreSQL database on port 5432
   - UI application on port 3000
   - Spring Boot backend will run on port 8080

3. **Access the application**
   - Backend API: `http://localhost:8080/api/v1/tasks`
   - Frontend UI: `http://localhost:3000`

## Configuration

### Application Properties

Edit `src/main/resources/application.properties`:

```properties
spring.application.name=tasks

# PostgreSQL Database Configuration
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=mysecretpassword

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
```

### Environment Variables

Override default configuration using environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://your-db-host:5432/your-db
export SPRING_DATASOURCE_USERNAME=your-username
export SPRING_DATASOURCE_PASSWORD=your-password
```

## Usage

### API Endpoints

All endpoints are prefixed with `/api/v1/tasks`

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| `POST` | `/` | Create a new task | `CreateTaskRequestDto` | `TaskResponseDto` (201) |
| `GET` | `/` | List all tasks | - | `List<TaskResponseDto>` (200) |
| `PUT` | `/{taskId}` | Update a task | `UpdateTaskRequestDto` | `TaskResponseDto` (200) |
| `DELETE` | `/{taskId}` | Delete a task | - | Empty (204) |

### Request Examples

#### Create a Task (POST /api/v1/tasks)

```bash
curl -X POST http://localhost:8080/api/v1/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Complete project documentation",
    "description": "Write comprehensive README and API documentation",
    "dueDate": "2024-12-31",
    "priority": "HIGH"
  }'
```

**Request Body Schema:**
```json
{
  "title": "string (required, task name)",
  "description": "string (optional, max 1000 chars)",
  "dueDate": "YYYY-MM-DD (optional)",
  "priority": "HIGH | MEDIUM | LOW (required)"
}
```

#### List All Tasks (GET /api/v1/tasks)

```bash
curl -X GET http://localhost:8080/api/v1/tasks \
  -H "Content-Type: application/json"
```

#### Update a Task (PUT /api/v1/tasks/{taskId})

```bash
curl -X PUT http://localhost:8080/api/v1/tasks/550e8400-e29b-41d4-a716-446655440000 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated task title",
    "description": "Updated description",
    "dueDate": "2025-01-15",
    "priority": "MEDIUM",
    "status": "COMPLETE"
  }'
```

**Request Body Schema:**
```json
{
  "title": "string (required)",
  "description": "string (optional, max 1000 chars)",
  "dueDate": "YYYY-MM-DD (optional)",
  "priority": "HIGH | MEDIUM | LOW (required)",
  "status": "OPEN | COMPLETE (required)"
}
```

#### Delete a Task (DELETE /api/v1/tasks/{taskId})

```bash
curl -X DELETE http://localhost:8080/api/v1/tasks/550e8400-e29b-41d4-a716-446655440000
```

### Response Examples

#### Success Response (200/201)

```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "Complete project documentation",
  "description": "Write comprehensive README and API documentation",
  "dueDate": "2024-12-31",
  "priority": "HIGH",
  "status": "OPEN"
}
```

#### List Response (200)

```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "title": "First task",
    "description": "Description of first task",
    "dueDate": "2024-12-31",
    "priority": "HIGH",
    "status": "OPEN"
  },
  {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "title": "Second task",
    "description": "Description of second task",
    "dueDate": "2024-11-15",
    "priority": "MEDIUM",
    "status": "COMPLETE"
  }
]
```

#### Error Response (400)

```json
{
  "message": "Validation failed: Title is required"
}
```

#### Not Found Error (400)

```json
{
  "message": "Task with ID '550e8400-e29b-41d4-a716-446655440000' does not exist"
}
```

#### Delete Success Response (204)

Empty body with 204 No Content status

## Project Structure

```
tasks/
├── src/
│   ├── main/
│   │   ├── java/com/avdo/tasks/
│   │   │   ├── TasksApplication.java              # Main Spring Boot entry point
│   │   │   ├── controller/
│   │   │   │   ├── TaskController.java            # REST endpoints
│   │   │   │   └── GlobalExceptionHandler.java    # Exception handling
│   │   │   ├── service/
│   │   │   │   ├── TaskService.java               # Service interface
│   │   │   │   └── impl/
│   │   │   │       └── TaskServiceImpl.java        # Service implementation
│   │   │   ├── repository/
│   │   │   │   └── TaskRepository.java            # JPA repository
│   │   │   ├── mapper/
│   │   │   │   ├── TaskMapper.java                # Mapper interface
│   │   │   │   └── impl/
│   │   │   │       └── TaskMapperImpl.java         # Mapper implementation
│   │   │   ├── domain/
│   │   │   │   ├── entities/
│   │   │   │   │   ├── Task.java                  # Task JPA entity
│   │   │   │   │   ├── TaskStatus.java            # Status enum (OPEN, COMPLETE)
│   │   │   │   │   └── TaskPriority.java          # Priority enum (HIGH, MEDIUM, LOW)
│   │   │   │   ├── dto/
│   │   │   │   │   ├── CreateTaskRequestDto.java  # Create request DTO
│   │   │   │   │   ├── UpdateTaskRequestDto.java  # Update request DTO
│   │   │   │   │   ├── TaskResponseDto.java       # Response DTO
│   │   │   │   │   └── ErrorDto.java              # Error response DTO
│   │   │   │   ├── CreateTaskRequest.java         # Create request record
│   │   │   │   └── UpdateTaskRequest.java         # Update request record
│   │   │   └── exception/
│   │   │       └── TaskNotFoundException.java      # Custom exception
│   │   └── resources/
│   │       ├── application.properties             # Application configuration
│   │       ├── static/                            # Static assets
│   │       └── templates/                         # HTML templates
│   └── test/
│       ├── java/com/avdo/tasks/
│       │   └── TasksApplicationTests.java         # Integration tests
│       └── resources/
│           └── application.properties             # Test configuration
├── .mvn/                                          # Maven wrapper files
├── docker-compose.yml                            # Docker Compose configuration
├── pom.xml                                        # Maven project object model
├── mvnw                                           # Maven wrapper (Unix)
├── mvnw.cmd                                       # Maven wrapper (Windows)
├── .gitignore                                     # Git ignore rules
└── README.md                                      # This file
```

## Testing

### Run Unit Tests

```bash
./mvnw test
```

On Windows:
```bash
mvnw.cmd test
```

### Run Integration Tests

```bash
./mvnw verify
```

### Test Coverage

Generate test coverage report:

```bash
./mvnw test jacoco:report
```

The coverage report is generated in `target/site/jacoco/index.html`

### Testing with cURL

Test the API locally:

```bash
# Create a task
curl -X POST http://localhost:8080/api/v1/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test Task",
    "description": "Testing the API",
    "dueDate": "2024-12-31",
    "priority": "HIGH"
  }'

# Get all tasks
curl -X GET http://localhost:8080/api/v1/tasks

# Update a task (replace UUID with actual task ID)
curl -X PUT http://localhost:8080/api/v1/tasks/<TASK_ID> \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated title",
    "description": "Updated description",
    "dueDate": "2024-12-31",
    "priority": "MEDIUM",
    "status": "COMPLETE"
  }'

# Delete a task
curl -X DELETE http://localhost:8080/api/v1/tasks/<TASK_ID>
```

## Docker Deployment

### Using Docker Compose (Recommended)

The project includes a complete Docker Compose setup with PostgreSQL and optional UI.

1. **Start all services:**
   ```bash
   docker-compose up -d
   ```

2. **View logs:**
   ```bash
   docker-compose logs -f
   ```

3. **Stop all services:**
   ```bash
   docker-compose down
   ```

### Building a Custom Docker Image

1. **Create a Dockerfile** (if needed):
   ```dockerfile
   FROM openjdk:17-jdk-slim
   COPY target/tasks-0.0.1-SNAPSHOT.jar app.jar
   ENTRYPOINT ["java", "-jar", "app.jar"]
   ```

2. **Build the image:**
   ```bash
   docker build -t tasks-app:latest .
   ```

3. **Run the container:**
   ```bash
   docker run -d \
     -p 8080:8080 \
     -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/postgres \
     -e SPRING_DATASOURCE_USERNAME=postgres \
     -e SPRING_DATASOURCE_PASSWORD=mysecretpassword \
     --name tasks-app \
     tasks-app:latest
   ```

### Docker Compose Services

The `docker-compose.yml` includes:

- **PostgreSQL Database**: Runs on port 5432
  - Username: `postgres`
  - Password: `mysecretpassword`
  - Database: `postgres`

- **Frontend UI**: Runs on port 3000
  - Connects to backend on `http://host.docker.internal:8080`

## Error Handling

The application includes comprehensive exception handling through the [`GlobalExceptionHandler`](src/main/java/com/avdo/tasks/controller/GlobalExceptionHandler.java) class.

### Exception Types

| Exception | HTTP Status | Message Format |
|-----------|------------|-----------------|
| `MethodArgumentNotValidException` | 400 Bad Request | Validation error details |
| `TaskNotFoundException` | 400 Bad Request | `Task with ID '{id}' does not exist` |
| General Exceptions | 500 Internal Server Error | Server error message |

### Validation Rules

- **title**: Required field
- **description**: Optional, maximum 1000 characters
- **dueDate**: Optional, ISO 8601 date format (YYYY-MM-DD)
- **priority**: Required, must be one of: HIGH, MEDIUM, LOW
- **status** (update only): Required, must be one of: OPEN, COMPLETE

## Contributing

### Code Style

- Follow Google Java Style Guide
- Use meaningful variable and method names
- Add JavaDoc comments for public methods
- Keep methods focused and single-purpose

### Development Workflow

1. Create a feature branch: `git checkout -b feature/your-feature`
2. Make your changes and commit: `git commit -am 'Add your feature'`
3. Push to the branch: `git push origin feature/your-feature`
4. Submit a pull request with detailed description

### Testing Requirements

- Write unit tests for new features
- Ensure all tests pass: `mvnw test`
- Maintain code coverage above 70%

## License

This project is provided as-is for educational and development purposes. Specify your chosen license here (MIT, Apache 2.0, GPL, etc.).

---

**Built with ❤️ using Spring Boot and PostgreSQL**

For issues, questions, or contributions, please open an issue or contact me.
