# Introduction 
Welcome to my project, a robust inventory management system built on Spring Boot 3 and powered by Docker containerization. Our project addresses the critical needs of managing computer assets, tracking components, and overseeing employee interactions efficiently.

# Getting Started

Welcome to the Inventory Management System powered by Spring Boot 3 and Docker. This guide will walk you through the steps to set up and run the application efficiently.

# Build and Test

### Clone the Repository

```kotlin
git clone https://github.com/Borgarelli/Project-Inventory.git
```

### Build the Docker-Compose on Terminal:
```kotlin
docker-compose -f mysql-docker-compose.yml up -d
```

### Build the Project:
```kotlin
mvn clean install
```

### Start the Application:
```kotlin
mvn spring-boot:run
```

### Run the Tests
```kotlin
mvn clean test
```

# Prerequisites

Before you begin, ensure that you have the following technologies installed on your system:

### Java Development Kit (JDK): 
You need Java 17 or later installed. You can download it from the official Oracle website or use an OpenJDK distribution.

### Maven: 
Maven is used for building and managing the project. You can download it from the official Apache Maven website and follow the installation instructions.

### Docker: 
To run the application within a Docker container, you must have Docker installed. You can download Docker for your specific platform from the Docker website.

### MySql WorkBench
To up a database and run the application, is necessary to install this SGBD. You can download a mysql workbench for free on Documentation.
