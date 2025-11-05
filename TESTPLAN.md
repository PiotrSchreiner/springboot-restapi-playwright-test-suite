# 📋 Test Plan: Spring Boot REST API Test Suite

## 1. 🎯 Test Goal and Scope

| Area                             | Description                                                                                                                                                          | Goal                                |
| :------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :---------------------------------- |
| **Project Goal**                 | Prove the correct functionality, stability, and API contract adherence of the Spring Boot REST API. Ensure the application is ready for continuous integration (CI). | Risk mitigation, Quality assurance. |
| **Application Under Test (AUT)** | Spring Boot REST API, accessible at **`http://localhost:8080`** (with endpoints like `/greeting`).                                                                   |                                     |
| **Scope**                        | Automated **End-to-End (E2E) Tests** primarily covering **API functionality** (CRUD operations, business logic). Focus is on API validation using Rest Assured.      | Functional Testing.                 |
| **Metric (Definition of Done)**  | 100% coverage of defined test cases; all critical tests must pass successfully in the **CI/CD flow (GitHub Actions)**.                                               |                                     |

---

## 2. 🧪 Test Strategy and Architecture (Best Practices)

The architecture adheres to the **Layered Automation Pattern** (POM/App Actions/BDD-Style) to ensure maximum maintainability and reusability, which is vital for professional test automation frameworks.

### 2.1. Test Architecture Layers

The test structure is strictly separated into packages within the `src/test/java` folder to ensure **Separation of Concerns**.

| Package/Layer | Purpose                                                                                                                   | Tool         | Role (WHAT/HOW/WHERE)    |
| :------------ | :------------------------------------------------------------------------------------------------------------------------ | :----------- | :----------------------- |
| **`tests`**   | **Test Specification (BDD Style)**: Defines _WHAT_ is being tested. Contains **`@Test`** methods and assertions.          | JUnit 5      | **WHAT** (The Story)     |
| **`actions`** | **App Actions / Business Logic**: Defines _HOW_ a test case is executed. Contains sequences of API calls.                 | Rest Assured | **HOW** (The Steps)      |
| **`pages`**   | **Page Object Model (POM)**: Defines _WHERE_ elements are located (only for future UI tests). Contains **only locators**. | Playwright   | **WHERE** (The Elements) |
| **`data`**    | **Helpers / Data**: Manages JSON payloads and test data objects.                                                          | Java POJOs   | Data Management          |

### 2.2. Selected Test Tools

- **Test Runner:** **JUnit 5** (Standard in Java ecosystems).
- **API Testing/Seeding:** **Rest Assured** (Best practice for robust HTTP checks in Java CI).
- **UI Testing (Future):** **Playwright** (Modern, high-performance engine).

---

## 3. 🧪 Detailed Test Cases (API Focus on `/greeting`)

Tests are formulated in a **BDD style (Given/When/Then)** to maximize readability and clearly articulate business value.

| ID               | Test Case (BDD Style)                            | Test Type | Steps (App Actions)                                                                 | Expected Result (Assertion)                                                                                            |
| :--------------- | :----------------------------------------------- | :-------- | :---------------------------------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------- |
| **API-GET-001**  | **GET /greeting: Successful Retrieval**          | Positive  | Send a `GET` request to `/greeting`.                                                | Status Code **`200 OK`**. Response contains JSON structure `{"id": <Integer>, "content": <String>}`.                   |
| **API-GET-002**  | **GET /greeting: Retrieval with Name Parameter** | Positive  | Send a `GET` request to `/greeting?name=Piotr`.                                     | Status Code **`200 OK`**. The `content` field contains the exact string "Hello, Piotr!".                               |
| **API-NEGF-001** | **GET /invalid-path: Non-existent Endpoint**     | Negative  | Send a `GET` request to an undefined path (e.g., `/posts`).                         | Status Code **`404 Not Found`** and/or a standard error message.                                                       |
| **API-POST-001** | **POST /greeting: Method Not Allowed**           | Negative  | Send a `POST` request to the `/greeting` endpoint (which is only designed for GET). | Status Code **`405 Method Not Allowed`** (Ensuring correct endpoint isolation).                                        |
| **API-SCHM-001** | **API Contract Validation**                      | Contract  | Send a `GET` request to `/greeting`.                                                | The JSON response must match a predefined JSON Schema (`GreetingSchema.json`) checking data types and required fields. |

---

## 4. 🚀 CI/CD Integration (GitHub Actions)

Test automation will be executed via **GitHub Actions**. The workflow must implement the **Two-Step Strategy** for E2E test execution: starting the backend service first, followed by running the tests against it.

| Step                              | Command/Action                                                                      | Purpose                                                                           |
| :-------------------------------- | :---------------------------------------------------------------------------------- | :-------------------------------------------------------------------------------- |
| **1. Checkout Code**              | `uses: actions/checkout@v4`                                                         | Fetches the current repository code.                                              |
| **2. Java Setup**                 | `uses: actions/setup-java@v4` with `distribution: 'temurin'` and `java-version: 17` | Installs a compatible Java version (JDK 17).                                      |
| **3. Backend Build**              | `run: mvn clean package -DskipTests`                                                | Builds the executable **`.jar`** file (skipping tests for speed).                 |
| **4. Backend Start (Background)** | `run: java -jar target/*.jar &` followed by a custom Health Check                   | Ensures the Spring Boot application is running and listening on `localhost:8080`. |
| **5. Test Execution**             | `run: mvn test`                                                                     | Executes all API/E2E tests against the live background service.                   |
| **6. Reporting**                  | _JUnit Reporter (Standard in Maven)_                                                | Generates standardized XML reports for GitHub/CI tools to parse.                  |
