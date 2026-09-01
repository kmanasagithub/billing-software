# 🧾 Billing Software

A full-stack **Billing and Order Management System** built with **React.js, Spring Boot, PostgreSQL, Spring Security, JWT, Razorpay, AWS S3, and Docker**.

The application provides authentication, role-based access control, user management, category and item management, order management, online payments, and cloud-based file/image storage.

---

## 🚀 Live Application

**Backend:**
https://billing-software-qjjq.onrender.com

**API Base URL:**
https://billing-software-qjjq.onrender.com/api/v1.0

**Frontend:**
https://billing-software-frontend-m7pp.onrender.com/

---

## ✨ Features

### 🔐 Authentication & Authorization

* User login
* JWT-based authentication
* BCrypt password hashing
* Role-based authorization
* Protected REST APIs
* Admin and User roles

### 👑 Admin Features

* Admin dashboard
* Add and delete users
* Create and delete categories
* Add and delete items
* View orders
* Manage application data

### 👤 User Features

* Login
* Browse categories
* Browse items
* Create orders
* View latest orders
* Delete orders
* Make online payments

### 📦 Category Management

* Create categories
* Delete categories
* View categories

### 🛒 Item Management

* Add items
* Delete items
* Manage item information
* Browse available items

### 🧾 Order Management

* Create orders
* View latest orders
* Delete orders
* Order processing
* Billing operations

### 💳 Razorpay Integration

* Create Razorpay orders
* Process online payments
* Verify payments securely
* Keep Razorpay secret keys on the backend

---

## 🛠️ Technology Stack

### Frontend

* React.js
* Vite
* JavaScript
* Axios
* HTML5
* CSS3
* Nginx

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* JWT
* Maven

### Database

* PostgreSQL

### Cloud & Integrations

* Razorpay
* AWS S3
* Render

### DevOps

* Docker
* Docker Compose
* GitHub

---

## 🏗️ Architecture

```text
                    INTERNET
                       │
                       ▼
             ┌────────────────────┐
             │   React Frontend   │
             │      + Nginx       │
             │      Port 80       │
             └──────────┬─────────┘
                        │
                   HTTPS / REST
                        │
                        ▼
             ┌────────────────────┐
             │   Spring Boot      │
             │      Backend       │
             │      Port 8080     │
             └──────────┬─────────┘
                        │
          ┌─────────────┼─────────────┐
          │             │             │
          ▼             ▼             ▼
   ┌────────────┐ ┌────────────┐ ┌────────────┐
   │ PostgreSQL │ │  AWS S3    │ │  Razorpay  │
   │  Database  │ │  Storage   │ │  Payments  │
   └────────────┘ └────────────┘ └────────────┘
```

The React frontend communicates with the Spring Boot backend through REST APIs. The backend handles authentication, authorization, business logic, database operations, payment processing, and file storage.

---

## 📂 Project Structure

```text
billing-software/
│
├── billingsoftware/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       ├── application-local.properties
│   │   │       └── application-prod.properties
│   │   │
│   │   └── test/
│   │
│   ├── pom.xml
│   └── Dockerfile
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   └── ...
│   │
│   ├── package.json
│   ├── Dockerfile
│   └── ...
│
├── docker-compose.yml
├── .gitignore
└── README.md
```
---

## 👑 Default Admin Account

The application automatically creates an administrator account when the Spring Boot application starts.

The `AdminInitializer` implements `CommandLineRunner` and checks whether the admin email already exists before creating the account.

```text
Email    : manu@gmail.com
Password : password
Role     : ROLE_ADMIN
```

The password is stored using BCrypt hashing rather than as plain text.

> ⚠️ **Security:** The credentials above are for demonstration purposes. Change the administrator password before using the application with real production data.

---

## 🔌 API Endpoints

Base URL:

```text
/api/v1.0
```

### Authentication

| Method | Endpoint | Description |
| ------ | -------- | ----------- |
| POST   | `/login` | Login       |

### Categories

| Method | Endpoint                         | Description     |
| ------ | -------------------------------- | --------------- |
| GET    | `/categories`                    | Get categories  |
| POST   | `/admin/categories`              | Add category    |
| DELETE | `/admin/categories/{categoryId}` | Delete category |

