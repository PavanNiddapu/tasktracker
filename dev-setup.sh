#!/bin/bash

# Task Tracker Development Setup Script

set -e

echo "🚀 Task Tracker Development Setup"
echo "================================="

# Check if MongoDB is running
if ! command -v mongod &> /dev/null; then
    echo "⚠️  MongoDB not found. Please install MongoDB or use Docker:"
    echo "   Option 1: Install MongoDB locally"
    echo "   Option 2: Run 'docker-compose up mongodb' to start MongoDB in Docker"
    echo ""
fi

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "❌ Node.js not found. Please install Node.js 14 or higher"
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java not found. Please install Java 11 or higher"
    exit 1
fi

echo "✅ Prerequisites check completed"
echo ""

# Setup backend
echo "📦 Setting up backend..."
if [ -f "./gradlew" ]; then
    chmod +x ./gradlew
    echo "Backend build system ready"
else
    echo "⚠️  Gradle wrapper not found. You may need to run gradle directly"
fi
echo ""

# Setup frontend
echo "🎨 Setting up frontend..."
cd UI/frontend

if [ ! -d "node_modules" ]; then
    echo "Installing frontend dependencies..."
    npm install
else
    echo "Frontend dependencies already installed"
fi

cd ../..
echo ""

echo "🎉 Setup completed!"
echo ""
echo "📋 Next steps:"
echo "1. Start MongoDB (if not already running)"
echo "   - Local: 'mongod'"
echo "   - Docker: 'docker-compose up mongodb'"
echo ""
echo "2. Start the backend:"
echo "   - './gradlew bootRun' (if gradle wrapper works)"
echo "   - 'gradle bootRun' (if gradle is installed globally)"
echo ""
echo "3. Start the frontend (in a new terminal):"
echo "   - 'cd UI/frontend && npm start'"
echo ""
echo "4. Open your browser to:"
echo "   - Frontend: http://localhost:3000"
echo "   - Backend API: http://localhost:8080/api/task"
echo ""
echo "Happy coding! 🎯"