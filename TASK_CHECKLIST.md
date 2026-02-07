# Task Checklist

## Lab 1 - Project Setup

### Directory Structure
- [x] Create `/web` directory
- [x] Create `/backend` directory
- [x] Create `/mobile` directory
- [x] Create `/docs` directory
- [x] Create `README.md`
- [x] Create `TASK_CHECKLIST.md`

## Lab 2 - User Registration and Authentication (Backend + Web)

### Backend – Spring Boot
- [x] POST /api/auth/register (commit: 2a4ebcf)
- [x] POST /api/auth/login (commit: 2a4ebcf)
- [x] GET /api/user/me (protected) - TODO: Not implemented yet
- [x] Database connection (MySQL) - Already configured
- [x] Password encryption (BCrypt) - Already implemented

### Web Application – ReactJS
- [x] Register page (Web UI) - commit: 2a4ebcf
- [x] Login page (Web UI) - commit: 2a4ebcf
- [x] Dashboard/Profile page (Web UI, protected) - commit: 2a4ebcf
- [x] Logout functionality (Web UI) - commit: 2a4ebcf

### Frontend Features Implemented
- [x] User registration with form validation
- [x] User login with token storage
- [x] Protected route for dashboard
- [x] Logout functionality
- [x] Route guarding (redirect unauthenticated users to login)
- [x] Auto-redirect authenticated users away from login/register pages
- [x] Error handling and user feedback
- [x] Responsive UI design

### Frontend File Structure
```
web/
├── src/
│   ├── components/
│   │   └── ProtectedRoute.js
│   ├── context/
│   │   └── AuthContext.js
│   ├── pages/
│   │   ├── Register.js
│   │   ├── Login.js
│   │   ├── Dashboard.js
│   │   ├── Auth.css
│   │   └── Dashboard.css
│   ├── services/
│   │   └── api.js
│   ├── App.js
│   └── index.js
```

### API Integration
- [x] POST /api/auth/register - Register new user
- [x] POST /api/auth/login - Login user and get token
- [ ] GET /api/user/me - Get user profile (TODO)

### Development Phases
- [x] **Web Development**
  - [x] Set up web project structure
  - [x] Implement frontend components
  - [x] Add authentication context
  - [x] Implement routing and protection

- [ ] **Backend Development**
  - [x] Set up backend API structure
  - [x] Implement server logic
  - [ ] Add GET /api/user/me endpoint

- [ ] **Mobile Development**
  - [ ] Set up mobile project structure
  - [ ] Implement mobile components

- [x] **Documentation**
  - [x] Document API endpoints
  - [x] Document project architecture
  - [x] Add usage instructions
  - [ ] Update FRS PDF with ERD, UML diagrams, and Web UI screenshots

### Notes
- Mobile directory may remain empty initially
- Update this checklist as tasks are completed
- All frontend features are implemented in commit: 2a4ebcf
- Frontend is tested and running successfully on port 3001
