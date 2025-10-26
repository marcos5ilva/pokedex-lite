# Use multi-stage build for smaller final image
FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Final stage
FROM eclipse-temurin:17-jre-jammy

# Add non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring
USER spring

WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /app/target/pokedex-lite-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Elastic Beanstalk uses 5000 by default)
EXPOSE 5000

# Add JVM options for better performance
ENTRYPOINT ["java", "-jar", "-Dserver.port=5000", "app.jar"]

