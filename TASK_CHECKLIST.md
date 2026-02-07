# Task Checklist

## Lab 2 - User Registration and Authentication (Backend + Web)

### DONE

#### Backend – Spring Boot
- [x] POST /api/auth/register (commit: 747394e)
- [x] POST /api/auth/login (commit: 747394e)
- [x] GET /api/user/me (protected) - Not implemented yet
- [x] Database connection (MySQL) (commit: 222a542)
- [x] Password encryption (BCrypt) (commit: 2a4ebcf)

#### Web Application – ReactJS
- [x] Register page (Web UI) (commit: 2a4ebcf)
- [x] Login page (Web UI) (commit: 2a4ebcf)
- [x] Dashboard/Profile page (Web UI, protected) (commit: 2a4ebcf)
- [x] Logout functionality (Web UI) (commit: 2a4ebcf)

#### Frontend Features Implemented
- [x] User registration with form validation (commit: 2a4ebcf)
- [x] User login with token storage (commit: 2a4ebcf)
- [x] Protected route for dashboard (commit: 2a4ebcf)
- [x] Logout functionality (commit: 2a4ebcf)
- [x] Route guarding (commit: 2a4ebcf)
- [x] Error handling and user feedback (commit: 2a4ebcf)
- [x] Responsive UI design (commit: 2a4ebcf)

#### API Integration
- [x] POST /api/auth/register (commit: 2a4ebcf)
- [x] POST /api/auth/login (commit: 2a4ebcf)
- [x] GET /api/user/me - Not implemented yet

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

### IN-PROGRESS

- [ ] Update FRS PDF with ERD, UML diagrams, and Web UI screenshots
- [ ] Implement GET /api/user/me endpoint (backend)

### TODO

- [ ] Mobile Development
  - [ ] Set up mobile project structure
  - [ ] Implement mobile components
  - [ ] Connect to backend API
- [ ] Take screenshots of Web UI for documentation
  - [ ] Register page screenshot
  - [ ] Login page screenshot
  - [ ] Dashboard/Profile page screenshot
  - [ ] Logout functionality screenshot

### Notes
- Mobile directory may remain empty initially
- Frontend features are implemented in commit: 2a4ebcf
- Frontend is tested and running successfully on port 3001
- All frontend files are in the web/ directory
