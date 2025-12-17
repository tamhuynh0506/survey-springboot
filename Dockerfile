FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8080 5005

CMD ["java",\
 "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005",\
 "-Dspring.devtools.restart.enabled=true",\
 "-Dspring.devtools.restart.additional-paths=/app/classes",\
 "-jar",\
 "app.jar"]
