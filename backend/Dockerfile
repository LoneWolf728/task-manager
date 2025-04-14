# ========================================================
# Build stage
# Uses Maven image to compile and package the application
# ========================================================
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy POM file first to leverage Docker cache for dependencies
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the application and create the JAR file
# Tests are skipped in this build stage
RUN mvn clean package -DskipTests

# ========================================================
# Run stage
# Uses lightweight JRE image for the final runtime image
# ========================================================
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/target/task-manager-0.0.1-SNAPSHOT.jar app.jar

# Document the port the application will listen on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]