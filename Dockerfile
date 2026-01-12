# Build stage
FROM gradle:8.5-jdk21 AS build
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
COPY src ./src
RUN gradle build -x test --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Render uses PORT environment variable
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=prod
EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "app.jar"]
