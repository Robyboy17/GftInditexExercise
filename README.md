# Prueba técnica Inditex Core Platform

Este proyecto es una REST API para manejar los precios de los productos

## RAEDME

## Tecnologías

- Java 21
- Spring Boot 3.2.5
- Spring Data JPA
- H2
- Flyway
- Lombok
- Maven
- JUnit 5 + Mockito
- Swagger / OpenAPI
- Mapstruct
- Docker & Docker Compose

### Ejecutar proyecto con docker

- docker-compose up -d
- docker-compose down
- swagger url: http://localhost:8080/swagger-ui/index.html#/

## Endpoints

### **Precios**

| Método | Endpoint                    | Descripción        |
|--------|-----------------------------|--------------------|
| POST   | `/api/v1/prices/applicable` | Crea un precio     |
| GET    | `/api/v1/prices`            | Recupera un precio |

## Testing

Los tests están hechos con `JUnit 5` and `Mockito`

## Autor

Robert Lungu  
Java Backend Developer