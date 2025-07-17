# Spring Boot JWT Authentication & Authorization Application

This project is a complete login/register backend system developed using Java Spring Boot. It supports user registration, login with JWT-based authentication, refresh tokens, role-based authorization, DTO mapping with MapStruct, and input validation.

---

## 💻 Technologies Used

- **Java 17+**
- **Spring Boot 3**
- **Spring Security**
- **JWT (JSON Web Token)**
- **MapStruct**
- **Lombok**
- **JPA / Hibernate**
- **Jakarta Validation**

---

## ✅ Features

- **User Registration**
  - Unique username, email, and phone checks
  - Passwords hashed with BCrypt
  - DTO conversion using MapStruct
  - Role assignment (default: USER)

- **User Login**
  - Authentication via username and password
  - JWT token + Refresh token generation
  - Previous tokens cleared automatically

- **JWT & Refresh Token Handling**
  - JWT creation, validation, and expiration check
  - Refresh token validation and new token issuing
  - Token persistence in database

- **Security Configuration**
  - Stateless session management
  - Role-based authorization
  - Custom JWT filter to secure endpoints

---

# 🌐 API Endpoints

## RegisterController

* `POST -> http://localhost:6767/api/v1/register`
  {
      "username": "username",
      "password": "password",
      "firstName": "firstname",
      "lastName": "lastname",
      "email": "email",
      "phone": "phone"
  }
  Users submits this request body to perform the registration process.

* ## LoginController

* URL: `http://localhost:6767/api/v1/auth/login`
  Users can log in with their username and password on this page.

## RefreshTokenController

* `POST -> http://localhost:6767/api/v1/auth/refresh-token`
  {
      "refreshToken": "refreshToken"
  }
  Users submit this request body to obtain a new JWT tokens using the refresh token.

