# Municipal Tax Rate Application

This repository contains a web application for displaying tax rates for municipalities based on a given date. The application consists of a frontend built with ReactJs and a backend implemented with Java and Spring Boot.

## Features

- **Frontend (React v18)**
  - Displays tax rates for municipalities.
  - Allows selection of a date to view tax rates.

- **Backend (Spring Boot v3.3.1 with Java 17)**
  - Exposes REST APIs for managing municipalities and tax schedules.
  - Utilizes Swagger UI for API documentation: [Swagger UI Link](http://localhost:8080/swagger-ui/index.html#)

## Backend Endpoints

### Municipality Controller
- **GET** `/municipalities`: Retrieves all municipalities.
- **POST** `/add-municipality`: Adds a new municipality to the database.

### Tax Schedule Controller
- **GET** `/tax-schedules`: Retrieves all tax schedules (Daily, Weekly, Monthly, Yearly)

### Municipality Tax Rate Controller
- **POST** `/add-municipality-tax-schedule-data`: Adds a new tax schedule for a municipality.
- **GET** `/municipality-tax-schedule-data`: Fetches all tax schedules for all municipalities.
- **GET** `/municipality/{municipalityName}/tax-schedule-data`: Retrieves all tax schedules for mentioned municipality.
- **GET** `/municipality/{municipalityName}/{date}/tax-schedule`: Retrieves tax schedules for a specific date for mentioned municipality.

## Installation

### Running with Docker

1. **Clone the repository:**

```
git clone https://github.com/rv1708/db-tax-manager-application.git
cd <repository-directory>
```
2. **Build and Run with Docker Compose:**

```dockerfile
docker-compose up --build
```

This command builds the Docker images for both the frontend and backend applications and starts the containers.

- Frontend will be accessible at: `http://localhost:3000`
- Backend APIs will be accessible at: `http://localhost:8080`

## Configuration

- **Backend Configuration:**
    - Database configuration (e.g., connection settings) can be found in `db-tax-manager-backend/src/main/resources/*.properties`.

- **Frontend Configuration:**
    - Frontend settings and environment variables can be adjusted in `db-tax-manager-frontend/.env.development`.


---

## Docker Details

### Frontend Dockerfile

The Dockerfile for the React frontend (`db-tax-manager-frontend/Dockerfile`):


### Backend Dockerfile

The Dockerfile for the Spring Boot backend (`db-tax-manager-backend/Dockerfile`):


#### Docker Compose File

The `docker-compose.yml` file to orchestrate both frontend and backend:

```yaml
version: '3.8'

services:
  frontend:
    build:
      context: ./db-tax-manager-frontend
      dockerfile: Dockerfile
    ports:
      - "3000:3000"
    depends_on:
      - backend

  backend:
    build:
      context: ./db-tax-manager-backend
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: "h2"
```
