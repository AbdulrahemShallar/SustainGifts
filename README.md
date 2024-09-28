# *Project Overview:* #

This project is a full-stack application using Spring Boot on the backend. The system is designed to handle various entities like products, users, orders, deliveries, and vendors. Key features include:

> RESTful API using Spring MVC.

> JWT Authentication for secured endpoints.

> Entity-DTO Conversion using custom converters.

> CRUD Operations for managing entities (Categories, Products, Orders, Users, etc.).

> Exception Handling via Global Exception Handler.


## **Techniques and Technologies Used:** ##
**Spring Boot Framework:** The application is built using Spring Boot, which simplifies Java development with features like auto-configuration, dependency injection, and a built-in web server.

**Spring MVC:** This was used to develop REST APIs. Each controller, such as ProductController, OrderController, etc., maps HTTP requests (GET, POST, PUT, DELETE) to methods.

**JWT (JSON Web Token) Authentication:**
Custom JWT Handling: The app includes a JwtAuthenticationFilter to handle JWT authentication and a JwtIssuer to issue JWT tokens. It ensures that users accessing secured routes are authenticated using their tokens.

**Security Configuration:** WebSecurityConfig defines security filters and grants access control based on user roles like ADMIN and USER.

**DTO (Data Transfer Object) Pattern:**
The project utilizes DTOs to decouple the domain model from API requests and responses. For example, ProductDTO, UserDTO, and OrderDTO are used in place of entities when returning data to the client.

**Converters:** Custom converters like ProductConverter and CategoryConverter handle the transformation between entities and DTOs.

**Exception Handling:** The application uses GlobalExceptionHandler to handle various exceptions like ResourceNotFoundException and illegal argument exceptions. This ensures that meaningful error messages are sent to the client.

**Repository Layer:** The repository interfaces, such as ProductRepository, UserRepository, and OrderRepository, extend JpaRepository to perform database operations. Spring Data JPA simplifies CRUD operations without the need to write boilerplate SQL queries.

## **Role-Based Access Control (RBAC):** ##

The system uses Spring Security to restrict access to certain API endpoints. For instance, only users with the ADMIN role can access certain endpoints.
User roles are handled using an enumeration, Role, which implements GrantedAuthority.
