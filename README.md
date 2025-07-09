# JWT Auth Project

A concise full‑stack starter that couples a **Spring Boot 3** API with a **React + TypeScript** front‑end, protected by **stateless JWT authentication** and backed by **PostgreSQL**.

---

## ⚡️ Tech Stack

| Layer    | Technology                              |
| -------- | --------------------------------------- |
| Backend  | Spring Boot 3 · Spring Security 6 (JWT) |
| Frontend | React 18 · Vite 7 · TypeScript          |
| Database | PostgreSQL 15                           |

---

## 🚀 Quick Start

```bash
# Backend
cd backend
./mvnw spring-boot:run        # API on http://localhost:8080

# Frontend (separate terminal)
cd frontend
npm install && npm run dev     # UI on http://localhost:5173
```

Default auth endpoints:

```
POST /api/auth/register   # create account
POST /api/auth/login      # returns JWT
```

Send the token in `Authorization: Bearer <jwt>` to call protected routes.

---

