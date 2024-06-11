# Price Finder API

## Description
This project is a price query service developed in Spring Boot. It provides a REST endpoint that accepts parameters of application date, product identifier, and chain identifier, and returns the applicable rate along with the dates and final price to apply. The API is documented and can be tested using Swagger and Cucumber.

## Requirements
- Java 17 or higher
- Maven

## Ejecución del Proyecto
1. Project Execution:
    ```bash
    git clone https://github.com/tu_usuario/price-finder-api.git
    cd price-finder-api
    ```

2. Build the project:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

4. The application will be available at `http://localhost:8080`.

## Documentation and Tests
Swagger UI is available at `http://localhost:8080/swagger-ui/index.html`.

### Run unit tests:
```bash
mvn test
```

### Run integration tests:
```bash
mvn -Dtest=CucumberPriceFinderApiTest test
```

### The tests performed with Cucumber:
Test 1: Request at 10:00 on the 14th for product 35455 for brand 1.

Test 2: Request at 16:00 on the 14th for product 35455 for brand 1.

Test 3: Request at 21:00 on the 14th for product 35455 for brand 1.

Test 4: Request at 10:00 on the 15th for product 35455 for brand 1.

Test 5: Request at 21:00 on the 16th for product 35455 for brand 1.