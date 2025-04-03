
# Product Name
Fake Data Generator API

# Version
1.0

# Date
April 2, 2025

# Author
Francyelle Souza

# 1. Overview
## 1.1 Purpose
The Fake Data Generator API is a lightweight service designed to generate fake data for testing, prototyping, and development purposes.
It allows users to specify an entity type, a set of fields with their data types, and the number of records to generate, returning the results in JSON.

## 1.2 Goals
Provide a simple, scalable tool for developers to generate mock data.
Support basic data types (String, Integer, Double) with extensible type handling.
Deliver a Docker-compatible deployment for easy hosting on free platforms.
Ensure the API is intuitive and requires minimal setup.

# 2. Features
## 2.1 Core Functionality

- Endpoint: POST /generate
Accepts a JSON payload specifying the entity, fields, and count.
Returns a JSON array of generated fake data objects.

Input Parameters:
entity (String): Name of the entity (e.g., "user", "product") —currently unused but included for future extensibility.
fields (Map<String, String>): A map where keys are field names and values are data types (e.g., "name": "String").
count (Integer): Number of fake data objects to generate.

Supported Data Types:

- String: Random words.
- Integer: Random numbers between 1 and 100.
- Double: Random decimal numbers between 0 and 1000.
- Unknown types return "Unknown type" as a fallback.

- Output: A list of JSON objects, each containing the specified fields with fake values.
## 2.2 Example Usage

### Request:
   {
    "entity": "user",
    "fields": {
        "name": "String",
        "age": "Integer",
        "salary": "Double"
    },
    "count": 3
}
### Response:
[
    {"name": "lorem", "age": 42, "salary": 567.89},
    {"name": "ipsum", "age": 23, "salary": 123.45},
    {"name": "dolor", "age": 87, "salary": 789.12}
]
### 2.3 Rate Limiting
- Objective: Prevent abuse and ensure fair usage of the API.
Implementation: 
Define the maximum number of requests allowed per client within a time window.
Specify how clients are identified (e.g., IP address, API key).
Detail the response when limits are exceeded (e.g., HTTP 429 Too Many Requests).

# 3. Technical Requirements

## 3.1 Technology Stack

#### Framework: Spring Boot (Java)
#### Fake Data Library: JavaFaker (com.github.javafaker)
#### Build Tool: Maven
#### Containerization: Docker
#### Dependencies:
spring-boot-starter-web: For RESTful API functionality.
javafaker: For generating fake data.
## 3.2 Architecture
- Controller Layer: FakeDataController
Handles the /generate endpoint.
Parses JSON input and returns JSON output.
- Service Layer: FakeDataService
Contains the logic for generating fake values based on type.
- Model Layer: FakeDataRequest
DTO to map incoming JSON requests.


## 3.3 Deployment


- Target Platforms: Free hosting services supporting Docker (e.g., Render, Fly.io, Railway).
- Port: Default Spring Boot port 8080.
  
## 3.4 Performance


# 4. Non-Functional Requirements
## 4.1 Usability
API should be self-documenting via clear JSON structure.
No authentication required for simplicity (rate limiting serves as basic control).
## 4.2 Reliability
Graceful handling of invalid inputs (e.g., negative count, unknown types).
Consistent fake data generation within specified ranges.
## 4.3 Security
No sensitive data stored or processed.
Rate limiting to mitigate abuse (details TBD).
## 4.4 Maintainability
Modular code structure for easy extension (e.g., adding new data types).
Minimal external dependencies.


