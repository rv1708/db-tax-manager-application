# Municipal Tax Rate Application - Backend

This repository contains a web application for displaying tax rates for municipalities based on a given date.

## Features
- **Backend (Spring Boot v3.3.1 with Java 17)**
    - Exposes REST APIs for managing municipalities and tax schedules.
    - Utilizes Swagger UI for API documentation: [Swagger UI Link](http://localhost:8080/swagger-ui/index.html#)
      
![img.png](https://github.com/rv1708/db-tax-manager-application/blob/main/db-tax-manager-backend/documentation/Swagger%20Snip.png)

## Backend Endpoints

### Municipality Controller
- **GET** `/municipalities`: Retrieves all municipalities.
- **POST**`/add-municipality`: Adds a new municipality to the database.

### Tax Schedule Controller
- **GET** `/tax-schedules`: Retrieves all tax schedules (Daily, Weekly, Monthly, Yearly)

### Municipality Tax Rate Controller
- **POST** `/add-municipality-tax-schedule-data`: Adds a new tax schedule for a municipality.
- **GET** `/municipality-tax-schedule-data`: Fetches all tax schedules for all municipalities.
- **GET** `/municipality/{municipalityName}/tax-schedule-data`: Retrieves all tax schedules for mentioned municipality.
- **GET** `/municipality/{municipalityName}/{date}/tax-schedule`: Retrieves tax schedules for a specific date for mentioned municipality.

## Configuration

- **Backend Configuration:**
    - Database configuration (e.g., connection settings) can be found in `db-tax-manager-backend/src/main/resources/*.properties`.

## Database

![img.png](https://github.com/rv1708/db-tax-manager-application/blob/main/db-tax-manager-backend/documentation/Database%20Snip.png)
