# IT342_G1_Salonga_Lab1

## Project Description

This is a User Registration and Authentication system built as a full-stack application. The system includes a Spring Boot backend API and a ReactJS web frontend. It provides secure user authentication with password encryption using BCrypt and JWT-like token management.

### Features
- User registration with email and password
- User login with authentication token
- Protected dashboard/profile page
- Secure password storage (BCrypt encrypted)
- Route protection for authenticated users
- Responsive UI design

## Technologies Used

### Backend
- **Java** - Programming language
- **Spring Boot** - Backend framework
- **Spring Security** - Security framework
- **MySQL** - Database (via XAMPP)
- **BCrypt** - Password encryption
- **Maven** - Build tool

### Frontend
- **ReactJS** - Frontend framework
- **React Router v6** - Routing library
- **Axios** - HTTP client
- **CSS3** - Styling

### Mobile (Coming Soon)
- **React Native** - Mobile framework (future implementation)

## Project Structure

```
IT342_G1_Salonga_Lab1/
├── backend/              # Spring Boot backend
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/backend/
│   │       │   ├── config/
│   │       │   ├── controller/
│   │       │   ├── entity/
│   │       │   └── repository/
│   │       └── resources/
│   │           └── application.properties
│   └── pom.xml
├── web/                  # ReactJS frontend
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── context/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── App.js
│   │   └── index.js
│   └── package.json
├── mobile/               # React Native mobile (future)
├── docs/                 # Documentation
├── README.md
└── TASK_CHECKLIST.md
```

## API Endpoints

### Authentication Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| POST | `/api/auth/register` | Register new user | `{ "email": "...", "password": "..." }` | `{ "message": "...", "email": "..." }` |
| POST | `/api/auth/login` | User login | `{ "email": "...", "password": "..." }` | `{ "message": "...", "token": "...", "email": "...", "userId": ... }` |
| GET | `/api/user/me` | Get user profile (protected) | N/A | `{ "email": "...", "id": ... }` |

### Request/Response Examples

#### Register Request
```json
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

#### Register Response (Success)
```json
{
  "message": "User registered successfully",
  "email": "user@example.com"
}
```

#### Login Request
```json
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

#### Login Response (Success)
```json
{
  "message": "Login successful",
  "token": "LOGIN_SUCCESS_TOKEN",
  "email": "user@example.com",
  "userId": 1
}
```

## Steps to Run Backend

### Prerequisites
- Java JDK 17 or higher
- Maven
- XAMPP (MySQL)
- MySQL running on port 3306

### Database Setup
1. Start XAMPP Control Panel
2. Start MySQL service
3. Create database named `backend` (or as configured in application.properties)

### Configuration
Update `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/backend
spring.datasource.username=root
spring.dassword=
spring.jpa.hibernate.ddl-auto=update
```

### Run Backend
1. Open terminal in `backend/` directory
2. Run: `mvn spring-boot:run`
3. Backend will start on `http://localhost:8080`

### Alternative (using Maven wrapper)
```bash
cd backend
./mvnw spring-boot:run
```

## Steps to Run Web App

### Prerequisites
- Node.js 18 or higher
- npm

### Installation
1. Open terminal in `web/` directory
2. Install dependencies:
```bash
npm install
```

### Run Web App
1. Start the development server:
```bash
npm start
```
2. Web app will open on `http://localhost:3000` (or port 3001 if 3000 is busy)

### Build for Production
```bash
npm run build
```
The built files will be in the `build/` directory.

## Steps to Run Mobile App

### ⚠️ Note
Mobile app is NOT required for this submission and will be implemented in the next laboratory session.

### Future Implementation
- React Native mobile application
- Connection to same backend API
- Similar authentication features

## Frontend Pages

### Register Page
- URL: `/register`
- Access: Unauthenticated users only
- Features: Email input, password input, confirm password, form validation

### Login Page
- URL: `/login`
- Access: Unauthenticated users only
- Features: Email input, password input, login button

### Dashboard/Profile Page
- URL: `/dashboard`
- Access: Authenticated users only (protected)
- Features: User email display, user ID display, logout button

## Security Features

- **Password Encryption**: All passwords are encrypted using BCrypt before storage
- **Route Protection**: Dashboard is protected and only accessible with valid token
- **Token Management**: Tokens stored in localStorage for session persistence
- **Input Validation**: Form validation on frontend before API calls

## Development Progress

See [TASK_CHECKLIST.md](TASK_CHECKLIST.md) for detailed progress tracking.

## Authors

- **IT342 Group 1** - Initial work

## License

This project is part of IT342 course requirements.
