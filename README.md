# Project & Task Management REST API System

## Overview

Project Task Management API is a secure RESTful backend application built using Spring Boot and PostgreSQL. The system allows management of Projects, Tasks, and Team Members with JWT Authentication and Role-Based Access Control (RBAC).

## Features

### Project Management

* Create Project
* Update Project
* Delete Project
* Get Project By ID
* Get All Projects

### Team Member Management

* Create Team Member
* Update Team Member
* Delete Team Member
* Get Team Member By ID
* Get All Team Members

### Task Management

* Create Task
* Update Task
* Delete Task
* Get Task By ID
* Get All Tasks

### Security

* JWT Authentication
* Password Encryption using BCrypt
* Role-Based Access Control (ADMIN, MANAGER, MEMBER)

### Validation & Exception Handling

* Request Validation
* Global Exception Handling
* Custom Resource Not Found Exception

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* PostgreSQL
* Lombok
* JWT (JJsonWebToken)
* Maven

---

## Security

- JWT Authentication
- BCrypt Password Encryption
- Role-Based Access Control (ADMIN, MANAGER, MEMBER)
- Protected REST APIs

---

## Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL Database
```
The application follows a layered architecture for maintainability and scalability.

---

## Database Entities

### Project

* id
* projectName
* description
* startDate
* endDate
* status

### TeamMember

* id
* name
* email
* role
* department

### Task

* id
* title
* description
* priority
* status
* dueDate
* project_id
* member_id

### User

* id
* name
* email
* password
* role

---

## Role-Based Access Control

### ADMIN

* Full Access

### MANAGER

* Manage Projects
* Manage Tasks
* View Team Members

### MEMBER

* View Projects
* View Tasks

---

## API Endpoints

### Authentication

POST /api/auth/register

POST /api/auth/login

### Projects

GET /api/projects

GET /api/projects/{id}

POST /api/projects

PUT /api/projects/{id}

DELETE /api/projects/{id}

### Team Members

GET /api/members

GET /api/members/{id}

POST /api/members

PUT /api/members/{id}

DELETE /api/members/{id}

### Tasks

GET /api/tasks

GET /api/tasks/{id}

POST /api/tasks

PUT /api/tasks/{id}

DELETE /api/tasks/{id}

---

## How To Run

1. Clone Repository

2. Create PostgreSQL Database

project_management_db

3. Configure application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/project_management_db

spring.datasource.username=postgres

spring.datasource.password=your_password

4. Run Application

5. Test APIs using Postman

---

## Future Enhancements

* Swagger Documentation
* Docker Support
* Unit Testing
* Deployment on Cloud
* Email Notifications

---

## Author

Ashish Pardeshi
Java Full Stack Developer
