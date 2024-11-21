# Stage 1: Build stage with OpenJDK 21 and Maven installed manually
FROM openjdk:21-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app

# Copy the project files and build the application
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage with OpenJDK 21
FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "app.jar"]
