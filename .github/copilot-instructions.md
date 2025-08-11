# Task Tracker

Task Tracker is a full-stack web application built with Kotlin Spring Boot (backend) and React (frontend). The backend provides RESTful APIs with MongoDB integration, while the frontend offers a modern React-based user interface. The application is designed for Azure Static Web Apps deployment.

Always reference these instructions first and fallback to search or bash commands only when you encounter unexpected information that does not match the info here.

## Working Effectively

### Prerequisites and Setup
- Ensure Java 17 is available: `java -version` should show Java 17+
- Install Gradle 7.4 manually (system gradle may be incompatible):
  ```bash
  cd /tmp
  wget https://services.gradle.org/distributions/gradle-7.4-bin.zip
  unzip gradle-7.4-bin.zip
  sudo mv gradle-7.4 /opt/
  export PATH=/opt/gradle-7.4/bin:$PATH
  ```
- Ensure Node.js 20+ is available: `node --version` should show v20+
- Ensure npm 10+ is available: `npm --version` should show 10+

### Backend Build and Test
- **Initial setup (first time only)**:
  - Use Gradle 7.4: `/opt/gradle-7.4/bin/gradle --version` (verify correct version)
  - Dependencies download: `/opt/gradle-7.4/bin/gradle build` -- **NEVER CANCEL**. Initial build takes 2-3 minutes. Set timeout to 10+ minutes.
- **Building the backend**:
  - `/opt/gradle-7.4/bin/gradle build -x test` -- takes 1-2 seconds (subsequent builds). **NEVER CANCEL**. Set timeout to 5+ minutes.
  - `/opt/gradle-7.4/bin/gradle build` -- includes tests but **WILL FAIL** due to MongoDB requirement. Use `-x test` to skip tests.
- **Running the backend**:
  - Demo mode (no MongoDB): `/opt/gradle-7.4/bin/gradle bootRun --args='--spring.profiles.active=demo'`
  - Full mode (requires MongoDB): `/opt/gradle-7.4/bin/gradle bootRun` -- **WILL FAIL** without MongoDB configuration
  - Server runs on port 8080, startup takes ~1.2 seconds
  - Test endpoints: `curl http://localhost:8080/api/demo` and `curl http://localhost:8080/api/json/hello`

### Frontend Build and Test  
- **Initial setup (first time only)**:
  - Navigate to frontend: `cd UI/frontend`
  - Install dependencies: `npm install` -- **NEVER CANCEL**. Takes 5-6 minutes. Set timeout to 10+ minutes.
  - Expect deprecation warnings - these are normal and can be ignored
- **Building the frontend**:
  - Production build: `npm run build` -- takes ~6 seconds. **NEVER CANCEL**. Set timeout to 2+ minutes.
  - Build output goes to `build/` directory
- **Testing the frontend**:
  - Run tests: `npm test -- --watchAll=false --testTimeout=30000` -- takes ~1.6 seconds. **NEVER CANCEL**. Set timeout to 2+ minutes.
  - Single test should pass (renders React components)
- **Running the frontend**:
  - Development server: `npm start` -- takes ~10 seconds to start. **NEVER CANCEL**. Set timeout to 2+ minutes.
  - Server runs on port 3000 with proxy to backend on port 8080
  - Test: `curl http://localhost:3000` should return HTML, `curl http://localhost:3000/api/demo` should proxy to backend

## Validation

### End-to-End Validation Scenarios
Always run these complete scenarios after making changes to validate the application works:

1. **Backend API Validation**:
   - Start backend: `/opt/gradle-7.4/bin/gradle bootRun --args='--spring.profiles.active=demo'`
   - Test demo endpoint: `curl http://localhost:8080/api/demo` should return "Hello Pavan"
   - Test JSON endpoint: `curl http://localhost:8080/api/json/hello` should return `{"name":"Lalith"}`
   - Stop backend: Ctrl+C

2. **Frontend Validation**:
   - Navigate to `UI/frontend`
   - Start frontend: `npm start`
   - Test frontend: `curl http://localhost:3000` should return HTML page
   - Stop frontend: Ctrl+C

3. **Full-Stack Integration**:
   - Start backend: `/opt/gradle-7.4/bin/gradle bootRun --args='--spring.profiles.active=demo'`
   - Start frontend: `cd UI/frontend && npm start`
   - Test proxy: `curl http://localhost:3000/api/demo` should return backend response
   - **Manual browser test** (if possible): Navigate to http://localhost:3000, click on list items to test API calls

