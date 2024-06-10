# Price Finder API

## Descripción
Este proyecto es un servicio de consulta de precios desarrollado en Spring Boot. Provee un endpoint REST que acepta parámetros de fecha de aplicación, identificador de producto y identificador de cadena, y devuelve la tarifa aplicable junto con las fechas y precio final a aplicar. La API está documentada y se puede probar usando Swagger.

## Requisitos
- Java 17 o superior
- Maven

## Ejecución del Proyecto
1. Clonar el repositorio:
    ```bash
    git clone https://github.com/tu_usuario/price-finder-api.git
    cd price-finder-api
    ```

2. Construir el proyecto:
    ```bash
    mvn clean install
    ```

3. Ejecutar la aplicación:
    ```bash
    mvn spring-boot:run
    ```

4. La aplicación estará disponible en `http://localhost:8080`.

## Documentación y Pruebas
Swagger UI está disponible en `http://localhost:8080/swagger-ui/index.html`.

### Ejecutar pruebas unitarias:
```bash
mvn test


