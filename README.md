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
* **Using parameters as separate class and method for JUnit ParameterizedTest**  
* ** **
* **WiremockTest:**
* **Using WireMock** For positive and negative tests
* Docker - Containerized execution environment.
* Allure Report - Advanced test reporting and visualization
 

### Bypassing Cloudflare (403 Forbidden)
Standard GitHub ubuntu-latest runners are often blocked by GoRest's Cloudflare protection. This project implements a Cloudflare WARP tunnel within the CI pipeline to:
1. Mask the runner's IP with a trusted Cloudflare exit node.
2. Bypass Managed Challenges that typically block Rest Assured clients.

---

### 🐳 Containerization & CI/CD
To ensure environment consistency and easy scaling, the entire test suite is containerized:
* Dockerized Execution: Tests run inside a lightweight Maven/JDK 17 container, isolating dependencies from the host machine.
* Automated Pipeline: GitHub Actions handles the full lifecycle:
1. Building the Docker image.
2. Setting up a Cloudflare WARP tunnel.
3. Running tests inside the container.
4. Mapping test results to the host for reporting.

### 📊 Test Reporting
The project uses Allure Report to provide deep insights into test execution:
* Interactive Dashboard: Visual summary of passed, failed, and broken tests.
* Step-by-Step Details: Detailed logs for each API request and response.
* History Tracking: Compare current results with previous runs to identify regressions.
* GitHub Pages Hosting: Reports are automatically deployed and accessible via the badge at the top of this README.

---

## 📂 Project Structure

```text
├── .github/workflows/      # CI/CD pipeline configuration
├── src/test/java/
│   ├── base/               # Setup for WireMock
│   ├── config/             # Configuration for request
│   ├── models/             # Plain Old Java Objects (POJOs) for JSON mapping
│   ├── test_data/          # Test data for ParameterizedTest
│   ├── tests/              # Test suites using Rest Assured
│   └── resources/          # Propetries for environment
├── src/test/resources/     # Configuration files (properties)
├── Dockerfile              # Optimized Maven & JDK 17 image configuration
├── pom.xml                 # Maven project configuration and profiles
└── README.md
