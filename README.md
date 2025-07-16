# JWT Auth Project

A concise full‑stack starter that couples a **Spring Boot 3** API with a **React + TypeScript** front‑end, protected by **stateless JWT authentication** and backed by **PostgreSQL**.

---

## Tech Stack

| Layer    | Technology                              |
| -------- | --------------------------------------- |
| Backend  | Spring Boot 3 · Spring Security 6 (JWT) |
| Frontend | React 18 · Vite 7 · TypeScript          |
| Database | PostgreSQL 15                           |

---

## Quick Start

```bash
# Backend
cd backend
./mvnw spring-boot:run        # API on http://localhost:8080

# Frontend (separate terminal)
cd frontend
npm install && npm run dev     # UI on http://localhost:5173
```

## .env (must make the file within the frontend/ and backend/ directory)
Backend:

SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=

JWT_SECRET_KEY=

FRONTEND_URL=

Frontend:
VITE_API_URL=""


Default backend auth endpoints:

```
POST /api/v1/auth/register          # create account
POST /api/v1/auth/login             # returns JWT
POST /api/v1/auth/logout            # signs the user out by removing their Jwt token from their cookies
GET  /api//auth/test-controller     # used to test the protected endpoint which can only be accessed if you sign in
```

Frontend endpoints:

```
/                     # login/signup page
/login                # login/signup page
/test                 # protected page which can only be accessed if you signed in
```

Send the token in `Authorization: Bearer <jwt>` to call protected routes.

---

