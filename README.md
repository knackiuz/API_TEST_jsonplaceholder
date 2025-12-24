# API Test Automation - JSONPlaceholder

This repository contains automated API tests for the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) service. The project is built using **Java 17**, **Rest Assured**, and **JUnit 5**, following best practices like POJO modeling and environment-specific configurations.

## 🛠 Tech Stack

* **Java 17** (Amazon Corretto / Temurin)
* **Rest Assured 5.4.0** - For RESTful API testing.
* **JUnit 5 (Jupiter)** - Test engine and assertions.
* **Jackson Databind** - For JSON serialization and deserialization.
* **Maven** - Build tool and dependency management.
* **GitHub Actions** - CI pipeline integration.

---

## 📂 Project Structure

```text
├── .github/workflows/      # CI/CD pipeline configuration
├── src/test/java/
│   ├── models/             # Plain Old Java Objects (POJOs) for JSON mapping
│   └── tests/              # Test suites using Rest Assured
├── src/test/resources/     # Configuration files (properties)
├── pom.xml                 # Maven project configuration and profiles
└── README.md
