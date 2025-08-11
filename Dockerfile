# Use OpenJDK 11 for compatibility with Spring Boot 2.6.6
FROM openjdk:11-jre-slim

# Set working directory
WORKDIR /app

# Copy the built JAR file (will be created by gradle build)
COPY build/libs/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Set JVM options
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]