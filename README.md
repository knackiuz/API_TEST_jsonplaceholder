# API Test Automation - JSONPlaceholder

This repository contains automated API tests for the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) service. The project is built using **Java 17**, **Rest Assured**, and **JUnit 5**, following best practices like POJO modeling and environment-specific configurations.

## 🛠 Tech Stack
* **ApiTest:**
* **Java 17** (Amazon Corretto / Temurin)
* **Rest Assured 5.4.0** - For RESTful API testing.
* **JUnit 5 (Jupiter)** - Test engine and assertions.
* **Jackson Databind** - For JSON serialization and deserialization.
* **Maven** - Build tool and dependency management.
* **GitHub Actions** - CI pipeline integration.
* ** **
* **ApiLombokTest:**
* **Using Lombok** - Automatically generates getters, setters, implements the Builder and "Jackson" deserialization
* **Using Soft Assertion** Approach for failed assertion
* ** **
* **WiremockTest:**
* **Using WireMock**  For positive and negative tests
 

### Infrastructure Note: Windows Runner
Initially, the tests encountered `403 Forbidden` errors on `ubuntu-latest` runners due to Cloudflare anti-bot protection (Managed Challenges).
---

## 📂 Project Structure

```text
├── .github/workflows/      # CI/CD pipeline configuration
├── src/test/java/
│   ├── config/             # Configuration for request
│   ├── models/             # Plain Old Java Objects (POJOs) for JSON mapping
│   └── tests/              # Test suites using Rest Assured
├── src/test/resources/     # Configuration files (properties)
├── pom.xml                 # Maven project configuration and profiles
└── README.md
