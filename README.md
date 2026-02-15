# IT342_G1_Salonga_Lab1

## Project Description

This is a User Registration and Authentication system built as a full-stack application. The system includes a Spring Boot backend API, a ReactJS web frontend, and an Android Kotlin mobile app. It provides secure user authentication with password encryption using BCrypt and token management.

### Features
- User registration with email and password
- User login with authentication token
- Protected dashboard/profile page
- Secure password storage (BCrypt encrypted)
- Route protection for authenticated users
- Responsive UI design (Web)
- Material Design 3 UI (Mobile)

## Technologies Used

### Backend
- **Java** - Programming language
- **Spring Boot** - Backend framework
- **Spring Security** - Security framework
- **MySQL** - Database (via XAMPP)
- **BCrypt** - Password encryption
- **Maven** - Build tool

### Web Frontend
- **ReactJS** - Frontend framework
- **React Router v6** - Routing library
- **Axios** - HTTP client
- **Tailwind CSS** - Styling

### Mobile App
- **Kotlin** - Programming language
- **Android** - Mobile platform
- **Retrofit** - HTTP client
- **Material Design 3** - UI components
- **Coroutines** - Async operations

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
├── mobile/               # Android Kotlin mobile app
│   ├── app/
│   │   ├── src/
│   │   │   └── main/
│   │   │       ├── java/com/example/salongalab1/
│   │   │       └── res/
│   │   └── build.gradle.kts
│   └── gradle/
├── docs/                 # Documentation (FRS)
├── README.md
└── TASK_CHECKLIST.md
```

## API Endpoints

### Authentication Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| POST | `/api/auth/register` | Register new user | `{ "email": "...", "password": "...", "firstName": "...", "lastName": "..." }` | `{ "message": "...", "email": "...", "firstName": "...", "lastName": "..." }` |
| POST | `/api/auth/login` | User login | `{ "email": "...", "password": "..." }` | `{ "message": "...", "token": "...", "email": "...", "userId": ..., "firstName": "...", "lastName": "...", "createdAt": "..." }` |
| POST | `/api/auth/logout` | User logout | N/A | `{ "message": "...", "loggedOut": true }` |

### Request/Response Examples

#### Register Request
```json
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe"
}
```

#### Register Response (Success)
```json
{
  "message": "User registered successfully",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe"
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
  "userId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "createdAt": "2024-01-01T00:00:00"
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
3. Create database named `auth_db`

### Configuration
Update `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/auth_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

### Run Backend
1. Open terminal in `backend/` directory
2. Run: `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`)
3. Backend will start on `http://localhost:8080`

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

### Prerequisites
- Android Studio (with Kotlin plugin)
- Android SDK
- Physical Android device or Emulator

### Run Mobile App
1. Open the `mobile/` directory in Android Studio
2. Wait for Gradle sync to complete
3. Click the Run button or press `Shift + F10`
4. Select an emulator or connected device

### Important Notes
- Mobile app connects to backend at `http://10.0.2.2:8080` (Android emulator)
- For physical device, use your computer's IP address instead
- Make sure backend is running before testing mobile app

## Mobile App Features

### Screens
- **Register Screen** - User registration with validation
- **Login Screen** - User login with session management
- **Dashboard Screen** - Profile display with logout option

### UI Features
- Material Design 3 styling
- Password visibility toggle
- Real-time input validation (green/red border colors)
- Loading indicators during API calls
- Error handling with user feedback

## Frontend Pages (Web)

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
- **Token Management**: Tokens stored in localStorage (Web) / SharedPreferences (Mobile) for session persistence
- **Input Validation**: Form validation on frontend before API calls

## Development Progress

See [TASK_CHECKLIST.md](TASK_CHECKLIST.md) for detailed progress tracking.

## Authors

- **Salonga, Andre D.** - IT342 G1

## License

This project is part of IT342 course requirements.