### Items

| Method | Endpoint                | Description |
| ------ | ----------------------- | ----------- |
| GET    | `/items`                | Get items   |
| POST   | `/admin/items`          | Add item    |
| DELETE | `/admin/items/{itemId}` | Delete item |

### Users

| Method | Endpoint            | Description   |
| ------ | ------------------- | ------------- |
| POST   | `/admin/register`   | Register user |
| GET    | `/admin/users`      | Get users     |
| DELETE | `/admin/users/{id}` | Delete user   |

### Orders

| Method | Endpoint         | Description       |
| ------ | ---------------- | ----------------- |
| POST   | `/orders`        | Create order      |
| GET    | `/orders/latest` | Get latest orders |
| DELETE | `/orders/{id}`   | Delete order      |

### Payments

| Method | Endpoint                 | Description           |
| ------ | ------------------------ | --------------------- |
| POST   | `/payments/create-order` | Create Razorpay order |
| POST   | `/payments/verify`       | Verify payment        |

Administrative endpoints require admin authentication.

---

## 🗄️ Database Configuration

Production uses PostgreSQL.

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
```

For local development:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/billingdb
spring.datasource.username=postgres
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update

server.port=8080
server.servlet.context-path=/api/v1.0
```

---

## ⚙️ Environment Variables

### Backend

```text
SPRING_PROFILES_ACTIVE=prod

DB_URL=
DB_USERNAME=
DB_PASSWORD=

AWS_ACCESS_KEY=
AWS_SECRET_KEY=
AWS_REGION=
AWS_BUCKET_NAME=

JWT_SECRET_KEY=

RAZORPAY_KEY_ID=
RAZORPAY_KEY_SECRET=

ADMIN_EMAIL=
ADMIN_PASSWORD=
```

### Frontend

```text
VITE_API_URL=https://billing-software-qjjq.onrender.com/api/v1.0
```

The frontend accesses the API through:

```javascript
const API_URL = import.meta.env.VITE_API_URL;
```

---

## 💻 Local Development

### Requirements

Install:

* Java 21
* Maven
* Node.js
* npm
* PostgreSQL
* Docker
* Docker Compose

### Run Backend

```bash
cd billingsoftware
mvn spring-boot:run
```

Backend:

```text
http://localhost:8080
```

API:

```text
http://localhost:8080/api/v1.0
```

### Run Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend:

```text
http://localhost:5173
```

---

## 🐳 Run with Docker

### Backend

```bash
docker build -t billing-backend ./billingsoftware
docker run -p 8080:8080 billing-backend
```

### Frontend

```bash
docker build -t billing-frontend ./frontend
docker run -p 3000:80 billing-frontend
```

Frontend:

```text
http://localhost:3000
```

---
## 📦 Deployment Architecture

```text
                         GitHub
                           │
             ┌─────────────┴─────────────┐
             │                           │
             ▼                           ▼
      React Docker                Spring Boot Docker
             │                           │
             ▼                           ▼
          Nginx                       Java 21
         Port 80                    Port 8080
             │                           │
             └───────────┐   ┌───────────┘
                         │   │
                         ▼   ▼
                       REST API
                         │
                         ▼
                     PostgreSQL
                         │
                  ┌──────┴──────┐
                  ▼             ▼
                AWS S3       Razorpay
```

---

## 📜 License

This project is developed for educational and application development purposes.

---

## 👨‍💻 Author

**Manasa**

GitHub:
https://github.com/kmanasagithub

Repository:
https://github.com/kmanasagithub/billing-software

---

## ⭐ Project Summary

This project demonstrates a complete full-stack billing application using:

```text
React.js
   +
Spring Boot
   +
PostgreSQL
   +
Spring Security
   +
JWT
   +
Razorpay
   +
AWS S3
   +
Docker
   +
Render
```

The application follows a REST API architecture, uses JWT-based authentication and role-based authorization, integrates Razorpay for online payments, stores files using AWS S3, is containerized with Docker, and is deployed using Render.
