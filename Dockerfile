FROM gradle:9-jdk
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and config first for caching
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

# Download dependencies only (cached layer)
RUN ./gradlew build -x test || true

# Copy the rest of the project
COPY . .

# Expose app port
EXPOSE 8080

# Run Spring Boot in dev mode with Gradle bootRun
CMD ["./gradlew", "bootRun"]
