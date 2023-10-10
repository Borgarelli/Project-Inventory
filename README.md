# Introduction 
Welcome to my project, a robust inventory management system built on Spring Boot 3 and powered by Docker containerization. Our project addresses the critical needs of managing computer assets, tracking components, and overseeing employee interactions efficiently.

# Getting Started

Welcome to the Inventory Management System powered by Spring Boot 3 and Docker. This guide will walk you through the steps to set up and run the application efficiently.

Prerequisites

Before you begin, ensure that you have the following technologies installed on your system:

### Java Development Kit (JDK): 
You need Java 8 or later installed. You can download it from the official Oracle website or use an OpenJDK distribution.

### Maven: 
Maven is used for building and managing the project. You can download it from the official Apache Maven website and follow the installation instructions.

### Docker: 
To run the application within a Docker container, you must have Docker installed. You can download Docker for your specific platform from the Docker website.

### MySql Postgres
To up a database and run the application, is necessary to install this SGBD. You can download a mysql workbench for free on Postgres Documentation.

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

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)"# Project-Inventory" 
