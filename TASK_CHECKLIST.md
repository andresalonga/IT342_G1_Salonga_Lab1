# Task Checklist

## Lab 2 - User Registration and Authentication (Backend + Web)

### DONE

#### Backend – Spring Boot
- [x] POST /api/auth/register (commit: 747394e)
- [x] POST /api/auth/login (commit: 747394e)
- [x] Database connection (MySQL) (commit: 222a542)
- [x] Password encryption (BCrypt) (commit: 2a4ebcf)

#### Web Application – ReactJS
- [x] Register page (Web UI) (commit: 15978dd)
- [x] Login page (Web UI) (commit: 15978dd)
- [x] Dashboard/Profile page (Web UI, protected) (commit: 15978dd)
- [x] Logout functionality (Web UI) (commit: 15978dd)

#### Frontend Features Implemented
- [x] User registration with form validation (commit: 15978dd)
- [x] User login with token storage (commit: 15978dd)
- [x] Protected route for dashboard (commit: 15978dd)
- [x] Logout functionality (commit: 15978dd)
- [x] Route guarding (commit: 15978dd)
- [x] Error handling and user feedback (commit: 15978dd)
- [x] Responsive UI design (commit: 15978dd)

#### API Integration
- [x] POST /api/auth/register (commit: 2a4ebcf)
- [x] POST /api/auth/login (commit: 2a4ebcf)

#### Project Setup
- [x] Create /web directory (commit: 4b41cfd)
- [x] Create /backend directory (commit: cf2d6ef)
- [x] Create /mobile directory (commit: 7a9cda4)
- [x] Create /docs directory (commit: 7a9cda4)
- [x] Create README.md (commit: 4b41cfd)
- [x] Create TASK_CHECKLIST.md (commit: 7a9cda4)

#### Backend Setup
- [x] Set up Spring Boot project (commit: cf2d6ef)
- [x] Add User entity (commit: ae8b86f)
- [x] Add UserRepository (commit: 7a06735)
- [x] Add AuthController (commit: 747394e)
- [x] Add BCrypt configuration (commit: 2a4ebcf)
- [x] Connect to MySQL database (commit: 222a542)

#### Web Setup
- [x] Set up React project (commit: 4b41cfd)
- [x] Add react-router-dom (commit: 2a4ebcf)
- [x] Add axios (commit: 2a4ebcf)
- [x] Create project structure (commit: 2a4ebcf)
- [x] Create AuthContext (commit: 2a4ebcf)
- [x] Create API service (commit: 2a4ebcf)
- [x] Create ProtectedRoute component (commit: 2a4ebcf)
- [x] Create Register page (commit: 2a4ebcf)
- [x] Create Login page (commit: 2a4ebcf)
- [x] Create Dashboard page (commit: 2a4ebcf)
- [x] Configure routing (commit: 2a4ebcf)
- [x] Add styling (commit: 2a4ebcf)

#### Documentation
- [x] Document API endpoints (commit: 4a65f70)
- [x] Document project architecture (commit: 4a65f70)
- [x] Add usage instructions (commit: 4a65f70)
- [x] Update TASK_CHECKLIST.md (commit: 4a65f70)

---

## Lab 3 - Mobile Application + Finalization

### DONE

#### Mobile Application – Android Kotlin
- [x] Set up mobile project structure (commit: 0eda989)
- [x] Implement Register screen (commit: 0eda989)
- [x] Implement Login screen (commit: 0eda989)
- [x] Implement Dashboard/Profile screen (commit: 0eda989)
- [x] Implement Logout functionality (commit: 0eda989)
- [x] Connect to backend API (commit: 0eda989)
- [x] Material Design 3 UI (commit: 0eda989)
- [x] Input validation with visual feedback (commit: 0eda989)
- [x] Password visibility toggle (commit: 0eda989)
- [x] Session management with SharedPreferences (commit: 0eda989)
- [x] Network security configuration for HTTP (commit: 0eda989)

#### Backend Finalization
- [x] POST /api/auth/logout endpoint (commit: f37ce9e)

#### Documentation (FRS)
- [ ] Update FRS PDF with Web UI screenshots
- [ ] Update FRS PDF with Mobile UI screenshots
- [ ] Update FRS PDF with any diagram revisions

### IN-PROGRESS

- [ ] FRS Documentation

### TODO

- [ ] Complete FRS documentation with screenshots

---

### Notes
- Mobile directory now contains complete Android authentication app
- Frontend features are implemented in commit: 15978dd
- Frontend is tested and running successfully on port 3001
- Backend running on port 8080
- Mobile app connects to backend at http://10.0.2.2:8080 (emulator)
