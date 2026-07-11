# Restaurant Voting System

A REST API for a lunch-place voting system: registered users vote for the restaurant where they want to have lunch, and administrators manage restaurants and their daily menus.
Built as a test assignment.

---

## Overview

The application is a backend-only REST service (no frontend). It exposes an HTTP API for two roles:

- **Admin** — manages restaurants, publishes and updates their daily menus, and administers user accounts.
- **User** — registers, browses restaurants with today's menu, and casts a vote.

The API is documented via OpenAPI/Swagger UI.

## Business rules

- There are two roles: `ADMIN` and `USER`.
- An admin creates restaurants and publishes a menu for the current day (a set of dishes with prices). Menus are day-scoped — each restaurant gets a fresh menu every day.
- A user can vote for exactly one restaurant per day.
- Re-voting on the same day follows a time constraint:
  - **before 11:00** — the vote is updated (the user changed their mind);
  - **after 11:00** — the vote is final and cannot be changed.

> The 11:00 cutoff uses the server time zone. <!-- Adjust if you made it configurable. -->

## Tech stack

<!-- Align this list with your real pom.xml / build.gradle -->

- **Java 21**, **Spring Boot 3.x**
- **Spring Web** — REST controllers
- **Spring Data JPA / Hibernate** — persistence
- **Spring Security** — HTTP Basic authentication, role-based authorization
- **Bean Validation** — request validation
- **H2** (in-memory, dev/test) / **PostgreSQL** (production)
- **springdoc-openapi** — Swagger UI documentation
- **JUnit 5 + Spring MockMvc** — controller and integration tests
- **Maven** — build

## Data model

<!-- Keep only the entities you actually implemented. -->

- **User** — id, name, email, password, roles, registration date.
- **Restaurant** — id, name.
- **Dish** — id, name, price, date, restaurant reference.
- **Vote** — id, user reference, restaurant reference, date.

A user has at most one `Vote` per date (enforced by a unique constraint on `user_id + date`).

## API

Base path: `/api`. Authentication: HTTP Basic. Representative endpoints (verify against your controllers):

### Profile — `/api/profile`
| Method | Path | Access | Description |
|--------|------|--------|-------------|
| POST | `/api/profile/register` | anonymous | Register a new user |
| GET | `/api/profile` | user | Get current profile |
| PUT | `/api/profile` | user | Update profile |
| DELETE | `/api/profile` | user | Delete account |
| GET | `/api/profile/votes` | user | Vote history of the current user |

### Restaurants (user view) — `/api/restaurants`
| Method | Path | Access | Description |
|--------|------|--------|-------------|
| GET | `/api/restaurants` | user | List restaurants with today's menu |
| GET | `/api/restaurants/{id}` | user | Restaurant with today's menu |
| POST | `/api/restaurants/{id}/vote` | user | Vote for a restaurant (subject to the 11:00 rule) |

### Admin — `/api/admin`
| Method | Path | Access | Description |
|--------|------|--------|-------------|
| GET / POST | `/api/admin/restaurants` | admin | List / create restaurants |
| GET / PUT / DELETE | `/api/admin/restaurants/{id}` | admin | Read / update / delete a restaurant |
| POST | `/api/admin/restaurants/{id}/dishes` | admin | Add a dish to today's menu |
| PUT / DELETE | `/api/admin/dishes/{id}` | admin | Update / delete a dish |
| GET / POST | `/api/admin/users` | admin | List / create users |
| GET / PUT / DELETE | `/api/admin/users/{id}` | admin | Read / update / delete a user |

## Getting started

### Prerequisites
- JDK 21+
- Maven 3.9+
- (optional) PostgreSQL, if not using the in-memory H2 profile

### Run

```bash
git clone https://github.com/<your-username>/<repo-name>.git
cd <repo-name>
./mvnw spring-boot:run
```

The service starts on `http://localhost:8080`.

### Swagger UI

```
http://localhost:8080/swagger-ui.html
```

### Test

```bash
./mvnw test
```

## Default credentials

<!-- Update to match your data.sql / seed data. -->

| Role | Email | Password |
|------|-------|----------|
| Admin | `admin@example.com` | `admin` |
| User | `user@example.com` | `password` |

## Project structure

```
src/
├── main/
│   ├── java/            # controllers, services, repositories, entities
│   └── resources/       # application.yml, schema.sql, data.sql
└── test/                # unit and integration tests
```

