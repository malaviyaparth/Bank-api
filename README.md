# Bank API

A scalable Banking REST API built with **Java**, **Spring Boot**, **JDBC**, and **PostgreSQL** that supports core banking operations such as account management, fund transfers, and transaction processing.

---

## Features

### Account Management

* Create and manage bank accounts
* Retrieve account details

### Transactions

* Deposit funds
* Withdraw funds
* Transfer money between accounts

### Transaction History

* View account transaction records
* Track transfer activity

---

## Tech Stack

* Java
* Spring Boot
* Spring Web
* Spring JDBC
* PostgreSQL
* Maven

---

## Architecture Goals

* REST API design
* Scalable and maintainable project structure
* Database-driven transaction management
* Clean separation of service and data layers


---

## API Capabilities

| Feature             | Description                         |
| ------------------- | ----------------------------------- |
| Account Management  | Create and manage customer accounts |
| Deposit             | Add money to an account             |
| Withdraw            | Remove money from an account        |
| Transfer            | Send money between accounts         |
| Transaction History | View previous transactions          |


---

## Getting Started

### Prerequisites

Make sure you have installed:

* Java 17+
* Maven
* PostgreSQL

### Installation

Clone the repository:

```bash
git clone https://github.com/malaviyaparth/Bank-api.git
cd bank-api
```

Configure database credentials inside:

```properties
src/main/resources/application.properties
```

Run the application:

```bash
mvn spring-boot:run
```

Application runs at:

```plaintext
http://localhost:8080
```

---

## Upcoming Features

* JWT Authentication
* Role-Based Access Control (RBAC)
* User Registration & Login

