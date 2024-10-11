# Backend Performance Challenge

This is a Java 21 Spring Boot application designed to persist data in a PostgreSQL database and return records based on either a search by ID or a string term. 
The project showcases my proficiency in various technologies including Java, Spring Boot, Gradle, unit/integration testing, Docker, Docker Compose, PostgreSQL, 
caching, Nginx, Prometheus, and Grafana.

The main for this application is handle a massive write/read data operations with the best performance as possible.
Memory and CPU resources can be limited on docker-compose file to add more difficulty to our challenge.

## Table of Contents
- [Technologies](#technologies)
- [Requirements](#requirements)
- [Setup](#setup)
- [Build](#build)
- [Run](#run)
- [Testing](#testing)
- [Monitoring](#monitoring)
- [Docker](#docker)

## Technologies
- Java 21
- Spring Boot
- Gradle
- PostgreSQL
- Docker / Docker Compose
- Nginx
- Prometheus / Grafana
- Caching (e.g., Redis or Spring Cache)

## Requirements
Before you can build or run the application, ensure you have the following installed:
- Java 21+
- Gradle
- Docker & Docker Compose
- PostgreSQL

## Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/your-repo.git
   cd your-repo
2. Configure the PostgreSQL database in the application.yml file:
``` yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/your_db_name
    username: your_db_username
    password: your_db_password
```

## Build
To build the project, use Gradle:
```bash
./gradlew build
```

## Run
To run the application locally:
```bash
./gradlew bootRun
```

## Testing
This project includes both unit and integration tests. To run the tests:
```bash
./gradlew test
```

## Monitoring
The project comes pre-configured with Prometheus and Grafana for monitoring.

Prometheus will scrape the application's metrics endpoint.
Grafana is available for visualizing metrics from Prometheus.
Both services can be run using the provided docker-compose.yml.

## Docker
Write instructions to run using docker-compose

