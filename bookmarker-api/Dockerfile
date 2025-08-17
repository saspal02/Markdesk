# ============================
# Stage 1: Build the application
# ============================
FROM eclipse-temurin:17-jdk AS builder

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml first to cache dependencies
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Pre-download dependencies (layer caching)
RUN ./mvnw dependency:go-offline -B

# Copy project source
COPY src ./src

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# ============================
# Stage 2: Create runtime image
# ============================
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy only the built JAR from the builder stage
COPY --from=builder /app/target/bookmarker-api-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
