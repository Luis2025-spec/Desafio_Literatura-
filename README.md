# Desafio_Literatura-
Desafio curso alura latam
Desafío Literatura

Este proyecto es una aplicación desarrollada en **Java con Spring Boot** como parte del curso de **Alura Latam**.  
La aplicación se conecta a la API de **Gutendex** para obtener información de libros y autores, almacenándolos en una base de datos para su posterior consulta.

---

## 🚀 Tecnologías usadas
- Java 17+
- Spring Boot 3
- Spring Data JPA
- Hibernate
- Maven
- Base de datos relacional (H2 / PostgreSQL / MySQL según configuración)
- API Gutendex (https://gutendex.com/)

---##  Estructura del Proyecto

##  Instalación y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Luis2025-spec/Desafio_Literatura-.git
   cd Desafio_Literatura-

   Compilar y empaquetar con Maven:

mvn clean install

Ejecutar la aplicación:

mvn spring-boot:run


La aplicación quedará disponible en:
 http://localhost:8080

En el archivo src/main/resources/application.properties puedes definir:

spring.datasource.url=jdbc:h2:mem:literatura_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Endpoints principales

GET /libros → Lista todos los libros guardados.

GET /autores → Lista todos los autores registrados.

POST /libros → Guarda un libro desde la API de Gutendex.

Ejemplo de respuesta (JSON):

{
  "titulo": "Don Quijote de la Mancha",
  "autor": "Miguel de Cervantes",
  "idioma": "es",
  "descargas": 12345
}

📌 Funcionalidades

✔️ Consultar libros desde la API de Gutendex
✔️ Guardar libros y autores en la base de datos
✔️ Consultar autores y sus libros asociados
✔️ Exponer endpoints REST para interactuar con la aplicación

🤝 Contribuciones

Las contribuciones son bienvenidas:


📜 Licencia

Este proyecto está bajo la licencia MIT - ver el archivo LICENSE


