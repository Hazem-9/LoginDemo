# Full Stack Login Feature — Spring Boot + Angular

login demo with:

- Spring Boot 3
- Spring Security
- JWT Access Token
- Refresh Token generation
- BCrypt password hashing
- Request validation
- Global exception handling
- Role-based authorization
- Angular standalone components
- Reactive Forms
- Auth Guard
- HTTP Interceptor

## Demo Users

| Username | Password | Roles |
|---|---|---|
| admin | admin123 | ADMIN, USER |
| user | user123 | USER |

## Run Backend

```bash
cd backend
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

Test login:

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

Protected endpoint:

```bash
curl http://localhost:8080/api/me \
  -H "Authorization: Bearer <ACCESS_TOKEN>"
```

## Run Frontend

```bash
cd frontend
npm install
npm start
```

Frontend runs on:

```text
http://localhost:4200
```