### Build Validation Before Committing
Always run these commands before committing changes:
- Backend: `/opt/gradle-7.4/bin/gradle build -x test` 
- Frontend: `cd UI/frontend && npm run build`
- Frontend tests: `cd UI/frontend && npm test -- --watchAll=false --testTimeout=30000`

## Common Issues and Limitations

### Known Issues
- **Gradle wrapper missing**: Use `/opt/gradle-7.4/bin/gradle` instead of `./gradlew`
- **MongoDB tests fail**: Full backend tests require MongoDB. Use `gradle build -x test` for compilation validation
- **TaskController requires MongoDB**: Use `demo` profile to run without MongoDB, which disables Task management features
- **Deprecated npm packages**: Warnings during `npm install` are expected and can be ignored

### Database Requirements
- **Production**: Requires MongoDB for full task management functionality
- **Demo mode**: Only demo endpoints work (no task CRUD operations)
- **Testing**: Backend tests require MongoDB configuration and will fail without it

### Port Configuration
- Backend: 8080 (Spring Boot default)
- Frontend dev server: 3000 (Create React App default)
- Frontend build: Static files for deployment (build/ directory)

## Project Structure

### Key Directories and Files
```
├── src/main/kotlin/com/learn/tasktracker/     # Backend source code
│   ├── TasktrackerApplication.kt              # Spring Boot main application
│   ├── controller/                            # REST controllers
│   │   ├── DemoController.kt                  # Demo endpoints (no DB required)
│   │   └── TaskController.kt                  # Task management endpoints (requires DB)
│   ├── service/TaskService.kt                 # Business logic
│   ├── repository/TaskRepository.kt           # Data access
│   └── model/                                 # Data models
├── src/main/resources/
│   ├── application.properties                 # Default configuration
│   └── application-demo.properties            # Demo profile (no MongoDB)
├── UI/frontend/                               # React frontend
│   ├── src/App.js                             # Main React component
│   ├── package.json                           # Frontend dependencies
│   └── build/                                 # Production build output
├── build.gradle.kts                           # Backend dependencies and build config
├── .github/workflows/                         # Azure Static Web Apps deployment
└── README.md, HELP.md                         # Documentation
```

### Common Commands Reference
```bash
# Backend
/opt/gradle-7.4/bin/gradle --version           # Check Gradle version
/opt/gradle-7.4/bin/gradle build -x test       # Build without tests (1-2s)
/opt/gradle-7.4/bin/gradle build              # Build with tests (will fail without MongoDB)
/opt/gradle-7.4/bin/gradle bootRun --args='--spring.profiles.active=demo'  # Run demo mode

# Frontend  
cd UI/frontend
npm --version                                  # Check npm version
npm install                                    # Install dependencies (5-6 minutes first time)
npm run build                                  # Production build (~6s)
npm test -- --watchAll=false --testTimeout=30000  # Run tests (~1.6s)
npm start                                      # Development server (~10s startup)

# Testing
curl http://localhost:8080/api/demo            # Test backend
curl http://localhost:3000                     # Test frontend
curl http://localhost:3000/api/demo            # Test frontend->backend proxy
```

### Time Expectations and Timeouts
- **CRITICAL**: Set explicit timeouts and **NEVER CANCEL** long-running builds
- Initial Gradle build: 2-3 minutes (set timeout to 10+ minutes)
- Subsequent Gradle builds: 1-2 seconds (set timeout to 5+ minutes)
- Initial npm install: 5-6 minutes (set timeout to 10+ minutes)
- npm build: 6 seconds (set timeout to 2+ minutes)
- npm test: 1.6 seconds (set timeout to 2+ minutes)
- Backend startup: 1.2 seconds
- Frontend startup: 10 seconds (set timeout to 2+ minutes)

### Architecture Notes
- **Backend**: Spring Boot WebFlux (reactive), Kotlin, MongoDB integration
- **Frontend**: Create React App, React 18, proxy configuration for API calls
- **Database**: MongoDB (Spring Data MongoDB)
- **Deployment**: Azure Static Web Apps (frontend), backend can be deployed separately
- **Testing**: JUnit 5 + Kotest (backend), Jest + React Testing Library (frontend)