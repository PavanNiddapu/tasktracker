# Task Tracker

A full-stack task tracking application built with **Spring Boot + Kotlin** backend and **React** frontend. Track your tasks, log time spent, and monitor productivity.

## Features

- ✅ **Task Management**: Create, view, and manage tasks
- ⏱️ **Time Tracking**: Start/stop time tracking for individual tasks
- 📊 **Time Analytics**: Calculate total hours worked on tasks
- 🔄 **Real-time Updates**: Live task status updates
- 🎯 **REST API**: Full RESTful API for task operations
- 🌐 **Modern UI**: React-based responsive web interface

## Tech Stack

### Backend
- **Spring Boot 2.6.6** - Web framework
- **Kotlin** - Programming language
- **MongoDB** - Database
- **Spring WebFlux** - Reactive web framework
- **JUnit 5 + Kotest** - Testing

### Frontend
- **React 18** - UI framework
- **JavaScript (ES6+)** - Programming language
- **CSS3** - Styling

## Prerequisites

Before running the application, make sure you have:

- **Java 11** or higher
- **Node.js 14** or higher
- **MongoDB** running locally (or modify `application.properties` for remote MongoDB)
- **Gradle** (or use the included wrapper)

## Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd tasktracker
```

### 2. Setup Backend

```bash
# Build the backend
./gradlew build

# Run the Spring Boot application
./gradlew bootRun
```

The backend will start on `http://localhost:8080`

### 3. Setup Frontend

```bash
cd UI/frontend

# Install dependencies
npm install

# Start the development server
npm start
```

The frontend will start on `http://localhost:3000` and proxy API requests to the backend.

## API Endpoints

### Tasks
- `GET /api/task` - Get all tasks
- `POST /api/task` - Create a new task
- `GET /api/task/start/{id}` - Start time tracking for a task
- `GET /api/task/stop/{id}` - Stop time tracking for a task

### Demo/Health Check
- `GET /api/demo` - Demo text endpoint
- `GET /api/json/hello` - Demo JSON endpoint

## Usage

1. **Create Tasks**: Use the web interface to create new tasks with name and description
2. **Start Tracking**: Click "Start" to begin time tracking for a task
3. **Stop Tracking**: Click "Stop" to end the current time tracking session
4. **View Analytics**: See total hours worked and timeline entries for each task

## Development

### Running Tests

```bash
# Backend tests
./gradlew test

# Frontend tests  
cd UI/frontend
npm test
```

### Building for Production

```bash
# Build backend
./gradlew build

# Build frontend
cd UI/frontend
npm run build
```

## Configuration

### Database
Modify `src/main/resources/application.properties` to configure MongoDB connection:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/tasktracker
spring.data.mongodb.database=tasktracker
```

### CORS
CORS is configured to allow frontend connections. Modify `CorsConfig.kt` if needed for production deployment.

## Project Structure

```
tasktracker/
├── src/main/kotlin/com/learn/tasktracker/
│   ├── controller/     # REST controllers
│   ├── service/        # Business logic
│   ├── model/          # Data models
│   ├── repository/     # Data access layer
│   └── config/         # Configuration classes
├── UI/frontend/        # React frontend application
└── src/test/          # Backend tests
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## Issues and Improvements

This application includes all core functionality but could be enhanced with:

- User authentication and authorization
- Task categories and tags
- Advanced time analytics and reporting
- Task sharing and collaboration
- Mobile responsive design improvements
- Docker containerization
- CI/CD pipeline setup

## License

This project is open source and available under the MIT License.