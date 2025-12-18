# Build stage
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /build

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN ./gradlew bootJar -x test

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the JAR from build stage
COPY --from=builder /build/build/libs/*.jar app.jar

EXPOSE 8080
EXPOSE 5005

# Run with debug enabled
CMD ["java", \
     "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", \
     "-jar", "app.jar"]