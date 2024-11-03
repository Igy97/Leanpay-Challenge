# Loan Calculator Application

## Overview
This project implements a loan calculation service designed to calculate monthly loan payments and save them to a database. The service provides an optimized way of handling loan calculations by first checking for pre-existing records to avoid redundant calculations. Key functionalities include checking, calculating, and storing loan payment data with persistent storage to enhance efficiency and consistency in repeated calculations. It provides an API for easy interaction.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)

## Features
- Calculate monthly payments for loans
- RESTful API for loan calculations
- Integration with a database to save loan calculations

## Technologies
- Java 17
- Spring Boot 3.3.5
- MariaDB
- Maven
- JUnit for testing

## Installation
1. Clone the repository:
```
git clone https://github.com/yourusername/loan-calculator.git
```   
2. Navigate to the project directory:
```
cd loan-calculator
```
3. Build the project using Maven:
```
mvn clean package
```
4. Run the MariaDB server:
```
docker compose up
```
5. Run the application:
```
mvn spring-boot:run
```

## Database Configuration

- Make sure to configure your MariaDB database in the application.properties file:
```
spring.datasource.url=jdbc:mariadb://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Usage
- To calculate a loan, make a POST request to the following endpoint:

## Endpoint
- POST /api/v1/loanCalculator/calculate

## Request Body
```
{
  "amount": 10000,
  "annualInterestRate": 5,
  "numberOfMonths": 24
}
```

## Response
```
{
    "month 1": "102.31",
    "month 2": "102.31",
    "month 3": "102.31",
    "month 4": "102.31",
    "month 5": "102.31",
    "month 6": "102.31",
    "month 7": "102.31",
    "month 8": "102.31",
    "month 9": "102.31",
    "month 10": "102.31"
}
```

## Testing

## Unit Tests

- To run the unit tests, execute:
```
mvn test
```
## Integration Tests

- For integration tests, ensure that your application is running and execute:
```
mvn integration-test
```
